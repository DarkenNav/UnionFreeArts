package ru.unionfreeart.ufart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.utils.Const;
import ru.unionfreeart.ufart.utils.Settings;

public class LoginActivity extends AppCompatActivity implements InputDialog.Result {
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
        findViewById(R.id.bAddress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog dialog = new InputDialog(LoginActivity.this);
                dialog.setResult(LoginActivity.this);
                dialog.show(settings.getAddress());
            }
        });
        findViewById(R.id.bEnter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = etLogin.getText().toString();
                settings.setLogin(login);
                settings.setPassword(etPassword.getText().toString());
                if (login.equals("user"))
                    MainActivity.open(LoginActivity.this, true);
                else if (login.equals("admin"))
                    MainActivity.open(LoginActivity.this, false);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    private void initUI() {
        LoginActivity.this.setTitle(getResources().getString(R.string.authorization));

        settings = new Settings(LoginActivity.this);

        etLogin = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etLogin.setText(settings.getLogin());
        etPassword.setText(settings.getPassword());
    }

    @Override
    public void putString(int action, String input) {
        if (action == Const.CANCEL)
            return;
        settings.setAddress(input);
    }
}
