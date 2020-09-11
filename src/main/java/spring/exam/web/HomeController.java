package spring.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.exam.model.entity.CategoryName;
import spring.exam.model.service.CategoryServiceModel;
import spring.exam.model.service.ProductServiceModel;
import spring.exam.service.CategoryService;
import spring.exam.service.ProductService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, Model model){
        if (httpSession.getAttribute("user") == null){
            return "index";
        }else {
            List<ProductServiceModel> products = this.productService.getAll();
            model.addAttribute("products", products);
            BigDecimal totalPriceOfProducts = new BigDecimal(0.00);
            for (ProductServiceModel product : products) {
                totalPriceOfProducts = totalPriceOfProducts.add(product.getPrice());
            }
            model.addAttribute("totalPriceOfProducts", totalPriceOfProducts);
            return "home";
        }
    }

    @GetMapping("/delete/{id}")
    public String buy(@PathVariable("id")String id){
        this.productService.delete(id);
        return "redirect:/";

    }

    @GetMapping("/buyAll")
    public String buyAll(){
        this.productService.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }


}
