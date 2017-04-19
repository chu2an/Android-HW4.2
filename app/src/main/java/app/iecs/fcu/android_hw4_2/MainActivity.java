package app.iecs.fcu.android_hw4_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView theDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theDisplay = (TextView)findViewById(R.id.textView_show);

        Intent myIntent = getIntent();
        String message = myIntent.getStringExtra("Name");

        theDisplay.setText("Hello "+message);

    }

    protected void onResume(Bundle savedInstanceState){
        theDisplay = (TextView)findViewById(R.id.textView_show);

        Intent myIntent = getIntent();
        String message = myIntent.getStringExtra("Name");

        theDisplay.setText("Hello "+message);
    }
}
