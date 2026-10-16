package procesos;

/**
 * TrabajadorPrimo muestra por consola todos lo número primos presentes en un rango dado. Es una clase independiente.
 * 
 * @author Eric Ramos Pastor
 * @version %I%, %G%
 * @since 1.5
 */
public class TrabajadorPrimo {
    /**
     * Cantidad de números primos encontrados.
     */
    private static int primeQty = 0;

    /**
     * Muestra la cantidad de números primos dentro de un intervalo.
     * <p>
     * Se comprueba que los argumentos no contengan texto, se hayan introducido 2 
     * valores como mínimo, el primero sea menor que el segundo y no existan negativos 
     * dentro del rango. Tras ello, se realizan comprobaciones iniciales sobre el rango mínimo 
     * descrito. Si <code>limit</code> es igual o inferior a 2 no es necesario invocar <code>findPrimes</code>. 
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
     * @see #findPrimes(int, int)
     * @since 1.5
     */
    public static void main(String[] args) {
        int begin = 0, limit = 0;
        try {
            begin = Integer.parseInt(args[0]);
            limit = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.exit(1);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.exit(2);
        }
        if(begin >= limit) {
            System.exit(3);
        } else if(begin < 0) {
            System.exit(4);
        } else {
            if(limit <= 2)
                primeQty += limit == 2 ? 1 : 0;
            else {
                if(begin <= 2) {
                    primeQty++;
                    begin = 3;
                }
                findPrimes(begin % 2 == 0 ? begin + 1 : begin, limit);
            }
            System.out.println(primeQty);
            System.exit(0);
        }
    }

    /**
     * Cuenta los números primos comprendidos por el rango <code>begin &lt;= x &gt;= limit</code>.
     * 
     * @param begin Número inicial que comprobar.
     * @param limit Número final que comprbar.
     * @since 1.5
     */
    private static void findPrimes(final int begin, final int limit) {
        for(int i = begin; i <= limit; i += 2) {
            boolean isPrime = true;
            for(int j = 3; j < i && isPrime; j += 2) {
                if(i % j == 0) isPrime = false;
            }
            if(isPrime) primeQty++;
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public TrabajadorPrimo(){}
}