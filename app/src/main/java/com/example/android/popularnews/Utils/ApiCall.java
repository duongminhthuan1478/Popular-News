package com.example.android.popularnews.Utils;


import android.os.AsyncTask;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import android.util.Log;

import com.google.gson.Gson;

import javax.xml.parsers.ParserConfigurationException;


public class ApiCall extends AsyncTask<URL, Integer, String> {
    // respone time
    long milisecStart = 0;
    // respone
    int responeCode = 0;
    String message = "OK";
    //
    public boolean isRunning = false;
    // data result
    private String ApiData = "";

    //interface
    AsyncApiCall asyncApiCall;

    // call api
    Gson body;
    String requestMethod = "", token = "";
    ArrayList<GetJsonAPI.header> headers = new ArrayList<>();

    public interface AsyncApiCall {
        void onSuccess(long resTime, String result) throws ParserConfigurationException;

        void onFail(int responeCode, String mess);
    }


    public ApiCall(String requestMethod, ArrayList<GetJsonAPI.header> headers, AsyncApiCall asyncApiCall, Gson body) {
        this.requestMethod = requestMethod;
        this.headers = headers;
        this.asyncApiCall = asyncApiCall;
        this.body = body;
    }


    @Override
    protected String doInBackground(URL... urls) {
        isRunning = true;
        milisecStart = System.currentTimeMillis();
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = urls[0];
            connection = (HttpURLConnection) url.openConnection();
            // header này tùy đứa đặt tên
            for (GetJsonAPI.header header: headers) {
                connection.setRequestProperty(header.key, header.value);
            }
            connection.setRequestMethod(requestMethod);
            submitBody(connection);
            connection.setConnectTimeout(50000);
            connection.connect();
            Log.d("api url", url.toString());

            // kiểm tra kết nối có fail hay k
            getResponeStatus(connection);

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);
            }
            ApiData = buffer.toString();

            return buffer.toString();


        } catch (MalformedURLException e) {
            Log.d("api call error : ", "MalformedURLException" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.d("api call error : ", "IOException" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Log.d("api url : ", e.getMessage());
            }
        }

        return ApiData;
    }

    protected void onProgressUpdate(Integer... progress) {
        // cập nhật progress tại đây
    }

    // Được gọi sau khi doInBackground kết thúc
    protected void onPostExecute(String lng) {
        isRunning = false;
        getResponseMess();
        long responeTime = System.currentTimeMillis() - milisecStart;
        if (responeCode < 200 || responeCode > 299 || ApiData.length() == 0) {
            asyncApiCall.onFail(responeCode, message);
        } else {
            try {
                asyncApiCall.onSuccess(responeTime, ApiData);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    void submitBody(HttpURLConnection conn) throws IOException {
        if(body != null){
            String json = body.toString().replace((char) 92 + "", "");
            conn.setRequestProperty("Content-Length", "" + json.getBytes().length);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(wr, StandardCharsets.UTF_8));
            Log.d("body api", json);
            writer.write(json);
            writer.close();
            wr.close();
        }
    }

    void getResponeStatus(HttpURLConnection connection) throws IOException {
        responeCode = connection.getResponseCode();
    }

    void getResponseMess() {
        if (responeCode == 0)
            message = "Connection : No network connection";
        else if (responeCode < 200)
            message = "Informational : Communicates transfer protocol-level information.";
        else if (responeCode > 299 && responeCode <= 399)
            message = "Redirection : Indicates that the client must take some additional action in order to complete their request.";
        else if (responeCode > 399 && responeCode <= 499)
            message = "Client error : This category of error status codes points the finger at clients.";
        else if (responeCode > 499 && responeCode <= 599)
            message = "Server Error : The server takes responsibility for these error status codes.";
    }
}
