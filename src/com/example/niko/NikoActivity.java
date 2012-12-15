package com.example.niko;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class NikoActivity extends Activity {

    private static final String SHARED_PREFERENCE_NAME = "nikoniko";

    public void toast(String message) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public Credentials getCredentials() {
        SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCE_NAME, 0);
        return new Credentials(preferences.getString("username", null), preferences.getString("password", null), preferences.getString("url", null));
    }

    public void setCredentials(Credentials credentials) {
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFERENCE_NAME, 0).edit();
        editor.putString("username", credentials.getUsername());
        editor.putString("password", credentials.getPassword());
        editor.putString("url", credentials.getUrl());
        editor.commit();
    }

    public class Credentials {
        private String username;
        private String password;
        private String url;

        Credentials(String username, String password, String url) {
            this.username = username;
            this.password = password;
            this.url = url;
        }


        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getUrl() {
            return url;
        }
    }
}
