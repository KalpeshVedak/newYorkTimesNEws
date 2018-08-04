package kv.myapplication.system;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkCalls {

    private static final int ONE_SECOND = 1000;
    private static final int DEFAULT_API_TIMEOUT = 30*ONE_SECOND;
    private static final NetworkCalls ourInstance = new NetworkCalls();

    public static NetworkCalls getInstance() {
        return ourInstance;
    }

    private NetworkCalls() {
    }

    public JSONObject performNetworkCall(String url) throws Exception {
        JSONObject responseObject = new JSONObject();

        int responseCode = -1;
        HttpURLConnection connection = null;
        URL connUrl = new URL(url);
        connection = (HttpURLConnection)connUrl.openConnection();
        connection.setConnectTimeout(DEFAULT_API_TIMEOUT);
        connection.setReadTimeout(DEFAULT_API_TIMEOUT);
        connection.setRequestMethod("GET");
        connection.connect();
        responseCode = connection.getResponseCode();
        if(isGoodResponse(responseCode))
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBody= new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
            responseObject = new JSONObject(responseBody.toString());
        }
        else
        {

            throw new NetworkException();

        }
        return responseObject;
    }

    private boolean isGoodResponse(int responseCode)
    {
        return responseCode == HttpURLConnection.HTTP_OK
                || responseCode == HttpURLConnection.HTTP_CREATED
                || responseCode == HttpURLConnection.HTTP_ACCEPTED
                || responseCode == HttpURLConnection.HTTP_NO_CONTENT;
    }

    class NetworkException extends Exception{

    }
}
