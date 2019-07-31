package com.coffeebeans.mywallet.ui;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.coffeebeans.mywallet.EspressoDaggerMockRule;
import com.coffeebeans.mywallet.R;
import com.coffeebeans.mywallet.data.Transaction;
import com.coffeebeans.mywallet.di.TransactionModule;
import com.coffeebeans.mywallet.domain.GetUserTransactionsUseCase;
import com.coffeebeans.mywallet.testHelper.RecyclerViewMatcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Collections;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private Transaction testTransaction = new Transaction(1L, "31-07-2019", "50", "Mock transaction", "credit");

    @Mock
    GetUserTransactionsUseCase transactionsUseCase;

    @Rule
    public EspressoDaggerMockRule daggerMockRule = new EspressoDaggerMockRule(new TransactionModule());


    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setup() {
        when(transactionsUseCase.getTransaction()).thenReturn(Collections.singletonList(testTransaction));
        daggerMockRule.provides(GetUserTransactionsUseCase.class, transactionsUseCase);
        activityRule.launchActivity(null);
    }

    @Test
    public void TestNumberOfRows() {
        onView(withId(R.id.rv_transaction)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_transaction)).check(matches(hasChildCount(1)));
    }

    @Test
    public void TestViews() {
        onView(withRecyclerView(R.id.rv_transaction).atPositionOnView(0, R.id.tv_date))
                .check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.rv_transaction).atPositionOnView(0, R.id.tv_amount))
                .check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.rv_transaction).atPositionOnView(0, R.id.tv_description))
                .check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.rv_transaction).atPositionOnView(0, R.id.tv_type))
                .check(matches(isDisplayed()));
    }

    @Test
    public void TestDataOfRecyclerViews() {
        RecyclerViewMatcher recyclerViewMatcher = withRecyclerView(R.id.rv_transaction);
        onView(recyclerViewMatcher.atPositionOnView(0, R.id.tv_description)).check(matches(withText("Mock transaction")));
        onView(recyclerViewMatcher.atPositionOnView(0, R.id.tv_amount)).check(matches(withText("50")));
        onView(recyclerViewMatcher.atPositionOnView(0, R.id.tv_date)).check(matches(withText("31-07-2019")));
        onView(recyclerViewMatcher.atPositionOnView(0, R.id.tv_type)).check(matches(withText("credit")));

    }

    // Convenience helper
    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }
}