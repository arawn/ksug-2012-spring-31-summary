package shopping.domain.support.convert;

import java.lang.annotation.Annotation;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.NumberUtils;

import shopping.domain.product.Movie;
import shopping.domain.product.Product;

import com.google.common.collect.Sets;

public class ProductEntityConvert implements GenericConverter {
	
	private static Logger logger = LoggerFactory.getLogger(ProductEntityConvert.class);
	
	private Set<ConvertiblePair> convertiblePairs;
	
	
	@PersistenceContext EntityManager entityManager;
	
			
	public ProductEntityConvert() {
		this.convertiblePairs = Sets.newHashSet(new ConvertiblePair(String.class, Movie.class)
											  , new ConvertiblePair(Movie.class, String.class));
	}

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return convertiblePairs;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		Object result = null;
		
		if(Product.class.isAssignableFrom(sourceType.getType())) {
			result = String.valueOf(((Product) source).getId());
		} else {
			result = entityManager.getReference(targetType.getType(), NumberUtils.parseNumber(String.valueOf(source), Long.class));
			
			Annotation annotation = targetType.getAnnotation(Valid.class);
			if(annotation != null && HibernateProxy.class.isAssignableFrom(result.getClass())) {
				Hibernate.initialize(result);
				HibernateProxy proxy = (HibernateProxy) result;
				result = proxy.getHibernateLazyInitializer().getImplementation();
				
				logger.info("hibernate initialize : {}", result);
			}
		}
		
		logger.info("convert {} to {} : {}", new Object[]{sourceType, targetType, result});
		
		return result;
	}

}
