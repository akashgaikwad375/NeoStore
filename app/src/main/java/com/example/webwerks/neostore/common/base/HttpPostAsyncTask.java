package com.example.webwerks.neostore.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.webwerks.neostore.model.RegistrationModel;
import com.example.webwerks.neostore.view.login.activity.RegisterActivity;

import org.json.JSONException;
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

public class HttpPostAsyncTask extends AsyncTask<String,Void,String>{

    private Map<String, Object> mdata;
    private Context context;
    int statusCode;
    HttpURLConnection connection;
    StringBuffer sb= new StringBuffer("");
    private static final String TAG = HttpPostAsyncTask.class.getSimpleName();

    public HttpPostAsyncTask(Map<String, Object> data, RegisterActivity registerActivity){
        context=registerActivity;
        if(data!=null)
            mdata=data;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url=new URL(strings[0]);
            connection= (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content_Type","applicaton/form-data");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization","someAuthString");
            if(mdata!=null){
                OutputStream outputStream=connection.getOutputStream();
                OutputStreamWriter writer=new OutputStreamWriter(outputStream);
                writer.write(getQuery(mdata));
                writer.flush();
                writer.close();
                outputStream.close();
            }
            connection.connect();
            statusCode=connection.getResponseCode();
            if(statusCode==200){
                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));
                String line="";
                while((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                Log.e(TAG, "doInBackground: "+statusCode);
                in.close();
                return sb.toString();
            }
            else {
                Log.e(TAG, "doInBackground: "+statusCode);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sb.toString();
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
}

