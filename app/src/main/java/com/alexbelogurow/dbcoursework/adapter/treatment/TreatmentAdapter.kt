package com.alexbelogurow.dbcoursework.adapter.treatment

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout.LayoutParams;
import com.alexbelogurow.dbcoursework.R
import com.alexbelogurow.dbcoursework.adapter.diagnosis.DiagnosesAdapter
import com.alexbelogurow.dbcoursework.model.SideEffect
import com.alexbelogurow.dbcoursework.model.Treatment
import com.alexbelogurow.dbcoursework.util.DBHandler
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
    private val ADD_TREATMENTS_FOR_DIAGNOSIS = 1

    // EXTRA
    private val EXTRA_DIAGNOSIS_ICD = "ICD"

    private var buttonDone: MenuItem? = null
    private var countOfSelection = 0

    fun setButtonDone(buttonDone: MenuItem?) {
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
        var mExpandableListSideEffects: ExpandableListView? = null

        var sideEffects: List<SideEffect>? = null


        init {
            mCardView = itemView.findViewById(R.id.card_item_treatment)
            mTextViewTreatmentName = itemView.findViewById(R.id.text_item_treatment_name)
            mTextViewTreatmentType = itemView.findViewById(R.id.text_item_treatment_type)
            mExpandableListSideEffects = itemView.findViewById(R.id.expanded_item_treatment)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TreatmentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_treatment, parent, false)
        val holder = TreatmentViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: TreatmentViewHolder?, position: Int) {
        val treatment = treatmentList[position]

        holder?.mTextViewTreatmentName?.text = treatment.name
        holder?.mTextViewTreatmentType?.text = treatment.treatmentType

        holder?.sideEffects = DBHandler.getInstance(context).getSideEffectByTreatment(treatment)

        if (treatment.isSelected) {
            holder?.itemView?.setBackgroundColor(Color.LTGRAY)
            buttonDone?.isVisible = countOfSelection > 0
        } else {
            holder?.itemView?.setBackgroundColor(Color.WHITE)
            buttonDone?.isVisible = countOfSelection > 0
        }
        Log.d("button done", "is visible + ${buttonDone?.isVisible}" )
        Log.d("button done", (countOfSelection > 0).toString())

        holder?.mCardView?.setOnClickListener {
            when (nextActivity) {
                INFO_ABOUT_TREATMENT            -> {
                    //TODO TREATMENT_ACTIVITY_INFO

                }
                ADD_TREATMENTS_FOR_DIAGNOSIS    -> {
                    treatment.isSelected = !treatment.isSelected

                    if (treatment.isSelected) {
                        countOfSelection++
                    } else {
                        countOfSelection--
                    }

                    Log.d("position", "$position + $countOfSelection + $buttonDone")
                    notifyItemChanged(position)
                }
            }
        }


        if (nextActivity == INFO_ABOUT_TREATMENT && holder?.sideEffects?.size!! > 0) {
            val groupData = ArrayList<Map<String, String>>()
            val childData = ArrayList<List<Map<String, String>>>()

            val map = HashMap<String, String>()
            map.put("groupName", "Побочные эффекты")
            groupData.add(map)

            val children = ArrayList<Map<String, String>>()
            holder.sideEffects?.forEach {
                val curChildMap = HashMap<String, String>()
                curChildMap.put("childName", "${it.name} - ${it.comments}" )
                children.add(curChildMap)
            }
            childData.add(children)


            val groupFrom = arrayOf("groupName")
            val groupTo = intArrayOf(android.R.id.text1)

            val childFrom: Array<String> = arrayOf("childName")
            val childTo = intArrayOf(android.R.id.text1)

            val adapter = SimpleExpandableListAdapter(
                    context, groupData, android.R.layout.simple_expandable_list_item_1,
                    groupFrom, groupTo,
                    childData, android.R.layout.simple_list_item_1,
                    childFrom, childTo)
            holder.mExpandableListSideEffects?.setAdapter(adapter)
            holder.mExpandableListSideEffects?.setOnGroupClickListener(ExpandableListView.OnGroupClickListener { parent, v, groupPosition, id ->
                if (!parent.isGroupExpanded(groupPosition)) {
                    holder.mExpandableListSideEffects?.layoutParams = LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            200 + 140 * (holder.sideEffects?.size!!))
                } else {
                    parent.layoutParams = LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.WRAP_CONTENT)
                }

                false
            })
        }

    }

    override fun getItemCount(): Int {
        return treatmentList.count()
    }
}