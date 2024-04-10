package com.example.On_class.adapters.driving.http.dto.request;

import com.example.On_class.adapters.driving.http.util.MessageConstants;
import com.example.On_class.domain.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@Getter
public class AddCapacityRequest {
    @NotBlank(message = MessageConstants.FIELD_NAME_NULL_MESSAGE)
    private final String name;
    @NotBlank(message = MessageConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    private final String description;
    @NotEmpty(message = MessageConstants.FIELD_LIST_TECHNOLOGIES_EMPTY_MESSAGE)
    @Size(min = 3, max = 20, message = MessageConstants.FIELD_LIST_CAPACITIES_LENGTH_MESSAGE)
    private final List<Technology> technologies;
}
