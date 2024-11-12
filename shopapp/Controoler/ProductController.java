package com.project.shopapp.Controoler;

import com.project.shopapp.DTOS.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {
    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@RequestParam String limit , @RequestParam int page) {
        return ResponseEntity.ok("limit: "+limit+" page: "+page);
    }
    @PostMapping( value ="",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public
    public ResponseEntity<?> insertImage(@Valid )
    @PostMapping("")
    public ResponseEntity<?> InsertProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok("this is insert Product "+productDTO);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getIdProduct(@PathVariable("id") String ProductId) {

        return ResponseEntity.ok("id : "+ProductId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body("Product Delete successly");
    }

}
