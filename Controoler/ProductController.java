package com.project.shopapp.Controoler;


import com.project.shopapp.DTOS.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Product")
public class ProductController {

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@RequestParam String limit , @RequestParam int page) {
        return ResponseEntity.ok("limit: "+limit+" page: "+page);
    }
//    @PostMapping( value ="",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?>
    @PostMapping(value="",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> InsertProduct(@Valid @RequestBody ProductDTO productDTO,
             @RequestPart("image") MultipartFile file
            ,BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                List<String> errors = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errors);
            }
            if(file != null) {
                //Kiem tra kich thuoc cua file va dinh dang
                if(file.getSize()>10*1024*1024) {
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large Maximum size is 1000mb");
                }
                String contentType = file.getContentType();
                if(contentType == null || contentType.startsWith("image/")){
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Product insert successfully");
    }
    private String storeFile(MultipartFile fileName) throws IOException {
         String fileN = StringUtils.cleanPath(fileName.getOriginalFilename());
         //Them UUID vao truoc ten file de file la duy nhat
        String uniqueFile = UUID.randomUUID().toString();
        //Duong dan den thu muc chua file
        java.nio.file.Path file = Paths.get("D:/Uploads" );
        //Kiem tra va tao thu muc neu no khong ton tai
        if(!Files.exists(file)){
          Files.createDirectory(file);  //Tao thu muc neu no khong ton tai
        }
         java.nio.file.Path destination = Paths.get(file.toString(),uniqueFile);
        //Sao chep file den thu muc dich
         Files.copy(fileName.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
         return uniqueFile;
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
