package ab.scotland.quiz;

public class Question {

    private String question;
    private String answer;
    private int score = 1;


    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer.toLowerCase();

    }

    public Question(String question, String answer, int score) {
        this(question, answer);
        this.score = score;

    }
    public String getQuestion() {
        return question + " ("+score+" pts) ?";
    }

    public String getAnswer() {
        return answer;
    }
    public int getScore() {return score; }

    @Override
    public String toString() {
        return "ab.scotland.quiz.Question{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + score +
                '}';
    }

    public boolean isCorrect(String userSays) {
        //checks that an answer was given
        if (userSays != null && userSays.length() > 0) {

            //make user's answer lowercase and compare with the correct answer
            userSays = userSays.toLowerCase();
            if (userSays.equals(getAnswer()))
                return true;
        }
        return false;
    }

    public static class Player {
    }
}
