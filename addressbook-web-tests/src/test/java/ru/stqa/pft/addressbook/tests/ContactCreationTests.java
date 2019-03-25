package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase {





  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getConactList();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getConactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }


}
