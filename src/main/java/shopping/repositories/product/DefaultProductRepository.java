package shopping.repositories.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import shopping.domain.product.Product;
import shopping.domain.product.ProductType;


@Repository
public class DefaultProductRepository implements ProductRepository {
	
	private Map<ProductType, JpaRepository<?, ?>> repositories = new HashMap<ProductType, JpaRepository<?, ?>>();
	
	@Inject
	public void setBookRepository(BookRepository bookRepository) {
		Assert.notNull(bookRepository);
		repositories.put(ProductType.BOOK, bookRepository);
	}
	
	@Inject
	public void setAlbumRepository(AlbumRepository albumRepository) {
		Assert.notNull(albumRepository);
		repositories.put(ProductType.ALBUM, albumRepository);
	}
	
	@Inject
	public void setMovieRepository(MovieRepository movieRepository) {
		Assert.notNull(movieRepository);
		repositories.put(ProductType.MOVIE, movieRepository);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> findByProductType(ProductType productType) {
		if(!repositories.containsKey(productType))
            throw new UnsupportedOperationException(productType + "은 지원하지 않습니다.");
		
		return (List<Product>) repositories.get(productType).findAll();
	}

}
