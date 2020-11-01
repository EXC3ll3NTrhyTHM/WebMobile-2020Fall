package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("message");
        TextView txtView = findViewById(R.id.summary_text);
        txtView.setText(message);

    }

    public void goToOrder(View view) {
        String mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("Pizza!!!!") +
                "&body=" + Uri.encode(message);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SENDTO);
        sendIntent.setData(Uri.parse(mailto));

        if (sendIntent.resolveActivity(getPackageManager()) !=null){
            startActivity(sendIntent);
        }
    }


}
