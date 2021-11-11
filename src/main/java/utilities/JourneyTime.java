package utilities;

public class JourneyTime {
    double journeyTime;

    public JourneyTime(double journeyTime) {
        this.journeyTime = roundTimeToSomethingxD(journeyTime);
    }

    public static double roundTimeToSomethingxD(double journeyTime) {
        int numberOfDecimalPlaces = 1;
        double decimalOperator = Math.pow(10, numberOfDecimalPlaces);
        return (Math.round(journeyTime * decimalOperator)) / decimalOperator;
    }

    @Override
    public String toString() {
        return journeyTime + " h";
    }
}
