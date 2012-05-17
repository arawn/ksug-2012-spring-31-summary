package shopping.repositories.product;

import java.util.List;

import shopping.domain.product.Product;
import shopping.domain.product.ProductType;


public interface ProductRepository {
	
	public List<Product> findByProductType(ProductType productType);
	
}
