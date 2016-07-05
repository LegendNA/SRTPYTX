package srtp.na.srtpytx;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.gson.JsonObject;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences = getSharedPreferences("config", Context.MODE_PRIVATE);
        String account = preferences.getString("account", null);
        String password = preferences.getString("password", null);

        if (account != null) {
            ((EditText) findViewById(R.id.input_account)).setText(account);
        }
        if (password != null) {
            ((EditText) findViewById(R.id.input_password)).setText(password);
        }


        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.login();
            }
        });
        findViewById(R.id.button_regist).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"进入注册页",Toast.LENGTH_SHORT);
            }
        });
    }


    private void login() {
        String account = ((EditText) findViewById(R.id.input_account)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.input_password)).getText().toString();
        if ("".equals(account) || "".equals(password)) {
            //TODO Warning:(28, 42) The resource `@style/Theme_AppCompat_Light_Dialog_Alert` is marked as private in com.android.support:appcompat-v7
            new AlertDialog.Builder(this, android.support.v7.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert).setTitle("错误").setMessage("请填写完整！").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        } else {
            login(account, password);
        }
    }

    private void login(final String account, final String password) {
//        JsonObject object = new JsonObject();
//        object.addProperty("account", account);
//        object.addProperty("password", password);
        final View button = findViewById(R.id.button_login);
        final View loadingView = findViewById(R.id.button_login_loading);
        button.setVisibility(View.GONE);


        //模拟访问服务器功能
        String adminAccount = "admin";
        String adminPassword = "admin";

        if (adminAccount.equals(account) && adminPassword.equals(password)) {   //成功
            loadingView.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);

            SharedPreferences preferences = LoginActivity.this.getSharedPreferences("config", Context.MODE_PRIVATE);
            preferences.edit().putString("account", account).putString("password", password).apply();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {   //失败
            loadingView.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            Toast.makeText(LoginActivity.this,"帐号或密码错误",Toast.LENGTH_SHORT).show();
        }

//        Net.clearCookie(this);
//        Net.post(this, "/account/login", object, new Net.OnResultCallback() {
//            @Override
//            public void onSuccess(JsonObject object) {
//
//                loadingView.setVisibility(View.GONE);
//                button.setVisibility(View.VISIBLE);
//
//                SharedPreferences preferences = LoginActivity.this.getSharedPreferences("config", Context.MODE_PRIVATE);
//                preferences.edit().putString("account", account).putString("password", password).apply();
//
//                Net.secret_key = object.get("secret_key").getAsString();
//                Net.seller_id = object.get("id").getAsString();
//
//                SharedData.SINGLETON.setIsLogined(true);
//
//                Intent intent = new Intent(LoginActivity.this, OrderActivity.class);
//                startActivity(intent);
//                finish();
//
//            }
//
//            @Override
//            public void onFailure(Exception e,int code) {
//
//                loadingView.setVisibility(View.GONE);
//                button.setVisibility(View.VISIBLE);
//
//                String error;
//                if(e != null){
//                    error = "网络无连接";
//                }else{
//                    error = "帐号密码错误";
//                }
//                Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
//
//            }
//        });
    }
}
