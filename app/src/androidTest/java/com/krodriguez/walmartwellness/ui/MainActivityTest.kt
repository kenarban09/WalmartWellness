package com.krodriguez.walmartwellness.ui

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.krodriguez.walmartwellness.R
import com.krodriguez.walmartwellness.ui.list.CountriesFragment
import com.krodriguez.walmartwellness.ui.util.launchFragmentInHiltContainer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testNavigationToInListFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<CountriesFragment, MainActivity> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.app_nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        assertEquals(navController.currentDestination?.id, R.id.nav_list)
    }
}