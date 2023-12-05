package task3;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonPlaceholderTasks {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static void main(String[] args) throws IOException {
        getAllOpenTasks(2);
    }
    public static void getAllOpenTasks(int userId) throws IOException {
        String userTodosUrl = BASE_URL + "/users" + "/" + userId + "/todos";
        String userTodosResponse = sendHttpGetRequest(userTodosUrl);
        System.out.println("Open tasks for user: " + userId);

        Gson gson = new Gson();
        JsonArray todosArray = gson.fromJson(userTodosResponse, JsonArray.class);
        for (int i = 0; i < todosArray.size(); i++) {
            JsonObject todoObject = todosArray.get(i).getAsJsonObject();
            if (!todoObject.get("completed").getAsBoolean()) {
                System.out.println(todoObject);
            }
        }
    }
    private static String sendHttpGetRequest(String url) throws IOException {
        URL apiUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            throw new IOException("Error: " + responseCode);
        }
    }
}
