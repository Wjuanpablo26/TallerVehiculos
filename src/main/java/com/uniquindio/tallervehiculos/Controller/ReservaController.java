package com.uniquindio.tallervehiculos.Controller;

import com.uniquindio.tallervehiculos.model.Cliente;
import com.uniquindio.tallervehiculos.model.Reserva;
import com.uniquindio.tallervehiculos.model.Vehiculo;

import java.util.ArrayList;

public class ReservaController {


    ModelFactory modelFactory;

    public ReservaController() {
        this.modelFactory = modelFactory.getInstance();
    }

    // Método para agregar una reserva
    public int addReserva(Reserva reserva) {
        return modelFactory.addReserva(reserva);
    }

    // Método para eliminar una reserva
    public int removeReserva(Reserva reserva) {
        return modelFactory.removeReserva(reserva);
    }

    // Método para obtener todas las reservas
    public ArrayList<Reserva> getReservas() {
        return modelFactory.getEmpresa().getReservas();
    }

    // Método para obtener todos los clientes
    public ArrayList<Cliente> getClientes() {
        return modelFactory.getEmpresa().getClientes();
    }

    // Método para obtener todos los vehículos
    public ArrayList<Vehiculo> getVehiculos() {
        return modelFactory.getEmpresa().getVehiculos();
    }

}

