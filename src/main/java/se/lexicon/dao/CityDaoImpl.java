package se.lexicon.dao;

import se.lexicon.db.dbConnection;
import se.lexicon.model.City;

import javax.management.InstanceAlreadyExistsException;
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

        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM City WHERE Name LIKE ?")
        ){


            statement.setString(1, name);

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
    public List<City> findAll() {

        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM City")
        ){

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
    public City save(City city) throws InstanceAlreadyExistsException{

        if (city == null) {
            throw new NullPointerException("You are trying to save a NULL city!");
        }

        final String insertCityQuery = "INSERT INTO City (Name, CountryCode, District, Population) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = dbConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(insertCityQuery, Statement.RETURN_GENERATED_KEYS);
        ){

            statement.setString(1, city.getName());
            statement.setString(2, city.getCountryCode());
            statement.setString(3, city.getDistrict());
            statement.setInt(4, city.getPopulation());


            // Checks if the city already exists to avoid adding a duplicate
            if(findByName(city.getName())  ==  null && findByCode(city.getCountryCode())  ==  null){
                int rows = statement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Done");
                    try (ResultSet keys = statement.getGeneratedKeys()) {
                        if (keys.next()) {
                            int id = keys.getInt(1);
                            // gets the generated ID
                            city.setID(id);
                        }
                        return city;
                    }
                }
            } // If the city already exists we throw an exception
            else {
                throw new InstanceAlreadyExistsException("City being added already exists! CityData -> " + city.toString());
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to the database :" + e.getMessage());
        }

        // Returns null if the save operation failed
        return null;
    }

    @Override
    public void update(City city) {

    }

    @Override
    public void deleteById(int id) {

    }
}
