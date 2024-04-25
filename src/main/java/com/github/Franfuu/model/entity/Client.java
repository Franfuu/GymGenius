package com.github.Franfuu.model.entity;

import javafx.scene.image.Image;

import java.util.Objects;

public class Client {
    private int ClientCode;
    private String Name;
    private String Surname;
    private String Dni;
    private String Phone;
    private static Image Photo;
    private static Sex Sex;

    public int getClientCode() {
        return ClientCode;
    }

    public void setClientCode(int clientCode) {
        ClientCode = clientCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Image getPhoto() {
        return Photo;
    }

    public static void setPhoto(Image photo) {
        Photo = photo;
    }

    public com.github.Franfuu.model.entity.Sex getSex() {
        return Sex;
    }

    public static void setSex(com.github.Franfuu.model.entity.Sex sex) {
        Sex = sex;
    }

    public Client(int clientCode, String name, String surname, String dni, String phone, Image photo, com.github.Franfuu.model.entity.Sex sex) {
        ClientCode = clientCode;
        Name = name;
        Surname = surname;
        Dni = dni;
        Phone = phone;
        Photo = photo;
        Sex = sex;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Client)) return false;
        Client client = (Client) object;
        return getClientCode() == client.getClientCode() && Objects.equals(getName(), client.getName()) && Objects.equals(getSurname(), client.getSurname()) && Objects.equals(getDni(), client.getDni()) && Objects.equals(getPhone(), client.getPhone()) && Objects.equals(getPhoto(), client.getPhoto()) && getSex() == client.getSex();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ClientCode=" + ClientCode +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Dni='" + Dni + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Photo=" + Photo +
                ", Sex=" + Sex +
                '}';
    }


}
