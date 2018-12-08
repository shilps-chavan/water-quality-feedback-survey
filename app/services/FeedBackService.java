package services;


import database.ConnectionManager;
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
        try {
            stmt = connection.createStatement();
            String sql="INSERT INTO feedback(device_id, answer, comments, mobileno,id,question_id) VALUES ('"+feedbackDetails.getDeviceId()+"'," +
                    "'"+feedbackDetails.getAnswer()+"','"+feedbackDetails.getComments()+"','"+feedbackDetails.getMobileNo()+"','"+ id+
                    "','"+feedbackDetails.getQuestionId()+"')";

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

    public List<FeedbackDetails> getFeedbackDetails() {
        Connection connection = ConnectionManager.getConnection();
        Statement stmt = null;
        int id=random.nextInt(1455660000);
        ResultSet rs = null;
        List<FeedbackDetails> feedbackDetails=new ArrayList<FeedbackDetails>();
        try {
            stmt = connection.createStatement();


            rs = stmt.executeQuery("select * from feedback,questions where feedback.question_id=questions.id");
            while (rs.next()) {

                String device_id = rs.getString("device_id");
                String mobileno = rs.getString("mobileno");
                String comments = rs.getString("comments");

                int answer = rs.getInt("answer");
                String question = rs.getString("question");



                FeedbackDetails feedback=new FeedbackDetails();
                feedback.setDeviceId(device_id);
                feedback.setMobileNo(mobileno);
                feedback.setQuestion(question);
                feedback.setAnswer(answer);
                feedback.setComments(comments);
                //feedback.setQuestionId(questionId);
                feedback.setCity("pune");
                //questionDetails.setQuestion(question);
               // questionDetails.setQuestionId(id);

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
