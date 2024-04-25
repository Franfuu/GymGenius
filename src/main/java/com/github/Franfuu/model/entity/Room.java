package com.github.Franfuu.model.entity;


import java.util.List;
import java.util.Objects;

public class Room {
    private int RoomCode;
    private int NRooms;
    private List<Machine> machines;


    public Room(int roomCode, int NRooms, List<Machine> machines) {
        RoomCode = roomCode;
        this.NRooms = NRooms;
        this.machines = machines;
    }

    public Room() {

    }

    public int getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(int roomCode) {
        RoomCode = roomCode;
    }

    public int getNRooms() {
        return NRooms;
    }

    public void setNRooms(int NRooms) {
        this.NRooms = NRooms;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Room)) return false;
        Room room = (Room) object;
        return getRoomCode() == room.getRoomCode() && getNRooms() == room.getNRooms() && Objects.equals(getMachines(), room.getMachines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomCode(), getNRooms(), getMachines());
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomCode=" + RoomCode +
                ", NRooms=" + NRooms +
                ", machines=" + machines +
                '}';
    }
}
