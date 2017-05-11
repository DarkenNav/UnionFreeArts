package ru.unionfreeart.ufart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.utils.Settings;

public class LoginActivity extends AppCompatActivity {
    private final String REG = "reg";
    private View bForgetMe, bRestorePassword, bRegistration;
    private EditText etLogin, etPassword;
    private Settings settings;
    private boolean boolReg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        initButtons();

        restoreActivityState(savedInstanceState);
    }

    private void restoreActivityState(Bundle state) {
        if (state != null) {
            boolReg = state.getBoolean(REG);
            if (boolReg)
                switchToReg();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(REG, boolReg);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (boolReg) {
            LoginActivity.this.setTitle(getResources().getString(R.string.authorization));
            bForgetMe.setVisibility(View.VISIBLE);
            bRestorePassword.setVisibility(View.VISIBLE);
            bRegistration.setVisibility(View.VISIBLE);
            boolReg = false;
        } else
            super.onBackPressed();
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
        findViewById(R.id.bRegistration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolReg = true;
                switchToReg();
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

    private void switchToReg() {
        LoginActivity.this.setTitle(getResources().getString(R.string.registration));
        bForgetMe.setVisibility(View.GONE);
        bRestorePassword.setVisibility(View.GONE);
        bRegistration.setVisibility(View.GONE);
    }

    private void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        settings = new Settings(LoginActivity.this);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etLogin.setText(settings.getLogin());
        etPassword.setText(settings.getPassword());
        bForgetMe = findViewById(R.id.bForgetMe);
        bRestorePassword = findViewById(R.id.bRestorePassword);
        bRegistration = findViewById(R.id.bRegistration);
    }

}
