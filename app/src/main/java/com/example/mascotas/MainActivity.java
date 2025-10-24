package com.example.mascotas;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable; // Legacy: android.support.annotation.Nullable
import androidx.appcompat.app.AlertDialog; // Legacy: android.support.v7.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity; // Legacy: android.support.v7.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator; // Legacy: android.support.v7.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager;  // Legacy: android.support.v7.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView;         // Legacy: android.support.v7.widget.RecyclerView

import com.google.android.material.floatingactionbutton.FloatingActionButton; // Legacy: android.support.design.widget.FloatingActionButton

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.example.mascotas.controllers.MascotasController;
import com.example.mascotas.modelos.Mascota;

public class MainActivity extends AppCompatActivity {

    private List<Mascota> listaDeMascotas;
    private RecyclerView recyclerView;
    private AdaptadorMascotas adaptadorMascotas;
    private MascotasController mascotasController;
    private FloatingActionButton fabAgregarMascota;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mascotasController = new MascotasController(this);

        recyclerView = findViewById(R.id.recyclerViewMascotas);
        fabAgregarMascota = findViewById(R.id.fabAgregarMascota);

        listaDeMascotas = new ArrayList<>();
        adaptadorMascotas = new AdaptadorMascotas(listaDeMascotas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorMascotas);

        refrescarListaDeMascotas();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override public void onClick(View view, int position) {
                Mascota m = adaptadorMascotas.getItem(position);
                Intent i = new Intent(MainActivity.this, EditarMascotaActivity.class);
                i.putExtra("idMascota", m.getId());
                i.putExtra("nombreMascota", m.getNombre());
                i.putExtra("edadMascota", m.getEdad());
                startActivity(i);
            }
            @Override public void onLongClick(View view, int position) {
                final Mascota m = adaptadorMascotas.getItem(position);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar a la mascota " + m.getNombre() + "?")
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                mascotasController.eliminarMascota(m);
                                refrescarListaDeMascotas();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .create().show();
            }
        }));

        fabAgregarMascota.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AgregarMascotaActivity.class)));

        fabAgregarMascota.setOnLongClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Acerca de")
                    .setMessage("CRUD SQLite por Parzibyte adaptado.\n\nIcons: Freepik (flaticon)")
                    .setNegativeButton("Cerrar", null)
                    .setPositiveButton("Sitio web", (d, w) -> {
                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://parzibyte.me"));
                        startActivity(in);
                    }).show();
            return true;
        });
    }

    @Override protected void onResume() {
        super.onResume();
        refrescarListaDeMascotas();
    }

    private void refrescarListaDeMascotas() {
        listaDeMascotas = mascotasController.obtenerMascotas();
        adaptadorMascotas.setListaDeMascotas(listaDeMascotas);
        adaptadorMascotas.notifyDataSetChanged();
    }
}
