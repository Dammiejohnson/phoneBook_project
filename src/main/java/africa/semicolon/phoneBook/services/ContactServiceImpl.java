package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.models.Contact;
import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.data.repositories.ContactRepositoryImpl;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;

import java.util.ArrayList;
import java.util.List;

public class ContactServiceImpl implements ContactService{
    ContactRepository contactRepository = new ContactRepositoryImpl();
    @Override
    public AddContactResponse save(AddContactRequest contactRequest) {
        Contact contact = new Contact(contactRequest.getFirstName(), contactRequest.getLastName(), contactRequest.getMobile());

        contactRepository.saveContact(contact);
        return null;
    }

    @Override
    public ContactRepository getRepository() {
        return contactRepository;
    }
}
