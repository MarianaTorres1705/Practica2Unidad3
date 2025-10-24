package com.example.mascotas.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.example.mascotas.AyudanteBaseDeDatos;
import com.example.mascotas.modelos.Mascota;
public class MascotasController {
    private AyudanteBaseDeDatos AyudanteBaseDeDatos;
    private String NOMBRE_TABLA = "mascotas";

    public MascotasController(Context contexto) {
        AyudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }

    public int eliminarMascota(Mascota mascota) {

        SQLiteDatabase baseDeDatos = AyudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(mascota.getId())};
        return baseDeDatos.delete(NOMBRE_TABLA, "id = ?", argumentos);
    }

    public long nuevaMascota(Mascota mascota) {
        SQLiteDatabase baseDeDatos = AyudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", mascota.getNombre());
        valoresParaInsertar.put("edad", mascota.getEdad());
        valoresParaInsertar.put("peso", mascota.getPeso());
        return baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);
    }

    public int guardarCambios(Mascota mascotaEditada) {
        SQLiteDatabase baseDeDatos = AyudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", mascotaEditada.getNombre());
        valoresParaActualizar.put("edad", mascotaEditada.getEdad());
        valoresParaActualizar.put("peso", mascotaEditada.getPeso());
        String campoParaActualizar = "id = ?";
        String[] argumentosParaActualizar = {String.valueOf(mascotaEditada.getId())};
        return baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Mascota> obtenerMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        SQLiteDatabase baseDeDatos = AyudanteBaseDeDatos.getReadableDatabase();
        String[] columnasAConsultar = {"nombre", "edad", "peso", "id"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {

            return mascotas;

        }
        if (!cursor.moveToFirst()) return mascotas;


        do {

            String nombreObtenidoDeBD = cursor.getString(0);
            int edadObtenidaDeBD = cursor.getInt(1);
            double pespObtenidoDeBD = cursor.getDouble(2);
            long idMascota = cursor.getLong(3);
            Mascota mascotaObtenidaDeBD = new Mascota(nombreObtenidoDeBD, edadObtenidaDeBD, pespObtenidoDeBD,idMascota);
            mascotas.add(mascotaObtenidaDeBD);
        } while (cursor.moveToNext());

        cursor.close();
        return mascotas;
    }
}