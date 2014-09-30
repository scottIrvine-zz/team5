package team5.restService;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.json.JsonObject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import team5.product.Product;


@Path("trades")
public class TradeRemote {
	
	 @Context
	    private ServletContext context;

    public TradeRemote() {
        // TODO Auto-generated constructor stub
    }

    @GET
    @Produces("text/plain")
    public String getText() {
        return "Hello world from Java!";
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
    
    @POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML,
			MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.TEXT_XML,
			MediaType.APPLICATION_JSON })
	public Response insertTrade(Product product) {
		ResponseBuilder rb; 
		
	    product.assignNextId();
	    System.out.println("This is the product" + product);
		rb = Response.ok(product);
		
		return rb.build();
       
	}

}