package se.lexicon;

import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;

import java.util.List;
import java.util.Optional;

/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        // TODO: Needs completion

        CityDaoImpl cityDao = new CityDaoImpl();
        cityDao.findById(4).ifPresent(city -> System.out.printf(city.toString()));


        List<City> cities = cityDao.findByCode("AFG");
        for (City c : cities){
            System.out.println(c.toString());
        }

    }
}
