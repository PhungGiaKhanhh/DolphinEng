package com.example.learnenglish.activity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignupActivity2 extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button signupButton;
    private TextView loginRedirectButton;

    private DatabaseHelper databaseHelper;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        emailEditText = findViewById(R.id.suemail);
        passwordEditText = findViewById(R.id.supassword);
        confirmPasswordEditText = findViewById(R.id.repassword);
        signupButton = findViewById(R.id.signupbtn);
        loginRedirectButton = findViewById(R.id.loginRedirectText);

        databaseHelper = new DatabaseHelper(this);
        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity2.this, "Hãy điền thông tin vào tất cả các ô", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity2.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity2.this, task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    // Lưu thông tin vào SQLite để đăng nhập offline
                                    boolean insert = databaseHelper.insertData(email, password);
                                    if (insert) {
                                        Toast.makeText(SignupActivity2.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity2_login.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignupActivity2.this, "Lỗi khi lưu thông tin (SQL)", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(SignupActivity2.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignupActivity2.this, "Lỗi đăng ký, hãy kiểm tra lại kết nối mạng (FB)", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        loginRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2_login.class);
                startActivity(intent);
            }
        });
    }
}
