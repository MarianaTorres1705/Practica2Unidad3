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
    private EditText etNombre, etEdad, etPeso;
    private MascotasController mascotasController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        etPeso = findViewById(R.id.etPeso);
        btnAgregarMascota = findViewById(R.id.btnAgregarMascota);
        btnCancelarNuevaMascota = findViewById(R.id.btnCancelarNuevaMascota);

        mascotasController = new MascotasController(this);

        btnAgregarMascota.setOnClickListener(v -> {
            etNombre.setError(null);
            etEdad.setError(null);
            etPeso.setError(null);

            String nombre = etNombre.getText().toString().trim();
            String edadStr = etEdad.getText().toString().trim();
            String pesoStr = etEdad.getText().toString().trim();

            if (nombre.isEmpty()) { etNombre.setError("Escribe el nombre"); etNombre.requestFocus(); return; }
            if (edadStr.isEmpty()) { etEdad.setError("Escribe la edad"); etEdad.requestFocus(); return; }
            if (pesoStr.isEmpty()) { etPeso.setError("Escribe el peso"); etPeso.requestFocus(); return; }

            int edad;
            double peso;

            try { edad = Integer.parseInt(edadStr); }
            catch (NumberFormatException e) { etEdad.setError("Número inválido"); etEdad.requestFocus(); return; }

            try { peso = Double.parseDouble(pesoStr); }
            catch (NumberFormatException e) { etPeso.setError("Número inválido"); etPeso.requestFocus(); return; }


            long id = mascotasController.nuevaMascota(new Mascota(nombre, edad, peso));
            if (id == -1) {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
            } else {
                finish();
            }
        });

        btnCancelarNuevaMascota.setOnClickListener(v -> finish());
    }
}
