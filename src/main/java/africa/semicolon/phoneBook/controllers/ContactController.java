package africa.semicolon.phoneBook.controllers;

import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;
import africa.semicolon.phoneBook.services.ContactService;
import africa.semicolon.phoneBook.services.ContactServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private ContactService contactService = new ContactServiceImpl();

    @PostMapping("/save")
    public AddContactResponse addContactResponse(@RequestBody AddContactRequest request){
        return contactService.save(request);
    }

    @GetMapping("/(search)")
    public FindContactResponse findByName(@PathVariable String name){
        return  contactService.findContactByName(name);
    }

    @DeleteMapping("/delete")
    public void deleteByMobile(@PathVariable String mobile) {
        contactService.deleteContactByMobile(mobile);

    }

}
