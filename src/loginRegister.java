import java.util.Scanner;

public class loginRegister{
    private static Scanner scanner = new Scanner (System.in);
    private DataStorage dataStorage;
    public User loggedInUser;

    public loginRegister(DataStorage dataStorage){
        this.dataStorage = dataStorage;
    }

    public void register(){
        System.out.println("---- Registration ----");
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your role (Educator/Parent/Young Student): ");
        String roleInput = scanner.nextLine();
        Role role = null;

        try{
            role = Role.valueOf(roleInput.toUpperCase());
        }catch(IllegalArgumentException e){
            System.out.println("Invalid role. Please enter one of the following: EDUCATOR, PARENT, YOUNG_STUDENT.");
        }

        double x = Math.random() * 1000 - 500;
        double y = Math.random() * 1000 - 500;
        LocationCoordinate locationCoordinate = new LocationCoordinate(x, y);
        System.out.printf("Your location: (%.2f, %.2f)%n", x ,y);

        User newUser = new User(email, username, password, role, locationCoordinate);
        dataStorage.saveUser(newUser);

        System.out.println("Registration successful!");
    }

    public void login(){
        System.out.println("---- Login ----");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = dataStorage.getUser(username,password);

        if(user!=null && user.password.equals(password)){
            loggedInUser = user;
            System.out.println("Login successfull! Welcome, " + user.username);
        }else{
            System.out.println("Login failed. Incorrect username or password.");
        }
    }

    public void viewProfile(){
        if(loggedInUser != null){
            viewProfile profileViewer = new viewProfile(loggedInUser);
            profileViewer.displayProfile();
        }else{
            System.out.println("No user is currently logged in.");
        }
    }
}