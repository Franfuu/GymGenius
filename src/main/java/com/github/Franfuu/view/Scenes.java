package com.github.Franfuu.view;

public enum Scenes {
    REGADMIN("view/regAdmin.fxml"),
    CLIENTLOGIN("view/clientLogin"),
    MAINPAGE("view/mainPage.fxml"),
    ROOT("view/layout.fxml"),
    ADDCLIENT("view/addClient.fxml");


    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
