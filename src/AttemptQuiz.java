import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class AttemptQuiz {
    private Scanner scanner = new Scanner(System.in);
    private List<String> validTheme = Arrays.asList("Science", "Technology", "Engineering", "Mathematics");

    public void attemptQuiz(YoungStudent student, List<Quiz> allQuizzes){
        List<String> selectedThemes = selectThemes();

        List<Quiz> filteredQuizzes = filterQuizzesByTheme(allQuizzes, selectedThemes);

        if(filteredQuizzes.isEmpty()){
            System.out.println("No quizzes available for the selected themes.");
            return;
        }

        System.out.println("Available Quizzes:");
        for(int i=0;i<filteredQuizzes.size();i++){
            Quiz quiz = filteredQuizzes.get(i);
            System.out.println((i+1)+". " + quiz.getQuizTitle()+" (Theme: " + quiz.getQuizTheme()+")");
        }

        System.out.print("Enter the quiz number you want to attempt: ");
        int quizIndex = scanner.nextInt();
        scanner.nextLine();

        if(quizIndex<1 || quizIndex>filteredQuizzes.size()){
            System.out.println("Invalid quiz selection.");
            return;
        }

        Quiz selectedQuiz = filteredQuizzes.get(quizIndex-1);

        if(selectedQuiz.isCompleted()){
            System.out.println("You have already completed this quiz.");
            return;
        }

        selectedQuiz.markAsCompleted();
        student.completeQuiz(selectedQuiz);
        System.out.println("Quiz completed! You earned 2 points.");
    }

    private List<String> selectThemes(){
        List<String> selectedThemes = new ArrayList<>();
        System.out.println("Select quiz themes (enter 'done' when finished)");
        for(String theme:validTheme){
            System.out.println("Would you like to filter by " + theme + "? (yes/no): ");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("yes")){
                selectedThemes.add(theme);
            }
        }

        if(selectedThemes.isEmpty()){
            System.out.println("No speific themes selected. Displaying all quizzes.");
            selectedThemes.addAll(validTheme);
        }

        return selectedThemes;
    }

    private List<Quiz> filterQuizzesByTheme(List<Quiz> allQuizzes, List<String> selectedThemes){
        List<Quiz> filteredQuizzes = new ArrayList<>();
        for(Quiz quiz:allQuizzes){
            if(selectedThemes.contains(quiz.getQuizTheme())){
                filteredQuizzes.add(quiz);
            }
        }
        return filteredQuizzes;
    }
}
