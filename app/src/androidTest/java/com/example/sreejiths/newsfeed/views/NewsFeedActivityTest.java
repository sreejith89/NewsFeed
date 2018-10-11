package com.example.sreejiths.newsfeed.views;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.sreejiths.newsfeed.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

public class NewsFeedActivityTest {

    @Rule
    public ActivityTestRule<NewsFeedActivity> newsFeedActivityActivityTestRule = new ActivityTestRule<NewsFeedActivity>(NewsFeedActivity.class);

    private NewsFeedActivity newsFeedActivity = null;


    @Before
    public void setUp() throws Exception {
        newsFeedActivity = newsFeedActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = newsFeedActivity.findViewById(R.id.recycler_view);
        assertNotNull(view);
    }

    @Test
    public void lastItemNotDisplayed() {
        Espresso.onView(withText("Language")).check(doesNotExist());
    }

    @Test
    public void testActionBarItem(){
        Espresso.onView(withId(R.id.action_refresh)).check(matches(withText("")));
    }

    @After
    public void tearDown() throws Exception {
        newsFeedActivity = null;
    }
}