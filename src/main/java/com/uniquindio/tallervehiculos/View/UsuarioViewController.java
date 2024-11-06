package com.uniquindio.tallervehiculos.View;

import com.uniquindio.tallervehiculos.Controller.UsuarioController;
import com.uniquindio.tallervehiculos.model.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class UsuarioViewController {

    @FXML private TextField nombreField;
    @FXML private PasswordField passwordField;

    @FXML private TableView<Cliente> clientesTable;
    @FXML private TableColumn<Cliente, String> nombreColumn;
    @FXML private TableColumn<Cliente, String> passwordColumn;
    @FXML private TableColumn<Cliente, String> reservasColumn;

    private ObservableList<Cliente> clientesList = FXCollections.observableArrayList();

    UsuarioController usuarioController;

    // Referencia a ReservaViewController
    private ReservaViewController reservaViewController; 

    // Método para establecer la referencia
    public void setReservaViewController(ReservaViewController reservaViewController) {
        this.reservaViewController = reservaViewController;
    }

    // Inicialización del controlador
    @FXML
    public void initialize() {
        // Se crea el controlador
        usuarioController = new UsuarioController();

        // Se establecen las propiedades de las columnas
        nombreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        passwordColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        reservasColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getReservas().size() + " reservas"));

        // Se cargan los clientes en la tabla
        clientesList.addAll(usuarioController.getAllCliente());
        clientesTable.setItems(clientesList);

        
        clientesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mostrarDatosCliente(newValue);
            }
        });

    }

    // Método para mostrar los datos de un cliente
    private void mostrarDatosCliente(Cliente cliente) {
        // Campos comunes
        nombreField.setText(cliente.getNombre());
        passwordField.setText(cliente.getPassword());
    }


    // Eventos de la interfaz actualizar cliente
    @FXML
    void handleActualizarCliente(ActionEvent event) {
        Cliente selectedCliente = clientesTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            selectedCliente.setNombre(nombreField.getText());
            usuarioController.updateCliente(selectedCliente,selectedCliente.getPassword());
            clientesTable.refresh(); // Refresca la tabla para mostrar los cambios
        }
    }

    // Eventos de la interfaz agregar cliente
    @FXML
    void handleAgregarCliente(ActionEvent event) {
        String nombre = nombreField.getText();
        String password = passwordField.getText();

        if (!nombre.isEmpty() && !password.isEmpty()) {
            Cliente nuevoCliente = new Cliente(nombre, password);
            usuarioController.addCliente(nuevoCliente);
            clientesList.add(nuevoCliente);
            clientesTable.setItems(clientesList);
            if (reservaViewController != null) {
                reservaViewController.actualizarClientes(usuarioController.getAllCliente());
            }
            System.out.println("Cliente agregado");
            limpiarFormulario();
        }
    }

    // Eventos de la interfaz eliminar cliente
    @FXML
    void handleEliminarCliente(ActionEvent event) {
        Cliente selectedCliente = clientesTable.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            usuarioController.removeCliente(selectedCliente);
            clientesList.remove(selectedCliente);
            clientesTable.setItems(clientesList);
            System.out.println("Cliente eliminado");
            limpiarFormulario();
        }
    }

    // Método para limpiar los campos
    private void limpiarFormulario() {
        nombreField.clear();
        passwordField.clear();
    }

}

