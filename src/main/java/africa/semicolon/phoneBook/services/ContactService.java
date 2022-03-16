package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.models.Contact;
import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.DeleteResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;

import java.util.List;

public interface ContactService {
    AddContactResponse save(AddContactRequest contactRequest);
    List<FindContactResponse> findContactByName(String name);
    DeleteResponse deleteContact(String name, String mobile);


//    ContactRepository getRepository();
}
