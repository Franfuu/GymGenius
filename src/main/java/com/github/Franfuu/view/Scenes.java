package com.github.Franfuu.view;

public enum Scenes {
    REGADMIN("view/regAdmin.fxml"),
    CLIENTLOGIN("view/clientLogin.fxml"),
    CHOOSEROLE("view/chooseRole.fxml"),
    CLIENTSHOWMACHINES("view/clientShowMachines.fxml"),
    MAINPAGE("view/mainPage.fxml"),
    ROOT("view/layout.fxml"),
    ADDCLIENT("view/addClient.fxml"),
    ADDROOM("view/addRoom.fxml"),
    ADDMACHINETOCLIENT("view/addMachineToClient.fxml"),
    SHOWMACHINES("view/showMachines.fxml"),
    ADDMACHINE("view/addMachine.fxml");

    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
