package com.krodriguez.walmartwellness.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

object ToolbarExtension {
    fun setupToolbar(
        context: Activity, toolbar: Toolbar, title: String, isBack: Boolean
    ) {
        (context as AppCompatActivity).setSupportActionBar(toolbar)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(isBack)
        context.supportActionBar?.title = title
        toolbar.setNavigationOnClickListener {
            context.onBackPressed()
        }
    }
}