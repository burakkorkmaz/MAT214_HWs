package tr.edu.gtu.bkorkmaz.Q2;

import java.util.ArrayList;

/**
 * Created by Burak Kağan Korkmaz on 15.05.2017.
 */


public class DividedDifference {
    private class Pair<E>{
        double x;
        double y;
        Pair(double x, double y){
            this.x = x;
            this.y = y;
        }

        double getX() {
            return x;
        }

        double getY() {
            return y;
        }

        void setX(double x) {
            this.x = x;
        }

        void setY(double y) {
            this.y = y;
        }
    }
    private ArrayList< Pair<Integer> > coordinates;

    /*private DividedDifference(double coordinateX, double coordinateY){
        Pair<Double> pair = new Pair<>(coordinateX,coordinateY);
    }*/

    public void add(double coordinateX, double coordinateY){
        coordinates = new ArrayList<>();
        coordinates.add(new Pair<>(coordinateX,coordinateY));
    }

    public double[][] polynomial(){
       double[][] polynome = new double[coordinates.size()][coordinates.size()];
       int n = coordinates.size();
       for (int i = 0; i < n; i++) {
            polynome[i][0] = coordinates.get(i).getY();
        }

        for (int i = 1; i < n; i++) {

            for (int j = 1; j <= i; j++) {
                polynome[i][j] = (polynome[i][j-1] - polynome[i-1][j-1])/
                        (coordinates.get(i).getX() - coordinates.get(i-j).getX());
            }
        }
        return polynome;
    }

}
