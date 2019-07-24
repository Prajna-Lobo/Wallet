package com.coffeebeans.mywallet.ui;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.coffeebeans.mywallet.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);


    @Before
    public void setup() {
        activityRule.launchActivity(null);
    }

    @Test
    public void TestNumberOfRows(){
        onView(withId(R.id.rv_transaction)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_transaction)).check(matches(hasChildCount(4)));
    }

    @Test
    public void TestViews() {

        onData(withId(R.id.tv_date)).inAdapterView(withId(R.id.rv_transaction)).atPosition(0).check(matches(isDisplayed()));
        onData(withId(R.id.tv_type)).inAdapterView(withId(R.id.rv_transaction)).atPosition(0)
                .check(matches(isDisplayed()));
        onData(withId(R.id.tv_amount)).inAdapterView(withId(R.id.rv_transaction)).atPosition(0)
                .check(matches(isDisplayed()));
        onData(withId(R.id.tv_description)).inAdapterView(withId(R.id.rv_transaction)).atPosition(0)
                .check(matches(isDisplayed()));
    }


}