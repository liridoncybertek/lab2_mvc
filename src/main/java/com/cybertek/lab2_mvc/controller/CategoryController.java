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
    public String categoryDetails(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryDAO.readById(id));
        return "category/category-details";
    }

    @GetMapping("/add-category")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "category/add-category";
    }

    @GetMapping("/edit-category")
    public String editCategory(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("category", categoryDAO.readById(id));
        return "category/edit-category";
    }

    @PostMapping("/create-category")
    public String createCategory(@ModelAttribute("category") Category category, Model model) {
        categoryDAO.create(category);
        model.addAttribute("categories", categoryDAO.readAllCategories());
        return "redirect:/categories";
    }

    @PostMapping("/update-category")
    public String updateCategory(@RequestParam("id") Integer id, @ModelAttribute("category") Category category, Model model) {
        categoryDAO.update(id, category);
        model.addAttribute("categories", categoryDAO.readAllCategories());
        return "redirect:/categories";
    }

    @GetMapping("/delete-category")
    public String deleteCategory(@RequestParam("id") Integer id) {
        categoryDAO.delete(id);
        return "redirect:/categories";
    }
}
