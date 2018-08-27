package mpip.finki.ukim.mk.lab01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    private static final int EXPLICIT_ACTIVITY_REQUEST_CODE = 1;
    private static final int PICK_PICTURE_REQUEST_CODE = 2;
    private static final String EXTRA_TEXT = "Content send from MainActivity";
    private static final String EXTRA_SUBJECT = "MPIP Send Title";

    private TextView mTextView;
    private Button mTextButton;
    private Button mActivitiesButton;
    private Button mShareButton;
    private Button mPickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivitiesButton = (Button)findViewById(R.id.main_activity_button_activities);
        mActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(ImplicitActivity.newIntent());
            }
        });
        mTextButton = (Button)findViewById(R.id.main_activity_button_text);
        mTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(getApplicationContext(),ExplicitActivity.class),EXPLICIT_ACTIVITY_REQUEST_CODE);
            }
        });
        mShareButton = (Button)findViewById(R.id.main_activity_button_share);
        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.send_chooser_title);
                Intent sendIntent = getImplicitIntent(Intent.ACTION_SEND,title,"text/plain",null);
                sendIntent.putExtra(Intent.EXTRA_TEXT,EXTRA_TEXT);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT,EXTRA_SUBJECT);
                startActivity(sendIntent);
            }
        });
        mPickButton = (Button)findViewById(R.id.main_activity_button_pick);
        mPickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getResources().getString(R.string.pick_chooser_title);
                Intent pickIntent = getImplicitIntent(Intent.ACTION_PICK,title,"image/*",null);
                startActivityForResult(pickIntent,PICK_PICTURE_REQUEST_CODE);
            }
        });
        mTextView = (TextView)findViewById(R.id.main_activity_text_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == EXPLICIT_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                String writtenText = ExplicitActivity.getWrittenText(data);
                mTextView.setText(writtenText);
            }
        }
        else if(requestCode == PICK_PICTURE_REQUEST_CODE){
            if(data!=null) {
                String title = getResources().getString(R.string.view_chooser_title);
                Intent viewIntent = getImplicitIntent(Intent.ACTION_VIEW, title, "image/*", data.getData());
                startActivity(viewIntent);
            }
        }
    }

    private Intent getImplicitIntent(String action,String title,String type,Uri data){
        Intent i = new Intent(action);
        i.setType(type);
        if(data!=null){
            i.setData(data);
        }
        return Intent.createChooser(i,title);
    }
}
