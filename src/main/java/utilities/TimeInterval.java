package utilities;

import java.text.DecimalFormat;

public class TimeInterval {
    int[] hoursMinutes;
    DecimalFormat decimalFormat = new DecimalFormat("##");

    public TimeInterval(int[] hoursMinutes) {
        this.hoursMinutes = hoursMinutes;
    }
    public TimeInterval (double hoursAndMinutesDecimal) {
        this.hoursMinutes = convertDecimalToHoursAndMinutes(hoursAndMinutesDecimal);
    }


    public static int[] convertDecimalToHoursAndMinutes(double hoursAndMinutesDecimal) {
        int hours = (int) Math.floor(hoursAndMinutesDecimal);
        double minutesAsFraction = hoursAndMinutesDecimal - hours;
        int minutes = convertDecimalToMinutes(minutesAsFraction);
        return new int[] {hours, minutes};
    }

    private static int convertDecimalToMinutes(double minutesAsFraction) {
        int minutes = (int) (minutesAsFraction * 60);
        return roundToFiveMinutes(minutes);
    }

    public static int roundToFiveMinutes(int minutes) {
        int roundedMinutes;
        if (minutes < 58) {
            if (minutes % 5 >= 2.5) {
                roundedMinutes = minutes - minutes % 5 + 5;
            } else {
                roundedMinutes = minutes - minutes % 5;
            }
        } else roundedMinutes = 55;
        return roundedMinutes;
    }




    @Override
    public String toString() {
        return (int) hoursMinutes[0] + ":" + String.format("%02d",hoursMinutes[1]) + " h";
    }

    public int[] getHoursMinutes() {
        return hoursMinutes;
    }

    public void setHoursMinutes(int[] hoursMinutes) {
        this.hoursMinutes = hoursMinutes;
    }

    public int getHours() {
        return hoursMinutes[0];
    }

    public int getMinutes() {
        return hoursMinutes[1];
    }
}
