package team5.product;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class Product {

    private static int nextId;
    
    private int id;
    private String description; 
    private double unitPrice;
    private Date created = new Date();
    private Date modified = new Date();

    // No-arg constructor, allows JAX-RS to create an "empty" Product object initially.
    public Product() {
    }
    
    // Parameterized constructor, allows us to create Product objects ourselves.
    public Product(String description, double unitPrice) {
        this.description = description;
        this.unitPrice = unitPrice;
    }

    // Assign the "next id" to this product, ensures each product at the server has a unique id.
    public int assignNextId() {
        id = ++nextId;
        return id;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.modified = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.modified = new Date();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        this.modified = new Date();
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %.2f (created %s, modified %s)", 
                              id,
                              description,
                              unitPrice,
                              created,
                              modified);
    }    
}

