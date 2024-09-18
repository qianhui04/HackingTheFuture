import java.time.LocalDate;

public class Booking{
    private LocalDate bookingDate;
    private String eventName;

    public Booking(LocalDate bookingDate, String eventName){
        this.bookingDate = bookingDate;
        this.eventName = eventName;
    }

    public LocalDate getBookingDate(){
        return bookingDate;
    }

    public String getEventName(){
        return eventName;
    }

    public String toString(){
        return eventName + " on " + bookingDate;
    }
}