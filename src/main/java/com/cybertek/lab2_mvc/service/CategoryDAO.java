package com.cybertek.lab2_mvc.service;

import com.cybertek.lab2_mvc.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoryDAO {

    private static List<Category> categories;

    {
        categories = new ArrayList<>();
        categories.add(new Category(1, "Clothes", "All clothes for men and women"));
        categories.add(new Category(2, "Phones", "All phones"));
        categories.add(new Category(3, "Technology", "All technology stuff"));
        categories.add(new Category(4, "Toys", "Toys for kids and more"));
        categories.add(new Category(5, "Sports", "All sports needed stuff"));
        sortCategories();
    }

    private void sortCategories() {
        categories = categories.stream()
                .sorted(Comparator.comparing(Category::getId))
                .collect(Collectors.toList());
    }

    /**
     * Returns list of categories from dummy database
     *
     * @return categories {@link List<Category>}
     */
    public List<Category> readAllCategories() {
        sortCategories();
        return categories;
    }

    /**
     * Return category object for given id   from dummy database. If category is
     * not found for id, returns null.
     *
     * @param id category id
     * @return category object for given id {@link Category}
     */
    public Category readById(Integer id) {
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
        int size = categories.size();
        int id = categories.get(size - 1).getId() + 1;
        category.setId(id);
        categories.add(category);
        sortCategories();
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
    public Category update(Integer id, Category category) {
        Optional<Category> foundCategory = categories.stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst();
        if (foundCategory.isPresent()) {
            category.setId(foundCategory.get().getId());
            categories.remove(foundCategory.get());
            categories.add(category);
            sortCategories();
            return category;
        }
        return null;
    }

    /**
     * Delete the category object from dummy database if category found for given id.
     *
     * @param id the category id
     */
    public void delete(Integer id) {
        categories.removeIf(category -> category.getId().equals(id));
    }

}
