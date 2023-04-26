package com.example.harjoitustyo;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuperheroViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView,deleteImage,editImage;
    TextView name, winsLosses, attack, health, experience, defense;
    EditText editName;

    public SuperheroViewHolder(@NonNull View itemView){
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        name=itemView.findViewById(R.id.txtSuperheroName);
        winsLosses=itemView.findViewById(R.id.txtWinsLosses);
        attack=itemView.findViewById(R.id.txtAttack);
        defense=itemView.findViewById(R.id.txtDefense);
        health=itemView.findViewById(R.id.txtHealth);
        experience=itemView.findViewById(R.id.txtExperience);
        deleteImage = itemView.findViewById(R.id.imgDelete);
        editImage = itemView.findViewById(R.id.imgEdit);
        editName = itemView.findViewById(R.id.editName);

    }
}
