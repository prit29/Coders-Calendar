package com.noobsever.codingcontests.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardAdapterViewHolder>{

    private List<ContestObject> ContestObjectArrayList;
    private Context context;

    public CardAdapter(Context context)
    {
        this.context = context;
    }

    public CardAdapter(Context context, List<ContestObject> ContestObjectArrayList)
    {
        this.context = context;
        this.ContestObjectArrayList = ContestObjectArrayList;
    }

    public void setData(List<ContestObject> data) {
        this.ContestObjectArrayList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_contest_card,parent,false);
        return new CardAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardAdapterViewHolder holder, final int position) {
        holder.roundName.setText(ContestObjectArrayList.get(position).getTitle());
        holder.dateEnd.setText(ContestObjectArrayList.get(position).getEnd());
        holder.dateStart.setText(ContestObjectArrayList.get(position).getStart());
    }

    @Override
    public int getItemCount(){
        return ContestObjectArrayList.size();
    }

    public static class CardAdapterViewHolder extends RecyclerView.ViewHolder{
        private final TextView roundName;
        private final TextView dateStart;
        private final TextView dateEnd;
        public CardAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            roundName = itemView.findViewById(R.id.tv_round_name);
            dateStart = itemView.findViewById(R.id.date_start);
            dateEnd = itemView.findViewById(R.id.date_end);
        }
    }

}
