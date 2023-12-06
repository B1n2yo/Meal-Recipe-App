package data_access;

import entity.UserProfile;
import entity.UserProfileFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.Exercise.ExerciseDataAccessInterface;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;

import java.io.File;
import java.io.*;
import java.util.*;

public class DataAccessObject implements ExerciseDataAccessInterface, LoginUserDataAccessInterface, SignupUserDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, UserProfile> accounts = new HashMap<>();
    private final UserProfileFactory userProfileFactory;
    public DataAccessObject(String csvPath, UserProfileFactory userProfileFactory) throws IOException {
        this.userProfileFactory = userProfileFactory;

        this.csvFile = new File(csvPath);
        this.headers.put("username", 0);
        this.headers.put("password", 1);
        this.headers.put("gender", 2);
        this.headers.put("weight", 3);
        this.headers.put("height", 4);
        this.headers.put("age", 5);
        this.headers.put("dietaryRestrictions", 6);
        this.headers.put("weeklyBudget", 7);
        this.headers.put("recommendedDailyCalories", 8);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,gender,weight,height,age,dietaryRestrictions,weeklyBudget," +
                        "recommendedDailyCalories");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String gender = String.valueOf(col[headers.get("gender")]);
                    float weight = Float.parseFloat(col[headers.get("weight")]);
                    float height = Float.parseFloat(col[headers.get("height")]);
                    int age = Integer.parseInt(col[headers.get("age")]);
                    ////////////////////////////
                    String stringRepresentation = String.valueOf(col[headers.get("dietaryRestrictions")]);
                    String[] elements = stringRepresentation.replaceAll("\\[|\\]",
                            "").split(", ");
                    ArrayList<String> dietaryRestrictions = new ArrayList<>(Arrays.asList(elements));
                    ////////////////////////////
                    float weeklyBudget = Float.parseFloat(col[headers.get("weeklyBudget")]);
                    float recommendedDailyCalories = Integer.parseInt(col[headers.get("recommendedDailyCalories")]);
                    UserProfile user = userProfileFactory.create(username, password, gender, weight, height, age,
                            dietaryRestrictions, weeklyBudget, recommendedDailyCalories);
                    accounts.put(username, user);
                }
            }
        }
    }
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(UserProfile user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return accounts.get(username);
    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (UserProfile user : accounts.values()) {
                String stringDietaryRestriction = "";
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(), String.valueOf(user.getWeight()),
                        String.valueOf(user.getHeight()), String.valueOf(user.getAge()),
                        user.getDietaryRestrictions().toString(), String.valueOf(user.getWeeklyBudget()),
                        String.valueOf(user.getRecommendedDailyCalories()));
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ExerciseData call(String username, String exercisePerformed) {
        try{
            UserProfile user = getUserProfile(username);
            String query =
                    "{\n" +
                            "\"query\" : \"" + exercisePerformed + "\",\n" +
                            "\"gender\" : \"" + user.getGender() + "\",\n" +
                            "\"weight_kg\" : \"" + user.getWeight() + "\",\n" +
                            "\"height_cm\" : \"" + user.getHeight() + "\",\n" +
                            "\"age\" : \"" + user.getAge() + "\"\n" +
                            "}";
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, query);
            Request request = new Request.Builder()
                    .url("https://trackapi.nutritionix.com/v2/natural/exercise")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .addHeader("x-app-id", "a850fd03")
                    .addHeader("x-app-key", "67f8395ca094e8e9fdeee99729678c18")
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(response.code());
            if (response.code() == 200) {

                // This is the string representation of the response body (looks exactly like a JSON file).
                String responseBody = response.body().string();
                JSONObject JSONResponseBody = new JSONObject(responseBody);
                JSONArray exerciseInfo = JSONResponseBody.getJSONArray("exercises");
                if (exerciseInfo.isEmpty()) {
                    return null;
                } else {
                    JSONObject data = exerciseInfo.getJSONObject(0);
                    return new ExerciseData(data.getString("user_input"), data.getInt("duration_min"), data.getInt("nf_calories"));
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}