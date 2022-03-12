package africa.semicolon.phoneBook.data.repositories;

import africa.semicolon.phoneBook.data.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContactRepositoryImplTest {
    ContactRepositoryImpl contactRepository;

    @BeforeEach
    void setUp(){
        contactRepository = new ContactRepositoryImpl();
    }
    @Test
    void contactCanBeSavedTest(){
        //given that we have a contact
        Contact contact = new Contact("Lotachi", "Bolanle", "234566");
        //when we try to save a contact
        contactRepository.saveContact(contact);
        //assert
        assertEquals(1, contactRepository.count());
    }

    @Test
    void contactCanBeDeletedTest(){
        //given that we have 2 contacts
        Contact contact = addContacts();
        //when we try to delete a contact
        contactRepository.deleteContact(contact);
        //assert
        assertEquals(1, contactRepository.count());
    }

    @Test
    void contactCanBeDeletedByMobileTest(){
        //given that we have 2 contacts
        Contact contact = new Contact("Lotachi", "Bolanle", "234566");
        Contact contact2 = new Contact("Alhaji", "Solomon", "2354322");

        contactRepository.saveContact(contact);
        contactRepository.saveContact(contact2);
        assertEquals(2, contactRepository.count());

        //when we try to delete a contact
       contactRepository.deleteContactByMobile("2354322");
        //assert
//        assertNull(contactRepository.findByMobile(contact2.getMobile()));
        assertEquals(1, contactRepository.count());
    }

    @Test
    void contactCanBeFoundByFirstNameTest(){
        //given that we have 2 contacts
        Contact contact = new Contact("Lotachi", "Bolanle", "234566");
        Contact contact2 = new Contact("Alhaji", "Solomon", "2354322");

        contactRepository.saveContact(contact);
        contactRepository.saveContact(contact2);
        assertEquals(2, contactRepository.count());
        //when we try to save a contact
        Contact foundContact = contactRepository.findByFirstName("Alhaji");
        //assert
        assertEquals(contact2, foundContact);

    }

    @Test
    void contactCanBeFoundByFirstName_inAnyCaseTest(){
        //given that we have 2 contacts
        Contact contact = addContacts();
        //when we try to find a contact by firstname
        Contact foundContact = contactRepository.findByFirstName("LotAChi");
        //assert
        assertEquals(contact, foundContact);

    }

    @Test
    void contactCanBeFoundByLastName(){
        //given that we have 2 contacts
        Contact contact = new Contact("Lotachi", "Bolanle", "234566");
        Contact contact2 = new Contact("Alhaji", "Solomon", "2354322");

        contactRepository.saveContact(contact);
        contactRepository.saveContact(contact2);
        assertEquals(2, contactRepository.count());

        //when we try to find a contact by lastname
        Contact foundContact = contactRepository.findByLastName("Solomon");
        //assert
        assertEquals(contact2, foundContact);

    }

    @Test
    void contactCanBeFoundByMobile(){
        //given that we have 2 contacts
        Contact contact = addContacts();
        //when we try to find a contact by mobile
        Contact foundContact = contactRepository.findByMobile("234566");
        //assert
        assertEquals(contact, foundContact);
    }

    @Test
    void contactThatCannotBeFoundByMobileThrowsException(){
        //given that we have 2 contacts
        Contact contact = addContacts();
        //when we try to find a contact by mobile
//        Contact foundContact = contactRepository.findByMobile("234566");
        //assert
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> contactRepository.findByMobile("234566890"));
        assertEquals("contact does not exist", e.getLocalizedMessage());
    }

    @Test
    void contactCanBeUpdatedByFirstName(){
        //given that we have 2 contacts
        Contact contact = addContacts();
        //when we try to update a contact firstname
        contact.setFirstName("Otunba");
        contactRepository.saveContact(contact);
        Contact foundContact = contactRepository.findByFirstName("Otunba");
        //Contact foundContact = contactRepository.findByFirstName("lotachi");

        //assert
        assertEquals(contact, foundContact);
    }

    private Contact addContacts() {
        Contact contact = new Contact("Lotachi", "Bolanle", "234566");
        Contact contact2 = new Contact("Alhaji", "Solomon", "2354322");

        contactRepository.saveContact(contact);
        contactRepository.saveContact(contact2);
        assertEquals(2, contactRepository.count());
        return contact;
    }

    @Test
    void findAllContactTest(){
        //given that we have 2 contacts
        Contact contact = addContacts();
        //when i try to find all contacts
        List<Contact> all = contactRepository.findAll(contact);
        assertEquals(2, all.size());

    }




}