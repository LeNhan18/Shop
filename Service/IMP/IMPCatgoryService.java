package com.project.shopapp.Service.IMP;

import com.project.shopapp.DTOS.CategoryDTO;
import com.project.shopapp.MODELS.Category;

import java.util.List;

public interface IMPCatgoryService {
    //Tạo một category
    Category createCategory(CategoryDTO categoryDTO);
    //Lấy 1 category
    Category getCategoryById(Long id);
    //Lấy 1 list category
    List<Category> getAllCategory();
    //Cập nhật 1 category
    Category updateCategory(long categoryId,CategoryDTO categoryDTO);
    //Xóa 1 category
    void deleteCategory(long id);

}
