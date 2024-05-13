package com.github.Franfuu.test;

import com.github.Franfuu.model.dao.MachineDAO;
import com.github.Franfuu.model.dao.RoomDAO;
import com.github.Franfuu.model.entity.Machine;
import com.github.Franfuu.model.entity.Room;
import java.sql.SQLException;

public class TestInsert2 {
    public static void main(String[] args) throws SQLException {
       // Room r = new Room(2,10,null);
        //RoomDAO.save(r);
        Machine m = new Machine(1,null,"polea",null);
        MachineDAO.save(m);
    }
}
