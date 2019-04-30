package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Column(name = "deprecated")
    private Timestamp deprecated;

    @Expose
    @Column(name = "firstname")

    private  String name;
    @Expose
    @Column(name = "lastname")

    private  String surname;
    @Expose
    @Transient
    private  String phone;
    @Transient
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private  String email;
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id;
    @Transient
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Transient
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Transient
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    @Transient
    private String email2;
    @Expose
    @Transient
    private String email3;
    @Expose
    @Transient
    private String address;
    @Transient
    private String allEmails;
    @Transient
    private String addressFromHomePage;
    @Expose
    @Transient
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();





    public File getPhoto() {
        return new File(photo);
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

    public Groups getGroups() { return new Groups(groups);
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
        this.photo = photo.getPath();
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
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, surname,id);
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

    public ContactData ingroup(GroupData group) {
        groups.add(group);
        return this;

    }
}

