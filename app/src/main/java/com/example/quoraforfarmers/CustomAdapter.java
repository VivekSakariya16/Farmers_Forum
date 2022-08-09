package com.example.quoraforfarmers;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final String[] questionData;
    private final int[] ans1Data;
    private final double[] ans2Data;

    public CustomAdapter(String[] questionData, int[] ans1Data, double[] ans2Data) {
        this.questionData = questionData;
        this.ans1Data = ans1Data;
        this.ans2Data = ans2Data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView q;
        private final TextView a1;
        private final TextView a2;
        private final TextView ans;

        public TextView getQ() {
            return q;
        }

        public TextView getA1() {
            return a1;
        }

        public TextView getA2() {
            return a2;
        }
        public TextView getANS() {
            return ans;
        }


        public ViewHolder(View view) {
            super(view);
            q = view.findViewById(R.id.question);
            a1 = view.findViewById(R.id.firstAns);
            a2 = view.findViewById(R.id.secondAns);
            ans=view.findViewById(R.id.addYourAns);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getQ().setText("\t\t\tQ. "+questionData[position]);
        viewHolder.getA1().setText("\t\t\t\t\t\t\t\tA. "+ans1Data[position]);
        viewHolder.getA2().setText("\t\t\t\t\t\t\t\tA. "+ans2Data[position]);
        viewHolder.getANS().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewHolder.a1.getContext(),addAnswer.class);
                startActivity(viewHolder.a1.getContext(), intent,null);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ans1Data.length;
    }
}

