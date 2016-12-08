package com.hakke.homework.bim493_assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private EditText etFullName, etAge, etEmail, etPasswd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
    }

    private void init() {
        btnRegister = (Button) findViewById(R.id.btnRegister);

        etFullName = (EditText) findViewById(R.id.register_namesurname);
        etAge = (EditText) findViewById(R.id.register_age);
        etAge.setText("0");
        etEmail = (EditText) findViewById(R.id.register_Email);
        etPasswd = (EditText) findViewById(R.id.register_passwd);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        if (!validate()) {

            return;
        }



        String fullname =  etFullName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String email = etEmail.getText().toString();
        String  passwd = etPasswd.getText().toString();

        //send details to "user" object
        User user = new User(fullname,age,email,passwd);

        Toast.makeText(getApplicationContext(), "Registeration is successfully", Toast.LENGTH_LONG).show();

        //go back to Login Activity with Registerated User Object using Intent and Serializable!
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("user_details", user);
        startActivity(intent);

        finish();

    }

    private boolean validate() {
        boolean valid = true;

        String fullname =  etFullName.getText().toString();
        int age = Integer.parseInt(etAge.getText().toString());
        String email = etEmail.getText().toString();

        if(fullname.isEmpty() ||  fullname.length() < 3){
            etFullName.setError("İsmin en az 3 karakterden oluşmalıdır.");
            valid =false;
        }else{
            etFullName.setError(null);
        }

        if(age < 1  || age > 85){
            etAge.setError("Tutarlı bir yaş giriniz");
            valid = false;
        }else{
            etAge.setError(null);
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Doğru bir email giriniz.");
            valid = false;
        }else{
            etEmail.setError(null);
        }

        return valid;
    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
