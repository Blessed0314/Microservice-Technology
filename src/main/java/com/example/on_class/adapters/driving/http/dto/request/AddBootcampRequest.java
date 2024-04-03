package com.example.on_class.adapters.driving.http.dto.request;

import com.example.on_class.adapters.driving.http.util.MessageConstants;
import com.example.on_class.domain.model.Capacity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class AddBootcampRequest {
    @NotBlank(message = MessageConstants.FIELD_NAME_NULL_MESSAGE)
    private final String name;
    @NotBlank(message = MessageConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    private final String description;
    @NotEmpty(message = MessageConstants.FIELD_LIST_CAPACITIES_EMPTY_MESSAGE)
    @Size(min = 1, max = 4, message = MessageConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE)
    private final List<Capacity> capacities;

}
