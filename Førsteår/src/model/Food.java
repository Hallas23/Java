package model;

public class Food extends Product {
    private final static double VAT = 1.05;
    
    private int expirationPeriodInDays;
    
    public Food(double price, String name) {
        super(price, name);
    }

    public int getExpirationPeriodInDays() {
        return expirationPeriodInDays;
    }

    public void setExpirationPeriodInDays(int expirationPeriodInDays) {
        this.expirationPeriodInDays = expirationPeriodInDays;
    }

    @Override
    public double getPriceWithVAT() {
        return super.getPrice() * Food.VAT;
    }
}
