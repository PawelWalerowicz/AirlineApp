package utilities;

public class Price {
    double price;

    public Price(double price) {
        this.price = roundPricetoCents(price);
    }

    public static double roundPricetoCents(double price) {
        int numberOfDecimalPlaces = 2;
        double decimalOperator = Math.pow(10, numberOfDecimalPlaces);
        return (Math.round(price * decimalOperator)) / decimalOperator;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return price + " EUR";
    }
}