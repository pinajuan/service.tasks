package com.juanpina.service.tasks;

import org.springframework.scheduling.config.Task;

import java.util.List;

public class TaskService {

    private final TasksRepository tasksRepository;
    private final NotificationClient notificationClient;

    public TaskService(TasksRepository tasksRepository, NotificationClient notificationClient) {
        this.tasksRepository = tasksRepository;
        this.notificationClient = notificationClient;
    }

    public void senNotificationForDueTasks() {

        List<TasksEntity> tasks = tasksRepository.findDueTasks();

        for (TasksEntity task : tasks) {
            NotificationRequest request = new NotificationRequest("Sua tarefa: " + task.getTitle() + "está prestes a vencer!", "Seu email: " + task.getEmail());
            notificationClient.sendNotification(request);
            task.setNotified(true);
        }
    }

}
