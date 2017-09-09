package com.alexbelogurow.dbcoursework.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexbelogurow.dbcoursework.Model.Diagnosis;
import com.alexbelogurow.dbcoursework.Model.Patient;
import com.alexbelogurow.dbcoursework.R;

import java.util.List;

/**
 * Created by alexbelogurow on 08.09.17.
 */

public class DiagnosesAdapter2 extends RecyclerView.Adapter<DiagnosesAdapter2.ViewHolder> {

    private List<Diagnosis> mDiagnosisList;
    private Context mContext;
    private Integer nextActivity;
    private Integer patientId;

    public DiagnosesAdapter2(List<Diagnosis> diagnosisList, Context context, Integer nextActivity, Integer patientId) {
        mDiagnosisList = diagnosisList;
        mContext = context;
        this.nextActivity = nextActivity;
        this.patientId = patientId;
    }

    public void updateList(List<Diagnosis> newDiagnosisList) {
        mDiagnosisList = newDiagnosisList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView mCardView;
        private TextView mTextViewICD;


        public ViewHolder(View itemView) {
            super(itemView);

            mCardView = itemView.findViewById(R.id.card_item_diagnosis);
            mTextViewICD = itemView.findViewById(R.id.text_item_diagnosis_ICD);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_diagnosis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Diagnosis diagnosis = mDiagnosisList.get(position);
        holder.mTextViewICD.setText(diagnosis.getICD());

        if (diagnosis.isSelected()) {
            holder.itemView.setBackgroundColor(Color.CYAN);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("position", String.valueOf(position));
                diagnosis.setSelected(!diagnosis.isSelected());
                notifyItemChanged(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiagnosisList.size();
    }
}
