package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


public class MainActivity extends ActionBarActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    private InterstitialAd ad;

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
                new EndpointsAsyncTask().execute(MainActivity.this);
            }
            @Override
            public void onAdOpened() {
                AsyncTask<Context, Void, String> asyncTask = new EndpointsAsyncTask() {
                    @Override
                    protected void onPostExecute(String result) {
                        try {
                            Thread.sleep(10000);
                        }
                        catch (InterruptedException e) {
                            Log.e(LOG_TAG, e.getMessage());
                        }

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
        // Display an interstitial ad before demanded joke
        requestNewAd();
    }
}
