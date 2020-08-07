package com.cybertek.lab2_mvc.controller;

import com.cybertek.lab2_mvc.model.Category;
import com.cybertek.lab2_mvc.service.CategoryDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    private final CategoryDAO categoryDAO;

    public CategoryController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @GetMapping("/categories")
    public String index(Model model) {
        model.addAttribute("categories", categoryDAO.readAllCategories());
        return "category/index";
    }

    @GetMapping("/category-details")
    public String categoryDetails(@RequestParam("id") Long id, Model model) {
        model.addAttribute("category", categoryDAO.readById(id));
        return "category/category-details";
    }

    @GetMapping("/add-category")
    public String addCategory() {
        return "category/add-category";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable("id") Long id) {
        return "category/edit-category";
    }

    @PostMapping("/create-category")
    public String createCategory(@ModelAttribute("category")Category category, Model model) {
        categoryDAO.create(category);
        model.addAttribute("categories", categoryDAO.readAllCategories());
        return "redirect:/categories";
    }

    @PutMapping("update-category")
    public String updateCategory(@ModelAttribute("category") Category category, Model model) {
        categoryDAO.update(category.getId(),category);
        model.addAttribute("categories", categoryDAO.readAllCategories());
        return "redirect:/categories";
    }

    @DeleteMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") Long id) {
        categoryDAO.delete(id);
        return "redirect:/categories";
    }
}
