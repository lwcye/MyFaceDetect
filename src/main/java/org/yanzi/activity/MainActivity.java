package org.yanzi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.yanzi.playcamera.R;

public class MainActivity extends Activity {
    EditText et_time;
    EditText et_confidence;
    Button btn;

    public static int time = 30;
    public static int confidence = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_time = (EditText) findViewById(R.id.et_time);
        et_confidence = (EditText) findViewById(R.id.et_confidence);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    time = Integer.parseInt(et_time.getText().toString().trim());
                    confidence = Integer.parseInt(et_confidence.getText().toString().trim());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });
    }
}
