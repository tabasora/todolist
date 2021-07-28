package com.example.todolist1

interface TaskRepository {
    fun create(content: String): Task

    fun update(task: Task)

    fun findAll(): List<Task>

    fun findById(id: Long): Task?
}
