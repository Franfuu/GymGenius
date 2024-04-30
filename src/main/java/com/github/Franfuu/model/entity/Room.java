package com.github.Franfuu.model.entity;


import java.util.List;
import java.util.Objects;

public class Room {
    private int RoomCode;
    private int NRoom;
    private List<Machine> machines;


    public Room(int roomCode, int NRooms, List<Machine> machines) {
        RoomCode = roomCode;
        this.NRoom = NRooms;
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

    public int getNRoom() {
        return NRoom;
    }

    public void setNRooms(int NRooms) {
        this.NRoom = NRooms;
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
        return getRoomCode() == room.getRoomCode() && getNRoom() == room.getNRoom() && Objects.equals(getMachines(), room.getMachines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomCode(), getNRoom(), getMachines());
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomCode=" + RoomCode +
                ", NRooms=" + NRoom +
                ", machines=" + machines +
                '}';
    }
}
