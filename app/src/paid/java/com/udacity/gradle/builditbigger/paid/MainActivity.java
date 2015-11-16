package com.udacity.gradle.builditbigger.paid;

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

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.R;


/**
 * An activity without ads
 */
public class MainActivity extends ActionBarActivity {

    private static Button button;
    private static ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        AsyncTask<Context, Void, String> asyncTask = new EndpointsAsyncTask() {
            @Override
            protected void onPostExecute(String result) {

                button.setEnabled(true);
                spinner.setVisibility(ProgressBar.GONE);
                super.onPostExecute(result);
            }
        };

        asyncTask.execute(this);
    }

    /**
     * A fragment to offer a joke.
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

            return root;
        }
    }

}
