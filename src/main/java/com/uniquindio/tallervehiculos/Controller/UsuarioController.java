package com.uniquindio.tallervehiculos.Controller;

import com.uniquindio.tallervehiculos.model.Cliente;
import com.uniquindio.tallervehiculos.model.Reserva;

import java.util.ArrayList;

public class UsuarioController {

    ModelFactory modelFactory;

    //Se crea el ModelFactory
    public UsuarioController() {
        this.modelFactory = modelFactory.getInstance();  
    }

    // Método para agregar un cliente
    public int addCliente(Cliente cliente) {
        return modelFactory.addCliente(cliente);
    }

    // Método para eliminar un cliente
    public int removeCliente(Cliente cliente) {
        return modelFactory.removeCliente(cliente);
    }

    // Método para actualizar un cliente
    public int updateCliente(Cliente cliente, String nombre) {
        return modelFactory.updateCliente(cliente, nombre);
    }

    // Método para obtener todas las reservas
    public ArrayList<Reserva> getAllReserva() {
        return modelFactory.getEmpresa().getReservas();
    }

    // Método para obtener todos los clientes
    public ArrayList<Cliente> getAllCliente() {
        return modelFactory.getEmpresa().getClientes();
    }
}
