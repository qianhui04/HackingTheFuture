import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreateEvent {
    private Scanner scanner = new Scanner(System.in);

    public void createEvent(User user){
        if(user.getRole()!=Role.EDUCATOR){
            System.out.println("Only Educators can create events");
            return;
        }

        System.out.println("---- Create Event ----");

        System.out.print("Enter Event Title: ");
        String eventTitle = scanner.nextLine();

        System.out.print("Enter Event Description: ");
        String eventDescription = scanner.nextLine();

        System.out.print("Enter Event Venue: ");
        String eventVenue = scanner.nextLine();

        System.out.print("Enter Event Date (dd/mm/yyyy): ");
        String dateInput = scanner.nextLine();
        LocalDate eventDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Enter Event Time (HH:mm): ");
        String timeInput = scanner.nextLine();
        LocalTime eventTime = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm"));

        CustomEvent event = new CustomEvent(eventTitle, eventDescription, eventVenue, eventDate, eventTime);
        user.addCreatedEvent(event);
        System.out.println("Event created successfully!");
        System.out.println(event);
    }
}
