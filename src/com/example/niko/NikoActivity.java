package com.example.niko;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class NikoActivity extends Activity {
    public void toast(String message) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public Credentials getCredentials() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return new Credentials(preferences.getString("email", null), preferences.getString("password", null), preferences.getString("base_url", null));
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
