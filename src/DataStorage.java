import java.io.*;
import java.util.*;

public class DataStorage {
    private static final String FILE_NAME = "userss.txt";
    
    public void saveUser(User user){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))){
            String formattedX = String.format("%.2f", user.getLocationCoordinate().getX());
            String formattedY = String.format("%.2f", user.getLocationCoordinate().getY());
            writer.write(user.email + "," + user.username + "," + user.password + "," + user.role + "," + formattedX + "," + formattedY);
            writer.newLine();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public User getUser(String username, String password){
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\limhu\\OneDrive - Universiti Malaya\\UM INFO\\HackingTheFuture\\userss.txt"))){
            String line;
            while((line=reader.readLine()) != null){
                String[] userDetails = line.split(",");
                if(userDetails.length<6){
                    continue;
                }
                if(userDetails[1].equals(username) &&userDetails[2].equals(password)){
                    try{
                    Role role = Role.valueOf(userDetails[3].toUpperCase());
                    LocationCoordinate location = new LocationCoordinate(Double.parseDouble(userDetails[4]),Double.parseDouble(userDetails[5]));
                    return new User (userDetails[0],userDetails[1],userDetails[2],role, location);
                    }catch(IllegalArgumentException e){
                        System.err.println("Error parsing role: " + e.getMessage());
                    }
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isUsernameTaken (String username){
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\limhu\\OneDrive - Universiti Malaya\\UM INFO\\HackingTheFuture\\users.txt"))){
            String line;
            while((line = reader.readLine())!=null){
                String[] userDetails = line.split(",");
                if(userDetails[1].equals(username)){
                    return true;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return false;
    }

    public static List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\limhu\\OneDrive - Universiti Malaya\\UM INFO\\HackingTheFuture\\users.txt"))){
            String line;
            while((line=reader.readLine())!=null){
                String[] userDetails = line.split(",");
                if(userDetails.length<6){
                    continue;
                }
                Role role = Role.valueOf(userDetails[3]);
                double x = Double.parseDouble(userDetails[4]);
                double y = Double.parseDouble(userDetails[5]);
                LocationCoordinate location = new LocationCoordinate(x,y);
                users.add(new User(userDetails[0],userDetails[1],userDetails[2],role,location));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return users;
    }
}
