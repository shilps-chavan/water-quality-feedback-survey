package model;

public class QuestionInfo {
    public String getQuestionId() {
        return questionId;
    }


    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getAnswerid() {
        return answerid;
    }

    public void setAnswerid(int answerid) {
        this.answerid = answerid;
    }

    private  String questionId;
    private  int answerid;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    private  String question;
}
