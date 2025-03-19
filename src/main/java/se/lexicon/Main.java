package se.lexicon;

import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;

/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        // TODO: Needs completion

        // Init
        CityDaoImpl cityDao = new CityDaoImpl();

        // Testing the FindById function
        cityDao.findById(4).ifPresent(city -> System.out.printf(city.toString()));

        // Testing the FindByCode function
        List<City> cities = cityDao.findByCode("AFG");
        for (City c : cities){
            System.out.println(c.toString());
        }

        // Testing the save function
        City homs = new City("Homs", "SYR", "Asia", 1000000);
        try {
            homs = cityDao.save(homs);
            System.out.println(homs.getID());
        } catch (InstanceAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }

        // Testing the findByName function
        System.out.println(cityDao.findByName("Homs"));

        // Testing the findAll function
        /*
        for (City c : cityDao.findAll()){
            System.out.println(c.toString());
        }*/

        // Testing the update function
        homs.setPopulation(2000000);
        cityDao.update(homs);
        System.out.println(cityDao.findByName("Homs"));


        // Testing the delete function
        cityDao.deleteById(homs.getID());
        System.out.println(cityDao.findByName("Homs"));
    }
}
