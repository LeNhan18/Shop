package com.project.shopapp.Controller;
import com.project.shopapp.DTOS.CategoryDTO;
import com.project.shopapp.MODELS.Category;
import com.project.shopapp.Service.CatgoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cate")
//DependencyInjection
@RequiredArgsConstructor
//@Validated
public class CateController {
    public final CatgoryService categoryService;

    //Hiển thị tất cả các link api
    @GetMapping("")
    public ResponseEntity<List<Category>> nhanle(@RequestParam("page") int page,
                                    @RequestParam("limit") int limit) {
        List<Category> categories = categoryService.getAllCategory();
        return  ResponseEntity.ok(categories);
    }
    @PostMapping("/insert")
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
        Category savedCategory = categoryService.createCategory(categoryDTO);
        return  ResponseEntity.ok("This is insert CateGory: "+categoryDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,CategoryDTO categoryDTO){
        categoryService.updateCategory(id,categoryDTO);
        return ResponseEntity.ok("Updated category successfully");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted category successfully");
    }
    @PostMapping("/Create")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO
            ,BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                   .stream()
                   .map(FieldError::getDefaultMessage)
                   .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok("This is create CateGory: "+categoryDTO);
    }
}
