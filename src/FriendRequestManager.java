import java.util.List;

public class FriendRequestManager {
    public void sendFriendRequest(User sender, User receiver){
        if(sender != receiver && !sender.getFriends().contains(receiver)){
            FriendRequest request = new FriendRequest(sender, receiver);
            receiver.getIncomingRequests().add(request);
            System.out.println("Friend requst sent to " + receiver.getUsername());
        }else{
            System.out.println("You cannot send a friend request to this user.");
        }
    }

    public void acceptFriendRequest(User receiver, FriendRequest request){
        if(receiver.getIncomingRequests().contains(request)){
            receiver.getFriends().add(request.getSender());
            request.getSender().getFriends().add(receiver);
            receiver.getIncomingRequests().remove(request);
            System.out.println("Friend request accepted.");
        }else{
            System.out.println("No such friend request found.");
        }
    }

    public void rejectFriendRequest(User receiver, FriendRequest request){
        if(receiver.getIncomingRequests().contains(request)){
            receiver.getIncomingRequests().remove(request);
            System.out.println("Friend request rejected.");
        }else{
            System.out.println("No such friend request found.");
        }
    }
}
