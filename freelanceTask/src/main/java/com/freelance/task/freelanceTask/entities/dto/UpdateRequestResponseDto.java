package com.freelance.task.freelanceTask.entities.dto;

import com.freelance.task.freelanceTask.entities.RequestStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequestResponseDto {

    private RequestStatus requestStatus;
    private String notesToRequester;
    private boolean isStatusChanged;
    private boolean isNotesChanged;
}
