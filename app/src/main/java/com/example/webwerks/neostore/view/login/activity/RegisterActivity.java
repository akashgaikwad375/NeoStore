package com.example.webwerks.neostore.view.login.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.neostore.R;
import com.example.webwerks.neostore.common.base.BaseActivity;
import com.example.webwerks.neostore.common.base.BaseAsyncTask;
import com.example.webwerks.neostore.common.base.onAsyncTaskRequest;
import com.example.webwerks.neostore.model.RegistrationModel;
import com.example.webwerks.neostore.view.home.activity.HomeActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener, View.OnClickListener,
        onAsyncTaskRequest {

    private Toolbar toolbar;
    private TextView terms, agree, header, male, female;
    private EditText firstname,lastname,email,confirmPass, password,phone;
    private Button register;
    private CheckBox cbAgree;
    private RadioGroup rgGender;
    String stGender="M";
    private Boolean check=false;
    private String url="http://staging.php-dev.in:8844/trainingapp/api/users/register";

    @Override
    public int getContentView() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        agree=findViewById(R.id.agree);
        terms=findViewById(R.id.terms);
        header=findViewById(R.id.registerheader);

        rgGender=findViewById(R.id.rgGender);
        male=findViewById(R.id.btnMale);
        female=findViewById(R.id.btnFemale);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        confirmPass=findViewById(R.id.confirmpass);
        phone=findViewById(R.id.phone);

        cbAgree=findViewById(R.id.agree);
        register=findViewById(R.id.register);

        toolbar=findViewById(R.id.toolbar);
        terms.setPaintFlags(terms.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void setListener() {
        rgGender.setOnCheckedChangeListener(this);
        cbAgree.setOnCheckedChangeListener(this);
        register.setOnClickListener(this);
       }

    @Override
    public void setActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_left_black_24dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: onBackPressed();
                                    break;
        }
        return true;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

       if(i==R.id.btnFemale)
           stGender="F";
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b)
            check=true;
        else check=false;
    }

    @Override
    public void onClick(View view) {
        if (check) {
            if (firstname.getText().toString().equals(""))
                firstname.setError("First Name required");
            else if (lastname.getText().toString().equals(""))
                lastname.setError("Last Name required");
            else if (email.getText().toString().equals(""))
                email.setError("Email Id required");
            else if (password.getText().toString().equals(""))
                password.setError("Password required");
            else if (confirmPass.getText().toString().equals(""))
                confirmPass.setError("Password required");
            else if (phone.getText().toString().equals(""))
                phone.setError("Phone no. required");
            else {
                Map<String, Object> data = new HashMap<>();
                data.put("first_name", firstname.getText().toString());
                data.put("last_name", lastname.getText().toString());
                data.put("email", email.getText().toString());
                data.put("password", password.getText().toString());
                data.put("confirm_password", confirmPass.getText().toString());
                data.put("gender", stGender);
                data.put("phone_no", phone.getText().toString());
                BaseAsyncTask registerTask = new BaseAsyncTask(this,"POST",data);
                registerTask.execute(url);
            }
        }
        else
            Toast.makeText(RegisterActivity.this, "Please accept the terms & conditions", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void asyncResponse(Object obj) {
        try {
            JSONObject jsonObject=new JSONObject((String) obj);
            int status=jsonObject.optInt("status");
            if(status==200){
                JSONObject dataObject=jsonObject.optJSONObject("data");
                RegistrationModel rgModel=new RegistrationModel();
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
                finish();
            }
            else{
                Toast.makeText(this, "Email id already exist", Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
