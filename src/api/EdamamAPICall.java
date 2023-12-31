package api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.*;

public class EdamamAPICall {

    // This is the API URL with the API key and API ID
    private static final String API_URL = "https://api.edamam.com/api/recipes/v2?type=public&app_id=981e8b83&" +
            "app_key=%202fba7f42e263a88f352970997e1158c3";

    //This method adds the parameters to the API URL from a dictionary
    private static String queryAdder(Dictionary<String, Object> query) {
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(API_URL)).newBuilder();

        Enumeration<String> keys = query.keys();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();

            if ("health".equals(key)) {
                Object value = query.get(key);
                if (value instanceof ArrayList) {
                    ArrayList<String> health = (ArrayList<String>) value;
                    if (health.contains("")) {
                        continue;
                    } else {
                        for (String s : health) {
                            urlBuilder.addQueryParameter("health", s);
                        }
                    }
                }
            } else {
                urlBuilder.addQueryParameter(key, (String) query.get(key));
            }
        }

        return urlBuilder.build().toString();
    }

    // This method filters the response body to get the recipe name and URL. Other information can be added as needed.
    private static Dictionary<String, ArrayList<String>> filterResponseBody(String responseBody) {
        Dictionary<String, ArrayList<String>> recipeInfo = new Hashtable<>();
        JSONObject jsonObject = JSONObject.parseObject(responseBody);
        JSONArray hits = jsonObject.getJSONArray("hits");
        for (int i = 0; i < hits.size(); i++) {
            JSONObject hit = hits.getJSONObject(i);
            JSONObject recipe = hit.getJSONObject("recipe");
            String label = recipe.getString("label");
            String url = recipe.getString("url");
            String calories = recipe.getString("calories");
            ArrayList<String> recipeInfoList = new ArrayList<>();

            JSONObject nutrients = recipe.getJSONObject("totalNutrients");
            JSONObject PROCNT = nutrients.getJSONObject("PROCNT");
            String protein = PROCNT.getString("quantity");
            recipeInfoList.add(url);
            recipeInfoList.add(calories);
            recipeInfoList.add(protein);

            recipeInfo.put(label, recipeInfoList);
        }
        return recipeInfo;
    }

    public static Dictionary<String, ArrayList<String>> RecipeUrl(Dictionary query) throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url(queryAdder(query))
                .method("GET", null)
                .build();
        Response response = client.newCall(request).execute();
        return filterResponseBody(response.body().string());
    }
}