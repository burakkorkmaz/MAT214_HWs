package tr.edu.gtu.bkorkmaz.Q2;

/**
 * Created by Burak Kağan Korkmaz on 15.05.2017.
 */
public class TestPolynomial {

    public static void main(String args[]) {
        DividedDifference dd = new DividedDifference();
        dd.add(1.0,0.7651977);
        dd.add(1.3,0.6200860);
        dd.add(1.6,0.4554022);
        dd.add(1.9,0.2818186);
        dd.add(2.2,0.1103623);



        double[][] pol = dd.polynomial();


        for (int i = 0; i < pol.length; i++) {
            System.out.println();
            for (int j = 0; j < pol.length; j++) {
                System.out.print(pol[i][j] + " ");
            }
        }
        System.out.println(pol[0][0]);

    }
}
