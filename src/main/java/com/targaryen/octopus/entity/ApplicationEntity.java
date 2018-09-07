package com.targaryen.octopus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationEntity {
    private int applicationId;
    private int status;
    private int applicantId;
    private int postId;
}
