package data_access;

import entity.UserProfile;
import entity.UserProfileFactory;
//import use_case.Exercise.ExerciseDataAccessInterface;
import use_case.Exercise.ExerciseDataAccessInterface;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;
import use_case.WeeklyDiet.WeeklyDietDataAccessInterface;

import java.io.File;
import java.io.*;
import java.util.*;

public class DataAccessObject implements LoginUserDataAccessInterface, SignupUserDataAccessInterface,
        WeeklyDietDataAccessInterface, ExerciseDataAccessInterface {
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
        this.headers.put("recommendedDailyCalories", 7);
        this.headers.put("recipes", 8);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                // For later: clean this up by creating a new Exception subclass and handling it in the UI.
                assert header.equals("username,password,gender,weight,height,age,dietaryRestrictions," +
                        "recommendedDailyCalories,recipes");

                String row;
                while ((row = reader.readLine()) != null) {

                    String[] col = new String[9];
                    String substring = "";
                    boolean inList = false;
                    int colNum = 0;
                    for (int i = 0; i < row.length(); i++) {
                        if (row.charAt(i) == '[') {
                            inList = true;
                        }
                        if (row.charAt(i) == ']') {
                            inList = false;
                        }
                        if (!inList && row.charAt(i) == ',') {
                            col[colNum] = substring;
                            colNum++;
                            substring = "";
                        }
                        else {
                            substring += row.charAt(i);
                        }
                    }
                    col[colNum] = substring;

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
                    float recommendedDailyCalories = Float.parseFloat(col[headers.get("recommendedDailyCalories")]);

                    ////////////////////////////
                    String stringRecipes = String.valueOf(col[headers.get("recipes")]);
                    String[] recipesElements = stringRecipes.replaceAll("\\[|\\]",
                            "").split(", ");
                    System.out.println(col);
                    ArrayList<String> recipes = new ArrayList<>(Arrays.asList(recipesElements));
                    ////////////////////////////

                    UserProfile user = userProfileFactory.create(username, password, gender, weight, height, age,
                            dietaryRestrictions, recommendedDailyCalories, recipes);
                    accounts.put(username, user);
                }
            }
        }
    }
    private float calculateDailyCalories(UserProfile user) {
        String gender = user.getGender();
        float weight = user.getWeight();
        float height = user.getHeight();
        int age = user.getAge();
        if (gender.equals("Male")) {
            return Float.parseFloat(String.valueOf(66 + (13.7 * weight) + (5 * height) - (6.8 * age)));
        }
        else {
            return Float.parseFloat(String.valueOf(655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)));
        }
    }


    public UserProfile get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(UserProfile user) {
        float recCalories = calculateDailyCalories(user);
        user.setRecommendedDailyCalories(recCalories);
        accounts.put(user.getUsername(), user);
        this.save();
    }

    @Override
    public void updateCalories(String username, float calories) {
        UserProfile user = accounts.get(username);
        float userCalories = user.getRecommendedDailyCalories();
        user.setRecommendedDailyCalories(userCalories + calories);
        accounts.replace(username, user);
        this.save();
//        UserProfile user = accounts.get(username);
//        float userCalories = user.getRecommendedDailyCalories();
//        user.setRecommendedDailyCalories(userCalories + calories);
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
//            String header = reader.readLine();
//            String row;
//            while ((row = reader.readLine()) != null) {
//                String[] col = row.split(",");
//                String name = String.valueOf(col[headers.get("username")]);
//                if (name.equals(username)) {
//                    col[headers.get("recommendedDailyCalories")] =
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    @Override
    public UserProfile getUserProfile(String username) {
        return accounts.get(username);
    }

    @Override
    public void saveRecipe(String recipeName, UserProfile userProfile) {
        if (csvFile.length() == 0) {
            this.save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    if (username.equals(userProfile.getUsername())) {
                        userProfile.addRecipe(recipeName);
                    }
                }
                this.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } ;
        }
    }

    @Override
    public boolean recipeSaved(String recipeName, UserProfile userProfile) {
        if (csvFile.length() == 0) {
            this.save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    if (username.equals(userProfile.getUsername())) {
                        if (userProfile.getRecipes().contains(recipeName)) {
                            return true;
                        }
                    }
                }
                this.save();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } ;
        }
        return false;
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (UserProfile user : accounts.values()) {
                String stringDietaryRestriction = "";
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        user.getUsername(), user.getPassword(), user.getGender(), String.valueOf(user.getWeight()),
                        String.valueOf(user.getHeight()), String.valueOf(user.getAge()),
                        user.getDietaryRestrictions().toString(), String.valueOf(user.getRecommendedDailyCalories()),
                        user.getRecipes().toString());
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public ExerciseData call(String username, String exercisePerformed) {
//        try{
//            UserProfile user = getUserProfile(username);
//            String query =
//                    "{\n" +
//                            "\"query\" : \"" + exercisePerformed + "\",\n" +
//                            "\"gender\" : \"" + user.getGender() + "\",\n" +
//                            "\"weight_kg\" : \"" + user.getWeight() + "\",\n" +
//                            "\"height_cm\" : \"" + user.getHeight() + "\",\n" +
//                            "\"age\" : \"" + user.getAge() + "\"\n" +
//                            "}";
//            System.out.println(query);
//            OkHttpClient client = new OkHttpClient();
//            MediaType mediaType = MediaType.parse("application/json");
//            RequestBody body = RequestBody.create(mediaType, query);
//            Request request = new Request.Builder()
//                    .url("https://trackapi.nutritionix.com/v2/natural/exercise")
//                    .post(body)
//                    .addHeader("content-type", "application/json")
//                    .addHeader("x-app-id", "a850fd03")
//                    .addHeader("x-app-key", "67f8395ca094e8e9fdeee99729678c18")
//                    .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(request);
////            System.out.println(response);
//            if (response.code() == 200) {
//
//                // This is the string representation of the response body (looks exactly like a JSON file).
//                String responseBody = response.body().string();
//                JSONObject JSONResponseBody = new JSONObject(responseBody);
//                JSONArray exerciseInfo = JSONResponseBody.getJSONArray("exercises");
//                if (exerciseInfo.isEmpty()) {
//                    return null;
//                } else {
//                    JSONObject data = exerciseInfo.getJSONObject(0);
//                    return new ExerciseData(data.getString("user_input"), data.getInt("duration_min"), data.getInt("nf_calories"));
//                }
//            }
//            System.out.println("Response Error: " + response.code());
//            return null;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}