package com.github.Franfuu.test;

import com.github.Franfuu.model.connection.ConnectionProperties;
import com.github.Franfuu.utils.XMLManager;

public class saveConnection {
    public static void main(String[] args) {
        ConnectionProperties c = new ConnectionProperties("localhost","3306","gymgenius","root","root");
        XMLManager.writeXML(c,"connection.xml");
    }
}
