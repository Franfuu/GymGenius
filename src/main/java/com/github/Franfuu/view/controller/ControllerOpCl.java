package com.github.Franfuu.view.controller;

import com.github.Franfuu.App;

import java.io.IOException;


public abstract class ControllerOpCl {
    App app;

    public void setApp(App app) {
        this.app = app;
    }

    public abstract void onOpen(Object input) throws IOException;

    public abstract void onClose(Object output);
}


