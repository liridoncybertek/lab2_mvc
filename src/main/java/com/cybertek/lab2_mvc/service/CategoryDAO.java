package com.cybertek.lab2_mvc.service;

import com.cybertek.lab2_mvc.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryDAO {

    private static List<Category> categories;

    {
        categories = new ArrayList<>();
        categories.add(new Category(System.currentTimeMillis(), "Clothes", "All clothes for men and women"));
        categories.add(new Category(System.currentTimeMillis(), "Phones", "All phones"));
        categories.add(new Category(System.currentTimeMillis(), "Technology", "All technology stuff"));
        categories.add(new Category(System.currentTimeMillis(), "Toys", "Toys for kids and more"));
        categories.add(new Category(System.currentTimeMillis(), "Sports", "All sports needed stuff"));
    }

    /**
     * Returns list of categories from dummy database
     *
     * @return categories {@link List<Category>}
     */
    public List<Category> readAllCategories() {
        return categories;
    }

    /**
     * Return category object for given id   from dummy database. If category is
     * not found for id, returns null.
     *
     * @param id category id
     * @return category object for given id {@link Category}
     */
    public Category readById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Category readByName(String name) {
        return categories.stream()
                .filter(category -> category.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst().orElse(null);
    }

    /**
     * Create new category in dummy database. Updates the id and insert new
     * category in list.
     *
     * @param category Category object
     * @return category {@link Category}
     */
    public Category create(Category category) {
        category.setId(System.currentTimeMillis());
        categories.add(category);
        return category;
    }

    /**
     * Update the category object for given id in dummy database. If category
     * does not exist, returns null
     *
     * @param id       id to update
     * @param category category
     * @return category object with id
     */
    public Category update(Long id, Category category) {
        Optional<Category> foundCategory = categories.stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst();
        if (foundCategory.isPresent()) {
            category.setId(foundCategory.get().getId());
            categories.remove(foundCategory.get());
            categories.add(category);
            return category;
        }
        return null;
    }

    /**
     * Delete the category object from dummy database if category found for given id.
     *
     * @param id the category id
     */
    public void delete(Long id) {
        categories.removeIf(category -> category.getId().equals(id));
    }

}
