package ru.unionfreeart.ufart.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import ru.unionfreeart.ufart.R;
import ru.unionfreeart.ufart.utils.Const;

/**
 * Created by NeoSvet on 08.05.2017.
 */

public class InputDialog extends Dialog {
    private Activity activity;
    private EditText etInput;
    private Result result;
    private String string = "";
    private int action = Const.ADD;
    private boolean boolCancel = true;

    public InputDialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_input);

        initInterface();
        showKeyboard();
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    private void initInterface() {
        etInput = (EditText) findViewById(R.id.etInput);
        etInput.setText(string);
        findViewById(R.id.bCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog.this.dismiss();
            }
        });
        findViewById(R.id.bOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etInput.getText().length() > 0) {
                    result.putString(action, etInput.getText().toString());
                    boolCancel = false;
                }
                InputDialog.this.dismiss();
            }
        });
    }

    public void show(String string) {
        action = Const.EDIT;
        this.string = string;
        super.show();
    }

    @Override
    public void dismiss() {
        if (boolCancel)
            result.putString(0, "");
        super.dismiss();
    }

    public interface Result {
        void putString(int action, String input);
    }
}
