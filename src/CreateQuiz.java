import java.util.Scanner;
import java.util.List;
import java.util.Arrays;

public class CreateQuiz {
    private Scanner scanner = new Scanner(System.in);
    private List<String> validThemes = Arrays.asList("Science", "Technology", "Engineering", "Mathematics");
    private QuizRespository quizRespository;

    public CreateQuiz(QuizRespository quizRespository){
        this.quizRespository = quizRespository;
    }

    public void createQuiz(User user){
        if(user.getRole()!=Role.EDUCATOR){
            System.out.println("Only educators can create quizzes.");
            return;
        }

        System.out.println("---- Create Quiz ----");

        System.out.print("Enter Quiz Title: ");
        String quizTitle = scanner.nextLine();

        System.out.print("Enter Quiz Description: ");
        String quizDescription = scanner.nextLine();

        String quizTheme;
        while(true){
            System.out.print("Enter Quiz Theme (Science/Technology/Engineering/Mathematics): ");
            quizTheme = scanner.nextLine();
            if(validThemes.contains(quizTheme)){
                break;
            }else{
                System.out.println("Invalid theme. Please enter one of the following: Science, Technology, Engineering, Mathematics.");
            }
        }

        System.out.print("Enter Quizizz Link: ");
        String quizLink = scanner.nextLine();

        Quiz quiz = new Quiz(quizTitle, quizDescription, quizTheme, quizLink);
        quizRespository.addQuiz(quiz);
        user.addCreatedQuiz(quiz);
        System.out.println("Quiz created successfully");
    }
}
