package com.example.On_class.adapters.driving.http.dto.request;

import com.example.On_class.adapters.driving.http.util.MessageConstants;
import com.example.On_class.domain.model.Capacity;
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

class AddBootcampRequestTest {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    @Test
    void testConstructorAndGetters(){
        String name = "Proof3";
        String description = "Bootcamp description";
        List<Capacity> capacities = createListCapacities(4,1);

        AddBootcampRequest request = new AddBootcampRequest(name, description, capacities);
        assertEquals(name, request.getName());
        assertEquals(description, request.getDescription());
        assertEquals(capacities, request.getCapacities());
    }

    @Test
    void testValidationNotBlankAndMinSize(){
        AddBootcampRequest request = new AddBootcampRequest("","", new ArrayList<>());
        assertViolationsSizeAndMessages(request, 4,
                MessageConstants.FIELD_NAME_NULL_MESSAGE,
                MessageConstants.FIELD_DESCRIPTION_NULL_MESSAGE,
                MessageConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE,
                MessageConstants.FIELD_LIST_CAPACITIES_EMPTY_MESSAGE);

    }

    @Test
    void testValidationMaxSize(){
        AddBootcampRequest request = new AddBootcampRequest(
                "Proof3", "Bootcamp description", createListCapacities(3,5));
        assertViolationsSizeAndMessages(request, 1,
                MessageConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE);
    }

    private List<Capacity> createListCapacities(int techSize, int capSize){

        List<Technology> technologies = new ArrayList<>();
        for (int i = 1; i <= techSize; i++) {
            Technology technology = new Technology((long) i, "Technology" + i, "Description of Technology" + i);
            technologies.add(technology);
        }

        List<Capacity> capacities = new ArrayList<>();
        for (int i = 1; i <= capSize; i++){
            Capacity capacity = new Capacity((long) i, "Capacity" + i, "Description of Capacity" + i, technologies);
            capacities.add(capacity);
        }
        return capacities;
    }

    private void assertViolationsSizeAndMessages(AddBootcampRequest request, int expectedSize, String... expectedMessages) {
        Set<ConstraintViolation<AddBootcampRequest>> violations = validator.validate(request);
        assertEquals(expectedSize, violations.size());
        for (ConstraintViolation<AddBootcampRequest> violation : violations) {
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