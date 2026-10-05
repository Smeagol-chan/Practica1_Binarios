package procesos;

public class TrabajadorPrimo {
    // exit.0 -> All good
    // exit.1 -> Invalid arguments tiping
    // exit.2 -> Arguments didn't introduce
    // exit.3 -> Beginning either greater or equal to limit
    // exit.4 -> Negative values
    public static void main(String[] args) {
        int begin = 0, limit = 0;
        try {
            begin = Integer.parseInt(args[0]);
            limit = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.err.println("Tipado de los argumentos inválido.");
            System.exit(1);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.err.println("Insuficientes argumentos.");
            System.exit(2);
        }
        if(begin >= limit) {
            System.err.println("Límites iguales o el mínimo superior al máximo.");
            System.exit(3);
        } else if(begin < 0) {
            System.err.println("Límites negativos.");
            System.exit(4);
        } else {
            System.out.println("Prime number in range ["+ begin +", "+ limit +"]:");
            if(limit <= 2) {
                System.out.print(limit == 2 ?
                    "2" :
                    "Not found");
            } else {
                if(2 >= begin) {
                    System.out.print("2, ");
                    begin = 3;
                }
                printPrimes(begin % 2 == 0 ? begin + 1 : begin, limit);
            }
            System.out.println("\n\n\n0");
            System.exit(0);
        }
    }

    public static void printPrimes(int begin, int limit) {
        boolean foundPrime = false;
        for(int i = begin; i <= limit; i += 2) {
            boolean isPrime = true;
            for(int j = 3; j < i && isPrime; j++) {
                if(i % j == 0) isPrime = false;
            }
            if(isPrime) {
                if(!foundPrime) foundPrime = true;
                else System.out.print(", ");
                System.out.print(i);
            }
        }
        if(!foundPrime) System.out.print("Not found");
    }
}