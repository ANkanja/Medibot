// PatientDialog.kt
package com.example.medibot_afrika

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import org.hl7.fhir.r4.model.Patient
import org.hl7.fhir.r4.model.HumanName
import java.text.SimpleDateFormat

class PatientDialog(
    private val onSave: (Patient) -> Unit,
    private val patient: Patient? = null
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_patient, null)
        val nameInput = view.findViewById<EditText>(R.id.inputName)
        val dobInput = view.findViewById<EditText>(R.id.inputDob)
        val ageInput = view.findViewById<EditText>(R.id.inputAge)

        patient?.let {
            nameInput.setText(it.nameFirstRep?.nameAsSingleString)
            dobInput.setText(it.birthDate?.let { date -> SimpleDateFormat("yyyy-MM-dd").format(date) })
            ageInput.setText(it.birthDate?.let { date -> /* calculate age */ "" })
        }

        return AlertDialog.Builder(requireContext())
            .setTitle(if (patient == null) "Add Patient" else "Edit Patient")
            .setView(view)
            .setPositiveButton("Save") { _, _ ->
                val name = nameInput.text.toString()
                val dobStr = dobInput.text.toString()
                val dob = if (dobStr.isNotEmpty()) SimpleDateFormat("yyyy-MM-dd").parse(dobStr) else null
                val newPatient = patient ?: Patient()
                newPatient.name = listOf(HumanName().setText(name))
                newPatient.birthDate = dob
                onSave(newPatient)
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}
