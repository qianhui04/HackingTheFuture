import java.util.stream.Collectors;

public class viewProfile {
    private User user;

    public viewProfile(User user){
        this.user = user;
    }

    public void displayProfile(){
        System.out.println("---- Profile ----");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());

        if(user.getRole() == Role.EDUCATOR){
            System.out.println("Number of Quizzes Created: " + user.getCreatedQuizzes().size());
            System.out.println("Number of Events Created: " + user.getCreatedEvents().size());
        }
        else if(user.getRole() == Role.PARENT){
            System.out.println("Past Booking Made: " + user.getPastBookings().stream().map(Booking::toString).collect(Collectors.joining(", ")));
        }
        else if(user.getRole() == Role.YOUNG_STUDENT){
            System.out.println("Current Points: " + user.getCurrentPoints());
            System.out.println("Friends: " + user.getFriends().stream().map(User::getUsername).collect(Collectors.joining(", ")));
        }
    }
}
