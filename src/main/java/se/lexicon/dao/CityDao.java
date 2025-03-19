package se.lexicon.dao;

import se.lexicon.model.City;

import javax.management.InstanceAlreadyExistsException;
import java.util.List;
import java.util.Optional;

public interface CityDao {
    Optional<City> findById(int id);

    List<City> findByCode(String code);

    List<City> findByName(String name);

    List<City> findAll();

    City save(City city) throws InstanceAlreadyExistsException;

    void update(City city);

    void deleteById(int id);
}
