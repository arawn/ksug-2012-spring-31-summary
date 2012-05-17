package shopping.domain.support.convert;

import org.springframework.core.convert.converter.Converter;

import shopping.common.lang.CodeEncodableEnum;
import shopping.domain.product.ProductType;

public class StringToProductTypeConvert implements Converter<String, ProductType> {

	@Override
	public ProductType convert(String source) {
		return CodeEncodableEnum.CodeEncodableEnumUtils.valueFromCode(ProductType.class, source);
	}

}
