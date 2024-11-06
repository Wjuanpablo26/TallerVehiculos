module com.uniquindio.tallervehiculos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.uniquindio.tallervehiculos to javafx.fxml;
    exports com.uniquindio.tallervehiculos;

    opens com.uniquindio.tallervehiculos.Controller to javafx.fxml;
    opens com.uniquindio.tallervehiculos.model to javafx.fxml;
    opens com.uniquindio.tallervehiculos.View to javafx.fxml;

    exports com.uniquindio.tallervehiculos.Controller;
    exports com.uniquindio.tallervehiculos.model;
    exports com.uniquindio.tallervehiculos.View;
}