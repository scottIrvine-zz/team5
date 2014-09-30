package marketData;

import java.util.HashSet;
import java.util.Set;

public class DataResearch {

	public static void main(String[]args){
		
		MarketDataReader mdr = new MarketDataReader();
		Set<String> symbols = new HashSet<String>();
		symbols.add("GOOG");
		symbols.add("AAPL");
		symbols.add("C");
		mdr.getData(symbols);
		
		
	}
}
