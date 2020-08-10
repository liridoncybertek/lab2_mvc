package com.cybertek.lab2_mvc.service;

import com.cybertek.lab2_mvc.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class ProductDAO {

    private final CategoryDAO categoryDAO;

    private List<Product> products;

    public ProductDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
        fillData();
    }


    /**
     * Returns list of products from dummy database
     *
     * @return categories {@link List<Product>}
     */
    public List<Product> readAllProducts() {
        return products;
    }

    /**
     * Return product object for given id from dummy database.
     * If product is not found for id, returns null.
     *
     * @param id product id
     * @return product object for given id {@link Product}
     */
    public Product readById(Long id) {
        return products.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst().orElse(null);
    }


    /**
     * Create new product in dummy database. Updates the id and insert new
     * product in list.
     *
     * @param product Product object
     * @return Product {@link Product}
     */
    public Product create(Product product) {
        product.setId(System.currentTimeMillis());
        products.add(product);
        return product;
    }

    /**
     * Update the product object for given id in dummy database.
     * If product does not exist, returns null
     *
     * @param id      id to update
     * @param product product
     * @return product object with id
     */
    public Product update(Long id, Product product) {
        Optional<Product> foundProduct = products.stream()
                .filter(cat -> cat.getId().equals(id))
                .findFirst();
        if (foundProduct.isPresent()) {
            product.setId(foundProduct.get().getId());
            products.remove(foundProduct.get());
            products.add(product);
            return product;
        }
        return null;
    }

    /**
     * Delete the product object from dummy database if product found for given id.
     *
     * @param id the product id
     */
    public void delete(Long id) {
        products.removeIf(category -> category.getId().equals(id));
    }

    private void fillData() {
        products = new ArrayList<>();
        products.add(new Product(System.currentTimeMillis(), "T-Shirt", 10, new BigDecimal("29.9"), "New collection of t-shirts are arrived.", categoryDAO.readByName("Clothes")));
        products.add(new Product(System.currentTimeMillis(), "Apple Iphone 11 Pro Max", 100, new BigDecimal("699"), "Newest iphone is coming", categoryDAO.readByName("Phones")));
        products.add(new Product(System.currentTimeMillis(), "MacBook Pro", 5, new BigDecimal("1499"), "MacBook pro 13 inch", categoryDAO.readByName("Technology")));
        products.add(new Product(System.currentTimeMillis(), "Lego Game", 15, new BigDecimal("14.5"), "Lego Game", categoryDAO.readByName("Toys")));
        products.add(new Product(System.currentTimeMillis(), "Fishing Reel", 150, new BigDecimal("1.77"), "Fishing reel", categoryDAO.readByName("Sports")));
    }

}
