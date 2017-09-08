package com.alexbelogurow.dbcoursework.Adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexbelogurow.dbcoursework.DataBase.DBHandler
import com.alexbelogurow.dbcoursework.Model.Diagnosis
import com.alexbelogurow.dbcoursework.R

/**
 * Created by alexbelogurow on 08.09.17.
 */
class DiagnosesAdapter(private var diagnosesList: List<Diagnosis>,
                       private val context: Context,
                       private var nextActivity: Int,
                       private var patientId: Int = -1) :
        RecyclerView.Adapter<DiagnosesAdapter.DiagnosesViewHolder>() {

    fun updateList(newDiagnosesList: List<Diagnosis>) {
        diagnosesList = newDiagnosesList
        notifyDataSetChanged()
    }

    class DiagnosesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCardView: CardView? = null
        var mTextViewICD: TextView? = null
        var mTextViewDiagnosisName: TextView? = null

        init {
            mCardView = itemView.findViewById(R.id.card_item_diagnosis)
            mTextViewICD = itemView.findViewById(R.id.text_item_diagnosis_ICD)
            mTextViewDiagnosisName = itemView.findViewById(R.id.text_item_diagnosis_name)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DiagnosesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_diagnosis, parent, false)
        return DiagnosesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiagnosesViewHolder, position: Int) {
        //val dbHandler = DBHandler(context)
        val diagnosis = diagnosesList[position]

        holder.mTextViewICD?.text = diagnosis.icd
        holder.mTextViewDiagnosisName?.text = diagnosis.name
    }

    override fun getItemCount(): Int {
        return diagnosesList.count()
    }

}
