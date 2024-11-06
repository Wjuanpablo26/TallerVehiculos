package com.uniquindio.tallervehiculos.model;

import java.time.LocalDate;

public class Camioneta extends Vehiculo {
    private float capacidadCarga;

    //Constructor
    public Camioneta(String placa, String marca, String modelo, LocalDate fechaFabricacion, float capacidadCarga) {
        super(placa, marca, modelo, fechaFabricacion);
        this.capacidadCarga = capacidadCarga;
    }

    //Constructor vacio
    public Camioneta() {
    }

    //Getters y Setters
    public float getCapacidadCarga() {
        return capacidadCarga;
    }
    public void setCapacidadCarga(float capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

}
