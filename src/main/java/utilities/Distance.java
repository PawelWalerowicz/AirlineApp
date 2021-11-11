package utilities;

import model.Geolocation;

public class Distance {

    //calucaltes orthodromic distance in kilometers
    public static double calculateDistance(Geolocation point1, Geolocation point2) {
        double earthRadius = 6371; //km
        double latitude1 = Math.toRadians(point1.getLatitude());
        double latitude2 = Math.toRadians(point2.getLatitude());
        double longitude1 = Math.toRadians(point1.getLongitude());
        double longitude2 = Math.toRadians(point2.getLongitude());
        double deltaLongitude = Math.abs(longitude1 - longitude2);
        double angularDistance;
        angularDistance = Math.acos(
                (Math.sin(latitude1) * Math.sin(latitude2))
                        + (Math.cos(latitude1) * Math.cos(latitude2) * Math.cos(deltaLongitude))
        );

        return roundDistanceToMeters(angularDistance * earthRadius);
    }

    public static double roundDistanceToMeters(double distance) {
        int numberOfDecimalPlaces = 3;
        double decimalOperator = Math.pow(10, numberOfDecimalPlaces);
        double roundedDouble = (Math.round(distance * decimalOperator)) / decimalOperator;
        return roundedDouble;
    }


}

