package com.example.webwerks.neostore.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.webwerks.neostore.model.RegisterationModel;
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
    StringBuffer sb;
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
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
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
                sb = new StringBuffer("");
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

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("onPostExecute",s);
        try {
            JSONObject jsonObject=new JSONObject(s);
            int status=jsonObject.optInt("status");
            JSONObject dataObject=jsonObject.optJSONObject("data");
            RegisterationModel rgModel=new RegisterationModel();
            rgModel.setId(dataObject.optInt("id"));
            rgModel.setRole_id(dataObject.optInt("role_id"));
            rgModel.setFirst_name(dataObject.optString("first_name"));
            rgModel.setLast_name(dataObject.optString("last_name"));
            rgModel.setEmail(dataObject.optString("email"));
            rgModel.setUsername(dataObject.optString("username"));
            rgModel.setProfile_pic(dataObject.optString("profile_pic"));
            rgModel.setCountry_id(dataObject.optString("country_id"));
            rgModel.setGender(dataObject.optString("gender"));
            rgModel.setPhone_no(dataObject.optInt("phone_no"));
            rgModel.setDob(dataObject.optString("dob"));
            rgModel.setIs_active(dataObject.optBoolean("is_active"));
            rgModel.setCreated(dataObject.optString("created"));
            rgModel.setModified(dataObject.optString("modified"));
            rgModel.setAccess_token(dataObject.optString("access_token"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ((Activity)context).finish();
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

