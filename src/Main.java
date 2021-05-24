import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Tour tour = new Tour();
        ArrayList<City> cities = tour.getCities();
        double numIterations = 1E7;

        //First variation - Exponential
        tour.setCities(cities);
        SimulatedAnnealingExponential saExp = new SimulatedAnnealingExponential(tour, 100);
        for (int i = 0; i < numIterations; i++) {
            Tour currentTour = saExp.runIteration(i);
            //System.out.println(currentTour + " " + saExp.getTemperature());
        }
        System.out.println("(Exponential) The shortest tour is: ");
        ArrayList<City> bestTourExp = saExp.getShortestTour();
        for (int i = 0; i < bestTourExp.size(); i++) {
            System.out.println(bestTourExp.get(i));
        }
        System.out.println(bestTourExp.get(0));
        System.out.println("(Exponential) The distance of the tour is: "
                + new DecimalFormat("##.##").format(saExp.getMinDistance()) + " km.");


        System.out.println();


        //Second variation - Modulo
        tour.setCities(cities);
        SimulatedAnnealingModulo saMod = new SimulatedAnnealingModulo(tour, 100);
        for (int i = 0; i < numIterations; i++) {
            Tour currentTour = saMod.runIteration(i);
            //System.out.println(currentTour + " " + saMod.getTemperature());
        }
        System.out.println("(Modulo) The shortest tour is: ");
        ArrayList<City> bestTourMod = saMod.getShortestTour();
        for (int i = 0; i < bestTourMod.size(); i++) {
            System.out.println(bestTourMod.get(i));
        }
        System.out.println(bestTourMod.get(0));
        System.out.println("(Modulo) The distance of the tour is: "
                + new DecimalFormat("##.##").format(saMod.getMinDistance()) + " km.");


        System.out.println();


        //Third variation - Logarithmic
        tour.setCities(cities);
        SimulatedAnnealingLogarithmic saLog = new SimulatedAnnealingLogarithmic(tour, 100, 1);
        for (int i = 0; i < numIterations; i++) {
            Tour currentTour = saLog.runIteration(i);
            //System.out.println(currentTour + " " + saLog.getTemperature());
        }
        System.out.println("(Logarithmic) The shortest tour is: ");
        ArrayList<City> bestTourLog = saLog.getShortestTour();
        for (int i = 0; i < bestTourLog.size(); i++) {
            System.out.println(bestTourLog.get(i));
        }
        System.out.println(bestTourLog.get(0));
        System.out.println("(Logarithmic) The distance of the tour is: "
                + new DecimalFormat("##.##").format(saLog.getMinDistance()) + " km.");
    }
}
