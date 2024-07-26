package com.example.hotspotmap

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.MapStyleOptions
import android.widget.ImageView
import android.widget.Toast

class MainMap : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var ProfileButton:ImageView
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_map)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Set click listener for the add location button
        val addLocationButton = findViewById<ImageView>(R.id.add_location_button)
        addLocationButton.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }

        ProfileButton = findViewById(R.id.profile_icon)
        ProfileButton.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Apply custom map style
        try {
            val success = mMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    this, R.raw.map_style
                )
            )
            if (!success) {
                // Log error if style parsing fails
            }
        } catch (e: Resources.NotFoundException) {
            // Handle the exception
        }

        // Enable UI controls
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true

        // Check for location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        } else {
            // Request location permission
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        }

        // Add a fire hazard marker with custom icon
        val fireHazardLocation = LatLng(32.0853, 34.7818)
        val fireMarker = mMap.addMarker(
            MarkerOptions()
                .position(fireHazardLocation)
                .title("Fire Hazard")
                .snippet("Details about the fire hazard")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmapMaintainingAspectRatio(R.drawable.fire_logo, 100, 100)))
        )

        // Add a flood hazard marker with custom icon
        val floodHazardLocation = LatLng(32.0753, 34.7818) // Adjust the coordinates as needed
        val floodMarker = mMap.addMarker(
            MarkerOptions()
                .position(floodHazardLocation)
                .title("Flood Hazard")
                .snippet("There is a flood in the area")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmapMaintainingAspectRatio(R.drawable.flood_logo, 100, 100)))
        )

        // Add an earthquake hazard marker with custom icon
        val earthquakeHazardLocation = LatLng(32.0653, 34.7818) // Adjust the coordinates as needed
        val earthquakeMarker = mMap.addMarker(
            MarkerOptions()
                .position(earthquakeHazardLocation)
                .title("Earthquake Hazard")
                .snippet("There is an earthquake in the area")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeBitmapMaintainingAspectRatio(R.drawable.earthquake_logo, 100, 100)))
        )

        // Set a marker click listener to show the InfoWindow
        mMap.setOnMarkerClickListener { marker ->
            when (marker) {
                fireMarker -> {
                    marker.showInfoWindow()
                    true // Return true to indicate the click has been handled
                }
                floodMarker -> {
                    marker.showInfoWindow()
                    true // Return true to indicate the click has been handled
                }
                earthquakeMarker -> {
                    marker.showInfoWindow()
                    true // Return true to indicate the click has been handled
                }
                else -> false // Return false to let the default behavior occur
            }
        }

        // Add other markers
        val marker1 = LatLng(32.092, 34.774)
        mMap.addMarker(
            MarkerOptions()
                .position(marker1)
                .title("5.0Km Away")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )

        val marker2 = LatLng(32.080, 34.770)
        mMap.addMarker(
            MarkerOptions()
                .position(marker2)
                .title("9.8Km Away")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        )

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(fireHazardLocation, 12f))
    }

    private fun resizeBitmapMaintainingAspectRatio(drawableRes: Int, targetWidth: Int, targetHeight: Int): Bitmap {
        val imageBitmap = BitmapFactory.decodeResource(resources, drawableRes)
        val width = imageBitmap.width
        val height = imageBitmap.height

        val aspectRatio = width.toFloat() / height.toFloat()

        val scaledWidth: Int
        val scaledHeight: Int

        if (width > height) {
            scaledWidth = targetWidth
            scaledHeight = (targetWidth / aspectRatio).toInt()
        } else {
            scaledHeight = targetHeight
            scaledWidth = (targetHeight * aspectRatio).toInt()
        }

        return Bitmap.createScaledBitmap(imageBitmap, scaledWidth, scaledHeight, false)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.isMyLocationEnabled = true
                }
            } else {
                // Permission was denied
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
            return
        }
    }
}
