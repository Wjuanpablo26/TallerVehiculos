package com.uniquindio.tallervehiculos.Controller;

import com.uniquindio.tallervehiculos.model.Vehiculo;

import java.util.ArrayList;

public class VehiculoController {

    ModelFactory modelFactory;

    //Se instancia el ModelFactory
    public VehiculoController() {
        this.modelFactory = modelFactory.getInstance(); 
    }

    // Método para agregar un vehículo
    public int addVehiculo(Vehiculo vehiculo) {
        return modelFactory.addVehiculo(vehiculo);
    }

    // Método para eliminar un vehículo
    public int removeVehiculo(Vehiculo vehiculo) {
        return modelFactory.removeVehiculo(vehiculo);
    }

    // Método para actualizar un vehículo
    public int updateVehiculo(Vehiculo vehiculo, String placa) {
        return modelFactory.updateVehiculo(vehiculo, placa);
    }

    // Método para obtener todos los vehículos
    public ArrayList<Vehiculo> getAllVehiculo() {
        return modelFactory.getEmpresa().getVehiculos();
    }
}
