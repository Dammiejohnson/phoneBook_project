package africa.semicolon.phoneBook.controllers;

import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.AddContactResponse;
import africa.semicolon.phoneBook.dtos.responses.DeleteResponse;
import africa.semicolon.phoneBook.dtos.responses.FindContactResponse;
import africa.semicolon.phoneBook.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/save")
    public AddContactResponse addContactResponse(@RequestBody AddContactRequest request){
        return contactService.save(request);
    }

    @GetMapping("/{keyword}")
    public List<FindContactResponse> findByName(@PathVariable ("keyword") String name){
        return contactService.findContactByName(name);
    }

    @DeleteMapping("/delete{name}/{mobile}")
    public DeleteResponse deleteByMobile(@PathVariable String name, @PathVariable String mobile) {
        return contactService.deleteContact(name,mobile);

    }

}
