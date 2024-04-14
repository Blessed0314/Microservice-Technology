package com.example.on_class.adapters.driving.http.dto.request;

import com.example.on_class.adapters.driving.http.util.MessageConstants;
import com.example.on_class.domain.model.Bootcamp;
import com.example.on_class.domain.model.Capacity;
import com.example.on_class.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddVersionRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetters() {
        int quota = 10;
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(30);
        Bootcamp bootcamp = createBootcamp();
        AddVersionRequest request = new AddVersionRequest(quota, initialDate, endDate, bootcamp);

        assertEquals(quota, request.getQuota());
        assertEquals(initialDate, request.getInitialDate());
        assertEquals(endDate, request.getEndDate());
        assertEquals(bootcamp, request.getBootcamp());
    }

    @Test
    void testValidQuota() {
        LocalDate endDate = LocalDate.now().plusDays(30);
        AddVersionRequest request = new AddVersionRequest(10, LocalDate.now(), endDate, createBootcamp());

        Set<ConstraintViolation<AddVersionRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidQuota() {
        LocalDate endDate = LocalDate.now().plusDays(30);
        AddVersionRequest request = new AddVersionRequest(0, LocalDate.now(), endDate, createBootcamp());

        Set<ConstraintViolation<AddVersionRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals(MessageConstants.FIELD_QUOTA_GREATER_THAN_0_OR_NOT_NULL, violations.iterator().next().getMessage());
    }

    @Test
    void testNullInitialDate() {
        LocalDate endDate = LocalDate.now().plusDays(30);
        AddVersionRequest request = new AddVersionRequest(10, null, endDate, createBootcamp());

        Set<ConstraintViolation<AddVersionRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals(MessageConstants.FIELD_INITIAL_DATE_NULL_MESSAGE, violations.iterator().next().getMessage());
    }

    @Test
    void testNullEndDate() {
        AddVersionRequest request = new AddVersionRequest(10, LocalDate.now(), null, createBootcamp());

        Set<ConstraintViolation<AddVersionRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals(MessageConstants.FIELD_END_DATE_NULL_MESSAGE, violations.iterator().next().getMessage());
    }

    private Bootcamp createBootcamp() {
        List<Technology> technologies = new ArrayList<>();
        technologies.add(new Technology(1L, "Java", "Programming Language"));

        List<Capacity> capacities = new ArrayList<>();
        capacities.add(new Capacity(1L, "Proof2", "Fronted", technologies));

        return new Bootcamp(1L, "Proof3", "bootcamp description", capacities);
    }
}