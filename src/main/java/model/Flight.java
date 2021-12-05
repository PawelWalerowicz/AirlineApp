package model;

import utilities.TimeInterval;
import utilities.Price;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    public void setDepartureDate(Calendar departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return route.toString()
                + ";\nDeparture on " + sdf.format(departureDate.getTime())
                + ", landing on " + sdf.format(landingDate.getTime())
                + " (" + flightTime.toString()
                +"), price: " + price.toString() + "\n" ;
    }
}
