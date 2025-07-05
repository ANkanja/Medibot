// PatientAdapter.kt
package com.example.medibot_afrika

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.hl7.fhir.r4.model.Patient

class PatientAdapter(
    private val patients: MutableList<Patient>,
    private val onEdit: (Patient) -> Unit,
    private val onDelete: (Patient) -> Unit
) : RecyclerView.Adapter<PatientAdapter.PatientViewHolder>() {

    class PatientViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(R.id.patientName)
        val dobText: TextView = view.findViewById(R.id.patientDob)
        val ageText: TextView = view.findViewById(R.id.patientAge)
        val editBtn: ImageButton = view.findViewById(R.id.editBtn)
        val deleteBtn: ImageButton = view.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_patient, parent, false)
        return PatientViewHolder(view)
    }

    override fun getItemCount() = patients.size

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = patients[position]
        holder.nameText.text = patient.nameFirstRep?.nameAsSingleString ?: "No Name"
        holder.dobText.text = patient.birthDate?.toString() ?: "No DOB"
        holder.ageText.text = patient.birthDate?.let { getAge(it) } ?: "N/A"
        holder.editBtn.setOnClickListener { onEdit(patient) }
        holder.deleteBtn.setOnClickListener { onDelete(patient) }
    }

    fun updateData(newPatients: List<Patient>) {
        patients.clear()
        patients.addAll(newPatients)
        notifyDataSetChanged()
    }

    private fun getAge(birthDate: java.util.Date): String {
        val today = java.util.Calendar.getInstance()
        val dob = java.util.Calendar.getInstance()
        dob.time = birthDate
        var age = today.get(java.util.Calendar.YEAR) - dob.get(java.util.Calendar.YEAR)
        if (today.get(java.util.Calendar.DAY_OF_YEAR) < dob.get(java.util.Calendar.DAY_OF_YEAR)) {
            age--
        }
        return "$age"
    }
}
