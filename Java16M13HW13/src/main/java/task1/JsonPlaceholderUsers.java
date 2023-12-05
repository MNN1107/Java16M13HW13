package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonPlaceholderUsers {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    public static void main(String[] args) throws IOException {
        createUser("{\"name\": \"Kyrylo Kozhumyaka\", \"username\": \"kyrylo_kozhumyaka\", \"email\": \"Kyrylo_Kozhumyaka@gmail.com\", \"city\": \"Kyivcity\", \"company\": \"Ukrainian fairy tails\"}");
        updateUser(11, "{\"name\": \"Kyrylo Kozhumyaka\", \"username\": \"kyrylo_kozhumyaka\", \"email\": \"Kyrylo_Kozhumyaka@gmail.com\", " +
                "\"address\":  {\"street\": \"Kite\", \"zipcode\": \"997733\", \"city\": \"Kyivcity\"}, \"phone\": \"(044)-648-95-69\", \"company\": \"Ukrainian fairy tails\"}");
        deleteUser(11);
        getAllUsers();
        getUserById(4);
        getUserByUsername("Antonette");
    }
    public static void createUser(String requestBody) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try(OutputStream outputStream = connection.getOutputStream()){
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
        }
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_CREATED){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line;
               while ((line = bufferedReader.readLine())!= null){
                    System.out.println(line);
                }
            }
        }else{
            System.out.println("Error:" + responseCode);
        }
        connection.disconnect();
    }

    public static void updateUser(int userId, String requestBody) throws IOException {
        URL url = new URL(BASE_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try(OutputStream outputStream = connection.getOutputStream()){
            outputStream.write(requestBody.getBytes());
            outputStream.flush();
        }
        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line;
                while ((line = bufferedReader.readLine())!= null){
                    System.out.println(line);
                }
            }
        }else{
            System.out.println("Error:" + responseCode);
        }
        connection.disconnect();
    }
    public static void deleteUser(int userId) throws IOException {
        URL url = new URL(BASE_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        if(responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE){
                    System.out.println("User deleted");
        }else{
            System.out.println("Error:" + responseCode);
        }
        connection.disconnect();
    }
    public static void getAllUsers() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } else {
            System.out.println("Error: " + responseCode);
        }

        connection.disconnect();
    }
    public static void getUserById(int userId) throws IOException {
        URL url = new URL(BASE_URL + "/" + userId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line;
                while ((line = bufferedReader.readLine())!= null){
                    System.out.println(line);
                }
            }
        }else{
            System.out.println("Error:" + responseCode);
        }
        connection.disconnect();
    }
    public static void getUserByUsername(String username) throws IOException {
        URL url = new URL(BASE_URL + "?username=" + username);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK){
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line;
                while ((line = bufferedReader.readLine())!= null){
                    System.out.println(line);
                }
            }
        }else{
            System.out.println("Error:" + responseCode);
        }
        connection.disconnect();
    }
}
