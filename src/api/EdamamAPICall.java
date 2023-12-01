package api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class EdamamAPICall {

    // This is the API URL with the API key and API ID
    private static final String API_URL = "https://api.edamam.com/api/recipes/v2?type=public&app_id=981e8b83&" +
            "app_key=%202fba7f42e263a88f352970997e1158c3";

    public static void main(String[] args) throws IOException {
        // This is an example of how parameters should be given to the API
        Dictionary<String, Object> query = new Hashtable<>();
        query.put("mealType", "Breakfast");
        query.put("calories", "100-300");

        try {
            RecipeUrl(query);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //This method adds the parameters to the API URL from a dictionary
    private static String queryAdder(Dictionary query) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();

        for (int i = 0; i < query.size(); i++) {
            urlBuilder.addQueryParameter((String) query.keys().nextElement(), (String) query.elements().nextElement());
        }

        String url = urlBuilder.build().toString();
        return url;
    }

    // This method filters the response body to get the recipe name and URL. Other information can be added as needed.
    private static void filterResponseBody(String responseBody) {
        JSONObject jsonObject = JSONObject.parseObject(responseBody);
        JSONArray hits = jsonObject.getJSONArray("hits");
        for (int i = 0; i < hits.size(); i++) {
            JSONObject hit = hits.getJSONObject(i);
            JSONObject recipe = hit.getJSONObject("recipe");
            String label = recipe.getString("label");
            String url = recipe.getString("url");
            System.out.println(label + ": " + url);
        }
    }

    public static JSONObject RecipeUrl(Dictionary query) throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "mealType=Breakfast");
        Request request = new Request.Builder()
                .url(queryAdder(query))
                .method("GET",null)
                .build();
        Response response = client.newCall(request).execute();
        filterResponseBody(response.body().string());
        return null;
    }
}