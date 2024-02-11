package com.shubham.myfamily

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val permission = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
    )
    val permissionCode = 78
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askForPermission()
        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    inflateFragment(HomeFragment.newInstance())
                }

                R.id.nav_dashboard -> {
                    inflateFragment(MapsFragment())
                }

                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }

                R.id.nav_profile -> {
                    inflateFragment(ProfileFragment.newInstance())
                }
            }
            true
        }
        bottomBar.selectedItemId = R.id.nav_home
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(this, permission, permissionCode)
    }

    private fun inflateFragment(newInstance: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.container, newInstance)
        transition.commit()
    }

    private fun allPermissionGranted(): Boolean {
        for (item in permission) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    item
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == permissionCode) {
            if (allPermissionGranted()){
                openCamera()
            }
        }
    }

    private fun openCamera() {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        startActivity(intent)
    }
}