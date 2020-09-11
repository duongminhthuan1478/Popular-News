package com.example.android.popularnews.Utils;

import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetJsonAPI {
    static String apiUrl = "";
    ApiCall apiCall = null;
    ArrayList<header> headers = new ArrayList<>();

    private GetJsonAPI(String apiUrl) {
        this.headers.add(new header("Accept-Charset", "UTF-8"));
        this.headers.add(new header("Content-Type", "application/json; charset=UTF-8"));
        this.apiUrl = apiUrl;
    }

    private GetJsonAPI(String apiUrl, ArrayList<header> headers) {
        this.apiUrl = apiUrl;
        this.headers = headers;
    }

    public static GetJsonAPI setUrl(String apiUrl1) {
        return new GetJsonAPI(apiUrl1);
    }

    private String addHeader(ArrayList<header> headers, String key, String value) {
        for(int i = 0; i< headers.size();i++) {
            if(headers.get(i).getKey().equals(key)) {
                headers.get(i).setValue(value);
                return apiUrl;
            }
        }
        headers.add(new header(key, value));
        return apiUrl;
    }

    public GetJsonAPI addHeader(String key, String value) {
        return new GetJsonAPI(addHeader(headers, key, value));
    }

    private String addParams(String apiUrl, String key, String value) {
        if (apiUrl.indexOf("?") == -1)
            return apiUrl + "?" + key + "=" + value;
        return apiUrl + "&" + key + "=" + value;
    }

    public GetJsonAPI addParams(String key, String value) {
        return new GetJsonAPI(addParams(apiUrl, key, value));
    }

    public GetJsonAPI addArrayParams(String key, ArrayList<String> values) {
        for (int i = 0; i < values.size(); i++) {
            apiUrl = addParams(apiUrl, "[" + i + "]" + key, values.get(i));
        }
        return new GetJsonAPI(apiUrl);
    }

    public void interruptApiCall() {
        if (apiCall == null)
            return;
        apiCall.cancel(true);
        apiCall.isRunning = false;
        return;
    }

    public boolean isRunning() {
        if (apiCall == null)
            return false;
        return apiCall.isRunning;
    }

    public void get(ApiCall.AsyncApiCall asyncApiCall) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("GET", headers,asyncApiCall, null));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void post(ApiCall.AsyncApiCall asyncApiCall) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("POST", headers, asyncApiCall, null));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void post(ApiCall.AsyncApiCall asyncApiCall, Gson body) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("POST", headers, asyncApiCall, body));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void put(ApiCall.AsyncApiCall asyncApiCall) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("PUT", headers, asyncApiCall, null));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void put(ApiCall.AsyncApiCall asyncApiCall, Gson body) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("PUT", headers, asyncApiCall, body));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ApiCall.AsyncApiCall asyncApiCall) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("DELETE", headers, asyncApiCall, null));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ApiCall.AsyncApiCall asyncApiCall, Gson body) {
        try {
            URL url = new URL(apiUrl);
            apiCall = (new ApiCall("DELETE", headers, asyncApiCall, body));
            apiCall.execute(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public class header {
        String key, value;

        public header(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

