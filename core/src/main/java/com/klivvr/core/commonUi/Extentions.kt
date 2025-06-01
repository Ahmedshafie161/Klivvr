package com.klivvr.core.commonUi

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

fun Context.navToGoogleMaps( latitude: Double, longitude: Double) {
    try {
        val gmmIntentUri = Uri.parse("geo:0,0?q=$latitude,$longitude")

        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")

        if (mapIntent.resolveActivity(this.packageManager) != null) {
            this.startActivity(mapIntent)
        } else {
            val webIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps?q=$latitude,$longitude")
            )
            this.startActivity(webIntent)
        }
    } catch (e: Exception) {
        Toast.makeText(this, "Error opening maps", Toast.LENGTH_SHORT).show()
    }
}