package com.github.Franfuu.test;

import com.github.Franfuu.model.dao.ClientDAO;
import com.github.Franfuu.model.entity.Client;

import java.sql.SQLException;

public class TestInsert {
    public static void main(String[] args) throws SQLException {
        Client c = new Client("Franfarsu","Furias","a@a.com","123456","12345678","Macho");
        ClientDAO.build().save(c);
    }

}
