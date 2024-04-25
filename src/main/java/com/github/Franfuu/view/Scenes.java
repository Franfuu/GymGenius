package com.github.Franfuu.view;

public enum Scenes {
    REGGYM("view/regGym.fxml"),
    MAINPAGE("view/mainPage.fxml"),
    ADDCLIENT("view/addClient.fxml");

    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}

