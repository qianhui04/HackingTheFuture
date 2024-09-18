import java.time.LocalDate;
import java.time.LocalTime;

public class CustomEvent {
    private String eventTitle;
    private String eventDescription;
    private String eventVenue;
    private LocalDate date;
    private LocalTime time;

    public CustomEvent(String eventTitle, String eventDescription, String eventVenue, LocalDate eventDate, LocalTime eventTime){
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventVenue = eventVenue;
        this.date = eventDate;
        this.time = eventTime;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CustomEvent [eventTitle= " + eventTitle + "\n eventDescription= " + eventDescription + "\n eventVenue= "+ eventVenue + "\n date= " + date + "\n time= " + time + "]";
    }
    
}
