package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * An activity populated with ads
 */
public class MainActivity extends ActionBarActivity {

    private InterstitialAd ad;
    private static Button button;
    private static ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad = new InterstitialAd(this);
        ad.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        ad.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded () {
                // Display ad immediately
                ad.show();
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Skip the ad
                AsyncTask<Context, Void, String> asyncTask = new EndpointsAsyncTask() {
                    @Override
                    protected void onPostExecute(String result) {

                        button.setEnabled(true);
                        spinner.setVisibility(ProgressBar.GONE);
                        super.onPostExecute(result);
                    }
                };

                asyncTask.execute(MainActivity.this);
            }
            @Override
            public void onAdOpened() {
                AsyncTask<Context, Void, String> asyncTask = new EndpointsAsyncTask() {
                    @Override
                    protected void onPostExecute(String result) {

                        button.setEnabled(true);
                        spinner.setVisibility(ProgressBar.GONE);
                        super.onPostExecute(result);
                    }
                };
                asyncTask.execute(MainActivity.this);
            }
        });
    }

    private void requestNewAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        ad.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view){

        button.setEnabled(false);
        // Make the progress bar visible only during background task
        spinner.setVisibility(ProgressBar.VISIBLE);

        // Display an interstitial ad before demanded joke
        requestNewAd();
    }

    /**
     * A fragment with a banner ad to offer a joke.
     */
    public static class MainActivityFragment extends Fragment {

        public MainActivityFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.fragment_main, container, false);

            button = (Button) root.findViewById(R.id.launcher);
            spinner = (ProgressBar) root.findViewById(R.id.progressBar);

            AdView mAdView = (AdView) root.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);

            return root;
        }
    }
}
