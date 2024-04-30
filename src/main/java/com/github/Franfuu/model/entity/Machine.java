package com.github.Franfuu.model.entity;

import java.util.List;
import java.util.Objects;

public class Machine {
    private int MachineCode;
    private int RoomCode;
    private String MachineType;


    private List<Client> clients;

    public Machine() {

    }

    public Machine(int machineCode, int roomCode, String machineType, List<Client> clients) {
        MachineCode = machineCode;
        RoomCode = roomCode;
        MachineType = machineType;
        this.clients = clients;
    }

    public int getMachineCode() {
        return MachineCode;
    }

    public void setMachineCode(int machineCode) {
        MachineCode = machineCode;
    }

    public int getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(int roomCode) {
        RoomCode = roomCode;
    }

    public String getMachineType() {
        return MachineType;
    }

    public void setMachineType(String machineType) {
        MachineType = machineType;
    }
    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Machine)) return false;
        Machine machine = (Machine) object;
        return getMachineCode() == machine.getMachineCode() && getRoomCode() == machine.getRoomCode() && Objects.equals(getMachineType(), machine.getMachineType()) && Objects.equals(getClients(), machine.getClients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMachineCode(), getRoomCode(), getMachineType(), getClients());
    }
}
