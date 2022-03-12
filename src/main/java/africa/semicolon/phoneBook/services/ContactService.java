package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;

public interface ContactService {
    AddContactResponse save(AddContactRequest contactRequest);
    ContactRepository getRepository();
}
