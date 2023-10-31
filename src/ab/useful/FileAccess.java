package ab.useful;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileAccess {

    /**
     *
     * @param userResults saves username, recent score and best score to CSV
     * @param datatotal appends user details and score set to new line of the CSV
     */
    // saves score to 'userResults' CSV file
    static public void saveToCSV(String userResults, String datatotal) {
        try (FileWriter writer = new FileWriter(userResults, true)) {
            writer.append(datatotal);
            writer.flush();
            writer.close();
            System.out.println("Data saved to " + userResults);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param filePath reads and adds questions from chosen CSV file
     * @return returns all rows of the CSV
     */
    // loads questions from CSV file
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

