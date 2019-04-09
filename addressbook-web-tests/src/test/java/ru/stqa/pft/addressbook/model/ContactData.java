package ru.stqa.pft.addressbook.model;

import java.io.File;
import java.util.Objects;

public class ContactData {

    private  String name;
    private  String surname;
    private  String phone;
    private  String email;
    private int id = Integer.MAX_VALUE;
    private String group;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;
    private String email2;
    private String email3;
    private String address;
    private String allEmails;
    private String addressFromHomePage;
    private File photo;




    public File getPhoto() {
        return photo;
    }

    public String getAddressFromHomePage() {
        return addressFromHomePage;
    }
    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }
    public String getAllEmails() {
        return allEmails;
    }
    public String getAddress() {
        return address;
    }

    public int getId() {
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



    public String getHomePhone() {
        return homePhone;
    }


    public String getMobilePhone() {
        return mobilePhone;
    }



    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    public ContactData withAllEmails (String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }
    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }
    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }
    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }
    public ContactData withAddressFromHomePage(String addressFromHomePage) {
        this.addressFromHomePage = addressFromHomePage;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }



    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }



    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }









    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, id);
    }



    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

}

