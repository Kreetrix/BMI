package com.example;

import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public Button btnCalculate;

    @FXML
    public Button btnEn;

    @FXML
    public Button btnFr;

    @FXML
    public Button btnUr;

    @FXML
    public Button btnVi;

    @FXML
    private TextField tfHeight;

    @FXML
    private TextField tfWeight;

    @FXML
    private Label lblWeight;

    @FXML
    private Label lblHeight;

    @FXML
    private Label calculation;

    @FXML
    private void initialize() {
        Locals locals = Locals.getInstance();
        locals.setLang(1);

        btnEn.setOnAction(e -> update(locals, 1));
        btnFr.setOnAction(e -> update(locals, 2));
        btnUr.setOnAction(e -> update(locals, 3));
        btnVi.setOnAction(e -> update(locals, 4));

        btnCalculate.setOnAction(e -> {
            ResourceBundle rb = locals.getBundle();
            calculate(rb);
        });
    }

    private void update(Locals locals, int language) {
        locals.setLang(language);
        ResourceBundle rb = locals.getBundle();
        lblWeight.setText(rb.getString("weight"));
        lblHeight.setText(rb.getString("height"));
        btnCalculate.setText(rb.getString("calculate"));

    }

    private void calculate(ResourceBundle rb) {
        try {
            double weight = Float.parseFloat(tfWeight.getText());
            double height = Float.parseFloat(tfHeight.getText());

            height = height / 100;
            double bmi = weight / (height * height);
            DecimalFormat df = new DecimalFormat("#.##");
            calculation.setText(rb.getString("bpmResults") + df.format(bmi));
        } catch (Exception e) {
            calculation.setText(rb.getString("invalid"));
        }

    }
}
