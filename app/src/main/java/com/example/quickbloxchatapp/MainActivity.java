package com.example.quickbloxchatapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

import org.jivesoftware.smack.util.MAC;

public class MainActivity extends AppCompatActivity {
    static final String Auth_key = "Anjpjk9rK5ND-Tq";
    static final String App_ID = "76779";
    static final String Auth_secret = "J5znE2TK2GB39um";
    static final String Acc_Key = "-uXkW2LQsn-hyNwrMZ_u";

    Button signup, signin;
    EditText editName, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeFramwrok();

        signin = findViewById(R.id.login_button);
        signup = findViewById(R.id.signup_button);

        editName = findViewById(R.id.maineditlogin);
        editPass = findViewById(R.id.maineditpass);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = editName.getText().toString();
                final String pass = editPass.getText().toString();
                QBUser qbUser = new QBUser(user, pass);
                QBUsers.signIn(qbUser).performAsync(new QBEntityCallback<QBUser>() {
                    @Override
                    public void onSuccess(QBUser qbUser, Bundle bundle) {

                        Toast.makeText(getBaseContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ChatDialoguesActivity.class);
                        intent.putExtra("user",user);
                        intent.putExtra("pass",pass);
                        startActivity(intent);

                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Toast.makeText(getBaseContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void intializeFramwrok() {
        QBSettings.getInstance().init(getApplicationContext(), App_ID, Auth_key, Auth_secret);
        QBSettings.getInstance().setAccountKey(Acc_Key);
    }
}
