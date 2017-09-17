package com.alexbelogurow.dbcoursework.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.alexbelogurow.dbcoursework.Activity.ActivityAddDiagnosesForPatient
import com.alexbelogurow.dbcoursework.Activity.ActivityDiagnoses
import com.alexbelogurow.dbcoursework.Activity.ActivityDoctorInfo
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Doctor
import com.alexbelogurow.dbcoursework.R
import java.util.*

/**
 * Created by alexbelogurow on 23.05.17.
 */

class DoctorAdapter(private var doctorList: List<Doctor>,
                    private val context: Context,
                    private var nextActivity: Int,
                    private var patientId: Int = -1) :
        RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    // NEXT ACTIVITY
    private val INFO_ABOUT_DOCTOR = 0
    private val ADD_DOCTOR_TO_PATIENT = 1

    // EXTRA
    private var EXTRA_DOCTOR_ID = "DOCTOR_ID"
    private var EXTRA_PATIENT_ID = "PATIENT_ID"

    fun updateList(newDoctorList: List<Doctor>) {
        doctorList = newDoctorList
        notifyDataSetChanged()
    }

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCardView: CardView? = null
        var mTextViewDoctorName: TextView? = null
        var mTextViewDoctorSpecialization: TextView? = null
        var mTextViewDoctorAge: TextView? = null

        init {
            mCardView = itemView.findViewById(R.id.cardViewDoctorInfo)
            mTextViewDoctorName = itemView.findViewById(R.id.text_doctor_card_name)
            mTextViewDoctorSpecialization = itemView.findViewById(R.id.text_doctor_card_specialization)
            mTextViewDoctorAge = itemView.findViewById(R.id.text_doctor_card_age)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false)
        return DoctorViewHolder(view)
    }


    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val dbHandler = DBHandler.getInstance(context)
        val doctor = doctorList[position]
        val person = dbHandler.getPerson(doctor.personID)

        holder.mTextViewDoctorName?.text = person.fullName
        holder.mTextViewDoctorSpecialization?.text = doctor.specialization
        holder.mTextViewDoctorAge?.text = getAge(person.birthDate)

        holder.mCardView?.setOnClickListener {

            when (nextActivity) {
                INFO_ABOUT_DOCTOR       -> {
                    val intentInfoDoctor = Intent(context, ActivityDoctorInfo::class.java)
                    intentInfoDoctor.putExtra(EXTRA_DOCTOR_ID, doctor.doctorID)
                    context.startActivity(intentInfoDoctor)
                }
                ADD_DOCTOR_TO_PATIENT   -> {
                    val patient = dbHandler.getPatient(patientId)
                    dbHandler.addDoctorForPatient(patient, doctor)

                    val intentAddDiagnosis = Intent(context, ActivityAddDiagnosesForPatient::class.java)
                    intentAddDiagnosis.putExtra(EXTRA_PATIENT_ID, patientId)
                    context.startActivity(intentAddDiagnosis)
                }
                else                    -> {
                    Toast.makeText(context, "NEXT ACTIVITY CODE ERROR - $nextActivity", Toast.LENGTH_LONG).show()
                }
            }
        }

        dbHandler.close()
    }

    override fun getItemCount(): Int {
        return doctorList.count()
    }

    private fun getAge(birthDate: String): String {
        val date = birthDate.split("/".toRegex())
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()

        dob.set(date[0].toInt(), date[1].toInt(), date[2].toInt())

        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--
        }

        val ageInt = age
        return "Возраст - ${ageInt.toString()}"
    }
}
//--------------------------------------------

