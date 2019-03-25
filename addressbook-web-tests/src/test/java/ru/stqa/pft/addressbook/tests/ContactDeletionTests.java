package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeletion(){
        app.getNavigationHelper().gotoHomepage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", "test1"));
            app.getContactHelper().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
        }
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().submitContactDeletion();
        app.getNavigationHelper().gotoHomepage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
