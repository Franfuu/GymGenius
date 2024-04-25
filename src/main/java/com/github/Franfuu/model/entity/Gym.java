package com.github.Franfuu.model.entity;

import java.util.List;
import java.util.Objects;

public class Gym {
    private int GymCode;
    private String Name;
    private int PostalCode;


    private String Adress;
    private List<Client> clients;

    public Gym() {
    }

    public Gym(int gymCode, String name, int postalCode, String adress, List<Client> clients) {
        GymCode = gymCode;
        Name = name;
        PostalCode = postalCode;
        Adress = adress;
        this.clients = clients;
    }

    public int getGymCode() {
        return GymCode;
    }

    public void setGymCode(int gymCode) {
        GymCode = gymCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(int postalCode) {
        PostalCode = postalCode;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
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
        if (!(object instanceof Gym)) return false;
        Gym gym = (Gym) object;
        return getGymCode() == gym.getGymCode() && getPostalCode() == gym.getPostalCode() && Objects.equals(getName(), gym.getName()) && Objects.equals(getAdress(), gym.getAdress()) && Objects.equals(getClients(), gym.getClients());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGymCode(), getName(), getPostalCode(), getAdress(), getClients());
    }

    @Override
    public String toString() {
        return "Gym{" +
                "GymCode=" + GymCode +
                ", Name='" + Name + '\'' +
                ", PostalCode=" + PostalCode +
                ", Adress='" + Adress + '\'' +
                ", clients=" + clients +
                '}';
    }


}
