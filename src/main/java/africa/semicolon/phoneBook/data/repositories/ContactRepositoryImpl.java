package africa.semicolon.phoneBook.data.repositories;

import africa.semicolon.phoneBook.data.models.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImpl  implements  ContactRepository{
    List<Contact> repositoryDb = new ArrayList<>();
    private int count;


    @Override
    public Contact saveContact(Contact contact) {
        repositoryDb.add(contact);
        count++;
        return contact;
    }

    @Override
    public int count() {
        return repositoryDb.size();
       // return  count;
    }

    @Override
    public void deleteContact(Contact contact) {
        repositoryDb.remove(contact);
        count--;

    }

    @Override
    public void deleteContactByMobile(String mobile) {
        for (Contact contact : repositoryDb){
            if(mobile.equals(contact.getMobile())){
                repositoryDb.remove(contact);
                break;
            }

        }
    }

    @Override
    public Contact findByFirstName(String firstName) {
        for (Contact contact : repositoryDb){
            if(firstName.equalsIgnoreCase(contact.getFirstName())){
                return contact;
            }
        }
        return null;
    }

    @Override
    public Contact findByLastName(String lastName) {
        for (Contact contact : repositoryDb){
            if(lastName.equalsIgnoreCase(contact.getLastName())){
                return contact;
            }
        }
        return null;
    }

    @Override
    public Contact findByMobile(String mobile) {
        for (Contact contact : repositoryDb){
            if(mobile.equals(contact.getMobile())){
                return contact;
            } else {
                throw new IllegalArgumentException("contact does not exist");
            }
        }
        return null;
    }

    @Override
    public List<Contact> findAll(Contact contact) {
        return repositoryDb;
    }


}
