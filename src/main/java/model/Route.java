package model;

import java.util.List;

import static database.aerial.AerialDatabaseController.getRoutesFromDatabase;

public class Route {
    Airline airline;
    Airport sourceAirport;
    Airport destinationAirport;
    Distance distance;
    TimeInterval timeZoneDifference;


    public static final int AIRLINE_POSITION = 0;
    public static final int SOURCE_AIRPORT_IATA_POSITION = 2;
    public static final int DESTINATION_AIRPORT_IATA_POSITION = 4;

    public Route(Airline airline, Airport sourceAirport, Airport destinationAirport) {
        this.airline = airline;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.distance = new Distance(sourceAirport.getGeolocation(), destinationAirport.getGeolocation());
        this.timeZoneDifference = getTimeZoneDifference();
    }

    public Route(Airport sourceAirport, Airport destinationAirport) {
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        addRouteData();
    }

    public boolean exist() {
        List<String[]> allRoutes = getRoutesFromDatabase();
        String sourceAirportIATA = sourceAirport.getIATA();
        String destinationAirportIATA = destinationAirport.getIATA();
        boolean routeExist = false;
        for (String[] route : allRoutes) {
            if (route[SOURCE_AIRPORT_IATA_POSITION].equals(sourceAirportIATA) && route[DESTINATION_AIRPORT_IATA_POSITION].equals(destinationAirportIATA)) {
                routeExist = true;
                break;
            }
        }
        return routeExist;
    }

    public void addRouteData() {
        List<String[]> allRoutes = getRoutesFromDatabase();
        String sourceAirportIATA = sourceAirport.getIATA();
        String destinationAirportIATA = destinationAirport.getIATA();
        for (String[] route : allRoutes) {
            if (route[SOURCE_AIRPORT_IATA_POSITION].equals(sourceAirportIATA) && route[DESTINATION_AIRPORT_IATA_POSITION].equals(destinationAirportIATA)) {
                this.airline = AirlineFactory.createAirline(route[AIRLINE_POSITION]);
                this.distance = new Distance(sourceAirport.getGeolocation(),destinationAirport.getGeolocation());
                this.timeZoneDifference = getTimeZoneDifference();
            }
        }
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(Airport sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Distance getDistance() {
        return distance;
    }

    public TimeInterval getTimeZoneDifference() {
        double sourceAirportOffset = sourceAirport.getTimeZoneOffset();
        double destinationAirportOffset = destinationAirport.getTimeZoneOffset();
        double timeDifferenceDecimal;
        if(sameHemisphere()) {
            timeDifferenceDecimal = Math.abs(sourceAirportOffset - destinationAirportOffset);
        } else {
            timeDifferenceDecimal = Math.abs(sourceAirportOffset) + Math.abs(destinationAirportOffset);
        }
        if(!isWestward()) {
            timeDifferenceDecimal = -timeDifferenceDecimal;
        }
        return new TimeInterval(timeDifferenceDecimal);
    }

    private boolean sameHemisphere() {
        double sourceAirportOffset = sourceAirport.getTimeZoneOffset();
        double destinationAirportOffset = destinationAirport.getTimeZoneOffset();
        return sourceAirportOffset * destinationAirportOffset > 0;
    }

    private boolean isWestward() {
        double sourceLongitude = sourceAirport.getGeolocation().getLongitude();
        double finalLongitude = destinationAirport.getGeolocation().getLongitude();
        return finalLongitude > sourceLongitude;
    }



    @Override
    public String toString() {
        String ending;
        if (airline.toString().contains("irline") || airline.toString().contains("irways") || airline.toString().contains("Air")) {
            ending = "";
        } else {
            ending = " airline";
        }
        return "Route: " + sourceAirport.toString()
                + " - " + destinationAirport.toString()
                + " (" + distance.toString() + ")"
                + " with " + airline.toString() + ending;
    }
}
