package shopping.web.servlet.mvc;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class ExtendedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

	private Set<String> mobileDeviceNames = new HashSet<String>();
	
	public Set<String> getMobileDeviceNames() {
		return mobileDeviceNames;
	}
	public void setMobileDeviceNames(Set<String> mobileDeviceNames) {
		this.mobileDeviceNames = mobileDeviceNames;
	}

	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		MobileMapping methodAnnotation = AnnotationUtils.findAnnotation(method, MobileMapping.class);
		return new MobileMapping.MobileMappingRequestCondition(methodAnnotation, mobileDeviceNames);
	}
	
}
