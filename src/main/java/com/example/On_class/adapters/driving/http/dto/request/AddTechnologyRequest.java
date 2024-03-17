package com.example.On_class.adapters.driving.http.dto.request;

import com.example.On_class.adapters.driving.http.util.MessageConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class AddTechnologyRequest {

    @NotBlank(message = MessageConstants.FIELD_NAME_NULL_MESSAGE)
    @Size(min = 4, max = 50, message = MessageConstants.FIELD_NAME_MAX_LENGTH_MESSAGE)
    private final String name;

    @NotBlank(message = MessageConstants.FIELD_DESCRIPTION_NULL_MESSAGE)
    @Size(min = 10, max = 90, message = MessageConstants.FIELD_DESCRIPTION_MAX_LENGTH_MESSAGE)
    private final String description;
}
