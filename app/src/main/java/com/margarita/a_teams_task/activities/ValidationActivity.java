package com.margarita.a_teams_task.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.margarita.a_teams_task.R;
import com.margarita.a_teams_task.models.Validation;

public class ValidationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        TextView tvValidation = (TextView) findViewById(R.id.tvValidation);
        String info = getIntent().getStringExtra(Validation.CLASS_NAME);
        tvValidation.setText(info);
    }
}
