package com.example.webwerks.neostore.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class BaseAsyncTask extends AsyncTask<Object,Object, Object> {

    Context context;
    onAsyncTaskRequest request;
    String method="GET";
    int statusCode;
    HttpURLConnection connection;
    StringBuffer sb= new StringBuffer("");
    Map<String,Object> data=null;
    private static final String TAG = BaseAsyncTask.class.getSimpleName();

    public BaseAsyncTask(Activity context, String method, Map<String, Object> data) {
        this.context = context;
        this.method = method;
        this.data = data;
        request= (onAsyncTaskRequest) context;
    }
    @Override
    protected Object doInBackground(Object... objects) {
        String url=objects[0].toString();
        if(method=="POST")
            return postMethod(url);
        if(method=="GET")
            return getMethod(url);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if(statusCode==200)
            request.onSuccess(o.toString());
        else request.onFaliure(o.toString());
    }

    private Object postMethod(String address) {
        try {
            URL url=new URL(address);
            connection= (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content_Type","applicaton/form-data");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization","someAuthString");
            if(data!=null){
                OutputStream outputStream=connection.getOutputStream();
                OutputStreamWriter writer=new OutputStreamWriter(outputStream);
                writer.write(getQuery(data));
                writer.flush();
                writer.close();
                outputStream.close();
            }
            connection.connect();
            statusCode=connection.getResponseCode();
            return readResponse(statusCode);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private Object getMethod(String address) {
        try {
            StringBuilder result = new StringBuilder(address);
            result.append("?"+getQuery(data));
            URL url = new URL(result.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            statusCode = connection.getResponseCode();
            return readResponse(statusCode);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private String getQuery(Map<String,Object> params) throws UnsupportedEncodingException {
        StringBuilder result=new StringBuilder();
        boolean first=true;

        for(Map.Entry<String,Object> pair:params.entrySet()){
            if(first)
                first=false;
            else result.append("&");
            result.append(URLEncoder.encode(pair.getKey(),"UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue().toString(),"UTF-8"));
        }
        return result.toString();
    }
    private Object readResponse(int statusCode) {
        BufferedReader in;
        try {
            if (statusCode == 200) {
                in = new BufferedReader(new InputStreamReader(
                                connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(
                                connection.getErrorStream()));
            }
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
                break;
            }
            in.close();
            Log.e(TAG, "postMethod: " + sb);
            return sb.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
