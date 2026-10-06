package procesos;

public class ResultadoProceso {
    private TipoSalida salida;
    private final int[] rango;
    private final int numero;

    public ResultadoProceso(final int[] rango, final int numero) {
        this.rango = rango;
        this.numero = numero;
    }

    public ResultadoProceso(final int[] rango, final int salida, final int numero) {
        this(rango, numero);
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