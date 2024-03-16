package com.example.shivamBhardwaj.simoneducation6392.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shivamBhardwaj.simoneducation6392.Activities.GiveQuizActivity;
import com.example.shivamBhardwaj.simoneducation6392.Models.QuizModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.QuizFragmentRecyclerDesignBinding;

import java.util.ArrayList;

public class QuizFragmentAdapter extends RecyclerView.Adapter<QuizFragmentAdapter.viewHolder>{
    ArrayList<QuizModel> list;
    Context context;

    public QuizFragmentAdapter(ArrayList<QuizModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.quiz_fragment_recycler_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        QuizModel model=list.get(position);
       holder.binding.Quiznum.setText( model.getQuizNumber());
       holder.binding.Layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(context, GiveQuizActivity.class);
               intent.putExtra("Quiz",list.get(position));
               context.startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        QuizFragmentRecyclerDesignBinding binding;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding=QuizFragmentRecyclerDesignBinding.bind(itemView);
        }
    }
}
