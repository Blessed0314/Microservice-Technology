package com.example.On_class.adapters.driving.http.dto.request;


import com.example.On_class.adapters.driving.http.util.MessageConstants;
import org.junit.jupiter.api.Test;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddTechnologyRequestTest {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testConstructorAndGetters() {
        String name = "Java";
        String description = "Programming language";
        AddTechnologyRequest request = new AddTechnologyRequest(name, description);
        assertEquals(name, request.getName());
        assertEquals(description, request.getDescription());
    }

    @Test
    void testValidationNotBlank() {
        AddTechnologyRequest request = new AddTechnologyRequest("", "");
        Set<ConstraintViolation<AddTechnologyRequest>> violations = validator.validate(request);
        assertEquals(4, violations.size());
        for (ConstraintViolation<AddTechnologyRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(MessageConstants.FIELD_NAME_NULL_MESSAGE) ||
                    violation.getMessage().contains(MessageConstants.FIELD_DESCRIPTION_NULL_MESSAGE)||
                    violation.getMessage().contains(MessageConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE) ||
                    violation.getMessage().contains(MessageConstants.FIELD_NAME_MAX_LENGTH_MESSAGE));
        }
    }

    @Test
    void testValidationMaxSize() {
        AddTechnologyRequest request = getAddTechnologyRequest();
        Set<ConstraintViolation<AddTechnologyRequest>> violations = validator.validate(request);
        assertEquals(2, violations.size());
        for (ConstraintViolation<AddTechnologyRequest> violation : violations) {
            assertTrue(violation.getMessage().contains(MessageConstants.FIELD_NAME_MAX_LENGTH_MESSAGE) ||
                    violation.getMessage().contains(MessageConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE));
        }
    }

    private static AddTechnologyRequest getAddTechnologyRequest() {
        String testName = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String testDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
        return new AddTechnologyRequest(testName, testDescription);
    }

}