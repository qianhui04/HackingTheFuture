import java.util.ArrayList;
import java.util.List;

public class Parent {
    private String username;
    private List<Child> children;

    public Parent(String username){
        this.username = username;
        this.children = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }
    
    public void addChild(Child child){
        this.children.add(child);
    }

    public void removeChile(Child child){
        this.children.remove(child);
    }
}
