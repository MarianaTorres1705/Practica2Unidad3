package com.example.mascotas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity; // Legacy: android.support.v7.app.AppCompatActivity

import com.example.mascotas.controllers.MascotasController;
import com.example.mascotas.modelos.Mascota;

public class AgregarMascotaActivity extends AppCompatActivity {
    private Button btnAgregarMascota, btnCancelarNuevaMascota;
    private EditText etNombre, etEdad;
    private MascotasController mascotasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        btnAgregarMascota = findViewById(R.id.btnAgregarMascota);
        btnCancelarNuevaMascota = findViewById(R.id.btnCancelarNuevaMascota);

        mascotasController = new MascotasController(this);

        btnAgregarMascota.setOnClickListener(v -> {
            etNombre.setError(null);
            etEdad.setError(null);

            String nombre = etNombre.getText().toString().trim();
            String edadStr = etEdad.getText().toString().trim();

            if (nombre.isEmpty()) { etNombre.setError("Escribe el nombre"); etNombre.requestFocus(); return; }
            if (edadStr.isEmpty()) { etEdad.setError("Escribe la edad"); etEdad.requestFocus(); return; }

            int edad;
            try { edad = Integer.parseInt(edadStr); }
            catch (NumberFormatException e) { etEdad.setError("Número inválido"); etEdad.requestFocus(); return; }

            long id = mascotasController.nuevaMascota(new Mascota(nombre, edad));
            if (id == -1) {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
        });

        btnCancelarNuevaMascota.setOnClickListener(v -> finish());
    }
}
