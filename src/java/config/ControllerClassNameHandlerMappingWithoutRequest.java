package config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

public class ControllerClassNameHandlerMappingWithoutRequest extends ControllerClassNameHandlerMapping {
    
    @Override
    protected String[] generatePathMappings(Class<?> beanClass) {
        if(beanClass.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping mapping = beanClass.getAnnotation(RequestMapping.class);
            return mapping.value();
        }
        return super.generatePathMappings(beanClass);
    }
    
}
