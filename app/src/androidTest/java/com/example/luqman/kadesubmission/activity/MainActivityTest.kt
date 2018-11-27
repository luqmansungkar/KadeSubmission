package com.example.luqman.kadesubmission.activity


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.runner.RunWith
import android.support.test.espresso.UiController
import android.support.test.espresso.idling.CountingIdlingResource
import org.jetbrains.anko.find
import org.junit.*


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    private lateinit var idlingResource: CountingIdlingResource

    @Before
    fun setUp(){
        idlingResource = CountingIdlingResource("ServerCalls")
        IdlingRegistry.getInstance().register(idlingResource)

    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(idlingResource)
    }
    @Test
    fun mainActivityTest() {
        val textView = onView(
            allOf(
                withId(R.id.largeLabel), withText("Past Match"),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Past Match")))

        val textView2 = onView(
            allOf(
                withId(R.id.smallLabel), withText("Next Match"),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Next Match")))

        val textView3 = onView(
            allOf(
                withId(R.id.smallLabel), withText("Favorites"),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Favorites")))

        onView(withId(R.id.list_event)).check(matches(isDisplayed()))

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        var match_date = mActivityTestRule.activity.find<TextView>(R.id.match_date).text.toString()

        Assert.assertNotEquals(match_date, "")

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        onView(withId(R.id.match_detail_home_image)).check(matches(isDisplayed()))

        onView(withId(R.id.match_detail_home_score)).check(matches(withText(not(""))))

        onView(withId(R.id.match_detail_home_defense)).check(matches(withText(not(""))))

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        onView(withId(R.id.list_event)).check(matches(isDisplayed()))

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        pressBack()

        onView(withId(R.id.list_event)).check(matches(isDisplayed()))

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.next_matches), withContentDescription("Next Match"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.bottom_navigation),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        onView(withId(R.id.list_event)).check(matches(isDisplayed()))

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        match_date = mActivityTestRule.activity.find<TextView>(R.id.match_date).text.toString()

        Assert.assertNotEquals(match_date, "")

        val match_score = mActivityTestRule.activity.find<TextView>(R.id.home_score).text.toString()

        Assert.assertEquals(match_score, "")

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        onView(withId(R.id.match_detail_home_image)).check(matches(isDisplayed()))
        onView(withId(R.id.match_detail_home_score)).check(matches(withText("")))
        onView(withId(R.id.match_detail_home_defense)).check(matches(withText("")))

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())

        onView(withId(R.id.list_event)).check(matches(isDisplayed()))

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        pressBack()

        onView(withId(R.id.list_event)).check(matches(isDisplayed()))
    }

    @Test
    fun dataSaveRemoveTest(){
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Added to favorite")
        )).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Removed from favorite")
        )).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Added to favorite")
        )).check(matches(isDisplayed()))

        val savedTeamName1 = getText(withId(R.id.match_detail_home_team_name))

        pressBack()

        onView(withId(R.id.next_matches)).perform(click())

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Added to favorite")
        )).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Removed from favorite")
        )).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.list_event)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(withId(R.id.list_event)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Added to favorite")
        )).check(matches(isDisplayed()))

        val savedTeamName2 = getText(withId(R.id.match_detail_home_team_name))

        pressBack()

        onView(withId(R.id.favorites)).perform(click())

        onView(withId(R.id.list_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Assert.assertEquals(savedTeamName1, getText(withId(R.id.match_detail_home_team_name)))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Removed from favorite")
        )).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.list_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Assert.assertEquals(savedTeamName2, getText(withId(R.id.match_detail_home_team_name)))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Removed from favorite")
        )).check(matches(isDisplayed()))

        pressBack()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun getText(matcher: Matcher<View>): String {
        var text: String = ""

        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController, view: View) {
                val tv = view as TextView //Save, because of check in getConstraints()
                text = tv.text.toString()
            }
        })

        return text
    }
}