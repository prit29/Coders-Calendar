package com.noobsever.codingcontests.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.noobsever.codingcontests.Models.ContestObject;
import com.noobsever.codingcontests.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardAdapterViewHolder> implements Filterable {

    private List<ContestObject> ContestObjectArrayList;
    private List<ContestObject> DummyContestObjectArrayList;
    private Context context;
    private List<Boolean> CheckMoreFlag;

    public CardAdapter(Context context)
    {
        this.context = context;
    }

    public CardAdapter(Context context, List<ContestObject> ContestObjectArrayList)
    {
        this.context = context;
        this.ContestObjectArrayList = ContestObjectArrayList;
        InitializeBool();
    }

    public void setData(List<ContestObject> data) {
        this.ContestObjectArrayList = data;
        notifyDataSetChanged();
        InitializeBool();
    }

    public void InitializeBool() {
        DummyContestObjectArrayList = new ArrayList<>(ContestObjectArrayList);
        CheckMoreFlag = new ArrayList<Boolean>(getItemCount());
        CheckMoreFlag.addAll(Collections.nCopies(getItemCount(), Boolean.FALSE));
    }

    @NonNull
    @Override
    public CardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_contest_card,parent,false);
        return new CardAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardAdapterViewHolder holder, final int position) {

        holder.mCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.pop_in));

        holder.mRoundName.setText(ContestObjectArrayList.get(position).getTitle());
        holder.mDateEnd.setText(ContestObjectArrayList.get(position).getEnd());
        holder.mDateStart.setText(ContestObjectArrayList.get(position).getStart());
        holder.mDuration.setText(ContestObjectArrayList.get(position).getDuration());

        if(CheckMoreFlag.get(position))
        {
            holder.mShare.show();
            holder.mNotification.show();
            holder.mReminder.show();
        }
        else{
            holder.mShare.hide();
            holder.mNotification.hide();
            holder.mReminder.hide();
        }

        holder.mCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if(CheckMoreFlag.get(position)){
                    holder.mShare.hide();
                    holder.mNotification.hide();
                    holder.mReminder.hide();
                }
                else
                {
                    holder.mShare.show();
                    holder.mNotification.show();
                    holder.mReminder.show();
                }
                CheckMoreFlag.set(position,!CheckMoreFlag.get(position));

                return true;
            }
        });

        holder.mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckMoreFlag.get(position)){
                    holder.mShare.hide();
                    holder.mNotification.hide();
                    holder.mReminder.hide();
                }
                else
                {
                    holder.mShare.show();
                    holder.mNotification.show();
                    holder.mReminder.show();
                }
                CheckMoreFlag.set(position,!CheckMoreFlag.get(position));
            }
        });

        holder.mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Implement Share "+position,Toast.LENGTH_SHORT).show();
            }
        });
        holder.mNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Implement Notification "+position,Toast.LENGTH_SHORT).show();
            }
        });
        holder.mReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Implement Reminder "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount(){
        return ContestObjectArrayList.size();
    }

    public static class CardAdapterViewHolder extends RecyclerView.ViewHolder{
        private final TextView mRoundName;
        private final TextView mDateStart;
        private final TextView mDateEnd,mDuration;
        private final MaterialCardView mCard;
        private final FloatingActionButton mShare,mNotification,mReminder;
        private final ImageView mMore;

        public CardAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mRoundName = itemView.findViewById(R.id.tv_round_name);
            mDateStart = itemView.findViewById(R.id.date_start);
            mDateEnd = itemView.findViewById(R.id.date_end);
            mCard = itemView.findViewById(R.id.card);
            mShare = itemView.findViewById(R.id.fab_share);
            mNotification = itemView.findViewById(R.id.fab_notification);
            mReminder = itemView.findViewById(R.id.fab_reminder);
            mMore = itemView.findViewById(R.id.more_option);
            mDuration = itemView.findViewById(R.id.duration);
        }

    }

    @Override
    public Filter getFilter() {
        return FilteredData;
    }

    private Filter FilteredData = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ContestObject> filtered = new ArrayList<>();

            if(constraint == null || constraint.length()==0){
                filtered.addAll(DummyContestObjectArrayList);
            }else {
                String check = constraint.toString().toLowerCase().trim();

                for(ContestObject items:DummyContestObjectArrayList){
                    if(items.getTitle().toLowerCase().contains(check) || items.getPlatform().toLowerCase().contains(check)
                        || items.getStatus().toLowerCase().contains(check)){
                        filtered.add(items);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtered;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ContestObjectArrayList.clear();
            ContestObjectArrayList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
