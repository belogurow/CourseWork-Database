package com.alexbelogurow.dbcoursework.Adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Doctor
import com.alexbelogurow.dbcoursework.Model.Patient
import com.alexbelogurow.dbcoursework.R
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by alexbelogurow on 23.05.17.
 */

class DoctorAdapter(private var doctorList: List<Doctor>, private val context: Context) :
        RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    fun updateList(newDoctorList: List<Doctor>) {
        doctorList = newDoctorList
        notifyDataSetChanged()
    }

    class DoctorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCardView: CardView? = null
        var mTextViewDocName: TextView? = null

        init {
            mCardView = itemView.findViewById(R.id.cardViewDoctorInfo) as CardView
            mTextViewDocName = itemView.findViewById(R.id.textViewDoctorInfoName) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false)
        return DoctorViewHolder(view)
    }


    override fun onBindViewHolder(holder: DoctorViewHolder?, position: Int) {
        val dbHandler = DBHandler(context)
        val doctor = doctorList[position]
        val person = dbHandler.getPerson(doctor.personID)

        holder?.mTextViewDocName?.text = "$person \n $doctor"
        dbHandler.close()

    }

    override fun getItemCount(): Int {
        return doctorList.count()
    }
}
//--------------------------------------------

