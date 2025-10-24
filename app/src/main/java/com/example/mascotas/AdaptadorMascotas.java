package com.example.mascotas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull; // Legacy: use android.support.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView; // Legacy: android.support.v7.widget.RecyclerView

import java.util.ArrayList;
import java.util.List;

import com.example.mascotas.modelos.Mascota;

public class AdaptadorMascotas extends RecyclerView.Adapter<AdaptadorMascotas.VH> {
    private List<Mascota> lista = new ArrayList<>();

    public AdaptadorMascotas(List<Mascota> inicial) {
        if (inicial != null) lista = inicial;
    }

    public void setListaDeMascotas(List<Mascota> nueva) {
        lista = nueva != null ? nueva : new ArrayList<Mascota>();
    }

    public Mascota getItem(int position) {
        return lista.get(position);
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mascota, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int pos) {
        Mascota m = lista.get(pos);
        h.tvNombre.setText(m.getNombre());
        h.tvEdad.setText("Edad: " + m.getEdad());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tvNombre, tvEdad;
        VH(View v) {
            super(v);
            tvNombre = v.findViewById(R.id.tvNombre);
            tvEdad = v.findViewById(R.id.tvEdad);
        }
    }
}
