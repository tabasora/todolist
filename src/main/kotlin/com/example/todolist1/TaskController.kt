package com.example.todolist1

import com.example.todolist1.TaskCreateForm
import com.example.todolist1.TaskRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("tasks")
class TaskController(private val taskRepository: TaskRepository) {
    @GetMapping
    fun index(model: Model): String {
        val tasks = taskRepository.findAll()
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }
    @GetMapping("new")
    fun new(form: TaskCreateForm): String {
        return "tasks/new"
    }
    @PostMapping("")
    fun create(@Validated form: TaskCreateForm, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "tasks/new"
        }
        val content = requireNotNull(form.content)
        taskRepository.create(content)
        return "redirect:/tasks"
    }

}
