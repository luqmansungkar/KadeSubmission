package com.example.luqman.kadesubmission.util

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource


object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = CountingIdlingResource(RESOURCE)

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

}