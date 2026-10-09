package procesos;

/**
 * TrabajadorPrimo muestra por consola todos lo número primos presentes en un rango dado. Es una clase independiente.
 * 
 * @author Eric Ramos Pastor
 * @version %I%, %G%
 * @since 1.0
 */
public class TrabajadorPrimo {
    /**
     * Invoca <code>printPrimes</code> tras validar los argumentos introducidos.
     * <p>
     * Se comprueba que los argumentos no contengan texto, se hayan introducido 2 
     * valores como mínimo, el primero sea menor que el segundo y no existan negativos 
     * dentro del rango. Tras ello, se realizan comprobaciones iniciales sobre el rango mínimo 
     * descrito. Si <code>limit</code> es igual o inferior a 2 no es necesario invocar <code>printPrimes</code>. 
     * Si es mayor y <code>begin</code> es menor o igual a 2, se iguala a 3. Antes de llamar la función, 
     * se comprueba si <code>begin</code> es par, enviando como parámetro valor <code>+ 1</code> de serlo. Se hace 
     * para acortar a la mitad los números que requieren validación.
     * <p>
     * Los valores de <code>exit</code> son:
     * <ol start="0">
     * <li>Ejecución satisfactoria.
     * <li>Tipado inválido de los argumentos.
     * <li>Argumentos sin introducir.
     * <li>Límite inferior del rango mayor o igual al superior.
     * <li>Valores negativos comprendidos dentro del rango.
     * </ol>
     * 
     * @param args Argumentos introducidos.
     * @see #printPrimes(int, int)
     * @since 1.0
     */
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
            if(limit <= 2)
                System.out.print(limit == 2 ? "2" : "Not found");
            else {
                if(begin <= 2) {
                    System.out.print("2, ");
                    begin = 3;
                }
                printPrimes(begin % 2 == 0 ? begin + 1 : begin, limit);
            }
            System.out.println("\nSatisfactorio.");
            System.exit(0);
        }
    }

    /**
     * Imprime todos los números primos comprendidos por el rango <code>begin &lt;= x &gt;= limit</code> en la consola.
     * <p>
     * La función comienza declarando <code>foundPrime</code> inicializado a <code>false</code> para mostrar un mensaje 
     * si no se ha llegado a encontrar ningún primo dentro del rango. Tras esto, un bucle <code>for</code> recorre todos 
     * los números impares. El incremento de <code>i</code> es de 2 porque se espera que <code>begin</code> contenga un 
     * valor impar. Continúa con un <code>for</code> anidado que comprueba si <code>i</code> es divisible por otro número 
     * salvo 1 y si mismo. <code>j</code> también es impar en todo momento, pues revisar <code>i % j == 0</code> cuando 
     * <code>j % 2 == 0</code> resulta redundante. En ese caso se podría validar <code>i % 2 == 0</code> directamente, justo 
     * la comprobación que <code>main</code> realiza para invocar esta función.
     * <p>
     * En el momento en el que <code>(i % j == 0) == true</code>, el booleano <code>isPrime</code> pasa a ser false y el bucle 
     * interno finaliza. Finalmente, se imprime <code>i</code> si fuera un primo y se marca <code>primeFound</code> de tratarse 
     * del primer número primo encontrado.
     * 
     * @param begin Número inicial que comprobar.
     * @param limit Número final que comprbar.
     * @since 1.0
     */
    public static void printPrimes(final int begin, final int limit) {
        boolean foundPrime = false;
        for(int i = begin; i <= limit; i += 2) {
            boolean isPrime = true;
            for(int j = 3; j < i && isPrime; j += 2) {
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

    /**
     * @deprecated
     */
    public TrabajadorPrimo(){}
}