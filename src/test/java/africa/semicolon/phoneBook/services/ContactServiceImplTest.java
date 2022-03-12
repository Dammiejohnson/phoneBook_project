package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.data.repositories.ContactRepositoryImpl;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
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
        //given that
        ContactService contactService = new ContactServiceImpl();
        AddContactRequest request = new AddContactRequest();

        request.setFirstName("Oluwadamilola");
        request.setLastName("Sanni");
        request.setMobile("234565787");
        // when
        contactService.save(request);
        //assert
        assertEquals(1, contactService.getRepository().count());
    }



}