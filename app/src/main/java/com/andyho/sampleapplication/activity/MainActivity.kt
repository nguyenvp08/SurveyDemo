package com.andyho.sampleapplication.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.andyho.sampleapplication.R
import com.andyho.sampleapplication.fragment.MainFragment

class MainActivity : AppCompatActivity() {

    private var fragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        if (supportFragmentManager.findFragmentByTag(MainFragment::class.simpleName) == null) {
            MainFragment.getInstance().also { aFragment ->
                fragment = aFragment
                supportFragmentManager.beginTransaction()
                    .add(R.id.contentMain, aFragment, MainFragment::class.simpleName)
                    .commit()
            }
        } else {
            supportFragmentManager.findFragmentByTag(MainFragment::class.simpleName)?. let { it ->
                fragment = it as MainFragment?
                supportFragmentManager.beginTransaction()
                    .show(it)
                    .commit()
            }
        }
    }
}