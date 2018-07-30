package com.tvaztecagioj.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.FundacionviewHolder>{

    List<Fundaciones> Fundacion;

    public Adapter(List<Fundaciones> fundacion) {
        Fundacion = fundacion;
    }

    @NonNull
    @Override
    public FundacionviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.obs_recycler,parent,false);
      FundacionviewHolder holder =new FundacionviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FundacionviewHolder holder, int position) {
        Fundaciones Fundacio =Fundacion.get(position);
        holder.tvnombre.setText(Fundacio.getNombreFun());
        holder.tvcategoria.setText(Fundacio.getCategoria());
        holder.tvmunicipio.setText(Fundacio.getMunicipio());

    }

    @Override
    public int getItemCount() {
        return Fundacion.size();
    }

    public static class FundacionviewHolder extends RecyclerView.ViewHolder{

        TextView tvnombre , tvcategoria, tvmunicipio;
        ImageView ivimagen;

        public FundacionviewHolder(@NonNull View itemView) {
            super(itemView);

            tvnombre=itemView.findViewById(R.id.tvnombrei);
            tvcategoria=itemView.findViewById(R.id.tvcategoriai);
            tvmunicipio=itemView.findViewById(R.id.tvmunicipioi);
        }
    }
}
