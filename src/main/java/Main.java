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

        Airport krakow = new Airport("KRK");
        System.out.println(krakow.getCountry().toString());
        System.out.println(krakow.getGeolocation().toString());
        System.out.println(krakow.getIATA());


        System.out.println("Tworzymy lotnisko w Hamburgu(przez IATA)");
        Airport jfk = new Airport(new IATA("JFK"));
        System.out.println(jfk.getName());
        System.out.println(jfk.getCountry().toString());
        System.out.println(jfk.getGeolocation().toString());
        System.out.println(jfk.getIATA());
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
        Route route = new Route(linia,krakow,jfk);
        System.out.println(route.exist());


    }
}
