package seedu.address.model.animal;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.address.storage.JsonAdaptedTask;

/**
 * Represents the TaskList containing tasks that are addressed to the {@link Animal}
 *
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs a TaskList that stores Tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns an unmodifiable list of tasks for copying.
     * @return an unmodifiable list of tasks.
     */
    public List<Task> getTasks() {
        return Collections.unmodifiableList(taskList);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Checks if the taskList does not contain any tasks.
     *
     * @return A boolean indicating whether taskList is empty or not.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    public List<JsonAdaptedTask> getSerializedTaskList() {
        return taskList.stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList());
    }

    /**
     * Adds all Tasks in the specified input taskList into this taskList.
     *
     * @param toCopyTaskList List of Tasks to copy from.
     */
    public void addAllTasks(List<Task> toCopyTaskList) {
        taskList.addAll(toCopyTaskList);
    }

    /**
     * Returns the number of tasks in the taskList.
     *
     * @return The number of tasks in the taskList.
     */
    public TaskList updateTaskList(int[] indexes, boolean isDone) throws IndexOutOfBoundsException {
        try {
            stream(indexes)
                    .forEach(index -> {
                        Task task = taskList.get(index);
                        Task updatedTask = task.updateTask(isDone);
                        taskList.set(index, updatedTask);
                    });

            return this;
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    /**
     * Returns the number of tasks in the taskList.
     *
     * @return The number of tasks in the taskList.
     */
    public TaskList resetTasks() {
        taskList.forEach(task -> {
            int index = taskList.indexOf(task);
            Task updatedTask = task.updateTask(false);
            taskList.set(index, updatedTask);
        });

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList that = (TaskList) o;
        return taskList.equals(that.taskList);
    }
    @Override
    public int hashCode() {
        return Objects.hash(taskList);
    }

    /**
     * Returns all tasks in the TaskList as a string, separated by a newline
     * @return A string of tasks, separated by a newline
     */
    @Override
    public String toString() {
        return taskList.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}