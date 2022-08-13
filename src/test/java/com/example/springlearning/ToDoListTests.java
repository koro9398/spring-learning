package com.example.springlearning;

import com.example.springlearning.model.Task;
import com.example.springlearning.model.ToDoList;
import com.example.springlearning.repository.TaskRepository;
import com.example.springlearning.repository.ToDoListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class ToDoListTests {
    @Autowired
    private ToDoListRepository toDoListRepository;

    @Autowired
    private TaskRepository taskRepository;

    @AfterEach
    public void cleanUp() {
        toDoListRepository.deleteAll();
    }

    @Test
    public void test() {
        assertEquals(0, toDoListRepository.count());


        Task task1 = new Task();
        task1.setTitle("Тестовый элемент 1");
        task1.setDescription("Проверка создания элемента списка");

        Task task2 = new Task();
        task2.setTitle("Тестовый элемент 2");
        task2.setDescription("Проверка создания элемента списка");

        ToDoList toDoList = new ToDoList();
        toDoList.setTitle("Тест создания списка");
        toDoList.getTasks().addAll(Set.of(task1, task2));
        task1.setToDoList(toDoList);
        task2.setToDoList(toDoList);


        toDoListRepository.save(toDoList);
        assertEquals(1, toDoListRepository.count());
        assertEquals(2, taskRepository.count());


        Optional<ToDoList> foundToDoListOpt = toDoListRepository.findById(toDoList.getId());
        assertTrue(foundToDoListOpt.isPresent());

        ToDoList foundToDoList = foundToDoListOpt.get();
        assertEquals(toDoList.getId(), foundToDoList.getId());
        assertEquals(toDoList.getTasks().size(), foundToDoList.getTasks().size());


        toDoListRepository.delete(toDoList);
        assertEquals(0, toDoListRepository.count());
        assertEquals(0, taskRepository.count());
    }
}
