package com.example.mascotas.modelos;

public class Mascota {
    private String nombre;
    private int edad;
    private double peso;
    private long id;

    public Mascota(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public Mascota(String nombre, int edad, double peso,  long id) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.id = id;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public double getPeso() { return peso; }

    public long getId() { return id; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setPeso (int peso) { this.edad = peso; }
    public void setId(long id) { this.id = id; }
}
