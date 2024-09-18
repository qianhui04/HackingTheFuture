import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class User{
    String email;
    String username;
    String password;
    Role role;
    List<User> parents = new ArrayList<>();
    List<User> children = new ArrayList<>();
    LocationCoordinate locationCoordinate;
    int currentPoints;
    List<CustomEvent> createdEvents = new ArrayList<>();
    List<Quiz> createdQuizzes = new ArrayList<>();
    List<CustomEvent> registeredEvents = new ArrayList<>();
    List<Quiz> completedQuizzes = new ArrayList<>();
    List<User> friendList = new ArrayList<>();
    List<FriendRequest> incomingRequests = new ArrayList<>();
    List<Booking> bookings = new ArrayList<>();
    LocalDateTime pointLastUpdated;

    public User(String email, String username, String password, Role role,  LocationCoordinate locationCoordinate){
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locationCoordinate = locationCoordinate;
        this.currentPoints = 0;
        this.pointLastUpdated = LocalDateTime.now();
    }

    public User(String email, String username, String password, Role role,  LocationCoordinate locationCoordinate, List<User> parents, List<User> children, int currentPoints){
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.locationCoordinate = locationCoordinate;
        this.parents = parents;
        this.children = children;
        this.currentPoints = currentPoints;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public LocationCoordinate getLocationCoordinate() {
        return locationCoordinate;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public List<User> getParents() {
        return parents;
    }

    public List<User> getChildren() {
        return children;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setParents(List<User> parents) {
        this.parents = parents;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }

    public void setLocationCoordinate(LocationCoordinate locationCoordinate) {
        this.locationCoordinate = locationCoordinate;
    }

    public void setCurrentPoints(int currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void addCreatedEvent(CustomEvent event){
        createdEvents.add(event);
    }

    public void addCreatedQuiz(Quiz quiz){
        createdQuizzes.add(quiz);
    }

    public List<CustomEvent> getCreatedEvents(){
        return createdEvents;
    }

    public List<Quiz> getCreatedQuizzes(){
        return createdQuizzes;
    }

    public void addRegisteredEvent(CustomEvent event){
        registeredEvents.add(event);
    }

    public List<CustomEvent> getRegisteredEvents() {
        return registeredEvents;
    }

    public void addCompletedQuiz(Quiz quiz){
        completedQuizzes.add(quiz);
        currentPoints += 2;
    }

    public List<Quiz> getCompletedQuizzes(){
        return completedQuizzes;
    }

    public List<User> getFriends(){
        return friendList;
    }

    public List<FriendRequest> getIncomingRequests(){
        return incomingRequests;
    }

    public List<Booking> getPastBookings(){
        LocalDate today = LocalDate.now();
        return bookings.stream().filter(booking -> booking.getBookingDate().isBefore(today)).collect(Collectors.toList());
    }

    public void updatePoints(int points){
        this.currentPoints += points;
        this.pointLastUpdated = LocalDateTime.now();
    }

    public LocalDateTime getPointLastUpdated() {
        return pointLastUpdated;
    }
}