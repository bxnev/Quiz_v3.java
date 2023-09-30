package ab.person.details;

public class Address {

    private int hoouseNumber;
    private String street;
    private String town;
    private String county;
    private String postcode;

    public Address(int hoouseNumber, String street, String town, String county, String postcode) {
        this.hoouseNumber = hoouseNumber;
        this.street = street;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
    }

    public int getHoouseNumber() {
        return hoouseNumber;
    }

    public void setHoouseNumber(int hoouseNumber) {
        this.hoouseNumber = hoouseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return
                hoouseNumber + " "  +
                        street + ",\n" +
                        town + ",\n" +
                        county + ",\n" +
                        postcode + ".";
    }
}
