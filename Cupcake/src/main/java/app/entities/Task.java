package app.entities;

public class Task {
    private int taskId;
    private String name;
    private boolean done;
    private int userId;

    public Task(int taskId, String name, boolean done, int userId) {
        this.taskId = taskId;
        this.name = name;
        this.done = done;
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + name + '\'' +
                ", done=" + done +
                ", userId=" + userId +
                '}';
    }
}
