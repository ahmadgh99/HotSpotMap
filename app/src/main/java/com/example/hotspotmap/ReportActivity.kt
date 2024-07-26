package com.example.hotspotmap

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val reportTypeSpinner = findViewById<Spinner>(R.id.report_type_spinner)
        val locationEditText = findViewById<EditText>(R.id.location_edit_text)
        val addCurrentLocationButton = findViewById<Button>(R.id.add_current_location_button)
        val addReportButton = findViewById<Button>(R.id.add_report_button)

        // Populate the spinner directly
        val reportTypes = arrayOf("Fire Hazard", "Flood", "Earthquake", "Terrorist Attack")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, reportTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        reportTypeSpinner.adapter = adapter

        addCurrentLocationButton.setOnClickListener {
            // Handle adding the current location
            Toast.makeText(this, "Current location added", Toast.LENGTH_SHORT).show()
        }

        addReportButton.setOnClickListener {
            val reportType = reportTypeSpinner.selectedItem.toString()
            val location = locationEditText.text.toString()

            // Handle adding the report
            Toast.makeText(this, "Report added: $reportType at $location", Toast.LENGTH_SHORT).show()

            // Redirect to MainMap activity
            val intent = Intent(this, MainMap::class.java)
            startActivity(intent)
            finish() // Optionally finish this activity if you don't want to return to it
        }
    }
}
