package mpip.finki.ukim.mk.lab01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExplicitActivity extends AppCompatActivity {

    private Button mOkayButton;
    private Button mCloseButton;
    private EditText mEditText;
    private String mWrittenText = "";
    private static final String EXTRA_TEXT = "mpip.finki.ukim.mk.lab01.text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);
        mOkayButton = (Button)findViewById(R.id.explicit_activity_button_okay);
        mOkayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra(EXTRA_TEXT,mWrittenText);
                setResult(RESULT_OK,data);
                finish();
            }
        });
        mCloseButton = (Button)findViewById(R.id.explicit_activity_button_close);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        mEditText = (EditText)findViewById(R.id.explicit_activity_edit_text);
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWrittenText = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static String getWrittenText(Intent data){
        String text = data.getStringExtra(EXTRA_TEXT);
        return text;
    }
}
