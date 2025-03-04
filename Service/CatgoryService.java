package com.project.shopapp.Service;

import com.project.shopapp.DTOS.CategoryDTO;
import com.project.shopapp.MODELS.Category;
import com.project.shopapp.Respository.CategoryRespository;
import com.project.shopapp.Service.IMP.IMPCatgoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatgoryService implements IMPCatgoryService {
    private final CategoryRespository categoryRespository;
    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category newCategory = Category.builder().name(categoryDTO.getName()).build();
        return categoryRespository.save(newCategory);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRespository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRespository.findAll();
    }

    @Override   
    public Category updateCategory(long categoryId, CategoryDTO categoryDTO) {
        Category existingCategory = getCategoryById(categoryId);
        existingCategory.setName(categoryDTO.getName());
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRespository.deleteById(id);

    }
}
