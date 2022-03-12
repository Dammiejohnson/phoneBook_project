package africa.semicolon.phoneBook.data.repositories;

import africa.semicolon.phoneBook.data.models.Contact;

import java.util.List;

public interface ContactRepository {
    Contact saveContact(Contact contact);
    void deleteContact(Contact contact);
    void deleteContactByMobile(String mobile);
    Contact findByFirstName(String firstName);
    Contact findByLastName(String lastName);
    Contact findByMobile(String mobile);
    List<Contact> findAll(Contact contact);
    int count();
}
