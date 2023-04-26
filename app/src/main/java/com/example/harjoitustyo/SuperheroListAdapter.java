package com.example.harjoitustyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SuperheroListAdapter extends RecyclerView.Adapter<SuperheroViewHolder>{
    private ArrayList<Superhero> superheroes = new ArrayList<>();
    private Context context;

    public SuperheroListAdapter(Context context, ArrayList<Superhero> superheroes) {
        this.context = context;
        this.superheroes = superheroes;
    }

    @NonNull
    @Override
    public SuperheroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SuperheroViewHolder(LayoutInflater.from(context).inflate(R.layout.superhero_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SuperheroViewHolder holder, int position) {
        holder.imageView.setImageResource(superheroes.get(position).getPicture());
        holder.name.setText(new StringBuilder().append(superheroes.get(position).getName()).append(", ").append(superheroes.get(position).getType()));
        holder.winsLosses.setText(new StringBuilder().append("Voitot: ").append(superheroes.get(position).getWins()).append(" | ").append("Häviöt: ").append(superheroes.get(position).getLosses()).toString());
        holder.attack.setText(new StringBuilder().append("Hyökkäyspisteet: ").append(superheroes.get(position).getAttack()));
        holder.defense.setText(new StringBuilder().append("Puolustuspisteet: ").append(superheroes.get(position).getDefense()));
        holder.health.setText(new StringBuilder().append("Elämäpisteet: ").append(superheroes.get(position).getHealth()).append("/").append(superheroes.get(position).getMaxHealth()));
        holder.experience.setText(new StringBuilder().append("Kokemuspisteet: ").append(superheroes.get(position).getExperience()));

        holder.deleteImage.setOnClickListener(view -> {
            int pos = holder.getAdapterPosition();
            Storage.getInstance().removeSuperhero(superheroes.get(pos).getName());
            notifyItemRemoved(pos);
        });

        holder.editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                if (holder.editName.getVisibility() == View.VISIBLE) {
                    Superhero superhero = Storage.getInstance().getName(pos);
                    superhero.setSuperheroName(holder.editName.getText().toString());
                    holder.editName.setVisibility(View.GONE);
                    notifyDataSetChanged();
                } else {
                    holder.editName.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return superheroes.size();

    }
}