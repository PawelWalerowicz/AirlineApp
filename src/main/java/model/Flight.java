package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import static utilities.createFakeData.createFakeJourneyTime;
import static utilities.createFakeData.createFakePrice;

public class Flight {
    private Route route;
    private Price price;
    private Calendar departureDate;
    private TimeInterval flightTime;
    private Calendar landingDate;

    SimpleDateFormat sdf = new SimpleDateFormat("E, dd-MM-yyyy, HH:mm");


    public Flight(Route route, Calendar departureDate) {
        this.route = route;
        this.price = createFakePrice(route.getDistance());
        this.departureDate = departureDate;
        this.flightTime = createFakeJourneyTime(route.getDistance());
        this.landingDate = calculateLandingDate();
    }

    public static Flight generateFlightFromCompliteData(Route route, Calendar departureDate, TimeInterval flightTime, Calendar landingDate, Price price) {
        Flight flight = new Flight(route, departureDate);
        flight.setPrice(price);
        flight.setFlightTime(flightTime);
        flight.setLandingDate(landingDate);
        return flight;
    }

    private Calendar calculateLandingDate() {
        Calendar landingDate = (Calendar) this.departureDate.clone();
        landingDate.add(Calendar.HOUR,this.flightTime.getHours());
        landingDate.add(Calendar.MINUTE,this.flightTime.getMinutes());
        landingDate.add(Calendar.HOUR,this.route.getTimeZoneDifference().getHours());
        landingDate.add(Calendar.MINUTE,this.route.getTimeZoneDifference().getMinutes());
        return landingDate;
    }

    public Calendar getDepartureDate() {
        return departureDate;
    }

    public Calendar getLandingDate() {
        return landingDate;
    }

    public Price getPrice() {
        return price;
    }

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    public TimeInterval getFlightTime() {
        return flightTime;
    }

    public Route getRoute() {
        return route;
    }


    public void setRoute(Route route) {
        this.route = route;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setFlightTime(TimeInterval flightTime) {
        this.flightTime = flightTime;
    }

    public void setLandingDate(Calendar landingDate) {
        this.landingDate = landingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(route, flight.route) && Objects.equals(price, flight.price) && Objects.equals(departureDate, flight.departureDate) && Objects.equals(flightTime, flight.flightTime) && Objects.equals(landingDate, flight.landingDate);
    }

    @Override
    public String toString() {
        return route.toString()
                + ";\nDeparture on " + sdf.format(departureDate.getTime())
                + ", landing on " + sdf.format(landingDate.getTime())
                + " (" + flightTime.toString()
                +"), single ticket price: " + price.toString();
    }


}
