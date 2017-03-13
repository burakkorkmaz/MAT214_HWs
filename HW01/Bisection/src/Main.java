public class Main {

    public static void main(String[] args) {

        if(args.length != 4){
            System.err.println("Usage:  a b StoppingCriteria Epsilon\n");
            System.out.println("Input 1: the start of the root search interval as a real value: a\n" +
                    "Input 2: the end of the root search interval as a real value: b\n" +
                    "Input 3: the type of stopping criterion as a character array (DISTANCE_TO_ROOT, " +
                    "ABSOLUTE_ERROR, RELATIVE_ERROR)\n" +
                    "Input 4: the epsilon value ε as a real value");

            return;
        }

        //RELATIVE_ERROR ABSOLUTE_ERROR DISTANCE_TO_ROOT
        Bisection bsc;
        bsc = new Bisection((Double.parseDouble(args[0])),Double.parseDouble(args[1]),
                args[2],Double.parseDouble(args[3]));



    }
}
