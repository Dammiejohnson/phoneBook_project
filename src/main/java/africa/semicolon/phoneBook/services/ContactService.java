package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;

public interface ContactService {
    AddContactResponse save(AddContactRequest contactRequest);
    FindContactResponse findContactByName(String name);
    void deleteContactByMobile(String mobile);

    ContactRepository getRepository();
}
