package com.project.shopapp.Controoler;


import com.project.shopapp.DTOS.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cate")
//@Validated
public class CateController {
    //Hiển thị tất cả các link api


    @PostMapping(value=" ",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> CreateProduct(@Valid @RequestBody )
    @GetMapping("")//http://localhost:8088/cate
    public ResponseEntity<?> nhanle(@RequestParam("page") int page,@RequestParam("limit") int limit) {
        return  ResponseEntity.ok("Nx`hanle"+" "+page+" "+limit);
    }
    @PostMapping("")
    //Nếu tham số truền vào là một object thì sao?=>Data tranfer object = Request Object
    public ResponseEntity<?> getCate(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result )
    {
        if(result.hasErrors()){
            List<String> Emessage =result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(Emessage);
        }
        return  ResponseEntity.ok("This is insert CateGory: "+categoryDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id){
        return ResponseEntity.ok("insert category with id:  "+id);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        return ResponseEntity.ok("delete category with id:  "+id);
    }
}
