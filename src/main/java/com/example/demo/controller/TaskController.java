package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @CrossOrigin
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        try{
            List<Task> tasks = new ArrayList<>();
            taskRepository.findAll().forEach(tasks::add);
            if (tasks.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") long id){
        Optional<Task> task =  taskRepository.findById(id);System.out.println(task.get());
        if (task.isPresent()){
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PostMapping("/tasks")
    public ResponseEntity<Task>createTask(@RequestBody Task task){
        try{
            System.out.println(task);
            Task _task = new Task(task.getText(), task.getDay(), task.isReminder());
            System.out.println(_task);
            taskRepository.save(_task);
            return new ResponseEntity<>(_task, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id){
        try{
            taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> toggleReminder(@PathVariable("id") long id){
        Optional<Task> taskData = taskRepository.findById(id);
        if(taskData.isPresent()){
            Task _task = taskData.get();
            _task.setReminder(!(_task.isReminder()));
            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
