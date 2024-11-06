package com.uniquindio.tallervehiculos.model;

import java.time.LocalDate;

public class Auto extends Vehiculo {
    private int numeroPuertas;

    //Constructor
    public Auto(String placa, String marca, String modelo, LocalDate fechaFabricacion, int numeroPuertas) {
        super(placa, marca, modelo, fechaFabricacion);
        this.numeroPuertas = numeroPuertas;
    }

    //Constructor vacio
    public Auto() {
    }

    //Getters y Setters
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }

}
