package africa.semicolon.phoneBook.data.repositories;

import africa.semicolon.phoneBook.data.models.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findContactByFirstName(String firstName);
    List<Contact>findContactByLastName(String contactName);
    List<Contact>findContactByMiddleName(String contactName);
   // List<Contact> deleteContactByFirstNameOrLastNameOrMiddleNameAndMobile(String name, String mobile);
}
