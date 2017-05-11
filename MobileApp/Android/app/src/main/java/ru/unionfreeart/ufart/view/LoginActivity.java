package ru.unionfreeart.ufart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.utils.Settings;

public class LoginActivity extends AppCompatActivity {
    private EditText etLogin, etPassword;
    private Settings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        initButtons();
    }

    private void initButtons() {
        findViewById(R.id.bForgetMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setLogin("");
                settings.setPassword("");
                etLogin.setText("");
                etPassword.setText("");
            }
        });
        findViewById(R.id.bUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.open(LoginActivity.this, true);
            }
        });
        findViewById(R.id.bAdmin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.open(LoginActivity.this, false);
            }
        });
        findViewById(R.id.fabOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings.setLogin(etLogin.getText().toString());
                settings.setPassword(etPassword.getText().toString());
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = new Settings(LoginActivity.this);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etLogin.setText(settings.getLogin());
        etPassword.setText(settings.getPassword());
    }

}
