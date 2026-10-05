package procesos;

public class ResultadoProceso {
    private TipoSalida salida;
    private final int[] rango;

    public ResultadoProceso(final int[] rango) {
        this.rango = rango;
    }

    public ResultadoProceso(final int[] rango, final int salida) {
        this(rango);
        this.salida = TipoSalida.buscarSalidaEquivalente(salida);
    }

    public TipoSalida getSalida() {
        return salida;
    }

    public void setSalida(final int salda) {
        this.salida = TipoSalida.buscarSalidaEquivalente(salida);
    }

    public int[] getRango() {
        return rango;
    }
}