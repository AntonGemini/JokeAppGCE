package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.IdlingResorces.TestingIdlingResources;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.udacity.gradle.builditbigger.backend.myApi.model.Joke;
import com.udacity.jokepresenter.JokeActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    private static MyApi myApiService = null;

    @Nullable private TestingIdlingResources idlingResources;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.pb_spinner);
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

    public void tellJoke(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        if (idlingResources!=null) {
            idlingResources.setIdleState(false);
        }
        new EndpointsAsyncTask().execute(this);
    }


    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource()
    {
        if (idlingResources == null)
        {
            return new TestingIdlingResources();
        }
        return idlingResources;
    }

    class EndpointsAsyncTask extends AsyncTask<Context, Void, List<Joke>> {

        private Context context;
        @Override
        protected List<Joke> doInBackground(Context... params) {
            if(myApiService == null) {  // Only do this once
                MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                        new AndroidJsonFactory(), null)
                        // options for running against local devappserver
                        // - 10.0.2.2 is localhost's IP address in Android emulator
                        // - turn off compression when running against local devappserver
                        .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
                // end options for devappserver

                myApiService = builder.build();
            }

            context = params[0];

            try {
                return myApiService.getJokes().execute().getItems();
            } catch (IOException e) {
               return null;
            }
        }

        @Override
        protected void onPostExecute(List<Joke> result) {
            Intent intent = new Intent(context,JokeActivity.class);
            ArrayList<String> jokesStrings = new ArrayList<>();
            for (int i = 0; i < result.size(); i++)
            {
                jokesStrings.add(result.get(i).getJokeText());
            }
            intent.putStringArrayListExtra("jokes", jokesStrings);
            if (idlingResources!=null) {
                idlingResources.setIdleState(true);
            }
            mProgressBar.setVisibility(View.INVISIBLE);
            startActivity(intent);
        }
    }
}


