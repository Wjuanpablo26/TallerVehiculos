package com.uniquindio.tallervehiculos.View;

import com.uniquindio.tallervehiculos.Controller.VehiculoController;
import com.uniquindio.tallervehiculos.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class VehiculoViewController {

    @FXML private TextField placaField;
    @FXML private TextField marcaField;
    @FXML private TextField modeloField;
    @FXML private DatePicker fechaFabricacionPicker;
    @FXML private ComboBox<String> tipoVehiculoComboBox;

    @FXML private Label capacidadCargaLabel;
    @FXML private TextField capacidadCargaField;

    @FXML private Label numeroPuertasLabel;
    @FXML private TextField numeroPuertasField;

    @FXML private Label cajaCambiosLabel;
    @FXML private ComboBox<CajaCambios> cajaCambiosComboBox;

    @FXML private TableView<Vehiculo> vehiculoTable;
    @FXML private TableColumn<Vehiculo, String> placaColumn;
    @FXML private TableColumn<Vehiculo, String> marcaColumn;
    @FXML private TableColumn<Vehiculo, String> modeloColumn;
    @FXML private TableColumn<Vehiculo, LocalDate> fechaFabricacionColumn;

    @FXML
    private TableColumn<Vehiculo, Float> capacidadCargaColumn;
    @FXML
    private TableColumn<Vehiculo, Integer> numeroPuertasColumn;
    @FXML
    private TableColumn<Vehiculo, String> cajaCambiosColumn;

    VehiculoController vehiculoController;

    private ObservableList<Vehiculo> vehiculosList = FXCollections.observableArrayList();

    // Inicialización del controlador
    @FXML
    public void initialize() {
        vehiculoController = new VehiculoController();

        // Configuración de la tabla
        placaColumn.setCellValueFactory(new PropertyValueFactory<>("placa"));
        marcaColumn.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloColumn.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        fechaFabricacionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaFabricacion"));

        // Inicialización del ComboBox para el tipo de vehículo
        tipoVehiculoComboBox.setItems(FXCollections.observableArrayList("Camioneta", "Auto", "Moto"));
        tipoVehiculoComboBox.getSelectionModel().selectFirst();

        vehiculosList.addAll(vehiculoController.getAllVehiculo());
        vehiculoTable.setItems(vehiculosList);

        // Opciones de caja de cambios para Moto
        cajaCambiosComboBox.setItems(FXCollections.observableArrayList(CajaCambios.values()));

        // Añadir Listener para escuchar la selección en la tabla
        vehiculoTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mostrarDatosVehiculo(newValue);
            }
        });

        handleTipoVehiculoSeleccionado();
    }

    // Método para manejar la selección de un tipo de vehículo
    @FXML
    private void handleTipoVehiculoSeleccionado() {
        String tipoSeleccionado = tipoVehiculoComboBox.getSelectionModel().getSelectedItem();

        // Ocultar todos los campos específicos
        capacidadCargaLabel.setVisible(false);
        capacidadCargaField.setVisible(false);
        numeroPuertasLabel.setVisible(false);
        numeroPuertasField.setVisible(false);
        cajaCambiosLabel.setVisible(false);
        cajaCambiosComboBox.setVisible(false);

        // Mostrar el campo específico según el tipo de vehículo
        switch (tipoSeleccionado) {
            case "Camioneta":
                capacidadCargaLabel.setVisible(true);
                capacidadCargaField.setVisible(true);
                break;
            case "Auto":
                numeroPuertasLabel.setVisible(true);
                numeroPuertasField.setVisible(true);
                break;
            case "Moto":
                cajaCambiosLabel.setVisible(true);
                cajaCambiosComboBox.setVisible(true);
                break;
        }
    }

    // Método para crear un vehículo
    public Vehiculo crearVehiculo(){
        String tipoSeleccionado = tipoVehiculoComboBox.getSelectionModel().getSelectedItem();
        switch (tipoSeleccionado) {
            case "Camioneta":
                Camioneta camioneta = new Camioneta();
                camioneta.setPlaca(placaField.textProperty().get());
                camioneta.setMarca(marcaField.textProperty().get());
                camioneta.setModelo(modeloField.textProperty().get());
                camioneta.setFechaFabricacion(fechaFabricacionPicker.getValue());
                camioneta.setCapacidadCarga(Integer.parseInt(capacidadCargaField.getText()));
                return camioneta;
            case "Auto":
                Auto auto = new Auto();
                auto.setPlaca(placaField.textProperty().get());
                auto.setMarca(marcaField.textProperty().get());
                auto.setModelo(modeloField.textProperty().get());
                auto.setFechaFabricacion(fechaFabricacionPicker.getValue());
                auto.setNumeroPuertas(Integer.parseInt(numeroPuertasField.getText()));
                return auto;
            case "Moto":
                Moto moto = new Moto();
                moto.setPlaca(placaField.textProperty().get());
                moto.setMarca(marcaField.textProperty().get());
                moto.setModelo(modeloField.textProperty().get());
                moto.setFechaFabricacion(fechaFabricacionPicker.getValue());
                moto.setCajaCambios(cajaCambiosComboBox.getSelectionModel().getSelectedItem());
                return moto;

            default:
                return null;
        }
    }

    // Eventos de la interfaz agregar, actualizar y eliminar vehículo
    @FXML
    private void handleAgregar() {
        if (validarCampos()){
            vehiculoController.addVehiculo(crearVehiculo());
            vehiculosList.add(crearVehiculo());
            System.out.println("Vehiculo Creado");
            vehiculoTable.setItems(vehiculosList);
            limpiar();
        }else{
            System.out.println("Error crear Vehiculo");
        }
    }

    @FXML
    private void handleActualizar() {
        Vehiculo vehiculo = vehiculoTable.getSelectionModel().getSelectedItem();
        if (vehiculo != null) {
        if (validarCampos()){
            Vehiculo vehiculoNuevo = crearVehiculo();
            vehiculosList.set(vehiculosList.indexOf(vehiculo), vehiculoNuevo);
            vehiculoController.updateVehiculo(vehiculoNuevo,vehiculo.getPlaca());
            System.out.printf("Vehiculo Actualizado");
            vehiculoTable.setItems(vehiculosList);
            limpiar();
        }else {
            System.out.println("Llene todos los campos");
        }
    }else{
        System.out.println("Error crear Vehiculo");
    }
    }


    @FXML
    private void handleEliminar() {
        Vehiculo vehiculo = vehiculoTable.getSelectionModel().getSelectedItem();
        if (!vehiculo.equals(null)){
            vehiculosList.remove(vehiculo);
            vehiculoController.removeVehiculo(vehiculo);
            vehiculoTable.setItems(vehiculosList);
            limpiar();
            System.out.println("Vehiculo Eliminado");
        }else{
            System.out.println("Seleccione un vehiculo");
        }
    }

    // Método para limpiar los campos
    public void limpiar(){
        placaField.clear();
        marcaField.clear();
        modeloField.clear();
        fechaFabricacionPicker.setValue(null);
        tipoVehiculoComboBox.getSelectionModel().selectFirst();
        capacidadCargaField.clear();
        numeroPuertasField.clear();
        cajaCambiosComboBox.getSelectionModel().clearSelection();
    }

    //Limipar campos del formulario
    @FXML
    private void handleLimpiar() {
        limpiar();
    }

    // Método para validar los campos del formulario
    public boolean validarCampos() {
        // Validar que todos los campos de texto no estén vacíos
        if (placaField.getText().isEmpty() || marcaField.getText().isEmpty() || modeloField.getText().isEmpty()) {
            mostrarError("Todos los campos de texto deben ser llenados.");
            return false;
        }

        // Validar que se haya seleccionado una fecha de fabricación
        if (fechaFabricacionPicker.getValue() == null) {
            mostrarError("Debe seleccionar una fecha de fabricación.");
            return false;
        }

        // Validar que se haya seleccionado un tipo de vehículo
        String tipoSeleccionado = tipoVehiculoComboBox.getSelectionModel().getSelectedItem();
        if (tipoSeleccionado == null) {
            mostrarError("Debe seleccionar un tipo de vehículo.");
            return false;
        }

        // Validar campos específicos según el tipo de vehículo
        switch (tipoSeleccionado) {
            case "Camioneta":
                if (capacidadCargaField.getText().isEmpty()) {
                    mostrarError("Debe ingresar la capacidad de carga para la camioneta.");
                    return false;
                }
                try {
                    Float.parseFloat(capacidadCargaField.getText());
                } catch (NumberFormatException e) {
                    mostrarError("La capacidad de carga debe ser un número.");
                    return false;
                }
                break;

            case "Auto":
                if (numeroPuertasField.getText().isEmpty()) {
                    mostrarError("Debe ingresar el número de puertas para el auto.");
                    return false;
                }
                try {
                    Integer.parseInt(numeroPuertasField.getText());
                } catch (NumberFormatException e) {
                    mostrarError("El número de puertas debe ser un número entero.");
                    return false;
                }
                break;

            case "Moto":
                if (cajaCambiosComboBox.getSelectionModel().getSelectedItem() == null) {
                    mostrarError("Debe seleccionar el tipo de caja de cambios para la moto.");
                    return false;
                }
                break;

            default:
                mostrarError("Tipo de vehículo no válido.");
                return false;
        }

        // Si pasa todas las validaciones, retorna true
        return true;
    }

    // Método para mostrar los datos del vehículo seleccionado
    private void mostrarDatosVehiculo(Vehiculo vehiculo) {
        // Campos comunes
        placaField.setText(vehiculo.getPlaca());
        marcaField.setText(vehiculo.getMarca());
        modeloField.setText(vehiculo.getModelo());
        fechaFabricacionPicker.setValue(vehiculo.getFechaFabricacion());

        // Muestra campos específicos según el tipo de vehículo
        if (vehiculo instanceof Camioneta) {
            Camioneta camioneta = (Camioneta) vehiculo;
            capacidadCargaField.setText(String.valueOf(camioneta.getCapacidadCarga()));
            capacidadCargaField.setVisible(true);
            numeroPuertasField.setVisible(false);
            cajaCambiosComboBox.setVisible(false);
        } else if (vehiculo instanceof Auto) {
            Auto auto = (Auto) vehiculo;
            numeroPuertasField.setText(String.valueOf(auto.getNumeroPuertas()));
            capacidadCargaField.setVisible(false);
            numeroPuertasField.setVisible(true);
            cajaCambiosComboBox.setVisible(false);
        } else if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            cajaCambiosComboBox.setValue(moto.getCajaCambios());
            capacidadCargaField.setVisible(false);
            numeroPuertasField.setVisible(false);
            cajaCambiosComboBox.setVisible(true);
        }
    }

    // Método para mostrar un mensaje de error
    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error de Validación");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}

