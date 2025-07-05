package com.example.medibot_afrika

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.fhir.DatabaseErrorStrategy
import com.google.android.fhir.FhirEngineConfiguration
import com.google.android.fhir.FhirEngineProvider
import com.google.android.fhir.ServerConfiguration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<Button>(R.id.buttonOpenPatientList)
        button.setOnClickListener {
            val intent = Intent(this, PatientList::class.java)
            startActivity(intent)
        }


        FhirEngineProvider.init(
            FhirEngineConfiguration(
                enableEncryptionIfSupported = true,
                DatabaseErrorStrategy.RECREATE_AT_OPEN,
                ServerConfiguration(
                    baseUrl = "http://10.0.2.2:8080/fhir/"
                )
            )
        )
    }
}