/**
 * @author Aleksandar B
 * Scotland CSV Quiz
 */

package ab.scotland.quiz;

import ab.useful.FileAccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Quiz {
    String userResults = "userResults.csv";
    static String filePath = "src/questions.csv";
    protected String DEFAULT_NAME = "Anon";
    protected int totalScore = 0;
    int lineNumber = 0;

    public ArrayList<Question> quizQuestions = new ArrayList();

    /**
     * Parse questions and separate them into type, question, answer, points
     * and additionally wrong answers for multiple choice questions
     *
     * @param filePath loads questions from chosen file path
     */

    private void parseQuestions(String filePath) {

        //separates the file information into 5 variables
        ArrayList<String> allRows = FileAccess.loadQuestions(filePath);
        for (String line : allRows) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                String typeOfQ = parts[0].trim();
                String question = parts[1].trim();
                String answer = parts[2].trim();
                String scoreString = parts[3].trim();
                //checks if there is data in the 5th column, if so then it is then also separated
                String mcWrongs = (parts.length >= 5) ? parts[4].trim() : "";

                int score = -1;

                try {
                    //attempt to parse scoreString as an integer
                    score = Integer.parseInt(scoreString);
                } catch (NumberFormatException e) {
                    //handle case if 'scoreString' is not a valid integer
                    System.err.println("Invalid score format: " + scoreString);
                }

                /**
                 *  separates questions into the 3 question types
                 */
                try {
                    int questionType = Integer.parseInt(typeOfQ);

                    switch (questionType) {
                        case 1 ->
                            //textual question
                                quizQuestions.add(new TextQuestion(question, answer, Integer.parseInt(scoreString)));
                        case 2 -> {
                            //true/false question
                            boolean isTrueAnswer = Boolean.parseBoolean(answer);
                            quizQuestions.add(new TrueFalseQuestion(question, isTrueAnswer, Integer.parseInt(scoreString)));
                        }
                        case 3 -> {
                            //multiple choice question
                            String[] mcWrongAnswers = mcWrongs.split(";");
                            quizQuestions.add(new MultipleChoiceQuestion(question, answer, mcWrongAnswers, Integer.parseInt(scoreString)));
                        }
                        default ->
                                System.err.println("Unknown question type in row " + lineNumber + ": " + questionType);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("NumberFormatException occurred: " + e.getMessage());
                }
            }
        }
    }



    public Quiz(String filePath) {
        parseQuestions(filePath);
    }


    public void start() {
        Scanner input = new Scanner(System.in);

        boolean retry = true;
        Player livePlayer = null;
        String name = null;
        int total = 0;
        livePlayer = new Player(name, "");

        //get the user's name
        System.out.println("What is your name?");
        name = input.nextLine();

        //if the user never entered a name give them a default name
        if (name.length() < 1)
            name = DEFAULT_NAME;
        livePlayer.setFirstName(name);

        while (retry) {

            //reset the total score for each retry
            totalScore = 0;

            //shuffles order of questions
            Collections.shuffle(quizQuestions);


            System.out.println("Welcome " + livePlayer.getFirstName() + " to the quiz of the century!");

            //ask the questions and keep track of the score
            total = 0;
            int i = 0;

            while (i < quizQuestions.size()) {
                System.out.print("Q" + (i + 1) + ": ");
                total += askQuestion(quizQuestions.get(i));
                i++;
            }

            System.out.println(name + " you scored " + total + "/" + totalScore);

            livePlayer.recordScore(total); //records Player score

            //asks if the user wants to retry the test
            System.out.println("Do you want to retry the test? (yes/no)");
            String retryChoice = input.nextLine().trim().toLowerCase();

            if (!retryChoice.equals("yes")) {
                livePlayer.incAttempts();
                retry = false;
            }
        }
        System.out.println("Goodbye!"); //if answer isn't yes quiz saves results to file and exits

        //save user results to CSV
        try {
            FileAccess.saveToCSV(userResults, (name + "," + total + "," + livePlayer.getHighestScore() + "\n"));
        }
        catch (Exception e){
            System.out.println("Unable to save details");
        }
        //message if user does not retry
        System.out.println("Thank you for playing " + livePlayer.getFirstName());
        System.out.println("Your last score: " + livePlayer.getLastScore());
        System.out.println("Your best score: " + livePlayer.getHighestScore());
        System.out.println("You have retried this quiz: " + livePlayer.getAttempts() + " times");
        System.exit(0);
    }


    /**
     * gets an answer input from the user
     * returns response and points if the answer is right
     *
     * @param q question to be asked
     * @return points for correct answer
     */
    public int askQuestion(Question q) {
        Scanner input = new Scanner(System.in);
        int score = 0;

        System.out.println(q.getQuestion());

        //get input from the user
        String answer = input.nextLine();

        totalScore = totalScore + q.getScore();

        //if the user entered nothing then give them zero
        if (answer.length() < 1) {
            System.out.println("No answer supplied. 0 points");
        } else {
            if (q.isCorrect(answer)) {
                System.out.println(answer + " is the correct answer. " + q.getScore() + " points.");
                score = q.getScore();
            } else {
                System.out.println(answer + " is the wrong answer. 0 points.");
            }
        }
        return score;
    }

    public static void main(String[] args) {
        //call the ab.scotland.quiz.Quiz constructor
        Quiz myQuiz = new Quiz(filePath);

        //kick off the quiz.
        myQuiz.start();
    }
}




