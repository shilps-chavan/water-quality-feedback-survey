package controllers;

import model.FeedBackInfo;
import model.FeedbackDetails;
import model.QuestionInfo;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.FeedBackService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FeedBackController extends Controller {

    FeedBackService feedBackService=new FeedBackService();
    public Result submitFeedBack() {
        DynamicForm requestData = Form.form().bindFromRequest();

  //      List<FeedbackDetails> feedbackDetailsList=new ArrayList<FeedbackDetails>();
        Map<String, String> data = requestData.data();
        int questionSize= Integer.valueOf(requestData.get("questionsize"));
        String mobileNo = requestData.get("mobileNo");
        String deviceId = requestData.get("deviceId");
        String comments = data.get("comments");
        FeedbackDetails feedbackDetails = new FeedbackDetails();
        feedbackDetails.setComments(comments);
        feedbackDetails.setDeviceId(deviceId);
        feedbackDetails.setMobileNo(mobileNo);
        List<QuestionInfo> questionInfos=new ArrayList<QuestionInfo>();
        for(int i=0;i<questionSize;i++)
        {
            int answerId = Integer.valueOf(data.get("answer["+i+"]"));

            String questionId = data.get("question["+i+"]");

            QuestionInfo questionInfo=new QuestionInfo();
            questionInfo.setAnwerid(answerId);
            questionInfo.setQuestionId(questionId);
            questionInfos.add(questionInfo);


        }
        feedbackDetails.setQuestionInfoList(questionInfos);
            feedBackService.submitFeedBack(feedbackDetails);


        return ok("success");



    }

    public Result showFeedBack() {


        List<FeedBackInfo> feedbackDetails = feedBackService.getFeedbackDetails();
                feedbackDetails.sort((feedBack1, feedBack2) -> feedBack1.getDeviceId().compareTo(feedBack2.getDeviceId()));

        Map<Integer, List<FeedBackInfo>> collect = feedbackDetails.stream().collect(
                Collectors.groupingBy(FeedBackInfo::getSurveyId, Collectors.toList()));
        List<FeedbackDetails> feedbackDetailsList=new ArrayList<FeedbackDetails>();


        for (Integer surveyId : collect.keySet())
        {
            FeedbackDetails feedBack=new FeedbackDetails();
            List<FeedBackInfo> feedBackInfos=collect.get(surveyId);
            System.out.println("lissst"+feedBackInfos);
            for(FeedBackInfo feedBackInfo:feedBackInfos)
            {

                feedBack.setCity(feedBackInfo.getCity());
                feedBack.setComments(feedBackInfo.getComments());
                QuestionInfo questionInfo = new QuestionInfo();
                questionInfo.setAnwerid(feedBackInfo.getAnswer());
                questionInfo.setQuestion(feedBackInfo.getQuestion());
                feedBack.setDeviceId(feedBackInfo.getDeviceId());
                feedBack.getQuestionInfoList().add(questionInfo);

        }
            feedbackDetailsList.add(feedBack);

        }
        System.out.println("ollecetete"+feedbackDetailsList);


        return ok(views.html.feedbackview.apply(feedbackDetailsList));

        }
}
