import java.util.List;
import java.util.Comparator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Leaderboard {
    private List<User> users;

    public Leaderboard(List<User> users){
        this.users = users;
    }

    public void displayLeaderboard(){
        List<User> sortedUsers = users.stream().filter(user -> user.getRole() == Role.YOUNG_STUDENT).sorted(Comparator.comparingInt(User::getCurrentPoints).reversed().thenComparing(User::getPointLastUpdated)).collect(Collectors.toList());

        System.out.println("---- Global Leaderboard ----");
        for(int i=0;i<sortedUsers.size();i++){
            User user = sortedUsers.get(i);
            System.out.printf("%d. %s - %d points\n", i+1,user.getUsername(), user.getCurrentPoints());
        }
    }
}
