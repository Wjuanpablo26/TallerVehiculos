package com.uniquindio.tallervehiculos.model;
import java.time.LocalDate;

public class Moto extends Vehiculo {
    public CajaCambios cajaCambios;

    
    //Constructor
    public Moto(String placa, String marca, String modelo, LocalDate fechaFabricacion, CajaCambios cajaCambios) {
        super(placa, marca, modelo, fechaFabricacion );
        this.cajaCambios = cajaCambios;
    }

    //Constructor vacio
    public Moto() {
    }

    //Getters y Setters
    public CajaCambios getCajaCambios() {
        return cajaCambios;
    }

    public void setCajaCambios(CajaCambios cajaCambios) {
        this.cajaCambios = cajaCambios;
    }


}
