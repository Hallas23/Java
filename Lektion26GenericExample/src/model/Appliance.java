package model;

public class Appliance extends Product {
    private double wattPerHour = 0;
    
    public Appliance(double price, String name) {
        super(price, name);
    }

    public double getWattPerHour() {
        return wattPerHour;
    }

    public void setWattPerHour(double wattPerHour) {
        this.wattPerHour = wattPerHour;
    }

    @Override
    public double getPriceWithVAT() {
        return Math.max(super.getPrice() * 1.30, 3.0);
    }
}
