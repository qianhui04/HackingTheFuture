import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizRespository {
    private Map<String, List<Quiz>> themeToQuizzesMap = new HashMap<>();
    private List<Quiz> allQuizzes = new ArrayList<>();

    public void addQuiz(Quiz quiz){
        allQuizzes.add(quiz);

        String theme = quiz.getQuizTheme();
        themeToQuizzesMap.putIfAbsent(theme, new ArrayList<>());
        themeToQuizzesMap.get(theme).add(quiz);
    }

    public List<Quiz> getQuizzesByTheme(String theme){
        return themeToQuizzesMap.getOrDefault(theme, new ArrayList<>());
    }

    public List<Quiz> getAllQuizzes(){
        return allQuizzes;
    }
}
