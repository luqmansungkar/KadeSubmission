package com.example.luqman.kadesubmission.activity


import android.support.test.espresso.Espresso.*
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
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import org.jetbrains.anko.find
import org.junit.*


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
    @Test
    fun matchFragmentTest() {

        onView(allOf(withId(R.id.league_spinner), isDisplayed())).perform(click())
        onData(allOf(`is`("Spanish La Liga"))).perform(click())

        val visibleListEvent = allOf(withId(R.id.list_event), isDisplayed())

        onView(visibleListEvent).check(matches(isDisplayed()))

        onView(visibleListEvent).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        var matchDate = mActivityTestRule.activity.find<TextView>(R.id.match_date).text.toString()

        Assert.assertNotEquals(matchDate, "")

        onView(visibleListEvent).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(visibleListEvent).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

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

        onView(visibleListEvent).check(matches(isDisplayed()))

        onView(visibleListEvent).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(visibleListEvent).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        pressBack()

        onView(visibleListEvent).check(matches(isDisplayed()))

        val nextMatchTab = onView(
            allOf(
                withContentDescription("Next Match"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_match),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        nextMatchTab.perform(click())

        onView(allOf(withId(R.id.league_spinner), isDisplayed())).perform(click())
        onData(allOf(`is`("Spanish La Liga"))).perform(click())

        onView(visibleListEvent).check(matches(isDisplayed()))

        onView(visibleListEvent).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(visibleListEvent).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

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

        onView(visibleListEvent).check(matches(isDisplayed()))

        onView(visibleListEvent).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(visibleListEvent).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        pressBack()

        onView(visibleListEvent).check(matches(isDisplayed()))
    }

    @Test
    fun teamFragmentTest(){
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.teams),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        onView(allOf(withId(R.id.team_spinner), isDisplayed())).perform(click())
        onData(allOf(`is`("Spanish La Liga"))).perform(click())

        val visibleListTeam = allOf(withId(R.id.list_team), isDisplayed())

        onView(visibleListTeam).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(visibleListTeam).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.team_detail_logo)).check(matches(isDisplayed()))
        onView(withId(R.id.team_detail_stadium)).check(matches(isDisplayed()))

        onView(withId(R.id.team_overview_desc)).check(matches(isDisplayed()))

        val playerListTab = onView(
            allOf(
                withContentDescription("PLAYERS"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.team_detail_tab),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        playerListTab.perform(click())

        val visibleListPlayer = allOf(withId(R.id.list_player), isDisplayed())

        onView(visibleListPlayer).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(3))
        onView(visibleListPlayer).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        onView(withId(R.id.player_weight)).check(matches(allOf(isDisplayed(), withText(not("")))))

        pressBack()

        onView(visibleListPlayer).check(matches(isDisplayed()))

        pressBack()

        onView(visibleListTeam).check(matches(isDisplayed()))
    }

    @Test
    fun dataSaveRemoveTest(){
        onView(allOf(withId(R.id.league_spinner), isDisplayed())).perform(click())
        onData(allOf(`is`("Spanish La Liga"))).perform(click())

        val visibleListEvent = allOf(withId(R.id.list_event), isDisplayed())


        onView(visibleListEvent).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(visibleListEvent).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Added to favorite")
        )).check(matches(isDisplayed()))

        val savedTeamName1 = getText(withId(R.id.match_detail_home_team_name))

        pressBack()

        onView(withId(R.id.teams)).perform(click())

        onView(allOf(withId(R.id.team_spinner), isDisplayed())).perform(click())
        onData(allOf(`is`("Spanish La Liga"))).perform(click())

        val visibleListTeam = allOf(withId(R.id.list_team), isDisplayed())

        onView(visibleListTeam).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
        onView(visibleListTeam).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Added to favorite")
        )).check(matches(isDisplayed()))

        val savedTeamName2 = getText(withId(R.id.team_detail_name))

        pressBack()

        onView(withId(R.id.favorites)).perform(click())

        val visibleListFavorite = allOf(withId(R.id.list_favorite), isDisplayed())

        onView(visibleListFavorite).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Assert.assertEquals(savedTeamName1, getText(withId(R.id.match_detail_home_team_name)))

        onView(withId(R.id.add_to_favorite)).perform(click())
        onView(allOf(
            withId(R.id.snackbar_text),
            withText("Removed from favorite")
        )).check(matches(isDisplayed()))

        pressBack()

        val teamFavoriteTab = onView(
            allOf(
                withContentDescription("TEAMS"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_match),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )

        teamFavoriteTab.perform(click())

        onView(visibleListFavorite).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Assert.assertEquals(savedTeamName2, getText(withId(R.id.team_detail_name)))

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