package com.uniquindio.tallervehiculos.model;
import java.util.ArrayList;


public class Cliente {

    private String nombre;
    private String password;
    private ArrayList<Reserva> reservas = new ArrayList<>();

    // Constructor
    public Cliente(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    // Constructor Vacio
    public Cliente() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }
}

