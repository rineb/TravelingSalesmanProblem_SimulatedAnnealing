import java.util.ArrayList;

public class SimulatedAnnealingExponential {
    private Tour tour;
    private double temperature;
    private double minDistance;
    private ArrayList<City> shortestTour;

    public SimulatedAnnealingExponential(Tour tour, double temperature) {
        this.tour = tour;
        this.temperature = temperature;
        minDistance = tour.getTourDistance();
        shortestTour = tour.getCities();
    }

    public Tour runIteration(int currentIteration) {
        ArrayList<City> initialSolution = tour.getCities();
        double initialDistance = tour.getTourDistance();

        //Two randomly chosen cities get swapped
        int[] indices = generateRandomIndices(initialSolution.size());
        tour.swapCities(indices[0], indices[1]);

        //New tour's distance
        double newDistance = tour.getTourDistance();

        //Checks whether the new tour is shorter than the previous one
        //If so, it checks whether it is the shorter tour up until now
        if (newDistance < initialDistance) {
            if (newDistance < minDistance) {
                minDistance = tour.getTourDistance();
                shortestTour = tour.getCities();
            }
        }

        else if (acceptanceProbability(initialDistance, newDistance)) {
            //Because of "luck", this tour will still be returned although it is worse (longer)
            //than the previous one
        }

        //If the new tour is worse than the previous one and has no "luck", it gets reset to the previous tour
        else {
            tour.setCities(initialSolution);
        }

        temperatureChange(currentIteration);

        //A tour based on the above conditions is returned
        return tour;
    }

    //Formula that calculates the probability of keeping a tour as a solution
    public boolean acceptanceProbability(double oldDistance, double newDistance) {
        double probability = Math.exp(-(newDistance-oldDistance)/temperature); //Boltzmann distribution
        double random = Math.random();

        return (random < probability);
    }

    //Generates two random indices from [1,number of cities)
    //The number generated shouldn't be 0 because the start (initial) city shouldn't change
    public int[] generateRandomIndices(int numOfCities) {
        numOfCities -= 1;
        int[] indices = new int[2];
        indices[0] = (int)(Math.random()*numOfCities)+1; //[1,29)
        indices[1] = (int)(Math.random()*numOfCities)+1;

        while (indices[0] == indices[1]) {
            indices[0] = (int)(Math.random()*numOfCities)+1;
            indices[1] = (int)(Math.random()*numOfCities)+1;
        }

        return indices;
    }

    public void temperatureChange(int currentIteration) {
        temperature = 100 * Math.pow(0.95, currentIteration);
    }

    public double getTemperature() {
        return temperature;
    }


    public double getMinDistance() {
        return minDistance;
    }

    public ArrayList<City> getShortestTour() {
        return shortestTour;
    }
}
