package com.github.seanzor.prefhelper.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.seanzor.prefhelper.SharedPrefHelper;

public class MainActivity extends AppCompatActivity {

    private SharedPrefHelper mPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // As in most cases, create a default shared preference
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Create the helper which will wrap the SharedPreferences we just created
        mPref = new SharedPrefHelper(getResources(), defaultSharedPreferences);

        // Sample for number of times the app ran, deafult of 0
        int numberOfTimesRan = mPref.getInt(R.string.pref_number_of_runs, 0);
        numberOfTimesRan++;

        // Now apply the change to be persistent
        mPref.applyInt(R.string.pref_number_of_runs, numberOfTimesRan);

        // Display the result
        ((TextView)findViewById(R.id.activityMainResultField)).setText(
                String.valueOf(numberOfTimesRan));
    }
}