package com.example.mascotas.modelos;

public class Mascota {
    private String nombre;
    private int edad;
    private long id;

    public Mascota(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public Mascota(String nombre, int edad, long id) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public long getId() { return id; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setId(long id) { this.id = id; }
}
