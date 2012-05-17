package shopping.web.showcase;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import shopping.domain.product.ProductType;
import shopping.repositories.product.ProductRepository;
import shopping.web.config.Routes;

@Controller
public class ProductController {
	
	@Inject ProductRepository productRepository;
	
	
    public String list(@RequestParam(required=false, defaultValue="book") ProductType productType, Model model) {
    	
    	model.addAttribute("products", productRepository.findByProductType(productType));
		model.addAttribute("productType", productType);
    	model.addAttribute("productTypes", ProductType.values());
    	
        return Routes.PRODUCT + "/product-list";
    }
    
}