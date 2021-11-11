import model.Airline;
import model.Airport;
import model.Country;
import model.Codes.IATA;
import model.Route;
import view.MainMenu;

public class Main {
    public static void main(String[] args) {
        System.out.println();
//        MainMenu mainMenu = new MainMenu();
//        mainMenu.viewMenu();

        System.out.println("Tworzymy Polskę po nazwie");
        Country polska = new Country("Poland");
        System.out.println(polska.getIsoCode());
        System.out.println("Tworzymy Czechy po kodzie");
        Country czechy = new Country("CZ");
        System.out.println(czechy.getName());

        System.out.println("Tworzymy lotnisko w Heathrow");
        Airport heathrow = new Airport("London Heathrow Airport");
        System.out.println(heathrow.getCountry().toString());
        System.out.println(heathrow.getGeolocation().toString());
        System.out.println(heathrow.getIATA());


        System.out.println("Tworzymy lotnisko w Hamburgu(przez IATA)");
        Airport hamburg = new Airport(new IATA("HAM"));
        System.out.println(hamburg.getName());
        System.out.println(hamburg.getCountry().toString());
        System.out.println(hamburg.getGeolocation().toString());
        System.out.println(hamburg.getIATA());
        System.out.println("Tworzymy lotnisko z dupy");
        Airport zdupy = new Airport("PIESE");
//        System.out.println(zdupy.getCountry().toString());

        System.out.println("Tworzymy linię lotniczą");
        Airline linia = new Airline("Arrowhead Airways");
        System.out.println(linia.getName());
        System.out.println("IATA: " + linia.getIATA().toString());
        System.out.println("ICAO: " + linia.getICAO().toString());
        System.out.println(linia.getIATAorICAO());
        System.out.println(linia.getIATAorICAO().getClass());

        System.out.println("Próujemy znowu w lotnisko");
//        Airport airport = new Airport("OBR");
//        System.out.println(airport.toString());
//        System.out.println("Tworzymy trasę");
        Route route = new Route(linia,heathrow,hamburg);
        System.out.println(route.exist());


    }
}
