package procesos;

public enum TipoSalida {
    BIEN(0, "Ejecución satisfactoria."),
    MAL_TIPADO(1, "Tipado de los argumentos inválido."),
    SIN_ARGS(2, "Argumentos no introducidos."),
    MAL_RANGO(3, "Límite inferior del rango igual o mayor al límite superior."),
    NEGATIVOS(4, "Rango con valores negativos.");

    private final int valor;
    private final String descripcion;

    TipoSalida(final int valor, final String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoSalida buscarSalidaEquivalente(final int salida) {
        switch(salida) {
            case 0:
                return TipoSalida.BIEN;
                break;

            case 1:
                return TipoSalida.MAL_TIPADO;
                break;

            case 2:
                return TipoSalida.SIN_ARGS;
                break;

            case 3:
                return TipoSalida.MAL_RANGO;
                break;

            case 4:
                return TipoSalida.NEGATIVOS;
                break;

            default:
                throw new RuntimeException("ERROR\nEl valor ("+ salida +") introducido no se correspsonde con ninguna salida.");
                break;
        }
    }

    @Override
    public String toString() {
        return valor +" --> "+ descripcion;
    }
}