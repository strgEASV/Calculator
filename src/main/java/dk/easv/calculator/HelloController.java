package dk.easv.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML
    private TextArea txtUserInput;
    @FXML
    private Label lblLastClick;
    @FXML
    private Label lblCalculation;

    @FXML private Button lblZero, lblOne, lblTwo, lblThree, lblFour, lblFive, lblSix, lblSeven, lblEight, lblNine;

    @FXML private Button lblPlus, lblMinus, lblMultiply, lblDivide, lblEqual, lblC, lblPercentage, lblPlusMinus, lblComma;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @FXML
    public void initialize() {
        setNumberHandler(lblZero);
        setNumberHandler(lblOne);
        setNumberHandler(lblTwo);
        setNumberHandler(lblThree);
        setNumberHandler(lblFour);
        setNumberHandler(lblFive);
        setNumberHandler(lblSix);
        setNumberHandler(lblSeven);
        setNumberHandler(lblEight);
        setNumberHandler(lblNine);
        setNumberHandler(lblComma);

        lblPlus.setOnAction(e -> appendOperator("+"));
        lblMinus.setOnAction(e -> appendOperator("-"));
        lblMultiply.setOnAction(e -> appendOperator("*"));
        lblDivide.setOnAction(e -> appendOperator("/"));

        lblEqual.setOnAction(e -> calculate());
        lblC.setOnAction(e -> clear());
    }

    private void setNumberHandler(Button btn) {
        btn.setOnAction(e -> {
            if (startNewNumber) {
                txtUserInput.clear();
                startNewNumber = false;
            }
            txtUserInput.appendText(btn.getText());
            lblLastClick.setText(btn.getText());
        });
    }

    private void appendOperator(String op) {
        try {
            firstNumber = Double.parseDouble(txtUserInput.getText());
        } catch (NumberFormatException e) {
            firstNumber = 0;
        }
        operator = op;
        txtUserInput.appendText(" " + op + " ");
        startNewNumber = false;
        lblLastClick.setText(op);
    }

    private void calculate() {
        String input = txtUserInput.getText();
        if (!input.contains(operator)) return;

        String[] parts = input.split(" \\" + operator + " ");
        if (parts.length < 2) return;

        double secondNumber;
        try {
            secondNumber = Double.parseDouble(parts[1]);
        } catch (NumberFormatException e) {
            secondNumber = 0;
        }

        double result = 0;
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0)
                    result = firstNumber / secondNumber;
                else {
                    txtUserInput.setText("Error");
                    return;
                }
                break;
        }

        txtUserInput.setText(String.valueOf(result));
        lblCalculation.setText(firstNumber + " " + operator + " " + secondNumber + " = " + result);
        startNewNumber = true;
    }

    private void clear() {
        txtUserInput.clear();
        lblCalculation.setText("");
        lblLastClick.setText("Vymazáno");
        firstNumber = 0;
        operator = "";
        startNewNumber = true;
    }
}