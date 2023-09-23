package ab.useful;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class FileAccess {
    /*public void saveToCSV(String name, int total) {
        String userResults = "userResults.csv";
        try (FileWriter writer = new FileWriter(userResults, true)) {

            writer.append(name).append(",").append(Integer.toString(total)).append("\n");
            writer.flush();
            writer.close();
            System.out.println("Data saved to " + userResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    static public void saveToCSV(String userResults, String datatotal) {
        //String userResults = "userResults.csv";
        try (FileWriter writer = new FileWriter(userResults, true)) {

            //writer.append(name).append(",").append(Integer.toString(total)).append("\n");
            writer.append(datatotal);
            writer.flush();
            writer.close();
            System.out.println("Data saved to " + userResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public ArrayList loadQuestions(String filePath) {

        ArrayList<String> allRows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            //separates the file information into 5 variables
            while ((line = reader.readLine()) != null) {
                allRows.add(line);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allRows;
    }
}

