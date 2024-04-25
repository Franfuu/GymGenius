package com.github.Franfuu.model.entity;

public class Machine {
    private int MachineCode;
    private int RoomCode;
    private MachineType MachineType;

    public Machine(int machineCode, int roomCode, MachineType machineType) {
        MachineCode = machineCode;
        RoomCode = roomCode;
        MachineType = machineType;
    }

    public Machine() {

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

    public com.github.Franfuu.model.entity.MachineType getMachineType() {
        return MachineType;
    }

    public void setMachineType(com.github.Franfuu.model.entity.MachineType machineType) {
        MachineType = machineType;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Machine)) return false;
        Machine machine = (Machine) object;
        return getMachineCode() == machine.getMachineCode() && getRoomCode() == machine.getRoomCode() && getMachineType() == machine.getMachineType();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "MachineCode=" + MachineCode +
                ", RoomCode=" + RoomCode +
                ", MachineType=" + MachineType +
                '}';
    }
}
