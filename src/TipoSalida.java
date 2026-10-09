package procesos;

/**
 * TipoSalida es un enum que describe todas las salidas que {@link src.TrabajadorPrimo} puede devolver.
 * 
 * @author Eric Ramos Pastor
 * @see src.TrabajadorPrimo
 * @version %I%, %G%
 * @since 1.0
 */
public enum TipoSalida {
    BIEN(0, "Ejecución satisfactoria."),
    MAL_TIPADO(1, "Tipado de los argumentos inválido."),
    SIN_ARGS(2, "Argumentos no introducidos."),
    MAL_RANGO(3, "Límite inferior del rango igual o mayor al límite superior."),
    NEGATIVOS(4, "Rango con valores negativos.");

    private final int valor;
    private final String descripcion;

    /**
     * Constructor parametrado de <code>TipoSalida</code>.
     * 
     * @param valor Valor de salida del programa.
     * @param descripcion Definición de la salida.
     * @since 1.0
     */
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

    /**
     * Devuelve un objeto <code>TipoSalida</code> en base a un entero dado. Se trata de una función estática.
     * 
     * @param salida Valor de salida a traducir.
     * @return <code>TipoSalida</code>, traducción del entero dado.
     * @since 1.0
     */
    public static TipoSalida buscarSalidaEquivalente(final int salida) {
        switch(salida) {
            case 0:
                return TipoSalida.BIEN;

            case 1:
                return TipoSalida.MAL_TIPADO;

            case 2:
                return TipoSalida.SIN_ARGS;

            case 3:
                return TipoSalida.MAL_RANGO;

            case 4:
                return TipoSalida.NEGATIVOS;

            default:
                throw new RuntimeException("ERROR\nEl valor ("+ salida +") introducido no se correspsonde con ninguna salida.");
        }
    }

    @Override
    public String toString() {
        return valor +" --> "+ descripcion;
    }
}