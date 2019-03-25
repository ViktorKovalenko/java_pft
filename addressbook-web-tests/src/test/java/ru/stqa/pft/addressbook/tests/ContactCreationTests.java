package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {





  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Viktor", "Kovalenko", "+380993020583", "vsutogan@gmail.com", "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }


}
