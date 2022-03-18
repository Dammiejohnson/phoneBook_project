package africa.semicolon.phoneBook.utils;

import africa.semicolon.phoneBook.data.models.Contact;
import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;

public class ModelMapper {
    public  static AddContactResponse getAddContactResponse(Contact addedContact) {
        AddContactResponse response = new AddContactResponse();
        response.setFullname(addedContact.getFirstName() + " " + addedContact.getLastName() + " " + addedContact.getMiddleName());
        response.setMobile(addedContact.getMobile());
        response.setFeedBackResponse("Your contact has been saved in your phonebook");
        return response;
    }

    public static Contact getContact(AddContactRequest contactRequest) {
        Contact contact = new Contact(contactRequest.getFirstName(), contactRequest.getLastName(), contactRequest.getMobile());
        contact.setMiddleName(contactRequest.getMiddleName());
        contact.setOffice(contactRequest.getOffice());
        return contact;
    }

    public static FindContactResponse findContactResponse (Contact contact){
        FindContactResponse response = new FindContactResponse();
        response.setFirstName(contact.getFirstName());
        response.setLastName(contact.getLastName());
        response.setMiddleName(contact.getMiddleName());
        response.setMobile(contact.getMobile());
        response.setOffice(contact.getOffice());
        return response;
    }
}
