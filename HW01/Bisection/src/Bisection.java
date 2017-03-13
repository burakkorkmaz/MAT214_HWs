/**
 * Created by Burak Kağan Korkmaz on 10.03.2017.
 */
public class Bisection {

    private static final int MAX_ITER = 100;
    private double pn;
    private double pn_1;
    private double fn;

    public Bisection(double a, double b, String stopCriteria, double epsilon) {
        double pn = findPn(a, b);
        this.fn = foo(pn);
        if ((foo(a) * foo(b)) > 0.0) {
            System.err.println("Approximation Interval is inappropriate");
            System.out.printf("f(%f) = %.16f\nf(%f) = %.16f\n",a, foo(a),b,foo(b));
            System.out.printf("f(a) * f(b) = %s\n",foo(a) * foo(b));
        } else {
            bisect(1, a, b, stopCriteria, epsilon);
        }
    }

    public void bisect(int n, double a, double b, String stopCriType, double epsilon) {
        double approximate;
        pn_1 = pn;
        pn = findPn(a, b);
        if (n > MAX_ITER) {
            System.err.println("Error! Approximation is failed.");
            return;
        } else {
            if (stopCriType.equals("DISTANCE_TO_ROOT")) {
                approximate = distanceToRoot(pn);
            } else if (stopCriType.equals("ABSOLUTE_ERROR")) {
                approximate = absoluteError(pn, pn_1);
            } else if (stopCriType.equals("RELATIVE_ERROR")) {
                approximate = relativeError(pn, pn_1);
            } else {
                System.err.println("Stopping Criterion Type is WRONG!");
                System.err.println("\n Use only this crietion types:" +
                        "\nDISTANCE_TO_ROOT or " +
                        "\nABSOLUTE_ERROR" +
                        "\nRELATIVE_ERROR");
                return;
            }
        }
        if (n == 1) {
            System.out.print("|  n  | DISTANCE_TO_ROOT | ABSOLUTE_ERROR   |  RELATIVE_ERROR |\n");
            //System.out.println("|  n  |      a       |       b      |      pn      |     f(pn)    |");
            //System.out.println("___________________________________________________________________");
        }
        System.out.format("| %3d |  %-14.11f  |  %-14.11f  |  %-14.11f |\n", n, distanceToRoot(pn),
                absoluteError(pn, pn_1), relativeError(pn, pn_1));

//        System.out.format("| %3s | %-12.11s | %-12.11s | %-12.11s | %-11.11s |\n", n, a, b, pn, foo(pn));
        if (approximate <= epsilon || foo(pn) == 0) {
            System.out.printf("Approximation root is %.12f.", pn);
        } else {
            if(foo(a) * foo(pn) > 0) {
                a = pn;
            }
            else {
                b = pn;
            }
            bisect(n + 1, a, b, stopCriType, epsilon);

        }
    }

    public double findPn(double a, double b) {

        return Math.abs(a + b) / 2;
    }

    public double foo(double x) {

        //return Math.pow(x, 3) + 4 * Math.pow(x, 2) - 10; // [1,2]
        // Section 2.1 Exercise 6
        return (3 * x - Math.pow(Math.E, x)); // [1,2]
        //return Math.pow(x,2) - 4 * x + 4 - Math.log(x); // [1,2] and [2,4]
        //return 2 * x + 3 * Math.cos(x) - Math.pow(Math.E, x); // [1, 2]
        //return x + 1 - 2 * Math.sin(Math.PI * x ); // [0,0.5] and [0.5, 1 ]
    }

    public double distanceToRoot(double pn) {
        return Math.abs(foo(pn));
    }

    public double absoluteError(double pn, double pn_1) {
        return Math.abs(pn - pn_1);
    }

    public double relativeError(double pn, double pn_1) {
        return Math.abs((pn - pn_1) / pn);
    }

}
