package services;


import database.ConnectionManager;
import model.QuestionDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionairService {

    public List<QuestionDetails> getQuestions(String questionType) {

        List<QuestionDetails> questions = new ArrayList<QuestionDetails>();
        Connection connection = ConnectionManager.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            if(questionType.equalsIgnoreCase("")) {
                questionType = "Water Quality";
            }

            rs = stmt.executeQuery("SELECT * FROM questions where questiontype='" + questionType + "';");
            while (rs.next()) {

                String question = rs.getString("question");
                int id = rs.getInt("id");
                QuestionDetails questionDetails=new QuestionDetails();
                questionDetails.setQuestion(question);
                questionDetails.setQuestionId(id);

                questions.add(questionDetails);

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
        return questions;
    }

}
