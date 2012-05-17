package shopping.web.showcase;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shopping.domain.product.ProductType;
import shopping.repositories.product.ProductRepository;
import shopping.web.config.Routes;
import shopping.web.servlet.mvc.MobileMapping;

@Controller
@RequestMapping(Routes.PRODUCT)
public class ProductController {
	
	@Inject ProductRepository productRepository;
	
	
    @RequestMapping(method=RequestMethod.GET)
    public String list(@RequestParam(required=false, defaultValue="book") ProductType productType, Model model) {
    	
    	listProcess(productType, model);
    	
        return Routes.PRODUCT + "/product-list";
    }
    
    @MobileMapping
    @RequestMapping(method=RequestMethod.GET)
    public String mobileList(@RequestParam(required=false, defaultValue="book") ProductType productType, Model model) {
    	
		listProcess(productType, model);
		
        return Routes.PRODUCT + "/product-mobile-list";
    }
    
    private void listProcess(ProductType productType, Model model) {
    	model.addAttribute("products", productRepository.findByProductType(productType));
		model.addAttribute("productType", productType);
    	model.addAttribute("productTypes", ProductType.values());
    }
    
}