package com.udacity.builditbigger.testing;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.core.Is.is;

/**
 * Created by ASassa on 13.03.2018.
 */

@RunWith(AndroidJUnit4.class)
public class JokesAndroidTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResources;

    @Before
    public void registerIdlingResource()
    {
        mIdlingResources = mainActivityActivityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResources);
    }

    @Test
    public void testClick()
    {
        Log.d("ESPRESSO1",String.valueOf(mIdlingResources.isIdleNow()));
        onView(withId(R.id.bt_showJoke)).perform(click());

        onData(anything()).inAdapterView(withId(R.id.lv_jokelist)).atPosition(0)
                .onChildView(withId(R.id.tv_jokeitem))
                .check(matches(hasDescendant(withText("My dog used to chase people on a bike a lot. It got so bad, finally I had to take his bike away."))));
    }

    @After
    public void unregisterIdlingResource()
    {
        if (mIdlingResources!= null)
            Espresso.unregisterIdlingResources(mIdlingResources);

    }

}
