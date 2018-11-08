package com.udacity.gradle.builditbigger.IdlingResorces;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;

/**
 * Created by ASassa on 13.03.2018.
 */

public class TestingIdlingResources implements IdlingResource {

    @Nullable
    private ResourceCallback mCallback;
    AtomicBoolean isIdle = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIdle.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

    public void setIdleState(boolean idleState)
    {
        isIdle.set(idleState);
        if (idleState && mCallback != null)
        {
            mCallback.onTransitionToIdle();
        }
    }
}
