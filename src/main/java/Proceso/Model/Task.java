package Proceso.Model;

import java.util.Objects;

public class Task {

    private String name;
    private String description;
    private Boolean obligatory;
    private int time;

    public Task(String name, String description, Boolean obligatory, int time) {
        this.name = name;
        this.description = description;
        this.obligatory = obligatory;

        this.time = time;
    }

    public String getname() {
        return name;
    }

    public String getdescription() {
        return description;
    }

    public Boolean getobligatory() {
        return obligatory;
    }


    public int gettime() {
        return time;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public void setobligatory(Boolean obligatory) {
        this.obligatory = obligatory;
    }

    public void settime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return time == task.time && Objects.equals(name, task.name) && Objects.equals(description, task.description) && Objects.equals(obligatory, task.obligatory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, obligatory, time);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", obligatory=" + obligatory +
                ", time=" + time +
                '}';
    }
}