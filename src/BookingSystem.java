import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class BookingSystem {
    private List<BookingDestination> destinations = new ArrayList<>();
    private final LocationCoordinate parentLocation;
    private List<LocalDate> availableDates;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Map<String , Parent> parents = new HashMap<>();
    private Map<String, Child> children = new HashMap<>();

    public BookingSystem(LocationCoordinate parentLocation){
        this.parentLocation = parentLocation;
        this.availableDates = getNextWeekDates();
        loadDestinations();
    }

    public void loadDestinations(){
        try(BufferedReader br = new BufferedReader(new FileReader("BookingDestination.txt"))){
            String name;
            while((name = br.readLine()) != null){
                String coordinatesLine = br.readLine();
                if(coordinatesLine != null){
                    String[] coordinates = coordinatesLine.split(",");
                    double x = Double.parseDouble(coordinates[0].trim());
                    double y = Double.parseDouble(coordinates[1].trim());
                    destinations.add(new BookingDestination(name, new LocationCoordinate(x,y)));
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private double calculateDistance(LocationCoordinate destCoord){
        double dx = destCoord.getX() - parentLocation.getX();
        double dy = destCoord.getY() - parentLocation.getY();
        return Math.sqrt((dx*dx) + (dy*dy));
    }

    public void displayDestinations(){
        List<BookingDestination> sortedDestinations = new ArrayList<>(destinations);
        sortedDestinations.sort(Comparator.comparingDouble(dest -> calculateDistance(dest.getCoordinate())));

        System.out.println("Booking page");
        System.out.println("=================================================");
        for(int i=0;i<sortedDestinations.size();i++){
            BookingDestination dest = sortedDestinations.get(i);
            double distance = calculateDistance(dest.getCoordinate());
            System.out.printf("[%d] %s%n    %.2f km away%n", i+1, dest.getName(), distance);
        }
    }

    public void displayAvailableTimeSlot(List<CustomEvent> registeredEvents){
        System.out.println("Available Time Slots: ");

        for(int i=0;i<availableDates.size();i++){
            LocalDate date = availableDates.get(i);
            if(!isDateClashing(date, registeredEvents)){
                System.out.printf("[%d] %s%n", i+1, date.format(formatter));
            }
        }
    }

    private boolean isDateClashing(LocalDate date, List<CustomEvent> registeredEvents){
        for(CustomEvent event:registeredEvents){
            if(event.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

    private List<LocalDate> getNextWeekDates(){
        List<LocalDate> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for(int i=1;i<=7;i++){
            dates.add(today.plusDays(i));
        }
        return dates;
    }

    public void makeBooking(List<CustomEvent> registeredEvents){
        Scanner scanner = new Scanner(System.in);

        displayDestinations();

        System.out.print("Enter destination ID for booking: ");
        int destinationIndex = scanner.nextInt();
        BookingDestination selectedDestination = destinations.get(destinationIndex - 1);
        System.out.println("=======================================================================");
        System.out.println("Selected booking for: " + selectedDestination.getName());

        displayAvailableTimeSlot(registeredEvents);

        System.out.print("Enter a time slot: ");
        int timeSlotIndex = scanner.nextInt();
        LocalDate selectedDate = availableDates.get(timeSlotIndex - 1);

        if(!isDateClashing(selectedDate, registeredEvents)){
            System.out.println("Booking confirmed!");
            System.out.println("Destination: " + selectedDestination.getName());
            System.out.println("Booking Date: " + selectedDate.format(formatter));
        }else{
            System.out.println("Cannot make booking, as this date conficts with a registered event.");
        }
    }

    public void loadParentChildRelationship(){
        try(BufferedReader br = new BufferedReader(new FileReader("ParentChild.txt"))){
            String line;
            while((line = br.readLine())!=null){
                String[] data = line.split(",");
                String parentUsername = data[0].trim();
                String childUsername = data[1].trim();

                Parent parent = parents.computeIfAbsent(parentUsername, username -> new Parent(username));
                Child child = children.computeIfAbsent(childUsername, username -> new Child(username));

                parent.addChild(child);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addNewParentChildRelation(String parentUsername, String childusername){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("ParentChild.txt", true))){
            writer.write(parentUsername + ", " + childusername);
            writer.newLine();
            System.out.println("New parent-child relationship added: " + parentUsername + " -> " + childusername);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
