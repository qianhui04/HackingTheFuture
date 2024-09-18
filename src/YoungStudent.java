import java.util.ArrayList;
import java.util.List;

public class YoungStudent extends User {
    private List<Quiz> attempQuizzes;

    public YoungStudent(String email, String username, String password, LocationCoordinate locationCoordinate){
        super(email, username, password, Role.YOUNG_STUDENT, locationCoordinate);
        this.attempQuizzes = new ArrayList<>();
    }

    public int getPoints(){
        return super.getCurrentPoints();
    }

    public List<Quiz> getAttemptedQuizzes(){
        return attempQuizzes;
    }

    public void attemptQuiz(Quiz quiz) {
        if (quiz.isCompleted()) {
            System.out.println("You have already completed this quiz.");
        } else {
            completeQuiz(quiz);
            attempQuizzes.add(quiz);
            System.out.println("You have attempted the quiz");
        }
    }

    public void completeQuiz(Quiz quiz){
        quiz.markAsCompleted();
        super.addCompletedQuiz(quiz);
        super.setCurrentPoints(super.getCurrentPoints()+2);
        System.out.println("Quiz completed! You earned 2 points");
    }
}
