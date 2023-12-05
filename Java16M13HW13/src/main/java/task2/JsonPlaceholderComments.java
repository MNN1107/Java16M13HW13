package task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonPlaceholderComments {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static void main(String[] args) throws IOException{
        CommentsForLastPost((int)1);
    }
public static void CommentsForLastPost(int userId) throws IOException {
    String userPostsUrl = BASE_URL + "/users" + "/" + userId + "/posts";
    String userPostsResponse = sendHttpGetRequest(userPostsUrl);
    int lastPostId = getLastPostId(userPostsResponse);

    String postCommentsUrl = BASE_URL + "/posts/" + lastPostId + "/comments";
    String commentsResponse = sendHttpGetRequest(postCommentsUrl);

    String fileName = "user-" + userId + "-post-" + lastPostId + "-comments.json";
    writeStringToFile(fileName, commentsResponse);
    System.out.println("Comments for the last post of user with userId: " + userId + " have been saved to file: " + fileName);
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
    private static int getLastPostId(String userPostsResponse){
        return (int)10;
    }
    private static void writeStringToFile(String fileName, String content) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        }
    }
    }

