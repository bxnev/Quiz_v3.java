package ab.scotland.quiz;

import ab.scotland.quiz.Question;

public class TrueFalseQuestion extends Question {

    public TrueFalseQuestion(String question, Boolean answer, int score) {
        super(question, answer.toString(), 1);
    }


    @Override
    public String getQuestion() {
        return super.getQuestion() + "\n(Answer true or false)";
    }
}

