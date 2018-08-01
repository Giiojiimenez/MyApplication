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

    List<FundacionFire> Fundacion;

    public Adapter(List<FundacionFire> fundacion) {
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
        FundacionFire Fundacio =Fundacion.get(position);

        holder.tvnombre.setText(Fundacio.getEtNombreFun());
        holder.tvcategoria.setText(Fundacio.getEtCat());
        holder.necesidad1.setText(Fundacio.getEtNece01());
        holder.necesidad2.setText(Fundacio.getEtNece02());
        holder.necesidad3.setText(Fundacio.getEtNece03());
        holder.necesidad4.setText(Fundacio.getEtNece04());
        holder.necesidad5.setText(Fundacio.getEtNece05());
        holder.tvmunicipio.setText(Fundacio.getEtMunicipio());
        holder.calle.setText(Fundacio.getEtCalle());
        holder.colonia.setText(Fundacio.getEtColonia());
        holder.codigopostal.setText(Fundacio.getEtPostal());
        holder.correoelectronico.setText(Fundacio.getEtCorreoEle());
        holder.telefonofun.setText(Fundacio.getEtPhoneFun());
    }

    @Override
    public int getItemCount() {
        return Fundacion.size();
    }

    public static class FundacionviewHolder extends RecyclerView.ViewHolder{

        TextView tvnombre , tvcategoria, tvmunicipio,necesidad1,necesidad2,necesidad3,necesidad4,necesidad5,calle,colonia,codigopostal,correoelectronico,telefonofun;

        public FundacionviewHolder(@NonNull View itemView) {
            super(itemView);

            tvnombre=itemView.findViewById(R.id.tvnombrei);
            tvcategoria=itemView.findViewById(R.id.tvcategoriai);
            tvmunicipio=itemView.findViewById(R.id.tvmunicipioi);
            necesidad1=itemView.findViewById(R.id.necesidad1);
            necesidad2=itemView.findViewById(R.id.necesidad2);
            necesidad3=itemView.findViewById(R.id.necesidad3);
            necesidad4=itemView.findViewById(R.id.necesidad4);
            necesidad5=itemView.findViewById(R.id.necesidad5);
            calle=itemView.findViewById(R.id.calle);
            colonia=itemView.findViewById(R.id.colonia);
            codigopostal=itemView.findViewById(R.id.codigopostal);
            correoelectronico=itemView.findViewById(R.id.correoelectronico);
            telefonofun=itemView.findViewById(R.id.telefonofun);

        }
    }
}
