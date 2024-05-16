package com.github.Franfuu.view;

public enum Scenes {
    REGADMIN("view/regAdmin.fxml"), //REGISTRO ADMINISTRADOR
    CLIENTLOGIN("view/clientLogin.fxml"), //LOGIN CLIENTE
    CHOOSEROLE("view/chooseRole.fxml"), //ELEGIR ROL
    MAINPAGE("view/mainPage.fxml"), //PAGINA PRINCIPAL
    ROOT("view/layout.fxml"), //PAGINA PRINCIPAL
    ADDCLIENT("view/addClient.fxml"),   //AÑADIR CLIENTE
    ADDMACHINE("view/addMachine.fxml"),   //AÑADIR MAQUINA
    ADDROOM("view/addRoom.fxml"),       //AÑADIR SALA
    ADDMACHINETOCLIENT("view/addMachineToClient.fxml"),     //AÑADIR MAQUINA A CLIENTE
    DELETEMACHINETOCLIENT("view/deleteMachineFromClient.fxml"),     //ELIMINAR MAQUINA DE CLIENTE
    MACHINESTOCLIENT("view/clientShowMachines.fxml"),     //VER MAQUINAS DE CLIENTE
    SHOWMACHINESTOCLIENT("view/showMachinesToClient.fxml"),     //VER MAQUINAS DE CLIENTE SHOW
    SHOWMACHINES("view/showMachines.fxml"),     //VER MAQUINAS
    DELETECLIENT("view/deleteClient.fxml"),     //ELIMINAR CLIENTE
    DELETEMACHINE("view/deleteMachine.fxml"),     //ELIMINAR MAQUINA
    DELETEROOM("view/deleteRoom.fxml");     //ELIMINAR SALA

    private final String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
