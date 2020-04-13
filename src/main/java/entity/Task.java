package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Serializable{
    private String title;

    private String description;
    private LocalDateTime creationDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Task(String title, String description){ //String deadline) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
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
        return  "title: " + title + '\'' +
                "(" + description + ")\'" +
                ", created='" + creationDate.format(formatter) + '\'' +
                '}';
    }
}
