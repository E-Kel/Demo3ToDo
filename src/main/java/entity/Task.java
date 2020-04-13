package entity;

import java.io.Serializable;

public class Task implements Serializable{
    private String title;

    private String description;
   // private LocalDateTime creationDate;

    public Task(String title, String description){ //String deadline) {
        this.title = title;
        this.description = description;
       //this.creationDate = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return title+"\n"+description+"\n----------";
    }
}
