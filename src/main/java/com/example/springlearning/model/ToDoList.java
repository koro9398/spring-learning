package com.example.springlearning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class ToDoList {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    @OneToMany(mappedBy = "toDoList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    @OrderBy("id")
    private Set<Task> tasks = new HashSet<>();

    @Override
    public String toString() {
        return "ToDoList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", tasks=" + tasks.stream().map(Task::getId).collect(Collectors.toSet()) +
                '}';
    }
}
