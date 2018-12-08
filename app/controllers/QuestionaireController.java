package controllers;

import model.QuestionDetails;
import play.mvc.Controller;
import play.mvc.Result;
import services.QuestionairService;
import views.html.feedback;

import java.util.List;

public class QuestionaireController extends Controller {
    QuestionairService questionairService=new QuestionairService();
    public Result questions(String questionType) {

        List<QuestionDetails> questions=questionairService.getQuestions(questionType);
        return ok(feedback.apply(questions));
        //return ok(Json.toJson(questions));
    }
}
