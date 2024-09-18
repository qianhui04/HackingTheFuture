import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DataStorage dataStorage = new DataStorage();
    private static loginRegister loginRegister = new loginRegister(dataStorage);
    private static CreateEvent createEvent = new CreateEvent();
    private static QuizRespository quizRespository = new QuizRespository(); 
    private static CreateQuiz createQuiz = new CreateQuiz(quizRespository);
    private static AttemptQuiz attemptQuiz = new AttemptQuiz();

    public static void main (String[] args){

        boolean exit = false;
        while(!exit){
            System.out.println("---- Welcome ----");
            System.out.println("1. Register");
            System.out.println("2. Login");
            if(loginRegister.loggedInUser != null){
                System.out.println("3. View Profile");
                if(loginRegister.loggedInUser.getRole() == Role.EDUCATOR){
                    System.out.println("4. Create Event");
                    System.out.println("5. Create Quiz");
                }
                if(loginRegister.loggedInUser.getRole() == Role.YOUNG_STUDENT){
                    System.out.println("6. Attempt Quiz");
                }
            }
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch(option){
                case 1: loginRegister.register(); break;
                case 2: loginRegister.login(); break;
                case 3: loginRegister.viewProfile();break;
                case 4: if(loginRegister.loggedInUser != null && loginRegister.loggedInUser.getRole()== Role.EDUCATOR){
                    createEvent.createEvent(loginRegister.loggedInUser);
                }else{
                    System.out.println("You need to log in first.");
                }
                break;
                case 5: if(loginRegister.loggedInUser != null){
                    createQuiz.createQuiz(loginRegister.loggedInUser);
                }else{
                    System.out.println("You need to log in first.");
                }
                break;
                case 6: if(loginRegister.loggedInUser != null){
                    if(loginRegister.loggedInUser instanceof YoungStudent){
                        YoungStudent student = (YoungStudent) loginRegister.loggedInUser;
                        List<Quiz> allQuizzes = quizRespository.getAllQuizzes();
                    attemptQuiz.attemptQuiz(student, allQuizzes);
                    }else{
                        System.out.println("Only Young Student can attempt quizzes.");
                    }
                }else{
                    System.out.println("You need to log in first.");
                }
                break;
                case 7: exit = true; System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option. Please try again");
            }
        }
    }
}
