package com.noobsever.codingcontests.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.noobsever.codingcontests.Models.HelpObject;
import com.noobsever.codingcontests.R;

import java.util.ArrayList;

public class HelpAdapter extends RecyclerView.Adapter<HelpAdapter.HelpAdapterViewHolder>{

    private ArrayList<HelpObject> helpObjectArrayList;
    private Context context;
    private ArrayList<Integer> viewHolderPosition  = new ArrayList<>();
    private int dropDownIndex = -1; //This variable controls the one at a time implementation of drop down view
    public HelpAdapter(Context context, ArrayList<HelpObject> helpObjectArrayList)
    {
        this.context = context;
        this.helpObjectArrayList = helpObjectArrayList;
    }

    @NonNull
    @Override
    public HelpAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_faq_design,parent,false);
        return new HelpAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HelpAdapterViewHolder holder, final int position) {
        holder.questionTextView.setText(helpObjectArrayList.get(position).getQuestion());
        holder.answerTextView.setText(helpObjectArrayList.get(position).getAnswer());
        if(helpObjectArrayList.get(position).getArrowID()==0)
        {
            holder.arrowImageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_48);
            holder.answerTextView.setVisibility(View.GONE); //Initially all question views will be unexpanded, so the visibilty of Answer view is set to View.GONE
        }
        else
        {
            holder.arrowImageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_48);
        }
        holder.arrowImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helpObjectArrayList.get(position).getArrowID()==0)
                {
                    //This condition implements the logic to expand the answer when question is unexpanded, expand the answer by set ArrowID to 1,
                    helpObjectArrayList.get(position).setArrowID(1);
                    holder.answerTextView.setVisibility(View.VISIBLE);  //Change the visibility of Answer View to View.VISIBLE
                    holder.arrowImageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_48);

                    holder.answerTextView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale));

                    if(dropDownIndex == -1)
                    {
                        dropDownIndex = position;
                    }
                    else
                    {
                        helpObjectArrayList.get(dropDownIndex).setArrowID(0);
                        notifyItemChanged(dropDownIndex);
                        dropDownIndex = position;
                    }

                }
                else
                {
                    // This condition implements logic to close the expanded question
                    dropDownIndex = -1;
                    helpObjectArrayList.get(position).setArrowID(0);
                    holder.arrowImageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_48);
                    holder.answerTextView.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount(){
        return helpObjectArrayList.size();
    }

    public static class HelpAdapterViewHolder extends RecyclerView.ViewHolder{
        private final TextView questionTextView;
        private final TextView answerTextView;
        private final ImageView arrowImageView;
        public HelpAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.help_question);
            answerTextView = itemView.findViewById(R.id.help_answer);
            arrowImageView = itemView.findViewById(R.id.help_arrow_id);
        }
    }

}