package com.example.quickbloxchatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignUpActivity extends AppCompatActivity {


    Button signup, cancel;
    EditText name_e, pass_e,full_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerSession();
        signup = findViewById(R.id.mainsignup_button);
        cancel = findViewById(R.id.signup_button_cancel);

        name_e = findViewById(R.id.maineSignuplogin);
        pass_e = findViewById(R.id.mainSignuppass);
        full_name=findViewById(R.id.editFullName);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name_e.getText().toString();
                String pass = pass_e.getText().toString();

                QBUser qbUser = new QBUser(name, pass);
                qbUser.setFullName(full_name.getText().toString());
                
                QBUsers.signUp(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {
                        Toast.makeText(getBaseContext(), "successfully signup", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(getBaseContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registerSession() {
        QBAuth.createSession().performAsync(new QBEntityCallback<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {

            }

            @Override
            public void onError(QBResponseException e) {
                Log.e("eroor", e.getMessage().toString());
            }
        });
    }
}
