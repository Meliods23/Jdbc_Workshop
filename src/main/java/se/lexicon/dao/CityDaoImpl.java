package se.lexicon.dao;

import se.lexicon.db.dbConnection;
import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of CityDao for interacting with the 'city' table in the database.
 */
public class CityDaoImpl implements CityDao{
    // TODO: Needs completion

    List<City> cities;

    public CityDaoImpl() {
        this.cities = new ArrayList<>();
    }

    @Override
    public Optional<City> findById(int id) {

        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM City WHERE id LIKE ?")
                ){

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()){
                    // Gets the city info if the city exists
                    int rsId = resultSet.getInt("ID");
                    String rsName = resultSet.getString("Name");
                    String rsCountryCode = resultSet.getString("CountryCode");
                    String rsDistrict = resultSet.getString("District");
                    int rsPopulation = resultSet.getInt("Population");


                    City city = new City(rsId, rsName, rsCountryCode,rsDistrict, rsPopulation);
                    return Optional.of(city);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database :" + e.getMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<City> findByCode(String code) {

        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM City WHERE CountryCode LIKE ?")
        ){


            statement.setString(1, code);

            List<City> found = new ArrayList<>();

            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    // Gets the city info if the city exists
                    int rsId = resultSet.getInt("ID");
                    String rsName = resultSet.getString("Name");
                    String rsCountryCode = resultSet.getString("CountryCode");
                    String rsDistrict = resultSet.getString("District");
                    int rsPopulation = resultSet.getInt("Population");


                    City city = new City(rsId, rsName, rsCountryCode,rsDistrict, rsPopulation);
                    found.add(city);
                }
                return found;
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database :" + e.getMessage());
        }

        // Returns null if no cities are found
        return null;
    }

    @Override
    public List<City> findByName(String name) {
        return List.of();
    }

    @Override
    public List<City> findAll() {
        return List.of();
    }

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deleteById(int id) {

    }
}
