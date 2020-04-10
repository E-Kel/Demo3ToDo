import org.joda.time.LocalDateTime;

public class Task {
    private String title;

    private String description;
    private LocalDateTime scaguledDate;
    private LocalDateTime crationDate;

    public Task(String title, String description, String scaguledDate) {
        this.title = title;
        this.description = description;
        this.scaguledDate = LocalDateTime.parse(scaguledDate);
        this.crationDate = LocalDateTime.now();
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

    public String getScaguledDate() {
        return scaguledDate.toString();
    }

    public void setScaguledDate(String scaguledDate) {
        this.scaguledDate = LocalDateTime.parse(scaguledDate);
    }

}