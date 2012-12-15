package com.example.niko;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class SettingsActivity extends NikoActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Populate username from preferences
        SharedPreferences preferences = getSharedPreferences("nikoniko", 0);
        String username = preferences.getString("username", null);
        if (username != null) {
            EditText viewById = (EditText) findViewById(R.id.username);
            viewById.setText(username);
        }

        String url = preferences.getString("url", null);
        if (url != null) {
            EditText viewById = (EditText) findViewById(R.id.url);
            viewById.setText(url);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        return true;
    }

    public void saveSettings(View view) {
        // Hide keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);


        // Save settings
        EditText passwordText = (EditText) findViewById(R.id.password);
        EditText usernameText = (EditText) findViewById(R.id.username);
        EditText urlText = (EditText) findViewById(R.id.url);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String url = urlText.getText().toString();

        setCredentials(new Credentials(username, password, url));

        //TODO: we should verify that the credentials is working.

        toast("Settings has been updated");
    }

}

