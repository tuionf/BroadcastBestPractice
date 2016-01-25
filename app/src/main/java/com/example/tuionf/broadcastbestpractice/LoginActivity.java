package com.example.tuionf.broadcastbestpractice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;

    private SharedPreferences sharedPreferences;
    private CheckBox checkBox;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        accountEdit =(EditText) findViewById(R.id.account);
        passwordEdit =(EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        checkBox = (CheckBox) findViewById(R.id.remember_checkbox);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = sharedPreferences.getBoolean("remember_password",false);

        if (isRemember){
            Log.e("Login",isRemember+"");
            //将账号和密码都设置到文本框中
            String account = sharedPreferences.getString("account","");
            String password = sharedPreferences.getString("password","");
            Log.e("Login","account   "+account);
            Log.e("Login","password  "+password);

            accountEdit.setText(account);
            passwordEdit.setText(password);
            checkBox.setChecked(true);
            checkBox.isChecked();
        }



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                //如果账号密码是 tuionf 123456，则认为登陆成功
                if(account.equals("tuionf") && password.equals("123456")){
                    editor = sharedPreferences.edit();
                    if (checkBox.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",account);
                        editor.putString("password",password);
                   }else{
                        editor.clear();
                    }

                    editor.commit();

                    Log.e("Login","account——"+account +"——password——"+password );
                    Log.e("Login", sharedPreferences.getBoolean("remember_password",false)+"");

                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,"account or password is invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
