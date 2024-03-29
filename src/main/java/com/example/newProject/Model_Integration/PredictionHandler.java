package com.example.newProject.Model_Integration;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;


public class PredictionHandler {

    private static final String GET_URL = "http://localhost:1234/predictNeuralGET?Sleep=22.00&Smoking=no&PET=yes&Luxury=2&GPA=3.45&Renting=17&Price=9000";
//    private static final String POST_URL = "http://127.0.0.1:1234/predictLogistic";
//    public static String useThis3(String sleep_time,String smoking,String having_a_pet,String luxury_care,String GPA,String age,String renting_duration,String price) throws JSONException, IOException {
//        String prediction_value="";
//        JSONObject json = new JSONObject();
//        json.put("Sleep Time",sleep_time);
//        json.put("Smoking",smoking);
//        json.put("Having a pet",having_a_pet);
//        json.put("Luxury Care",luxury_care);
//        json.put("GPA",GPA);
////        json.put("Age",age);
//        //age i göndermedik
//        json.put("Renting Duration",renting_duration);
//        json.put("Price",price);
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//
//        try {
//            HttpPost request = new HttpPost("http://127.0.0.1:1234/predictNeural");
////            HttpPost request = new HttpPost("http://192.168.167.31:5000/predictLogistic");
//            StringEntity params = new StringEntity(json.toString());
//            request.addHeader("content-type", "application/json");
//            request.setEntity(params);
//            ResponseHandler<String> responseHandler=new BasicResponseHandler();
//            String responseBody = httpClient.execute(request,responseHandler);
//            //isteği icra edip gelen response body yi cavap olaral alırız
//            prediction_value = responseBody;
//            System.out.println(prediction_value);
//            JSONObject response=new JSONObject(responseBody);
//            //System.out.println(response);
//// handle response here...
//        } catch (Exception ex) {
//            // handle exception here
//        } finally {
//            httpClient.close();
//        }
//        //System.out.println(prediction_value);
//        return prediction_value;
//        //burada şimdilik deneme string i döner
//    }


    private static final OkHttpClient client = new OkHttpClient();


    private static String sendGET(String sleep_time, String smoking, String having_a_pet, String luxury_care,
                                  String GPA, String age, String renting_duration, String price) throws IOException {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("housemate-flask.up.railway.app")
                .addPathSegment("predictSVCGet")
                .addQueryParameter("Sleep", sleep_time)
                .addQueryParameter("Smoking", smoking)
                .addQueryParameter("PET", having_a_pet)
                .addQueryParameter("Luxury", luxury_care)
                .addQueryParameter("GPA", GPA)
                .addQueryParameter("Renting", renting_duration)
                .addQueryParameter("Price", price)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("GET request did not work.");
                return "";
            }
            return response.body().string();
        }
    }


    public static void main(String[] args) throws JSONException, IOException, URISyntaxException, InterruptedException {
//        String value = sendGET("22.00","no","yes","2","3.45","","17","9000");
//        System.out.println(value);
        //useThis3("22.00","no","no","5","3.45","null","17","9000");
//        System.out.println(ModelFunction("22.00", "no", "yes", "2", "3.45", "", "17", "9000"));
    }

    public static String ModelFunction(String sleepTime,String smooking,String havingAPet,
                                       String luxuryCare,String gpa,String age,
                                       String rentingDuration,String price)
            throws IOException, InterruptedException, JSONException {

//        System.out.println("sending satas:");
//        System.out.println( sleepTime+"\n"+ smooking+"\n"+ havingAPet+"\n"+
//                 luxuryCare+"\n"+ gpa+"\n"+ age+"\n"+
//                 rentingDuration+"\n"+ price);

//        String prediction = "deneme2";

//        String prediction =PredictionHandler.sendGET(sleepTime,smooking,havingAPet,luxuryCare,gpa,age,rentingDuration,price);
//        prediction = prediction.replace("[","").
//                replace("]","").replace("\'","");


        //predictionu buraya eşitleyeecğiz

//        System.out.println(prediction);
        return "predictionX";


    }




}
