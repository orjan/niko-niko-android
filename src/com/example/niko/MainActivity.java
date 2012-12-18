package com.example.niko;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.niko.api.AddRatingRequest;
import com.example.niko.api.NikoNikoApi;
import com.example.niko.api.Response;

public class MainActivity extends NikoActivity {
    private static final String TAG = "MainActivity";

    /**
     * When starting this activity, the invoking Intent can contain this extra
     * string to specify which fragment should be initially displayed.
     */
    public static final String EXTRA_SHOW_FRAGMENT = ":android:show_fragment";

    /**
     * When starting this activity, the invoking Intent can contain this extra
     * boolean that the header list should not be displayed.  This is most often
     * used in conjunction with {@link #EXTRA_SHOW_FRAGMENT} to launch
     * the activity to display a specific fragment that the user has navigated
     * to.
     */
    public static final String EXTRA_NO_HEADERS = ":android:no_headers";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra( MainActivity.EXTRA_SHOW_FRAGMENT, SettingsActivity.Prefs1Fragment.class.getName() );
        intent.putExtra( MainActivity.EXTRA_NO_HEADERS, true );

        startActivity(intent);
        return true;

        /*switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, SettingsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }

    public void submitHappy(View view) {
        submitNikoNikoStatus(1);
    }

    public void submitIndifferent(View view) {
        submitNikoNikoStatus(0);
    }

    public void submitSad(View view) {
        submitNikoNikoStatus(-1);
    }

    private void submitNikoNikoStatus(int rating) {
        AsyncTask<AddRatingRequest, Void, Response> asyncTask = new AsyncTask<AddRatingRequest, Void, Response>() {
            @Override
            protected Response doInBackground(AddRatingRequest... addRatingRequests) {
                Credentials credentials = getCredentials();
                NikoNikoApi nikoNikoApi = new NikoNikoApi(credentials.getUrl(), credentials.getUsername(), credentials.getPassword());
                return nikoNikoApi.postRating(addRatingRequests[0]);
            }

            @Override
            protected void onPostExecute(Response response) {
                Exception exception = response.getException();
                if (exception != null) {
                    toast(exception.getMessage());
                } else {
                    toast("We have received your NikoNiko for today");
                }
            }
        };

        asyncTask.execute(new AddRatingRequest(rating));


    }
}
