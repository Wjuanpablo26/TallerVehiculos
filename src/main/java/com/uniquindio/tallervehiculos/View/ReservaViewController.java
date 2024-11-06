package com.uniquindio.tallervehiculos.View;

import com.uniquindio.tallervehiculos.Controller.ReservaController;
import com.uniquindio.tallervehiculos.model.Cliente;
import com.uniquindio.tallervehiculos.model.Reserva;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaViewController {

    @FXML private DatePicker fechaInicioPicker;
    @FXML private TextField cantidadDiasField;
    @FXML private TextField tipoVehiculoField;
    @FXML private TextField placaVehiculoField;
    @FXML private ComboBox<Cliente> clienteComboBox;
    @FXML private TextField precioField;
    @FXML private TableView<Reserva> reservaTable;
    @FXML private TableColumn<Reserva, LocalDate> fechaInicioColumn;
    @FXML private TableColumn<Reserva, Integer> cantidadDiasColumn;
    @FXML private TableColumn<Reserva, String> tipoVehiculoColumn;
    @FXML private TableColumn<Reserva, String> placaVehiculoColumn;
    @FXML private TableColumn<Reserva, Cliente> clienteColumn;
    @FXML private TableColumn<Reserva, Double> precioColumn;


    ReservaController reservaController;

    // Inicialización del controlador
    @FXML
    public void initialize() {
        reservaController = new ReservaController();

        fechaInicioColumn.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        cantidadDiasColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadDias"));
        tipoVehiculoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoVehiculo"));
        placaVehiculoColumn.setCellValueFactory(new PropertyValueFactory<>("placaVehiculo"));
        clienteColumn.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        //Actualizar la lista de clientes
        actualizarClientes(reservaController.getClientes()); 
        cargarReservas();
    }

    // Método para cargar las reservas en la tabla
    private void cargarReservas() {
        
        reservaTable.setItems(FXCollections.observableArrayList(reservaController.getReservas()));
    }

    // Método para actualizar la lista de clientes
    public void actualizarClientes(ArrayList<Cliente> clientes) {
        clienteComboBox.setItems(FXCollections.observableArrayList(clientes));
    }

    // Método para crear una reserva
    public Reserva crearReserva(){
        Reserva reserva = new Reserva();
        reserva.setFechaInicio(fechaInicioPicker.getValue());
        reserva.setTipoVehiculo(tipoVehiculoField.getText());
        reserva.setPlacaVehiculo(placaVehiculoField.getText());
        reserva.setCliente(clienteComboBox.getValue());
        reserva.setCantidadDias(Integer.parseInt(cantidadDiasField.getText()));
        return reserva;
    }

    // Eventos de la interfaz gráfica
    @FXML
    void handleActualizar(ActionEvent event) {

    }

    // Evento para agregar una reserva
    @FXML
    void handleAgregar(ActionEvent event) {
        if (validarCampos()){
            reservaController.addReserva(crearReserva());
            System.out.println("Reserva agregada");
            cargarReservas();
            limpiarCampos();
        }
    }

    // Evento para eliminar una reserva
    @FXML
    void handleEliminar(ActionEvent event) {
        Reserva selectedCliente = reservaTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            reservaController.removeReserva(selectedCliente);
            System.out.println("Cliente eliminado");
            cargarReservas();
            limpiarCampos();
        }
    }

    // Evento para limpiar los campos
    @FXML
    void handleLimpiar(ActionEvent event) {
        limpiarCampos();
    }

    // Método para validar los campos
    private boolean validarCampos() {
        // Validar fecha de inicio
        if (fechaInicioPicker.getValue() == null) {
            showErrorMessage("La fecha de inicio es obligatoria.");
            return false;
        }

        // Validar cantidad de días
        if (cantidadDiasField.getText().isEmpty()) {
            showErrorMessage("La cantidad de días es obligatoria.");
            return false;
        }

        try {
            Integer.parseInt(cantidadDiasField.getText()); 
        } catch (NumberFormatException e) {
            showErrorMessage("La cantidad de días debe ser un número válido.");
            return false;
        }

        // Validar tipo de vehículo
        if (tipoVehiculoField.getText().isEmpty()) {
            showErrorMessage("El tipo de vehículo es obligatorio.");
            return false;
        }

        // Validar placa de vehículo
        if (placaVehiculoField.getText().isEmpty()) {
            showErrorMessage("La placa del vehículo es obligatoria.");
            return false;
        }

        // Validar cliente
        if (clienteComboBox.getValue() == null) {
            showErrorMessage("Debe seleccionar un cliente.");
            return false;
        }

        // Validar precio
        if (precioField.getText().isEmpty()) {
            showErrorMessage("El precio es obligatorio.");
            return false;
        }

        try {
            Double.parseDouble(precioField.getText()); 
        } catch (NumberFormatException e) {
            showErrorMessage("El precio debe ser un valor numérico.");
            return false;
        }

        return true; 
    }

    // Método para mostrar un mensaje de error
    private void showErrorMessage(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error de Validación");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        fechaInicioPicker.setValue(null);
        cantidadDiasField.clear();
        tipoVehiculoField.clear();
        placaVehiculoField.clear();
        clienteComboBox.getSelectionModel().clearSelection();
        precioField.clear();
    }




}

