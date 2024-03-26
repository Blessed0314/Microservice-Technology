package com.example.On_class.adapters.driving.http.dto.request;

import com.example.On_class.adapters.driving.http.util.MessageConstants;
import com.example.On_class.domain.model.Technology;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddCapacityRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testConstructorAndGetters(){
        String name = "proof2";
        String description = "Fronted";
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language" ));
        AddCapacityRequest request = new AddCapacityRequest(name, description, technologies);
        assertEquals(name, request.getName());
        assertEquals(description, request.getDescription());
        assertEquals(technologies, request.getTechnologies());
    }

    @Test
    void testValidationNotBlankAndMinSize() {
        AddCapacityRequest request = new AddCapacityRequest("", "", new ArrayList<>());
        assertViolationsSizeAndMessages(request, 4,
                MessageConstants.FIELD_NAME_NULL_MESSAGE,
                MessageConstants.FIELD_DESCRIPTION_NULL_MESSAGE,
                MessageConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE,
                MessageConstants.FIELD_LIST_TECHNOLOGIES_EMPTY_MESSAGE);
    }

    @Test
    void testValidationMaxSize() {
        AddCapacityRequest request = getAddCapacityRequest();
        assertViolationsSizeAndMessages(request, 1,
                MessageConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE);
    }

    private AddCapacityRequest getAddCapacityRequest(){
        String testName = "Proof4";
        String testDescription = "Fronted";
        List<Technology> technologies = new ArrayList<>();
        for (int i = 1; i <= 21; i++) {
            Technology technology = new Technology((long) i, "Technology" + i, "Description of Technology" + i);
            technologies.add(technology);
        }
        return new AddCapacityRequest(testName, testDescription, technologies);
    }

    private void assertViolationsSizeAndMessages(AddCapacityRequest request, int expectedSize, String... expectedMessages) {
        Set<ConstraintViolation<AddCapacityRequest>> violations = validator.validate(request);
        assertEquals(expectedSize, violations.size());
        for (ConstraintViolation<AddCapacityRequest> violation : violations) {
            assertTrue(expectedMessages.length > 0 && containsMessage(violation.getMessage(), expectedMessages));
        }
    }

    private boolean containsMessage(String message, String... expectedMessages) {
        for (String expectedMessage : expectedMessages) {
            if (message.contains(expectedMessage)) {
                return true;
            }
        }
        return false;
    }
}