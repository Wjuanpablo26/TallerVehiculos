package com.uniquindio.tallervehiculos.model;

import java.time.LocalDate;

public class Reserva {

    private LocalDate fechaInicio;
    private int cantidadDias;
    private String tipoVehiculo;
    private String placaVehiculo;
    private Cliente cliente;
    private double precio;

    // Constructor
    public Reserva(LocalDate fechaInicio, int cantidadDias, String tipoVehiculo, String placaVehiculo, Cliente cliente, double precio) {
        this.fechaInicio = fechaInicio;
        this.cantidadDias = cantidadDias;
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = placaVehiculo;
        this.cliente = cliente;
        this.precio = precio;
    }

    // Constructor Vacio
    public Reserva() {
    }

    // Getters y Setters
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}