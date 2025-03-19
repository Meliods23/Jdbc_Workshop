package se.lexicon.model;

/**
 * Represents a City entity based on the 'city' table in the 'world' database.
 */
public class City {

    private int ID;
    private String name;
    private String countryCode;
    private String district;
    private int population;

    public City(int ID, String name, String countryCode, String district, int population) {
        this.ID = ID;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public City(String name, String countryCode, String district, int population) {
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        // Primitive types - no null check needed
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // For wrapper types (e.g., Integer, Long, Double)
        if (name != null) {
            this.name = name;
        }
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        // For wrapper types (e.g., Integer, Long, Double)
        if (countryCode != null) {
            this.countryCode = countryCode;
        }
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        // For wrapper types (e.g., Integer, Long, Double)
        if (district != null) {
            this.district = district;
        }
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        // Primitive types - no null check needed
        this.population = population;
    }

    @Override
    public String toString() {
        return "City{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
