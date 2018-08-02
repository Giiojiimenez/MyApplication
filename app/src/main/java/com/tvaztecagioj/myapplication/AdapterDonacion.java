package com.tvaztecagioj.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterDonacion extends RecyclerView.Adapter<AdapterDonacion.DonacionviewHolder> {

    List<DonacionFire> donacion;

    public AdapterDonacion(List<DonacionFire> donacion) {
        this.donacion = donacion;
    }

    @NonNull
    @Override
    public DonacionviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.donacion_recy,viewGroup,false);
        DonacionviewHolder holder =new DonacionviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonacionviewHolder h, int i) {
            DonacionFire d=donacion.get(i);
            h.tvfundaR.setText(d.getNomFun());
            h.tvCantidadR.setText("Cantidad: "+d.getCant());
        h.tvProductR.setText("Producto: "+d.getProducto());
    }

    @Override
    public int getItemCount() {
        return donacion.size();
    }

    public static class DonacionviewHolder extends RecyclerView.ViewHolder{

        TextView tvfundaR,tvProductR,tvCantidadR;

        public DonacionviewHolder(@NonNull View itemView) {
            super(itemView);

            tvfundaR=itemView.findViewById(R.id.tvfundaR);
            tvProductR=itemView.findViewById(R.id.tvProductR);
            tvCantidadR=itemView.findViewById(R.id.tvCantidadR);


        }
    }
}
