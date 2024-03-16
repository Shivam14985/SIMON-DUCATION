package com.example.shivamBhardwaj.simoneducation6392.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shivamBhardwaj.simoneducation6392.Models.PracticeQuestionModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.HomeRecyclerDesignBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder> {
    ArrayList<PracticeQuestionModel> list;
    Context context;

    public HomeAdapter(ArrayList<PracticeQuestionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recycler_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PracticeQuestionModel model = list.get(position);
        String timeago= TimeAgo.using(model.getAddedAt());
        holder.binding.textView.setText(timeago);
        holder.binding.textView2.setText(model.getDescription());
       }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        HomeRecyclerDesignBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeRecyclerDesignBinding.bind(itemView);
        }
    }
}
