package com.example.learnenglish.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.R;
import com.example.learnenglish.database.DatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2_login extends AppCompatActivity {

    EditText liemail, lipassword;
    Button loginbtn;
    TextView signupRedirectText;
    DatabaseHelper databaseHelper;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_login);

        liemail = findViewById(R.id.liemail);
        lipassword = findViewById(R.id.lipassword);
        loginbtn = findViewById(R.id.loginbtn);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        databaseHelper = new DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = liemail.getText().toString();
                String password = lipassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity2_login.this, "Hãy điền thông tin vào tất cả các ô", Toast.LENGTH_SHORT).show();
                } else {
                    if (isNetworkAvailable()) {
                        // Đăng nhập với Firebase
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(MainActivity2_login.this, task -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity2_login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity2_login.this, MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(MainActivity2_login.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        // Đăng nhập với SQLite
                        boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);
                        if (checkCredentials) {
                            Toast.makeText(MainActivity2_login.this, "Đăng nhập ngoại tuyến thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity2_login.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity2_login.this, "Thông tin không hợp lệ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2_login.this, SignupActivity2.class);
                startActivity(intent);
            }
        });
    }

    // Kiểm tra xem có kết nối mạng hay không
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
