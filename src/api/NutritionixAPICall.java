package api;

//import data_access.ExerciseData;
import entity.CommonUserProfileFactory;
import entity.UserProfile;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class NutritionixAPICall {
//    public static void main(String[] args) throws IOException { // example call
//        NutritionixAPICall apicall = new NutritionixAPICall();
//        String exercisePerformed = "jogging for 120 mins";
//        ArrayList<String> dr = new ArrayList<>();
//
//        UserProfile userProfile = new UserProfile("ben1", "1", "male", 12, 12,
//                12, dr, 0, dr);
//        System.out.println(apicall.caloriesBurned(exercisePerformed, userProfile));
//    }

    public float caloriesBurned(String exercisePerformed, UserProfile userProfile) {
        try {
            OkHttpClient client = new OkHttpClient();

            String gender = userProfile.getGender();
            float weight = userProfile.getWeight();
            float height = userProfile.getHeight();
            int age = userProfile.getAge();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\n  \"query\": \"" + exercisePerformed + "\"\n}");
            Request request = new Request.Builder()
                    .url("https://trackapi.nutritionix.com/v2/natural/exercise?gender=" + gender + "&weight_kg=" +
                            String.valueOf(weight) + "&height_cm=" + String.valueOf(height) + "&age=" +
                            String.valueOf(age))
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("x-app-id", "a850fd03")
                    .addHeader("x-app-key", "67f8395ca094e8e9fdeee99729678c18")
                    .build();

            Response response = client.newCall(request).execute();
            String responseBody = response.body().string();
            JSONObject JSONResponseBody = new JSONObject(responseBody);
            JSONArray exerciseInfo = JSONResponseBody.getJSONArray("exercises");
            if (exerciseInfo.isEmpty()) {
                return 0;
            } else {
                JSONObject data = exerciseInfo.getJSONObject(0);
                return data.getInt("nf_calories");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}