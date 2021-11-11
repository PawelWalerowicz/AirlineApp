import model.Airport;
import model.Country;

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


        System.out.println("Tworzymy lotnisko w Hamburgu");
        Airport hamburg = new Airport("HAM");
        System.out.println(hamburg.getName());
        System.out.println(hamburg.getCountry().toString());
        System.out.println(hamburg.getGeolocation().toString());
        System.out.println(hamburg.getIATA());
    }
}
