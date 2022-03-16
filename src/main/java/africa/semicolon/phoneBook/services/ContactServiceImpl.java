package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.models.Contact;
import africa.semicolon.phoneBook.data.repositories.ContactRepository;
//import africa.semicolon.phoneBook.data.repositories.ContactRepositoryImpl;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.DeleteResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;
import africa.semicolon.phoneBook.exceptions.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService{

    @Autowired
    ContactRepository contactRepository;
    @Override
    public AddContactResponse save(AddContactRequest contactRequest) {
        Contact contact = new Contact(contactRequest.getFirstName(), contactRequest.getLastName(), contactRequest.getMobile());
        contact.setMiddleName(contactRequest.getMiddleName());
        contact.setOffice(contactRequest.getOffice());

        Contact addedContact = contactRepository.save(contact);

        AddContactResponse response = new AddContactResponse();
        response.setFullname(addedContact.getFirstName() + " " + addedContact.getLastName() + " " + addedContact.getMiddleName());
        response.setMobile(addedContact.getMobile());
        response.setFeedBackResponse("Your contact has been saved in your phonebook");
        return response;

    }

    private static FindContactResponse findContactResponse (Contact contact){
        FindContactResponse response = new FindContactResponse();
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setMiddleName(contact.getMiddleName());
        response.setMobile(contact.getMobile());
        response.setOffice(contact.getOffice());
        return response;
}


    @Override
    public List<FindContactResponse> findContactByName(String contactName) {
        List<Contact> contacts = findContactByFirstNameOrLastNameOrMiddleName(contactName);
        if (contacts.isEmpty()) throw new ContactNotFoundException(contactName + " not found");
        List <FindContactResponse> responses = new ArrayList<>();
        contacts.forEach(contact -> {
            responses.add(new FindContactResponse());
        });
       return responses;
    }



    private List<Contact> findContactByFirstNameOrLastNameOrMiddleName(String contactName) {
        List<Contact> contacts = new ArrayList<>();
        contacts.addAll(contactRepository.findContactByFirstName(contactName));
        contacts.addAll(contactRepository.findContactByLastName(contactName));
        contacts.addAll(contactRepository.findContactByMiddleName(contactName));
        return contacts;
    }

    @Override
   public DeleteResponse deleteContact(String name, String mobile) {
        List<Contact> contacts = findContactByFirstNameOrLastNameOrMiddleName(name);
        contacts.forEach(contact -> {
            if(contact.getMobile().equals(mobile)){
                contactRepository.delete(contact);
            }
        });
        DeleteResponse deleteMessage = new DeleteResponse();
        deleteMessage.setMessage("Contact Deleted");
        return deleteMessage;
    }

    }
