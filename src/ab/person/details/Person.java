package ab.person.details;

import java.time.LocalDate;
import java.time.Period;


public class Person {
    public Person(String firstName, String surname) {
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
        }
    }

    public int getAge() {

        Period difference = Period.between(dateOfBirth, LocalDate.now());
        return difference.getYears();
    }

    Address address;
    LocalDate dateOfBirth;

    public void setAddress(int i, String glebe_street, String auchenshoogle, String perthshire, String s) {
    }
}

