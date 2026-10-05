package procesos;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class DatosEjecucion {
    private long inicioMillis;
    private long finMillis;
    private final int cantidadProcesos;
    private final int[] rango;
    private List<ResultadoProceso> procesosEjecutados;

    public DatosEjecucion(final int cantidadProcesos, final int[] rango) {
        this.cantidadProcesos = cantidadProcesos;
        this.rango = rango;
        procesosEjecutados = new ArrayList<>(cantidadProcesos);
    }

    public DatosEjecucion(final long inicioMillis, final long finMillis, final int cantidadProcesos, final int[] rango) {
        this.inicioMillis = inicioMillis;
        this.finMillis = finMillis;
        this(cantidadProcesos, rango);
    }

    public double getTiempoEjecucion() {
        return ((double) finMillis - inicioMillis) / 1000;
    }

    public void setInicioMillis(final long inicioMillis) {
        this.inicioMillis = inicioMillis;
    }

    public void setFinMillis(final long finMillis) {
        this.finMillis = finMillis;
    }

    public int getCantidadProcesos() {
        return cantidadProcesos;
    }

    public int[] getRango() {
        return rango;
    }

    public List<ResultadoProceso> getProcesosEjecutados() {
        return Collections.unmodifiableList(procesosEjecutados);
    }

    public boolean agregarResultadoProceso(final ResultadoProceso proceso) {
        return procesosEjecutados.add(proceso);
    }
}