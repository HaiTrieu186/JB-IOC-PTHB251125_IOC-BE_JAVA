package BT2.model;

public class Task {
    private int id;
    private String task_name;
    private TaskStatusEnum status;

    public Task() {
    }

    public Task(int id, String task_name, TaskStatusEnum status) {
        this.id = id;
        this.task_name = task_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }
}
