package com.example.newProject.Model_Integration;


import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;




public class PredictionHandler {
    private static final String GET_URL = "http://127.0.0.1:1234/predict";
    private static final String POST_URL = "http://127.0.0.1:1234/returnjson";

    public static String useThis3(String sleep_time,String smoking,String having_a_pet,String luxury_care,String GPA,String age,String renting_duration,String price) throws JSONException, IOException {
        String prediction_value="";
        JSONObject json = new JSONObject();
        json.put("Sleep Time",sleep_time);
        json.put("Smoking",smoking);
        json.put("Having a pet",having_a_pet);
        json.put("Luxury Care",luxury_care);
        json.put("GPA",GPA);
        json.put("Age",age);
        json.put("Renting Duration",renting_duration);
        json.put("Price",price);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost("http://127.0.0.1:1234/predictLogistic");
//            HttpPost request = new HttpPost("http://192.168.167.31:5000/predictLogistic");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            ResponseHandler<String> responseHandler=new BasicResponseHandler();
            String responseBody = httpClient.execute(request,responseHandler);
            prediction_value = responseBody;
            JSONObject response=new JSONObject(responseBody);
            //System.out.println(response);
// handle response here...
        } catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.close();
        }
        return prediction_value;
        //burada şimdilik deneme string i döner
    }
//    public static void main(String[] args) throws IOException, InterruptedException, JSONException {
//        String prediction = Trials.useThis3("21.00",
//                "yes","yes","8",
//                "3.00","23","15","8500");
//        prediction = prediction.replace("[","").replace("]","").replace("\'","");
//        System.out.println(prediction);
//
//    }

    public static String ModelFunction(String sleepTime,String smooking,String havingAPet,
                                String luxuryCare,String gpa,String age,
                                String rentingDuration,String price)
            throws IOException, InterruptedException, JSONException {

        String prediction = "deneme2";

//        Trials.useThis3(sleepTime,smooking,havingAPet,luxuryCare,gpa,age,rentingDuration,price);
//        prediction = prediction.replace("[","").
//                replace("]","").replace("\'","");
        //predictionu buraya eşitleyeecğiz

     return prediction;


    }
}
