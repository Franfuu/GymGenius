package com.github.Franfuu.model.entity;

import java.util.Objects;

public class Client {
    private int ClientCode;

    private Machine machine;
    private String Name;
    private String Surname;
    private String Dni;
    private String Phone;
    private String Sex;

    public Client() {
    }

    public Client(int clientCode, String name, String surname, String dni, String phone, String sex) {
        ClientCode = clientCode;
        Name = name;
        Surname = surname;
        Dni = dni;
        Phone = phone;
        Sex = sex;
    }

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

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Client)) return false;
        Client client = (Client) object;
        return getClientCode() == client.getClientCode() && Objects.equals(getName(), client.getName()) && Objects.equals(getSurname(), client.getSurname()) && Objects.equals(getDni(), client.getDni()) && Objects.equals(getPhone(), client.getPhone()) && Objects.equals(getSex(), client.getSex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientCode(), getName(), getSurname(), getDni(), getPhone(), getSex());
    }

    @Override
    public String toString() {
        return "Client{" +
                "ClientCode=" + ClientCode +
                ", Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Dni='" + Dni + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Sex='" + Sex + '\'' +
                '}';
    }
}
