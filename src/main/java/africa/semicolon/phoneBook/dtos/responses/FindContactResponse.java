package africa.semicolon.phoneBook.dtos.responses;

import africa.semicolon.phoneBook.data.models.Contact;
import lombok.Data;

@Data
public class FindContactResponse {
    private String firstName;
    private String lastName;
    private String middleName;
    private String office;
    private String mobile;

    public FindContactResponse (Contact contact){
        firstName =contact.getFirstName();
        middleName= contact.getMiddleName();
        lastName=contact.getLastName();
        mobile= contact.getMobile();
        office=contact.getOffice();
    }

    public FindContactResponse() {

    }
}
