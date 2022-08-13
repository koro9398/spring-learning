package com.example.springlearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Task parent;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "parent", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Task> subTasks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "to_do_list_id")
    @NotNull
    @JsonBackReference
    private ToDoList toDoList;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + (parent != null ? parent.getId() : null) +
                ", subTasks=" + subTasks.stream().map(Task::getId).collect(Collectors.toSet()) +
                ", toDoList=" + (toDoList != null ? toDoList.getId() : null) +
                '}';
    }
}
