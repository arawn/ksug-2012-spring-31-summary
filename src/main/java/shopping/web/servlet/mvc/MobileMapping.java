package shopping.web.servlet.mvc;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collections;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MobileMapping {

	
	public static class MobileMappingRequestCondition implements RequestCondition<MobileMappingRequestCondition> {
		
		private final MobileMapping mobileMapping;
		private final Set<String> mobileDeviceNames;
		
		public MobileMappingRequestCondition(MobileMapping mobileMapping, Set<String> mobileDeviceNames) {
			this.mobileMapping = mobileMapping;
			
			if(CollectionUtils.isEmpty(mobileDeviceNames))
				this.mobileDeviceNames = Collections.emptySet();
			else
				this.mobileDeviceNames = mobileDeviceNames;
		}

		@Override
		public MobileMappingRequestCondition combine(MobileMappingRequestCondition other) {
			return this;
		}

		@Override
		public MobileMappingRequestCondition getMatchingCondition(HttpServletRequest request) {
			String userAgent = request.getHeader("user-agent");
			if(mobileMapping == null) {
				for(String deviceName : mobileDeviceNames)
					if(userAgent.indexOf(deviceName) > -1)
						return null;
				
				return this;		
			} else {
				for(String deviceName : mobileDeviceNames)
					if(userAgent.indexOf(deviceName) > -1)
						return this;
				
				return null;
			}
		}

		@Override
		public int compareTo(MobileMappingRequestCondition other, HttpServletRequest request) {
			return 0;
		}
		
	}
	
}
