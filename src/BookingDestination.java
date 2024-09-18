public class BookingDestination {
    private String name;
    private LocationCoordinate coordinate;

    public BookingDestination(String name, LocationCoordinate coordinate){
        this.name = name;
        this.coordinate = coordinate;
    }

    public String getName(){
        return name;
    }

    public LocationCoordinate getCoordinate(){
        return coordinate;
    }
}
