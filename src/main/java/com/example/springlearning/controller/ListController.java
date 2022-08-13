package com.example.springlearning.controller;

import com.example.springlearning.model.ToDoList;
import com.example.springlearning.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListController {
    private final ToDoListRepository toDoListRepository;

    @GetMapping
    public Iterable<ToDoList> allLists() {
        return toDoListRepository.findAll();
    }

    @GetMapping("/{id}")
    public ToDoList listById(@PathVariable Long id) {
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("List with id " + id + " not found"));
    }

    @PostMapping
    public HttpStatus create(@RequestBody ToDoList toDoList) {
        toDoListRepository.save(toDoList);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        toDoListRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
