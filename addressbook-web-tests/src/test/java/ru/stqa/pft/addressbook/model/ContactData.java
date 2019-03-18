package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String name;
    private final String surname;
    private final String phone;
    private final String email;
    private String group;

    public ContactData(String name, String surname, String phone, String email, String group) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.group = group;
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
