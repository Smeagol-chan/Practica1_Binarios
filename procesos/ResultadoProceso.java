package procesos;

/**
 * ResultadoProceso registra un identificador, el rango de números que ha procesado y la del programa.
 * 
 * @author Eric Ramos Pastor
 * @see procesos.TipoSalida
 * @version %I%, %G%
 * @since 1.5
 */
public class ResultadoProceso {
    /**
     * Salida del programa.
     */
    private TipoSalida salida;
    /**
     * Intervalo de números procesados.
     */
    private final int[] rango;
    /**
     * Identificador del proceso.
     */
    private final int numero;
    /**
     * Cantidad de números primos encontrados.
     */
    private int primosEncontrados;

    /**
     * Constructor que define un identificador y el rango que comprende.
     * 
     * @param rango Intervalo numérico procesado.
     * @param numero Identificador del proceso.
     * @see procesos.MaestroProcesos#arrancarTrabajadorPrimo(String[], int)
     * @since 1.0
     */
    public ResultadoProceso(final int[] rango, final int numero) {
        this.rango = rango;
        this.numero = numero;
    }

    /**
     * Constructor con todos los parámetros.
     * <p>
     * <code>rango</code> y <code>salida</code> se envían a {@link #ResultadoProceso(int[], int)} y 
     * <code>salida</code> a {@link #setSalida(int)}. <code>primosEncontrados</code> se inicializa con 
     * el valor homónimo.
     * 
     * @param rango Intervalo numérico procesado.
     * @param salida Valor de salida de la ejecución.
     * @param numero Identificador del proceso.
     * @since 1.5
     */
    public ResultadoProceso(final int[] rango, final int salida, final int numero, final int primosEncontrados) {
        this(rango, numero);
        this.primosEncontrados = primosEncontrados;
        setSalida(salida);
    }

    /**
     * Getter de <code>salida</code>.
     * 
     * @return {@link #salida}
     * @since 1.0
     */
    public TipoSalida getSalida() {
        return salida;
    }

    /**
     * Inicializa <code>this.salida</code> con el <code>TipoSalida</code> recibido.
     * 
     * @param salida Valor de salida de la ejecución.
     * @see procesos.TipoSalida#buscarSalidaEquivalente(int)
     * @since 1.0
     */
    public void setSalida(final int salida) {
        this.salida = TipoSalida.buscarSalidaEquivalente(salida);
    }

    /**
     * Getter de <code>rango</code>.
     * 
     * @return {@link #rango}
     * @since 1.0
     */
    public int[] getRango() {
        return rango;
    }

    /**
     * Getter de <code>numero</code>.
     * 
     * @return {@link #numero}
     * @since 1.0
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter de <code>primosEncontrados</code>.
     * 
     * @return {@link #primosEncontrados}
     * @since 1.5
     */
    public int getPrimosEncontrados() {
        return primosEncontrados;
    }

    /**
     * Setter de <code>numerosEncontrados</code>.
     * 
     * @since 1.5
     */
    public void setPrimosEncontrados(int primosEncontrados) {
        this.primosEncontrados = primosEncontrados;
    }

    @Override
    public String toString() {
        return "\tProceso: #"+ numero +
            "\n\tRango: ["+ rango[0] +", "+ rango[1] +"]"+
            "\n\tPrimos encontrados: "+ primosEncontrados +
            "\n\tSalida: "+ salida;
    }
}