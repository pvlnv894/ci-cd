package ru.netology.cicd.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class Task {
    @Getter
    @Setter
    @NotNull
    private Long id;
    @Setter
    @Getter
    @NotBlank
    private String title;
    @Setter
    @Getter
    @NotBlank
    private String createdAt;
    @Setter
    @Getter
    private boolean done;

    public Task() {
    }

    public Task(Long id, String title, String createdAt, boolean done) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.done = done;
    }
}
