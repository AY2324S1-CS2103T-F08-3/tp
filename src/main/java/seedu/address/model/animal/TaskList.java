package seedu.address.model.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * Returns the taskList.
     * @return the taskList containing tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) { taskList.add(task); }

    /**
     * Checks if the taskList does not contain any tasks.
     *
     * @return A boolean indicating whether taskList is empty or not.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
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
