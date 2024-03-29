package com.mylibrary.libraryapp.controller;

import com.mylibrary.libraryapp.dto.CheckoutRegisterDTO;
import com.mylibrary.libraryapp.service.CheckoutRegisterService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor  // to secure auto-injection of services
@RequestMapping("api/registers")
public class CheckoutRegisterController {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutRegisterController.class);
    private final CheckoutRegisterService checkoutRegisterService;

    @PostMapping("addRegister")
    // http://localhost:8080/api/registers/addRegister
    public ResponseEntity<CheckoutRegisterDTO> addRegister(@RequestBody CheckoutRegisterDTO checkoutRegisterDTO) {
        System.out.println("entering addRegister...");
        CheckoutRegisterDTO savedRegisterDTO = checkoutRegisterService.createCheckoutRegister(checkoutRegisterDTO);
        return new ResponseEntity<>(savedRegisterDTO, HttpStatus.CREATED);
    }

    // API to get register by id
    // e.g. http://localhost:8080/api/registers/1
    @GetMapping("{id}")
    public ResponseEntity<CheckoutRegisterDTO> getCheckoutRegisterById(@PathVariable("id") Long registerId) {
        logger.info("Fetching checkout register with ID: {}", registerId);
        CheckoutRegisterDTO checkoutRegisterDTO = checkoutRegisterService.getCheckoutRegisterById(registerId);
        return new ResponseEntity<>(checkoutRegisterDTO, HttpStatus.OK);
    }
    // get all registers
    // http://localhost:8080/api/registers/listAll
    @GetMapping("listAll")
    public ResponseEntity<List<CheckoutRegisterDTO>> getAllRegisters() {
        logger.info("Fetching all checkout registers...");
        List<CheckoutRegisterDTO> allRegisters = checkoutRegisterService.getAllRegisters();
        return new ResponseEntity<>(allRegisters, HttpStatus.OK);
    }

    // get register by member_id
    // e.g. http://localhost:8080/api/registers/member/1
    @GetMapping("/member/{id}")
    public ResponseEntity<List<CheckoutRegisterDTO>> getCheckoutRegistersByMemberId(@PathVariable Long id) {
        logger.info("Fetching all checkout registers for member ID: {}", id);
        List<CheckoutRegisterDTO> registers = checkoutRegisterService.getCheckoutsByMemberId(id);
        return new ResponseEntity<>(registers, HttpStatus.OK);
    }

    // get register by book_id
    // e.g. http://localhost:8080/api/registers/book/1
    @GetMapping("/book/{id}")
    public ResponseEntity<List<CheckoutRegisterDTO>> getCheckoutRegistersByBookId(@PathVariable Long id) {
        logger.info("Fetching all checkout registers for book ID: {}", id);
        List<CheckoutRegisterDTO> registers = checkoutRegisterService.getCheckoutsByBookId(id);
        return new ResponseEntity<>(registers, HttpStatus.OK);
    }

    @PatchMapping("updateRegister/{id}")
    // e.g. http://localhost:8080/api/registers/updateRegister/5
    public ResponseEntity<CheckoutRegisterDTO> updateRegister(@PathVariable Long id, @RequestBody CheckoutRegisterDTO checkoutRegisterDTO) {
        logger.info("Updating register with ID: {}", id);
        checkoutRegisterDTO.setId(id);
        CheckoutRegisterDTO updatedRegister = checkoutRegisterService.updateRegister(checkoutRegisterDTO);
        return new ResponseEntity<>(updatedRegister, HttpStatus.OK);
    }

    @DeleteMapping("deleteRegister/{id}")
    public ResponseEntity<String> deleteRegister(@PathVariable Long id) {
        checkoutRegisterService.deleteRegister(id);
        return new ResponseEntity<>("Checkout register successfully deleted", HttpStatus.OK);
    }

}
