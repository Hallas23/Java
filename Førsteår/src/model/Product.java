package model;

public class Product {
    private final static double VAT = 1.25;
    private double price;
    private String name = "";
    private String description = "";
    
    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPriceWithVAT() {
        return this.getPrice() * VAT;
    }
}
