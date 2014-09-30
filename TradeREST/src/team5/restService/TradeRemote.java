package team5.restService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("trades")
public class TradeRemote {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    public TradeRemote() {
        // TODO Auto-generated constructor stub
    }

    @GET
    @Produces("text/plain")
    public String getText() {
        return "Hello world!";
    }

    /**
     * PUT method for updating or creating an instance of TradeRemote
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
    }

}