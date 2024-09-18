public class LocationCoordinate {
    private double x;
    private double y;

    public LocationCoordinate() {
        this.x = Math.random() * 1000 - 500; 
        this.y = Math.random() * 1000 - 500;
    }

    public LocationCoordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public String toString() {
        return "LocationCoordinate{" + "x=" + x + ", y=" + y +'}';
    }
}
