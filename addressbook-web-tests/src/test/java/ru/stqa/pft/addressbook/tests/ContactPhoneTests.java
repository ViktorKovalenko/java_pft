package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withName("Viktor").withSurname("Kovalenko").withEmail("vsutogan@gmail.com")
                    .withEmail2("test1@mail.com").withEmail3("test2@mail.com").withAddress("Kyiv")
                    .withGroup("test1").withHomePhone("1111").withMobilePhone("222222").withWorkPhone("33333"));
            app.contact().returnToHomePage(); //if app.timeouts().implicitlyWait is 0
        }
    }

    @Test
    public void testContactPhones() {

        app.goTo().homepage();
        ContactData contact = app.contact().allPhonz().iterator().next();
        ContactData contact1 = app.contact().allEmails().iterator().next();
        ContactData contact2 = app.contact().AddressFromHomePage().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(),  equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat( contact1.getAllEmails(),  equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat( contact2.getAddressFromHomePage(),  equalTo(contactInfoFromEditForm.getAddress() ));


    }

    private String mergePhones(ContactData contact) {
            return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                    .stream().filter(s -> !s.equals(""))
                    .map(ContactPhoneTests::cleaned)
                    .collect(Collectors.joining("\n"));

        }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter(s -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));

    }
    public static String cleaned (String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}





