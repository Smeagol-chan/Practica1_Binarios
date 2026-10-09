package procesos;
import java.util.List;
import java.util.ArrayList;

/**
 * DatosEjecucion almacena el instante de inicio y fin de la ejecución de un programa, la cantidad de procesos que lo forman, 
 * el rango total de números a analizar y una lista de todos los procesos.
 * 
 * @author Eric Ramos Pastor
 * @see procesos.ResultadoProceso
 * @version %I%, %G%
 * @since 1.0
 */
public class DatosEjecucion {
    /**
     * Milisegundos transcurridos hasta inicio de ejecución.
     */
    private long inicioMillis;
    /**
     * Milisegundos transcurridos hasta fin de ejecución.
     */
    private long finMillis;
    /**
     * Cantidad de procesos simultáneos.
     */
    private final int cantidadProcesos;
    /**
     * Intervalo de números a procesar.
     */
    private final int[] rango;
    /**
     * Procesos que lo componen.
     */
    private List<ResultadoProceso> procesosEjecutados;

    /**
     * Constructor con parámetros mínimos imprescindibles.
     * <p>
     * Los atributos <code>cantidadProcesos</code> y <code>rango</code> se inicializan con los valores 
     * recibidos. A su vez, <code>procesosEjecutados</code> se inicializa como <code>ArrayList(int)</code> 
     * de tamaño inicial <code>cantidadProcesos</code>.
     * 
     * @param cantidadProcesos Cantidad de procesos que el objeto comprende.
     * @param rango Rango de números a procesar.
     * @since 1.0
     */
    public DatosEjecucion(final int cantidadProcesos, final int[] rango) {
        this.cantidadProcesos = cantidadProcesos;
        this.rango = rango;
        procesosEjecutados = new ArrayList<>(cantidadProcesos);
    }

    /**
     * Constructor con todos los parámetros definidos.
     * <p>
     * Los atributos <code>inicioMillis</code> y <code>finMillis</code> se inicializan con los parámetros homónimos. 
     * El resto se envían como parámetros a {@link #DatosEjecucion(int, int[])}.
     * 
     * @param inicioMillis Milisegundos totales hasta el inicio de la ejecución.
     * @param finMillis Milisegundo totales hasta el final de la ejecución.
     * @param cantidadProcesos Total de procesos simultáneos.
     * @param rango Rango numérico que ha procesado.
     * @since 1.0
     */
    public DatosEjecucion(final long inicioMillis, final long finMillis, final int cantidadProcesos, final int[] rango) {
        this.inicioMillis = inicioMillis;
        this.finMillis = finMillis;
        this(cantidadProcesos, rango);
    }

    /**
     * Devuelve el tiempo total de ejecución en segundos.
     * 
     * @return Segundos de ejecución.
     * @since 1.0
     */
    public double getTiempoEjecucion() {
        return ((double) finMillis - inicioMillis) / 1000;
    }

    /**
     * Setter de <code>inicioMillis</code>.
     * 
     * @param inicioMillis {@link #inicioMillis}
     */
    public void setInicioMillis(final long inicioMillis) {
        this.inicioMillis = inicioMillis;
    }

    /**
     * Setter de <code>finMillis</code>.
     * 
     * @param finMillis {@link #finMillis}
     */
    public void setFinMillis(final long finMillis) {
        this.finMillis = finMillis;
    }

    /**
     * Getter de <code>cantidadProcesos</code>.
     * 
     * @return {@link #cantidadProcesos}
     */
    public int getCantidadProcesos() {
        return cantidadProcesos;
    }

    /**
     * Getter de <code>rango</code>.
     * 
     * @return {@link #rango}
     */
    public int[] getRango() {
        return rango;
    }

    /**
     * Getter de <code>procesosEjecutados</code>.
     * 
     * @return {@link #procesosEjecutados}
     */
    public List<ResultadoProceso> getProcesosEjecutados() {
        return procesosEjecutados;
    }

    /**
     * Añade un <code>ResultadoPoceso</code> a <code>procesosEjecutados</code>, informando de vuelta 
     * de si la acción ha podido ser realizada.
     * 
     * @param proceso <code>ResultadoProceso</code> con datos del subproceso.
     * @return <code>true</code> si ha podido ser añadido y <code>false</code> en su defecto.
     * @see procesos.ResultadoProceso
     * @since 1.0
     */
    public boolean agregarResultadoProceso(final ResultadoProceso proceso) {
        return procesosEjecutados.add(proceso);
    }

    /**    (non-Javadoc)
     * El <code>foreach</code> sirve para formatear los <code>toString()</code> de cada proceso con 
     * un tabulador a la izquierda. Se utiliza un <code>StringBuilder</code> para ahorrar memoria.
     * 
     * @since 1.0
     */
    @Override
    public String toString() {
        StringBuilder resultadosProcesos = new StringBuilder();
        for(ResultadoProceso proceso : procesosEjecutados)
            resultadosProcesos.append("\n").append(proceso.toString()).append("\n");

        return "Rango: ["+ rango[0] +", "+ rango[1] +"]"+
            "\nCantidad de procesos: "+ cantidadProcesos +
            "\nTiempo de ejecución: "+ getTiempoEjecucion() +" segundos"+
            "\nProcesos: "+ resultadosProcesos.toString();
    }
}