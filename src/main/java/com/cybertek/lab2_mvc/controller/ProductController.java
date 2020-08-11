package com.cybertek.lab2_mvc.controller;

import com.cybertek.lab2_mvc.model.Category;
import com.cybertek.lab2_mvc.model.Product;
import com.cybertek.lab2_mvc.service.CategoryDAO;
import com.cybertek.lab2_mvc.service.ProductDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductDAO productDAO;
    private final CategoryDAO categoryDAO;

    /**
     * Default constructor.
     *
     * @param productDAO  productDao {@link ProductDAO}
     * @param categoryDAO categoryDao{@link CategoryDAO}
     */
    public ProductController(ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }


    /**
     * List all products.
     *
     * @param model model {@link Model}
     * @return list of product {@link List<Product>}
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productDAO.readAllProducts());
        return "product/index";
    }

    /**
     * Add new product form
     *
     * @param model model
     * @return createdProduct.
     */
    @GetMapping("/add-product")
    public String addProduct(Model model) {
        model.addAttribute("categories", categoryDAO.readAllCategories());
        model.addAttribute("product", new Product());
        return "product/add-product";
    }

    /**
     * Create product
     *
     * @param product product to be created.
     * @param model   model
     * @return create product
     */
    @PostMapping("/create-product")
    public String createProduct(@ModelAttribute("product") Product product, Model model) {
        productDAO.create(product);
        model.addAttribute("products", productDAO.readAllProducts());
        return "redirect:/";
    }

    /**
     * Product details page.
     *
     * @param id    id of specific product
     * @param model model
     * @return product details.
     */
    @GetMapping("/product")
    public String productDetails(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productDAO.readById(id));
        return "product/product-details";
    }

    /**
     * Edit product page.
     *
     * @param id    id of specific product.
     * @param model model
     * @return edit product
     */
    @GetMapping("/edit-product")
    public String editProduct(@RequestParam("id") Integer id, Model model) {
        model.addAttribute("product", productDAO.readById(id));
        model.addAttribute("categories", categoryDAO.readAllCategories());
        return "product/edit-product";
    }

    /**
     * Update product
     *
     * @param product product to be updated
     * @param model   model
     * @return updatedProduct.
     */
    @PostMapping("/update-product")
    public String updateProduct(@RequestParam("id") Integer id, @ModelAttribute("product") Product product, Model model) {
        productDAO.update(id, product);
        model.addAttribute("products", productDAO.readAllProducts());
        return "redirect:/";
    }
}
