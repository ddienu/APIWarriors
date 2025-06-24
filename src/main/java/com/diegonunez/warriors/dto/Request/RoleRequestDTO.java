package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RoleRequestDTO {
    @NotNull(message = "Role name cannot be null")
    @NotBlank(message = "Role name cannot be empty")
    private String name;
    @NotNull(message = "Role description cannot be null")
    @NotEmpty(message = "Role description cannot be empty")
    private String description;

    public RoleRequestDTO(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
