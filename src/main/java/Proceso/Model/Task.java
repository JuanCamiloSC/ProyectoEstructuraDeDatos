package Proceso.Model;

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

}