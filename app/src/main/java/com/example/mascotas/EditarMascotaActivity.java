package com.example.mascotas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity; // Legacy: android.support.v7.app.AppCompatActivity

import com.example.mascotas.controllers.MascotasController;
import com.example.mascotas.modelos.Mascota;

public class EditarMascotaActivity extends AppCompatActivity {
    private EditText etNombre, etEdad, etPeso;
    private Button btnGuardar, btnCancelar;
    private long idMascota;
    private MascotasController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_mascota);

        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        etPeso = findViewById(R.id.etPeso);
        btnGuardar = findViewById(R.id.btnGuardarCambios);
        btnCancelar = findViewById(R.id.btnCancelarEditar);

        controller = new MascotasController(this);

        // Datos que llegaron desde MainActivity
        idMascota = getIntent().getLongExtra("idMascota", -1);
        String nombre = getIntent().getStringExtra("nombreMascota");
        int edad = getIntent().getIntExtra("edadMascota", 0);
        double peso = getIntent().getDoubleExtra("pesoMascota", 0.0);


        etNombre.setText(nombre);
        etEdad.setText(String.valueOf(edad));
        etPeso.setText(String.valueOf(peso));


        btnGuardar.setOnClickListener(v -> {
            etNombre.setError(null);
            etEdad.setError(null);
            etPeso.setError(null);


            String n = etNombre.getText().toString().trim();
            String e = etEdad.getText().toString().trim();
            String p = etPeso.getText().toString().trim();


            if (n.isEmpty()) { etNombre.setError("Requerido"); etNombre.requestFocus(); return; }
            int edadNum;
            double pesoNum;

            try { edadNum = Integer.parseInt(e); }
            catch (NumberFormatException ex) { etEdad.setError("Número inválido"); etEdad.requestFocus(); return; }
            try { pesoNum = Double.parseDouble(p); }
            catch (NumberFormatException ex) { etPeso.setError("Número inválido"); etPeso.requestFocus(); return; }

            Mascota m = new Mascota(n, edadNum, pesoNum,  idMascota);
            int filas = controller.guardarCambios(m);
            if (filas > 0) finish();
            else Toast.makeText(this, "Sin cambios / error", Toast.LENGTH_SHORT).show();
        });

        btnCancelar.setOnClickListener(v -> finish());
    }
}
