package order.controllers.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private static final Logger LOG = LoggerFactory.getLogger(ResourceNotFoundException.class);
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s is empty", resourceName));
        LOG.debug("{} is empty", resourceName);
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("Cannot found %s with %s : '%s'", resourceName, fieldName, fieldValue));
        LOG.debug("Cannot found [{}] with [{}] : [{}]", resourceName, fieldName, fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
