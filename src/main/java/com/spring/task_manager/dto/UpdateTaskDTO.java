package com.spring.task_manager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTaskDTO {
    String description;
    Boolean completed;
    String deadline;
}
