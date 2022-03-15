package africa.semicolon.phoneBook.services;

import africa.semicolon.phoneBook.data.models.Contact;
import africa.semicolon.phoneBook.data.repositories.ContactRepository;
import africa.semicolon.phoneBook.data.repositories.ContactRepositoryImpl;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;


public class ContactServiceImpl implements ContactService{
    ContactRepository contactRepository = new ContactRepositoryImpl();
    @Override
    public AddContactResponse save(AddContactRequest contactRequest) {
        Contact contact = new Contact(contactRequest.getFirstName(), contactRequest.getLastName(), contactRequest.getMobile());
        contact.setMiddleName(contactRequest.getMiddleName());
        contact.setOffice(contactRequest.getOffice());

        contactRepository.saveContact(contact);

        AddContactResponse response = new AddContactResponse();
        response.setFullname(contact.getFirstName() + " " + contact.getLastName() + " " + contact.getMiddleName());
        response.setMobile(contact.getMobile());
        response.setFeedBackResponse("Your contact has been saved in your phonebook");

        return response;

    }

    @Override
    public FindContactResponse findContactByName(String contactName) {
        contactName = contactName.toLowerCase();
        Contact contact = contactRepository.findByName(contactName);
        FindContactResponse findContactResponse = new FindContactResponse();
        findContactResponse.setFullName(contact.getFirstName() + " " + contact.getLastName() + " " + contact.getMiddleName());
        findContactResponse.setMobile(contact.getMobile());
        return findContactResponse;
    }

    @Override
    public void deleteContactByMobile(String mobile) {
        Contact contact = contactRepository.findByMobile(mobile);
        if(contact.getMobile().equals(mobile)){
            contactRepository.deleteContact(contact);
        }
    }

    @Override
    public ContactRepository getRepository() {
        return contactRepository;
    }
}
