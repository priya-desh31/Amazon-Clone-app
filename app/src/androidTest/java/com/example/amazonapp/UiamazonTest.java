package com.example.amazonapp;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UiamazonTest {

    @Rule
    public ActivityScenarioRule<SplashScreen> mActivityScenarioRule =
            new ActivityScenarioRule<>(SplashScreen.class);

    @Test
    public void uiamazonTest() {
        ViewInteraction materialRadioButton = onView(
                allOf(withId(R.id.bottomAddproduct), withText("Add Product"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                1),
                        isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.addProductName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText.perform(scrollTo(), replaceText("smart"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText4.perform(scrollTo(), replaceText("smarpt"));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.addProductName), withText("smarpt"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.addProductName), withText("smarpt"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.addProductName), withText("smarpt"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText7.perform(scrollTo(), replaceText("smatphone"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.addProductName), withText("smatphone"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.addProductPrice),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatEditText9.perform(scrollTo(), replaceText("200000\n"), closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.addProductDesc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText10.perform(scrollTo(), replaceText("smar"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.addProductCategory),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatEditText11.perform(scrollTo(), replaceText("smartpho"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.addProductDesc), withText("smar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText12.perform(scrollTo(), replaceText("smartphone"));

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.addProductDesc), withText("smartphone"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText13.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.addProductCategory), withText("smartpho"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatEditText14.perform(scrollTo(), replaceText("smartphone\n"));

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.addProductCategory), withText("smartphone\n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8),
                        isDisplayed()));
        appCompatEditText15.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction materialTextView = onView(
                allOf(withId(R.id.confirmAddProd), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        materialTextView.perform(scrollTo(), click());

        ViewInteraction materialTextView2 = onView(
                allOf(withId(R.id.confirmAddProd), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        materialTextView2.perform(scrollTo(), click());

        ViewInteraction materialRadioButton2 = onView(
                allOf(withId(R.id.bottomAddproduct), withText("Add Product"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                1),
                        isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction materialTextView3 = onView(
                allOf(withId(R.id.confirmAddProd), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        materialTextView3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.addProductName),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText16.perform(scrollTo(), replaceText("smart"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText17.perform(scrollTo(), click());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText18.perform(scrollTo(), click());

        ViewInteraction appCompatEditText19 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText19.perform(scrollTo(), click());

        ViewInteraction appCompatEditText20 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText20.perform(scrollTo(), click());

        ViewInteraction appCompatEditText21 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText21.perform(scrollTo(), click());

        ViewInteraction appCompatEditText22 = onView(
                allOf(withId(R.id.addProductName), withText("smart"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatEditText22.perform(scrollTo(), replaceText("smartphone"));

        ViewInteraction appCompatEditText23 = onView(
                allOf(withId(R.id.addProductName), withText("smartphone"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText23.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText24 = onView(
                allOf(withId(R.id.addProductPrice),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        appCompatEditText24.perform(scrollTo(), replaceText("20000\n"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText25 = onView(
                allOf(withId(R.id.addProductDesc),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText25.perform(scrollTo(), replaceText("smartphone"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText26 = onView(
                allOf(withId(R.id.addProductDesc), withText("smartphone"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatEditText26.perform(scrollTo(), replaceText("smartphone\n\n"));

        ViewInteraction appCompatEditText27 = onView(
                allOf(withId(R.id.addProductDesc), withText("smartphone\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6),
                        isDisplayed()));
        appCompatEditText27.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText28 = onView(
                allOf(withId(R.id.addProductCategory),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                8)));
        appCompatEditText28.perform(scrollTo(), replaceText("smartphone"), closeSoftKeyboard());

        pressBack();

        ViewInteraction materialTextView4 = onView(
                allOf(withId(R.id.confirmAddProd), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        materialTextView4.perform(scrollTo(), click());

        ViewInteraction materialTextView5 = onView(
                allOf(withId(R.id.confirmAddProd), withText("Confirm"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        materialTextView5.perform(scrollTo(), click());

        ViewInteraction materialRadioButton3 = onView(
                allOf(withId(R.id.bottomAddproduct), withText("Add Product"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                1),
                        isDisplayed()));
        materialRadioButton3.perform(click());

        ViewInteraction materialRadioButton4 = onView(
                allOf(withId(R.id.bottomHome), withText("Home"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                0),
                        isDisplayed()));
        materialRadioButton4.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.productslist),
                        childAtPosition(
                                withId(R.id.home_layout),
                                5)))
                .atPosition(0);
        linearLayout.perform(click());

        pressBack();

        ViewInteraction materialRadioButton5 = onView(
                allOf(withId(R.id.bottomsearch), withText("Search"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                2),
                        isDisplayed()));
        materialRadioButton5.perform(click());

        ViewInteraction materialRadioButton6 = onView(
                allOf(withId(R.id.bottomMycart), withText("My Cart"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                3),
                        isDisplayed()));
        materialRadioButton6.perform(click());

        ViewInteraction appCompatEditText29 = onView(
                allOf(withId(R.id.shipname),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText29.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatEditText30 = onView(
                allOf(withId(R.id.shipname), withText("a"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText30.perform(click());

        ViewInteraction appCompatEditText31 = onView(
                allOf(withId(R.id.shipname), withText("a"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText31.perform(replaceText("Pri"));

        ViewInteraction appCompatEditText32 = onView(
                allOf(withId(R.id.shipname), withText("Pri"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText32.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText33 = onView(
                allOf(withId(R.id.shipphone),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText33.perform(replaceText("708"), closeSoftKeyboard());

        ViewInteraction appCompatEditText34 = onView(
                allOf(withId(R.id.shipname), withText("Pri"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText34.perform(replaceText("Priyanka"));

        ViewInteraction appCompatEditText35 = onView(
                allOf(withId(R.id.shipname), withText("Priyanka"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatEditText35.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText36 = onView(
                allOf(withId(R.id.shipphone), withText("708"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText36.perform(replaceText("7083912"));

        ViewInteraction appCompatEditText37 = onView(
                allOf(withId(R.id.shipphone), withText("7083912"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText37.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText38 = onView(
                allOf(withId(R.id.shipaddress),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                3),
                        isDisplayed()));
        appCompatEditText38.perform(replaceText("US"), closeSoftKeyboard());

        ViewInteraction appCompatEditText39 = onView(
                allOf(withId(R.id.shipcity),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatEditText39.perform(replaceText("US"), closeSoftKeyboard());

        ViewInteraction appCompatEditText40 = onView(
                allOf(withId(R.id.shipphone), withText("7083912"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText40.perform(replaceText("7083912349"));

        ViewInteraction appCompatEditText41 = onView(
                allOf(withId(R.id.shipphone), withText("7083912349"),
                        childAtPosition(
                                allOf(withId(R.id.linealLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                1)),
                                2),
                        isDisplayed()));
        appCompatEditText41.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.confirmorder), withText("PLACE ORDER"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        2),
                                2),
                        isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        pressBack();

        pressBack();

        ViewInteraction materialRadioButton7 = onView(
                allOf(withId(R.id.bottomProfile), withText("Profile"),
                        childAtPosition(
                                allOf(withId(R.id.radioGraup1),
                                        childAtPosition(
                                                withId(R.id.bottomNavBar),
                                                0)),
                                4),
                        isDisplayed()));
        materialRadioButton7.perform(click());

        ViewInteraction materialTextView6 = onView(
                allOf(withId(R.id.profilehistory), withText("Your Orders"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                2)));
        materialTextView6.perform(scrollTo(), click());

        pressBack();

        ViewInteraction materialTextView7 = onView(
                allOf(withId(R.id.profilelogout), withText("Logout"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        7),
                                0)));
        materialTextView7.perform(scrollTo(), click());

        ViewInteraction materialTextView8 = onView(
                allOf(withId(R.id.yesButton), withText("YES"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1),
                        isDisplayed()));
        materialTextView8.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
