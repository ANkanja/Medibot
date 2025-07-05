package com.example.medibot_afrika

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.fhir.FhirEngine
import com.google.android.fhir.FhirEngineProvider
import com.google.android.fhir.search.search
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.ResourceType

class PatientList : AppCompatActivity() {
    private lateinit var fhirEngine: FhirEngine
    private lateinit var patientAdapter: PatientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_patient_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fhirEngine = FhirEngineProvider.getInstance(this)

        val recyclerView = findViewById<RecyclerView>(R.id.patientRecyclerView)
        patientAdapter = PatientAdapter(mutableListOf(), this::editPatient, this::deletePatient)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = patientAdapter

        findViewById<FloatingActionButton>(R.id.addPatientFab).setOnClickListener {
            showPatientDialog()
        }

        loadPatients()
    }

    private fun loadPatients() {
        CoroutineScope(Dispatchers.IO).launch {
            // Correct: pass an empty lambda with Search receiver
            val patients = fhirEngine.search<Patient>({ })
            withContext(Dispatchers.Main) {
                patientAdapter.updateData(patients)
            }
        }
    }

    private fun showPatientDialog(patient: Patient? = null) {
        PatientDialog(
            onSave = { newPatient ->
                CoroutineScope(Dispatchers.IO).launch {
                    if (patient == null) {
                        fhirEngine.create(newPatient)
                    } else {
                        fhirEngine.update(newPatient)
                    }
                    withContext(Dispatchers.Main) {
                        loadPatients()
                        Toast.makeText(this@PatientList, "Patient saved!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            patient = patient
        ).show(supportFragmentManager, "PatientDialog")
    }

    private fun editPatient(patient: Patient) {
        showPatientDialog(patient)
    }

    private fun deletePatient(patient: Patient) {
        CoroutineScope(Dispatchers.IO).launch {
            // Correct: pass ResourceType.Patient and patient ID string
            fhirEngine.delete(ResourceType.Patient, patient.idElement.idPart)
            withContext(Dispatchers.Main) {
                loadPatients()
                Toast.makeText(this@PatientList, "Patient deleted!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
