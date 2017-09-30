package com.alexbelogurow.dbcoursework.adapter.diagnosis

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexbelogurow.dbcoursework.activity.diagnosis.ActivityDiagnosesInfo
import com.alexbelogurow.dbcoursework.model.Diagnosis
import com.alexbelogurow.dbcoursework.R

/**
 * Created by alexbelogurow on 08.09.17.
*/
class DiagnosesAdapter(private var diagnosesList: List<Diagnosis>,
                       private val context: Context,
                       private var nextActivity: Int,
                       private var patientId: Int = -1) :
        RecyclerView.Adapter<DiagnosesAdapter.DiagnosesViewHolder>() {

    // NEXT ACTIVITY
    private val INFO_ABOUT_DIAGNOSIS = 0
    private val ADD_DIAGNOSIS_TO_PATIENT = 1

    // EXTRA
    private val EXTRA_ICD = "ICD"

    private var buttonDone: MenuItem? = null
    private var countOfSelection = 0

    fun setButtonDone(buttonDone: MenuItem?) {
        this.buttonDone = buttonDone
    }
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
        val holder = DiagnosesViewHolder(view)

        /*
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val diagnosis = diagnosesList[holder.adapterPosition]
            Log.d("position", "$position")

            when (nextActivity) {
                INFO_ABOUT_DIAGNOSIS        ->  {
                    // TODO ADD TRANSITION TO DIAGNOSES INFO
                }
                ADD_DIAGNOSIS_TO_PATIENT    ->  {
                    diagnosis.isSelected = !diagnosis.isSelected
                    if (diagnosis.isSelected) {
                        holder.itemView.setBackgroundColor(Color.DKGRAY)
                        holder.mCardView?.cardElevation = 10f
                    } else {
                        holder.itemView.setBackgroundColor(Color.WHITE)
                        holder.mCardView?.cardElevation = 0f
                    }
                }
            }


        }
        */
        return holder
    }

    override fun onBindViewHolder(holder: DiagnosesViewHolder, position: Int) {
        //val dbHandler = DBHandler(context)
        val diagnosis = diagnosesList[position]

        holder.mTextViewICD?.text = diagnosis.icd
        holder.mTextViewDiagnosisName?.text = diagnosis.name


        if (diagnosis.isSelected) {
            holder.itemView.setBackgroundColor(Color.LTGRAY)
            buttonDone?.isVisible = countOfSelection > 0
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
            buttonDone?.isVisible = countOfSelection > 0
        }

        holder.mCardView?.setOnClickListener {

            when (nextActivity) {
                INFO_ABOUT_DIAGNOSIS        ->  {
                    // TODO ADD TRANSITION TO DIAGNOSES INFO
                    val diagnosisInfoIntent = Intent(context, ActivityDiagnosesInfo::class.java)
                    diagnosisInfoIntent.putExtra(EXTRA_ICD, diagnosis.icd)
                    context.startActivity(diagnosisInfoIntent)
                }
                ADD_DIAGNOSIS_TO_PATIENT    ->  {
                    diagnosis.isSelected = !diagnosis.isSelected

                    if (diagnosis.isSelected) {
                        countOfSelection++
                    } else {
                        countOfSelection--
                    }

                    Log.d("position", "$position + $countOfSelection + $buttonDone")
                    notifyItemChanged(position)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return diagnosesList.count()
    }

}