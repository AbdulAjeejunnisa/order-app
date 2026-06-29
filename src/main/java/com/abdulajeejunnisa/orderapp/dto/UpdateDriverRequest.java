package com.abdulajeejunnisa.orderapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDriverRequest {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phoneNo;

    @NotBlank
    private String vehicleNumber;

    private boolean available;
}