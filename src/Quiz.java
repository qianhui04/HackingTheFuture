public class Quiz {
    private String quizTitle;
    private String quizDescription;
    private String quizTheme;
    private String quizLink;
    private boolean isCompleted;

    public Quiz(String quizTitle, String quizDescription, String quizTheme, String quizLink){
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.quizTheme = quizTheme;
        this.quizLink = quizLink;
        this.isCompleted = false;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public String getQuizTheme() {
        return quizTheme;
    }

    public String getQuizLink() {
        return quizLink;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public void setQuizTheme(String quizTheme) {
        this.quizTheme = quizTheme;
    }

    public void setQuizLink(String quizLink) {
        this.quizLink = quizLink;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }
    
}
