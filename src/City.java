public class City {
    private String name;
    private double latitude;
    private double longitude;

    public City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //Haversine Formula
    public double distanceToCity(City city) {
        double latitude1 = Math.toRadians(this.latitude);
        double latitude2 = Math.toRadians(city.latitude);
        double longitude1 = Math.toRadians(this.longitude);
        double longitude2 = Math.toRadians(city.longitude);

        double latitudeDifference = latitude2 - latitude1;
        double longitudeDifference = longitude2 - longitude1;

        double a = Math.pow(Math.sin((latitudeDifference)/2), 2) +
                Math.cos(latitude1) * Math.cos(latitude2)
                * Math.pow(Math.sin((longitudeDifference)/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        int R = 6371; //Earth's radius in km

        return R * c;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
