package com.hakke.homework.bim493_assignment3;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    final String LOG = "Login_Log";
    static final String USER_DETAILS = "user_details";
    private Button btnSignIn, btnGoRegister;
    private TextView tvMessage;
    private EditText etEmail, etPasswd;
    private User lastRegisteredUser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    init();
    }

    private void init() {
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnGoRegister = (Button) findViewById(R.id.btn_Go_Register_Page);
        etEmail = (EditText) findViewById(R.id.login_email);
        etPasswd = (EditText) findViewById(R.id.login_passwd);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    private void login() {
        String email = etEmail.getText().toString();
        String  passwd = etPasswd.getText().toString();

        if(lastRegisteredUser != null)
        {
            if(lastRegisteredUser.getEmail().contains(email))
            {
                Toast.makeText(this,"Giriş Başarılı!", Toast.LENGTH_SHORT).show();

                StringBuilder sb= new StringBuilder();
                sb.append("Previously saved user signed in");
                sb.append("\n");
                sb.append("Name: " + lastRegisteredUser.getFullname());
                tvMessage.setText(sb.toString());
            }
            else{
                Toast.makeText(this,"Son Kayıtlı kullanıcının maili bu değil!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            String alert_message = "You should be registered first!";
            Toast.makeText(getApplicationContext(), alert_message,Toast.LENGTH_LONG).show();
        }
    }

    /* Aktiviti öldükten sonra çağırılır */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(LOG,"onRestoreInstanceState");
        lastRegisteredUser = (User) savedInstanceState.getSerializable(USER_DETAILS);

    }

    /* Aktivite PAUSE OLDUKTAN SONRA ÇAĞIRILIR: SON DURUMU KAYDEDER. */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(LOG, "onSaveInstanceState");

        outState.putSerializable(USER_DETAILS, lastRegisteredUser);
    }


    @Override
    protected void onPause() {
        super.onPause();
        tvMessage.setText("");
        Log.d(LOG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvMessage.setText("");
        Log.d(LOG, "onResume");

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            lastRegisteredUser = (User)getIntent().getSerializableExtra("user_details"); //Obtaining data

            StringBuilder sb = new StringBuilder();
            sb.append("Last Registered User:");
            sb.append("\n");
            sb.append(lastRegisteredUser.getFullname());
            sb.append("\n");
            sb.append("Email: " + lastRegisteredUser.getEmail());
            sb.append("\n");
            sb.append("Age: " + lastRegisteredUser.getAge());


            tvMessage.setText(sb.toString());
        }
    }
    public void onBackPressed() {
        //  super.onBackPressed();
        moveTaskToBack(true);

    }
}
