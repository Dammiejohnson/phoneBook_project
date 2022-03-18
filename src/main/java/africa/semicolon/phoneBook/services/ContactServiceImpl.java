package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.models.Contact;
import africa.semicolon.phoneBook.data.repositories.ContactRepository;
//import africa.semicolon.phoneBook.data.repositories.ContactRepositoryImpl;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.DeleteResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;
import africa.semicolon.phoneBook.exceptions.ContactNotFoundException;
import africa.semicolon.phoneBook.utils.ModelMapper;
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
        Contact contact = ModelMapper.getContact(contactRequest);
        if (contactRepository.existsByMobile(contactRequest.getMobile())) throw new ContactNotFoundException("Contact already Exist");
        Contact addedContact = contactRepository.save(contact);
        return ModelMapper.getAddContactResponse(addedContact);
    }


//    private boolean mobileNumberExist(String mobile){
//        Contact contact = contactRepository.findContactsByMobile(mobile);
//        return contact != null;
//    }


    @Override
    public List<FindContactResponse> findContactByName(String contactName) {
        List<Contact> contacts = findContactByFirstNameOrLastNameOrMiddleName(contactName);
        if (contacts.isEmpty()) throw new ContactNotFoundException(contactName + " not found");
        List <FindContactResponse> responses = new ArrayList<>();
        contacts.forEach(contact -> {
            responses.add(new FindContactResponse(contact));
           // responses.add(ModelMapper.findContactResponse(contact));
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
        DeleteResponse response = new DeleteResponse();
        response.setMessage("Contact Deleted");
        return response;
    }

    }
