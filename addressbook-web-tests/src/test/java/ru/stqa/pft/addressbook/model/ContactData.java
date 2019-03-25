package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private final String name;
    private final String surname;
    private final String phone;
    private final String email;
    private final String id;
    private String group;

    public ContactData(String name, String surname, String phone, String email, String group) {
        this.id = null;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }


    public ContactData(String id, String name, String surname, String phone, String email, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id);
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

}
