package com.shubham.myfamily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomBar.setOnItemSelectedListener {
            if(it.itemId == R.id.nav_guard){
                inflateFragment(GuardFragment.newInstance())
            }
            else if(it.itemId == R.id.nav_home) {
                inflateFragment(HomeFragment.newInstance())
            }
            true
        }
    }

    private fun inflateFragment(newInstance:Fragment) {
        val transition =  supportFragmentManager.beginTransaction()
        transition.replace(R.id.container,newInstance)
        transition.commit()
    }
}