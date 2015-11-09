package com.udacity.gradle.builditbigger.test;


import android.test.InstrumentationTestCase;
import android.util.Log;

import com.udacity.gradle.builditbigger.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/*
   {@link EndpointsAsyncTaskTest} tests if {@link EndpointsAsyncTask}
   retrieves non-empty String in a reasonable amount of time
*/
public class EndpointsAsyncTaskTest extends InstrumentationTestCase {

    public void testEndpointsAsyncTask () throws Throwable {

        // This is to let the method know that the task is done
        final CountDownLatch cdl = new CountDownLatch(1);

        /*
            Instead of actually using retrieved String,
            onPostExecute will be used for assertions
         */
        final EndpointsAsyncTask asyncTask = new EndpointsAsyncTask() {

            @Override
            protected void onPostExecute(String result) {

                Log.d(getClass().getSimpleName(), "doInBackground successfully finished");
                assertNotNull(result);
                Log.d(getClass().getSimpleName(), "Pulled joke --> " + result);
                assertFalse(result.equals(""));
                cdl.countDown();
                Log.d(getClass().getSimpleName(), "onPostExecute successfully finished");
            }
        };

        // Execute the async task on the UI thread
        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                asyncTask.execute(getInstrumentation().getContext());
            }
        });

        // Wait if the task retrieves data until specified seconds
        assertTrue(cdl.await(60, TimeUnit.SECONDS));

        // The task is presumably done
    }
}
