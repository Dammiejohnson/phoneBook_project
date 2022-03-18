package africa.semicolon.phoneBook.controllers;

import africa.semicolon.phoneBook.dtos.requests.AddContactRequest;
import africa.semicolon.phoneBook.dtos.responses.ApiResponse;
import africa.semicolon.phoneBook.services.ContactService;
import africa.semicolon.phoneBook.utils.CaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping("/save")
    public ResponseEntity<?> addContactResponse(@RequestBody AddContactRequest request){
        try {
            CaseUtil.sentenceCase(request);
            return new ResponseEntity<>(contactService.save(request), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{keyword}")
    public ResponseEntity<?> findByName(@PathVariable ("keyword") String name){
        name = CaseUtil.ignoreCase(name);
        try {
            return new ResponseEntity<>(contactService.findContactByName(name), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{name}/{mobile}")
    public ResponseEntity<?> deleteByMobile(@PathVariable String name, @PathVariable String mobile) {
        name = CaseUtil.ignoreCase(name);
        try {
            return new ResponseEntity<>(contactService.deleteContact(name, mobile), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.NOT_FOUND);
        }

    }

}
