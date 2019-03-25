package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase{
   @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomepage();
       List<ContactData> before = app.getContactHelper().getConactList();
       if (! app.getContactHelper().isThereAContact()){
           app.getContactHelper().createContact(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", "test1"));
           app.getContactHelper().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
       }
        app.getContactHelper().initContactModification(before.size() -1);
        app.getContactHelper().fillContactForm(new ContactData("Viktor", null, null, "vsutogan@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
       List<ContactData> after = app.getContactHelper().getConactList();
       Assert.assertEquals(after.size(), before.size());
    }
}
