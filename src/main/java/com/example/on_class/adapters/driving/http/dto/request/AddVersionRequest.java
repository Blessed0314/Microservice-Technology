package com.example.on_class.adapters.driving.http.dto.request;

import com.example.on_class.adapters.driving.http.util.MessageConstants;
import com.example.on_class.domain.model.Bootcamp;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class AddVersionRequest {
    @Positive(message = MessageConstants.FIELD_QUOTA_GREATER_THAN_0_OR_NOT_NULL)
    private final int quota;
    @NotNull(message = MessageConstants.FIELD_INITIAL_DATE_NULL_MESSAGE)
    private final LocalDate initialDate;
    @NotNull(message = MessageConstants.FIELD_END_DATE_NULL_MESSAGE)
    private final LocalDate endDate;
    private final Bootcamp bootcamp;
}
