public class FriendRequest {
    private User sender;
    private User receiver;

    public FriendRequest(User sender, User receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }
}
