package tr.edu.gtu.bkorkmaz.Q1;

public class Main {

    public static void main(String[] args) {

        Neighbour matrix = new Neighbour();
        int element = 0;
        for (int i = 0; i < matrix.DEFAULT_SIZE; i++) {
            for (int j = 0; j < matrix.DEFAULT_SIZE; j++) {
                matrix.addElement(element++,i,j);

            }
        }
        double [][] extended = matrix.interpolate(3);

        System.out.println(matrix.toString());
    }

}
