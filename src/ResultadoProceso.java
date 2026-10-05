package procesos;

public class ResultadoProceso {
    private final TipoSalida salida;
    private final int[] rango;

    public Resultado(final int salida, final int[] rango) {
        this.salida = TipoSalida.buscarSalidaEquivalente(salida);
        this.rango = rango;
    }

    public TipoSalida getSalida() {
        return salida;
    }

    public int[] getRango() {
        return rango;
    }
}