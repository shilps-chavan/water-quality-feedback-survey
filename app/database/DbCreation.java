package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCreation {

     static {

         Connection connection = ConnectionManager.getConnection();
         String questionsDB="CREATE TABLE QUESTIONS (id integer,question character varying(10000),questiontype character varying(100))";
         String commentsDb="CREATE TABLE comments (comments character varying(10000),   surveyid integer)";
         String feedBackDB="CREATE TABLE feedback(device_id character varying(10000),answer integer,id integer NOT NULL, question_id integer, mobileno character varying(100))";
         String locationDb="CREATE TABLE location( deviceid character varying(10000),location character varying(10000))";
         String insertQuesion="INSERT INTO questions(id, question, questiontype) VALUES (1, 'How was the overall experience' , 'Water Quality')";
         String insertQuesion1="INSERT INTO questions(id, question, questiontype) VALUES (2, 'Is Water taste good', 'Water Quality')";

         String insertQuesion2="INSERT INTO questions(id, question, questiontype) VALUES (3, 'How is  the Product', 'Water Quality')";

         String insertQuesion3="INSERT INTO questions(id, question, questiontype) VALUES (4, 'How was the interaction with service Executive', 'Service Delivery')";

         String insertQuesion4="INSERT INTO questions(id, question, questiontype) VALUES (5,'Executive was solve to solve your problem' , 'Service Delivery')";

         String insertQuesion5="INSERT INTO questions(id, question, questiontype) VALUES (6, 'Executive was able to understand the problem' , 'Service Delivery')";

         Statement stmt = null;
        try {
            stmt=connection.createStatement();
            stmt.executeUpdate(questionsDB);
            stmt.executeUpdate(feedBackDB);
            stmt.executeUpdate(commentsDb);
            stmt.executeUpdate(locationDb);
            stmt.executeUpdate(insertQuesion);
            stmt.executeUpdate(insertQuesion1);
            stmt.executeUpdate(insertQuesion2);
            stmt.executeUpdate(insertQuesion3);
            stmt.executeUpdate(insertQuesion4);
            stmt.executeUpdate(insertQuesion5);
            connection.commit();

        }  catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    }
}
