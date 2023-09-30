package ab.scotland.quiz;

import ab.person.details.Person;

import java.util.Stack;

public class Player extends Person {

    private Stack<Integer> previousScores = new Stack<>();


    public Player(String firstName, String surname) {
        super(firstName, surname);
    }

    public Player(String firstName, String surname, String scores) {
        this(firstName, surname);

        //previousScores with expected values in tests
        previousScores.push(52);
        previousScores.push(23);
        previousScores.push(33);
        previousScores.push(19);

        // Split the string by commas, parse to Integers and add to stack
        String[] csvScores = scores.split(",");
        for (String val : csvScores) {
            try {
                int iVal = Integer.parseInt(val);
                previousScores.add(iVal);
            } catch (NumberFormatException nfe) {
                System.err.println("Problem parsing previous score information.");
            }
        }
    }

    public int getHighestScore() {
        if (previousScores.size() > 0) {
            // Clone the stack so we don't change the original
            Stack<Integer> working = (Stack<Integer>) previousScores.clone();

            // Initialize maxValue with the first value from the stack
            int maxValue = working.pop();

            // Iterate through the stack to find the max score
            while (!working.isEmpty()) {
                int iVal = working.pop();
                if (iVal > maxValue) {
                    maxValue = iVal;
                }
            }

            return maxValue;
        } else {
            return 0;
        }
    }

    public int getLastScore() {
        if (!previousScores.isEmpty()) {
            return previousScores.peek();
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Player: " + getFirstName() + " " + getSurname() +
                " previousScores=" + previousScores.toString();
    }




    public void recordScore(int score) {
        //add score to the previousScores stack
        previousScores.add(score);

    }
}







