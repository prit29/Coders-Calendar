package com.noobsever.codingcontests.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Screens.ShowContestCardsActivity;
import com.noobsever.codingcontests.Utils.Constants;

import java.util.List;


public class PlatformsListAdapter extends RecyclerView.Adapter<PlatformsListAdapter.PlatformsListAdapterViewHolder>{

    private List<String> ContestObjectArrayList;
    private Context context;

    public PlatformsListAdapter(Context context)
    {
        this.context = context;
    }

    public PlatformsListAdapter(Context context, List<String> ContestObjectArrayList)
    {
        this.context = context;
        this.ContestObjectArrayList = ContestObjectArrayList;
    }

    public void setData(List<String> data) {
        this.ContestObjectArrayList = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlatformsListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_contest_title,parent,false);
        return new PlatformsListAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlatformsListAdapterViewHolder holder, final int position) {

        holder.contestTitle.setAnimation(AnimationUtils.loadAnimation(context,R.anim.pop_in));
        holder.contestTitle.setText(ContestObjectArrayList.get(position));

        holder.contestTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ShowContestCardsActivity.class).putExtra(Constants.WEBSITE,ContestObjectArrayList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount(){
        return ContestObjectArrayList.size();
    }

    public static class PlatformsListAdapterViewHolder extends RecyclerView.ViewHolder{
        private final MaterialButton contestTitle;

        public PlatformsListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
             contestTitle = itemView.findViewById(R.id.contest_title);
        }
    }
}
