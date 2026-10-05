package procesos;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class DatosEjecucion {
    private final long inicioMillis;
    private final long finMillis;
    private final int cantidadProcesos;
    private final int[] rango;
    private Map<Integer, ResultadoProceso> procesosEjecutados;

    public DatosEjecucion(final long inicioMillis, final long finMillis, final int cantidadProcesos, final int[] rango) {
        this.inicioMillis = inicioMillis;
        this.finMillis = finMillis;
        this.cantidadProcesos = cantidadProcesos;
        this.rango = rango;
        procesosEjecutados = new HashMap<>();
    }

    public double getTiempoEjecucion() {
        return ((double) finMillis - inicioMillis) / 1000;
    }

    public int getCantidadProcesos() {
        return cantidadProcesos;
    }

    public int[] getRango() {
        return rango;
    }

    public HashMap<Integer, ResultadoProceso> getProcesosEjecutados() {
        return Collections.unmodifableList(procesosEjecutados);
    }

    public void agregarResultadoProceso(final ResultadoProceso proceso) {
        procesosEjecutados.put((procesosEjecutados.size() + 1), proceso);
    }
}