package procesos;

/**
 * ResultadoProceso registra un identificador, el rango de números que ha procesado y la del programa.
 * 
 * @author Eric Ramos Pastor
 * @see procesos.TipoSalida
 * @version %I%, %G%
 * @since 1.0
 */
public class ResultadoProceso {
    private TipoSalida salida;
    private final int[] rango;
    private final int numero;

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
     * <code>salida</code> a {@link #setSalida(int)}.
     * 
     * @param rango Intervalo numérico procesado.
     * @param salida Valor de salida de la ejecución.
     * @param numero Identificador del proceso.
     * @since 1.0
     */
    public ResultadoProceso(final int[] rango, final int salida, final int numero) {
        this(rango, numero);
        setSalida(salida);
    }

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

    public int[] getRango() {
        return rango;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "\tProceso: #"+ numero +
            "\n\tRango: ["+ rango[0] +", "+ rango[1] +"]"+
            "\n\tSalida: "+ salida;
    }
}