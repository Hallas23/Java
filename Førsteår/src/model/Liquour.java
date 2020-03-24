package model;

public class Liquour extends Product {
    private double alcoholPercentage = 0;
    
    public Liquour(double price, String name) {
        super(price, name);
    }
    
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    @Override
    public double getPriceWithVAT() {
        if (super.getPrice() > 90) {
            return super.getPrice() * 2.20;
        } else {
            return super.getPrice() * 1.80;
        }
    }
}
