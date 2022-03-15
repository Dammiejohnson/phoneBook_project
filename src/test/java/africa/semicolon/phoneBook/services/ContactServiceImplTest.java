package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.data.repositories.ContactRepositoryImpl;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {
    ContactRepository contactRepository;

    @BeforeEach
    public  void setUp(){
        contactRepository = new ContactRepositoryImpl();
    }

    @Test
    public void contactCanBeAddedTest(){
        //given that there is a contact service that takes in a request form
        ContactService contactService = new ContactServiceImpl();
        AddContactRequest request = new AddContactRequest();
        request.setFirstName("Oluwadamilola");
        request.setLastName("Sanni");
        request.setMiddleName("Ademiju");
        request.setMobile("234565787");
        request.setOffice("UBA");
        // when i save the request
        AddContactResponse response = contactService.save(request);
        //assert
        assertEquals(1, contactService.getRepository().count());
    }

    @Test
    public void twoContactCanBeAddedTest(){
        //given that there is a contact service that takes in a request form
        ContactService contactService = new ContactServiceImpl();
        AddContactRequest request = new AddContactRequest();
        AddContactRequest request2 = new AddContactRequest();
        request.setFirstName("Oluwadamilola");
        request.setLastName("Sanni");
        request.setMiddleName("Ademiju");
        request.setMobile("234565787");
        request.setOffice("UBA");
        request2.setFirstName("Inc");
        request2.setLastName("Mosunrat");
        request2.setMiddleName("Yemisi");
        request2.setMobile("234565");
        request2.setOffice("FirstBank");

        // when i save the request
        AddContactResponse response = contactService.save(request);
        AddContactResponse response2 = contactService.save(request2);
        //assert
        assertEquals(2, contactService.getRepository().count());
    }

    @Test
    public void canFindContactByNameTest() {
        ContactService contactService = new ContactServiceImpl();
        AddContactRequest request = new AddContactRequest();
        AddContactRequest request2 = new AddContactRequest();

        request.setFirstName("Oluwadamilola");
        request.setLastName("Sanni");
        request.setMiddleName("Ademiju");
        request.setMobile("234565787");
        request.setOffice("UBA");

        request2.setFirstName("Inc");
        request2.setLastName("Mosunrat");
        request2.setMiddleName("Yemisi");
        request2.setMobile("234565");
        request2.setOffice("FirstBank");
        contactService.save(request);
        contactService.save(request2);
        assertEquals(2, contactService.getRepository().count());

        //when i try to find contact
        FindContactResponse findContactResponse = contactService.findContactByName("Mosunrat");
        //assert
        assertEquals("Inc Mosunrat Yemisi", findContactResponse.getFullName());

    }

    @Test
    public void canDeleteContactByMobileTest() {
        ContactService contactService = new ContactServiceImpl();
        AddContactRequest request = new AddContactRequest();
        AddContactRequest request2 = new AddContactRequest();

        request.setFirstName("Oluwadamilola");
        request.setLastName("Sanni");
        request.setMiddleName("Ademiju");
        request.setMobile("234565787");
        request.setOffice("UBA");

        request2.setFirstName("Inc");
        request2.setLastName("Mosunrat");
        request2.setMiddleName("Yemisi");
        request2.setMobile("234565");
        request2.setOffice("FirstBank");
        contactService.save(request);
        contactService.save(request2);
        assertEquals(2, contactService.getRepository().count());

        //when i try to to delete a contact form
        contactService.deleteContactByMobile("234565787");

        //assert
        assertEquals(1,contactService.getRepository().count());

    }



}