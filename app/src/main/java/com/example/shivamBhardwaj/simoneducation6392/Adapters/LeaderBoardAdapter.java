package com.example.shivamBhardwaj.simoneducation6392.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shivamBhardwaj.simoneducation6392.Models.UsersModel;
import com.example.shivamBhardwaj.simoneducation6392.R;
import com.example.shivamBhardwaj.simoneducation6392.databinding.LeaderboarRecyclerDesignBinding;

import java.util.ArrayList;

public class LeaderBoardAdapter extends RecyclerView.Adapter<LeaderBoardAdapter.viewHolder> {
    ArrayList<UsersModel> list;
    Context context;

    public LeaderBoardAdapter(ArrayList<UsersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leaderboar_recycler_design, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        UsersModel usersModel = list.get(position);
        Glide.with(context).load(usersModel.getProfile()).placeholder(R.drawable.profile).into(holder.binding.ProfileImage);
        holder.binding.Name.setText(usersModel.getName());
        holder.binding.textView5.setText(usersModel.getContribution());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        LeaderboarRecyclerDesignBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = LeaderboarRecyclerDesignBinding.bind(itemView);
        }
    }
}
