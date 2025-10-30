package com.example;

import java.util.Locale;
import java.util.ResourceBundle;

public class Locals {
    private static Locals instance;
    private ResourceBundle resourceBundle;
    private int language = 1;

    private Locals() {
        setLang(language);
    }

    public static Locals getInstance() {
        if (instance == null) {
            instance = new Locals();
        }
        return instance;
    }

    private void local(String languageCode) {
        Locale locale = new Locale(languageCode);
        resourceBundle = ResourceBundle.getBundle("locals", locale);
    }

    public void setLang(int language) {
        this.language = language;
        switch (language) {
            case 1 -> local("en");
            case 2 -> local("fr");
            case 3 -> local("ur");
            case 4 -> local("vi");
            default -> System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    public ResourceBundle getBundle() {
        return resourceBundle;
    }
}
