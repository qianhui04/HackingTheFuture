import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.List;

public class ViewEvents {
   private Scanner scanner = new Scanner(System.in);

   public ViewEvents(User user, List<CustomEvent> allEvents){
    LocalDate today = LocalDate.now();
    List<CustomEvent> liveEvents = new ArrayList<>();
    List<CustomEvent> upcomingEvents = new ArrayList<>();

    for(CustomEvent event:allEvents){
        if(event.getDate().isEqual(today)){
            liveEvents.add(event);
        }else if(event.getDate().isAfter(today)){
            upcomingEvents.add(event);
        }
    }

    upcomingEvents.sort(Comparator.comparing(CustomEvent::getDate));

    System.out.println("---- Live Events ----");
    if(!liveEvents.isEmpty()){
        for(CustomEvent event:liveEvents){
            System.out.println(event.getEventTitle() + " on " + event.getDate());
        }
    }else{
        System.out.println("No live events today.");;
    }

    System.out.println("\n---- Closest 3 Upcoming Events ----");
    int eventLimit = Math.min(3, upcomingEvents.size());
    for(int i=0;i<eventLimit;i++){
        CustomEvent event = upcomingEvents.get(i);
        System.out.println((i+1) + ". " + event.getEventTitle() + " on " + event.getDate());
    }

    if(user.getRole() == Role.YOUNG_STUDENT){
        registerForEvent(user, allEvents);
    }
   }

   private void registerForEvent(User user, List<CustomEvent> allEvents){
    System.out.println("\n---- Register for an Event ----");
    System.out.print("Enter the title of the event you want to register for: ");
    String eventTitle = scanner.nextLine();

    CustomEvent eventToRegister = null;
    for(CustomEvent event:allEvents){
        if(event.getEventTitle().equalsIgnoreCase(eventTitle)){
            eventToRegister = event;
            break;
        }
    }
    
    if(eventToRegister==null){
        System.out.println("Event not found.");
        return;
    }

    for(CustomEvent registeredEvent : user.getRegisteredEvents()){
        if(registeredEvent.getDate().isEqual(eventToRegister.getDate())){
            System.out.println("You are already registered for an event on the same day. Registration failed.");
            return;
        }
    }

    user.addRegisteredEvent(eventToRegister);
    user.setCurrentPoints(user.getCurrentPoints()+5);
    System.out.println("Successfully registered for " + eventToRegister.getEventTitle() + ". You have earned 5 points!");
   }
}
