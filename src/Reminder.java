import java.time.LocalDateTime;

public class Reminder {
    private String message;
    private LocalDateTime dateTime;

    public Reminder(String message, LocalDateTime dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "[" + dateTime + "] " + message;
    }
}
