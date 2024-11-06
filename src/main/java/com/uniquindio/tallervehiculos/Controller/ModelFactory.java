package com.uniquindio.tallervehiculos.Controller;

import com.uniquindio.tallervehiculos.model.*;


public class ModelFactory {

    private Empresa empresa;

    //Met
    private static class SingletonHolder {
        
        private final static ModelFactory eINSTANCE;

        static {
            try {
                eINSTANCE = new ModelFactory();
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Método para obtener la instancia de la clase
    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public ModelFactory(){

        empresa = new Empresa();
    }



    // Método para agregar un vehículo
    public int addVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            return empresa.addVehiculo(vehiculo);
        }
        return 0;
    }

    // Método para eliminar un vehículo
    public int removeVehiculo(Vehiculo vehiculo) {
        if (vehiculo != null) {
            return empresa.RemoveVehiculo(vehiculo);
        }
        return 0;
    }

    // Método para actualizar un vehículo
    public int updateVehiculo(Vehiculo vehiculo, String placa) {
        if (vehiculo != null) {
            return empresa.updateVehiculo(vehiculo, placa);
        }
        return 0;
    }

    // Método para agregar un cliente
    public int addCliente(Cliente cliente) {
        if (cliente != null) {
            return empresa.addCliente(cliente);
        }
        return 0;
    }

    // Método para eliminar un cliente
    public int removeCliente(Cliente cliente) {
        if (cliente != null) {
            return empresa.removeCliente(cliente);
        }
        return 0;
    }

    // Método para actualizar un cliente
    public int updateCliente(Cliente cliente, String password) {
        if (cliente != null) {
            return empresa.updateCliente(cliente, password);
        }
        return 0;
    }

    // Método para agregar una reserva
    public int addReserva(Reserva reserva) {
        if (reserva != null) {
            return empresa.addReserva(reserva);
        }
        return 0;
    }

    // Método para eliminar una reserva
    public int removeReserva(Reserva reserva) {
        if (reserva != null) {
            return empresa.removeReserva(reserva);
        }
        return 0;
    }

    // Método para actualizar una reserva
    public int updateReserva(Reserva reserva, String placa) {
        if (reserva != null) {
            return empresa.updateReserva(reserva, placa);
        }
        return 0;
    }

    // Método para autenticar un cliente
    public boolean autenticarCliente(String password) {
        if (password != null && !password.isEmpty()) {
            return empresa.autenticarCliente(password);
        }
        return false;
    }

    // Método para calcular el costo de una reserva
    public Reserva calcularCostoReserva(Vehiculo vehiculo, Cliente cliente, int dias) {
        if (vehiculo != null && cliente != null && dias > 0) {
            return empresa.calcularCostoReserva(vehiculo, cliente, dias);
        }
        return null;
    }

    // Métodos para encontrar vehículos por su placa
    public Vehiculo encontrarVehiculo(String placa) {
        if (placa != null && !placa.isEmpty()) {
            return empresa.encontrarVehiculo(placa);
        }
        return null;
    }

    public Auto encontrarAuto(String placa) {
        return empresa.encontrarAuto(placa);
    }

    public Moto encontrarMoto(String placa) {
        return empresa.encontrarMoto(placa);
    }

    public Camioneta encontrarCamioneta(String placa) {
        return empresa.encontrarCamioneta(placa);
    }

    // Método para quemar datos
    public void quemarDatos() {
        empresa.quemarClientes();
    }
}

