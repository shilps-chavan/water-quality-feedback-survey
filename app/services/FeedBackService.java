package services;


import database.ConnectionManager;
import model.FeedBackInfo;
import model.QuestionInfo;
import model.FeedbackDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FeedBackService {

    private  Random random=new Random();
    public FeedbackDetails submitFeedBack(FeedbackDetails feedbackDetails) {
        Connection connection = ConnectionManager.getConnection();
        Statement stmt = null;
        int id=random.nextInt(1455660000);
        feedbackDetails.setSurveyId(id);

        for(QuestionInfo question:feedbackDetails.getQuestionInfoList()) {
            try {
                stmt = connection.createStatement();
                String sql = "INSERT INTO feedback(device_id, answer, mobileno,id,question_id) VALUES ('" + feedbackDetails.getDeviceId() + "'," +
                        "'" + question.getAnswerid()  + "','" + feedbackDetails.getMobileNo() + "','" + id +
                        "','" + question.getQuestionId() +"')";


                stmt.executeUpdate(sql);
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    stmt.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

        try {
            stmt = connection.createStatement();

            String sql = "INSERT INTO public.comments(comments, surveyid) VALUES ('" + feedbackDetails.getComments() +  "','" + feedbackDetails.getSurveyId() +"')";

            stmt.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }



return  feedbackDetails;

    }

    public List<FeedBackInfo> getFeedbackDetails() {
        Connection connection = ConnectionManager.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        List<FeedBackInfo> feedbackDetails=new ArrayList<FeedBackInfo>();
        try {
            stmt = connection.createStatement();


            rs = stmt.executeQuery("select * from feedback,questions,comments where feedback.question_id=questions.id and feedback.id=comments.surveyid" );
            List<QuestionInfo> questionInfo=new ArrayList<QuestionInfo>();
            while (rs.next()) {
                FeedBackInfo feedback=new FeedBackInfo();

                String device_id = rs.getString("device_id");
                String mobileno = rs.getString("mobileno");

                int answer = rs.getInt("answer");
                int id = rs.getInt("id");

                String comments=rs.getString("comments");
                String question = rs.getString("question");




                feedback.setDeviceId(device_id);
                feedback.setMobileNo(mobileno);
                feedback.setSurveyId(id);

                feedback.setAnswer(answer);
                feedback.setQuestion(question);
                //feedback.
                feedback.setComments(comments);
                //feedback.setQuestionId(questionId);
                feedback.setCity("pune");
                //questionDetails.setQuestion(question);
               // questionDetails.setQuestionId(id);
                //feedback.setQuestionInfoList();

                feedbackDetails.add(feedback);

            }
        } catch (Exception e) {
        } finally {
            try {
                rs.close();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return  feedbackDetails;

    }
}
