package com.example.project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.project.Domain.CategoryDomain;
import com.example.project.R;


import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{

    ArrayList<CategoryDomain> categoryDomains;

    public CategoryAdapter(ArrayList<CategoryDomain> categoryDomains)
    {
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl="pic8";
                break;
            }
            case 1:{
                picUrl="pic2";
                break;
            }
            case 2:{
                picUrl="pic3";
                break;
            }
            case 3:{
                picUrl="pic4";
                break;
            }
            case 4:{
                picUrl="pic5";
                break;
            }
            case 5:{
                picUrl="pic6";
                break;
            }
            case 6:{
                picUrl="pic7";
                break;
            }

        }

        int drawableReourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableReourceId).into(holder.categoryPic);
    }

    @Override
    public int getItemCount() { return categoryDomains.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemview)
        {
            super(itemview);
            categoryName=itemview.findViewById(R.id.categoryName);
            categoryPic=itemview.findViewById(R.id.categoryPic);
            mainLayout=itemview.findViewById(R.id.mainLayout);
        }
    }
}



