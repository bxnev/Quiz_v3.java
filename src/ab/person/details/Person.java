package ab.person.details;

import java.time.LocalDate;
import java.time.Period;


public class Person {

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String firstName;
    private String surname;
    private int attempts = 0;

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;

    }

    public void setDateOfBirth(String dateOfBirth) throws Exception {
        //check if a date of birth string was supplied before trying to split it
        if (!dateOfBirth.isEmpty()) {
            String[] data = dateOfBirth.split("-");
            //check that there were three entries
            if (data.length == 3) {
                this.dateOfBirth = LocalDate.of(Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            } else {
                throw new Exception("Date of birth supplied in wrong format.");

            }
        } else {
            this.dateOfBirth = LocalDate.now();
        }
    }

    public int getAge() {

        Period difference = Period.between(dateOfBirth, LocalDate.now());
        return difference.getYears();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public int getAttempts() {
        return attempts;
    }

    public void incAttempts() {
        attempts++;
    }

    Address address;
    LocalDate dateOfBirth;


    public void setAddress(int i, String glebeStreet, String auchenshoogle, String perthshire, String s) {
        address = new Address(i, glebeStreet, auchenshoogle, perthshire, s);
    }
}

