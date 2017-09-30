package com.alexbelogurow.dbcoursework.adapter.treatment

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.adapter.diagnosis.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.model.Treatment
import kotlinx.android.synthetic.main.item_treatment.view.*

/**
 * Created by alexbelogurow on 30.09.2017.
 */
class TreatmentAdapter(private var treatmentList: List<Treatment>,
                       private val context: Context,
                       private var nextActivity: Int,
                       private var diagnosisICD: String = "") :
        RecyclerView.Adapter<TreatmentAdapter.TreatmentViewHolder>() {

    // NEXT ACTIVITY
    private val INFO_ABOUT_TREATMENT = 0
    private val ADD_TREATMENTS_TO_DIAGNOSIS = 1

    // EXTRA
    private val EXTRA_ICD = "ICD"

    private var buttonDone: MenuItem? = null
    private var countOfSelection = 0

    fun setButtonDone() {
        this.buttonDone = buttonDone
    }

    fun updateList(newTreatmentList: List<Treatment>) {
        treatmentList = newTreatmentList
        notifyDataSetChanged()
    }

    class TreatmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mCardView: CardView? = null
        var mTextViewTreatmentName: TextView? = null
        var mTextViewTreatmentType: TextView? = null

        init {
            mCardView = itemView.findViewById(R.id.card_item_treatment)
            mTextViewTreatmentName = itemView.findViewById(R.id.text_item_treatment_name)
            mTextViewTreatmentType = itemView.findViewById(R.id.text_item_treatment_type)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TreatmentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_treatment, parent, false)
        return TreatmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: TreatmentViewHolder?, position: Int) {
        val treatment = treatmentList[position]

        holder?.mTextViewTreatmentName?.text = treatment.name
        holder?.mTextViewTreatmentType?.text = treatment.treatmentType
    }

    override fun getItemCount(): Int {
        return treatmentList.count()
    }
}