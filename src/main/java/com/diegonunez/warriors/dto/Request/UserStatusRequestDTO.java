package com.diegonunez.warriors.dto.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserStatusRequestDTO {
    @NotNull(message = "User status name cannot be null")
    @NotEmpty(message = "User status name cannot be empty")
    private String name;
    @NotNull(message = "User status description cannot be null")
    @NotEmpty(message = "User status description cannot be empty")
    private String description;

    public UserStatusRequestDTO(String name, String description){
        this.name = name;
        this.description = description;
    }

    //Getters and Setters

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
