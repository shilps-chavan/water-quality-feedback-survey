package model;

public class QuestionInfo {
    public String getQuestionId() {
        return questionId;
    }


    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getAnwerid() {
        return anwerid;
    }

    public void setAnwerid(int  anwerid) {
        this.anwerid = anwerid;
    }

    private  String questionId;
    private  int anwerid;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private  String question;
}
