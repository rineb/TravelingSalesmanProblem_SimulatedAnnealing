import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Tour {
    private ArrayList<City> cities;
    private City startCity;

    public Tour() {
        this.cities = new ArrayList<>();
        readCitiesInfo("Cities.txt");
        Collections.shuffle(cities);
        Collections.swap(cities, cities.indexOf(startCity), 0);
    }

    //This method is used to read the .txt file containing the list of cities that need to be visited
    //Arraylist of cities is initialized (with each line containing a city and its location information)
    public void readCitiesInfo(String path) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String[] lineParts;
            String line = br.readLine();
            while (line != null) {
                lineParts = line.split(",");
                cities.add(new City(lineParts[0], Double.valueOf(lineParts[1]), Double.valueOf(lineParts[2])));
                line = br.readLine();
            }

            br.close();
            startCity = cities.get(0);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public double getTourDistance() {
        double distance = 0;

        for (int i = 0; i < cities.size(); i++) {
            City currentCity = cities.get(i);
            City nextCity;
            if (i+1 < cities.size()) {
                nextCity = cities.get(i+1);
            }
            else {
                nextCity = cities.get(0);
            }

            distance += currentCity.distanceToCity(nextCity);
        }

        return distance;
    }

    public ArrayList<City> getCities() {
        return (ArrayList<City>)cities.clone();
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = (ArrayList<City>)cities.clone();
    }

    public void swapCities(int firstIndex, int secondIndex) {
        Collections.swap(cities, firstIndex, secondIndex);
    }

    @Override
    public String toString() {
        String result = cities.get(0).toString();
        for (int i = 1; i < cities.size(); i++) {
            result += " -> " + cities.get(i).toString();
        }

        result += " -> " + cities.get(0) + ": " + getTourDistance() + " km";

        return result;
    }
}
