package com.example;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
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
            calculate(locals);
        });
    }

    private Map<String, String> localizedStrings;

    // public void onCalculateClick(ActionEvent actionEvent) {
    // try {
    // double weight = Double.parseDouble(tfWeight.getText());
    // double height = Double.parseDouble(tfHeight.getText()) / 100.0;
    // double bmi = weight / (height * height);
    // DecimalFormat df = new DecimalFormat("#0.00");
    // lblResult.setText(localizedStrings.getOrDefault("result", "Your BMI is") + "
    // " + df.format(bmi));
    // // Save to database
    // String language = Locale.getDefault().getLanguage(); // or store current
    // locale
    // BMIResultService.saveResult(weight, height, bmi, language);
    // } catch (NumberFormatException e) {
    // lblResult.setText(localizedStrings.getOrDefault("invalid", "Invalid input"));
    // }
    // }

    private void update(Locals locals, int language) {
        locals.setLang(language);
        localizedStrings = locals.getLocalizedString();

        lblWeight.setText(localizedStrings.getOrDefault("weight", "Weight"));
        lblHeight.setText(localizedStrings.getOrDefault("height", "Height"));
        btnCalculate.setText(localizedStrings.getOrDefault("calculate", "Calculate"));

    }

    private void calculate(Locals locals) {
        try {
            double weight = Float.parseFloat(tfWeight.getText());
            double height = Float.parseFloat(tfHeight.getText());

            height = height / 100;
            double bmi = weight / (height * height);
            DecimalFormat df = new DecimalFormat("#.##");
            String language = locals.getLangString();
            BMIResultService.saveResult(weight, height, bmi, language);
            calculation.setText(localizedStrings.getOrDefault("result", "Your BMI is") + " " + df.format(bmi));
        } catch (Exception e) {
            calculation.setText(localizedStrings.getOrDefault("invalid", "Invalid input"));
        }

    }
}
