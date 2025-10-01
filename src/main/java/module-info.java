module dk.easv.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens dk.easv.calculator to javafx.fxml;
    exports dk.easv.calculator;
}