module com.martaarjona.cronometro {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;

    opens com.martaarjona.cronometro to javafx.fxml;
    exports com.martaarjona.cronometro;
}
