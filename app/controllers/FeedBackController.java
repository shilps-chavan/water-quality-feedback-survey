package controllers;

import model.FeedbackDetails;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.FeedBackService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FeedBackController extends Controller {

    FeedBackService feedBackService=new FeedBackService();
    public Result submitFeedBack() {
        DynamicForm requestData = Form.form().bindFromRequest();

        List<FeedbackDetails> feedbackDetailsList=new ArrayList<FeedbackDetails>();
        Map<String, String> data = requestData.data();
        int questionSize= Integer.valueOf(requestData.get("questionsize"));
        String mobileNo = requestData.get("mobileNo");
        String deviceId = requestData.get("deviceId");
        for(int i=0;i<questionSize;i++)
        {
            int answerId = Integer.valueOf(data.get("answer["+i+"]"));
            String comments = data.get("comments["+i+"]");
            String questionId = data.get("question["+i+"]");

            FeedbackDetails feedbackDetails = new FeedbackDetails();
            feedbackDetails.setComments(comments);
            feedbackDetails.setAnswer(answerId);
            feedbackDetails.setQuestionId(questionId);
            feedbackDetails.setDeviceId(deviceId);
            feedbackDetails.setMobileNo(mobileNo);
            feedbackDetailsList.add(feedbackDetails);
        }
        for(int j=0;j<feedbackDetailsList.size();j++) {
            feedBackService.submitFeedBack(feedbackDetailsList.get(j));
        }

        return ok("success");



    }

    public Result showFeedBack() {

        return ok(views.html.feedbackview.apply(feedBackService.getFeedbackDetails()));

        }
}
