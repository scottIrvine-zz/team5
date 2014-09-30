package marketData;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
Client of the Yahoo! web service for market data.

@author Will Provost
*/
public class MarketDataReader
{
    private static final Logger LOGGER =
        Logger.getLogger (MarketDataReader.class.getName ());
    
    /**
    Data structure for passing market data around the application,
    once it's been read.
    All but stock (ticker) may be N/A -- we represent this with null.
    */
    public static class MarketData
    {
        public String stock;
        public Integer volume;
        public Double open;
        public Double close;
        public Double high;
        public Double low;
        public Double price;
        public Double bid;
        public Double bidSize;
        public Double ask;
        public Double askSize;
        
        public Double getMarketPrice ()
        {
            if (price != null)
                return price;
            
            if (bid != null && ask != null)
                return (bid + ask) / 2;
            
            return null;
        }
    }
    
    private static final String YAHOO_URL =
        "http://download.finance.yahoo.com/d/quotes.csv";
    
    private Client client = ClientBuilder.newClient ();

    /**
    Helper to read a value from the Yahoo! response, or
    set it to null if the value is "N/A".
    */
    private static Integer intOrNull (String field)
    {
        return field.equals ("N/A") ? null : Integer.parseInt (field);
    }
    
    /**
    Helper to read a value from the Yahoo! response, or
    set it to null if the value is "N/A".
    */
    private static Double doubleOrNull (String field)
    {
        return field.equals ("N/A") ? null : Double.parseDouble (field);
    }
    
    /**
    Assemble a request URL for Yahoo! by compiling a comma-separated list
    of the stocks of interest and a concatenation of field codes that we
    request as standard data. Make the request using a JAX-RS client and
    web resource. Parse the results into a map of our 
    {@link #MarketData} objects, with the stock(ticker) as the key.
    */
    public Map<String,MarketData> getData (Set<String> stocks)
    {
        Map<String,MarketData> data = new HashMap<String,MarketData> ();
        
        if (stocks.size () == 0)
            return data;
        
        StringBuilder stockList = new StringBuilder ();
        for (String stock : stocks)
            stockList.append (stock).append (',');
        stockList.deleteCharAt (stockList.length () - 1); // trailing ,
        LOGGER.log (Level.FINE, "Requesting stocks: " + stockList);
        System.out.println("Requesting stocks: " + stockList);
        
        final String FIELD_LIST = "s0v0o0p0h0g0l1b0b6a0a5";
        
        WebTarget target = client.target (YAHOO_URL);
        String update = target.queryParam ("s", stockList.toString ())
                              .queryParam ("f", FIELD_LIST)
                              .queryParam ("e", ".csv")
                              .request ()
                              .get (String.class);
        LOGGER.log (Level.FINE, "Received data: " + update);
        System.out.println("Received data: " + update);
        
        for (String line : update.split ("\r\n"))
        {
            String[] fields = line.split (",");
            MarketData datum = new MarketData ();
            datum.stock = fields[0].replace ("\"", "");
            datum.volume = intOrNull (fields[1]);
            datum.open = doubleOrNull (fields[2]);
            datum.close = doubleOrNull (fields[3]);
            datum.high = doubleOrNull (fields[4]);
            datum.low = doubleOrNull (fields[5]);
            datum.price = doubleOrNull (fields[6]);
            datum.bid = doubleOrNull (fields[7]);
            datum.bidSize = doubleOrNull (fields[8]);
            datum.ask = doubleOrNull (fields[9]);
            datum.askSize = doubleOrNull (fields[10]);
            
            data.put (datum.stock, datum);
        }
        
        return data;
    }
}
