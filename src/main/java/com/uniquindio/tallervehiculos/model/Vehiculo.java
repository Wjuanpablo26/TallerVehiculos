package com.uniquindio.tallervehiculos.model;

import java.time.LocalDate;

public abstract class Vehiculo {
    private String placa;
    private String marca;
    private String modelo;
    private LocalDate fechaFabricacion;
    

    //Constructor vacio
    public Vehiculo() {}

    //Constructor
    public Vehiculo(String placa, String marca, String modelo, LocalDate fechaFabricacion ) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.fechaFabricacion = fechaFabricacion;
        
    }

    //Getters y Setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    


}
