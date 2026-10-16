package procesos;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

/**
 * MaestroProcesos es la clase encargada de invocar la clase {@link procesos.TrabajadorPrimo} con 
 * y sin multiproceso, comparando el tiempo de ejecución. Es dependiente de {@link procesos.DatosEjecucion} 
 * para la gestión de los datos.
 * 
 * @author Eric Ramos Pastor
 * @see procesos.TrabajadorPrimo
 * @see procesos.DatosEjecucion
 * @version %I%, %G%
 * @since 1.5
 */
public class MaestroProcesos {
    /**
     * Valor por defecto del primer argumento: {@value #DEFAULT_INICIO}
     */
    private static final String DEFAULT_INICIO = "0";
    /**
     * Valor por defecto del segundo argumento: {@value #DEFAULT_FIN}
     */
    private static final String DEFAULT_FIN = "1000000";

    /**
     * Invoca <code>arrancarTrabajadorPrimo</code> primero empleando multiprocesamiento y 
     * después con un único proceso, mostrando el resultado de cada ejecución.
     * <p>
     * La información de cada ejecución se compone de el tiempo total en segundo, la cantidad
     * de procesos que lo componen, los números primos encontrados y el rango de números que cada uno ha procesado. Todo se 
     * almacena en un objeto {@link procesos.DatosEjecucion}.
     * 
     * @param args Argumentos introducidos.
     * @see #arrancarTrabajadorPrimo(String[], int)
     * @see procesos.DatosEjecucion
     * @since 1.5
     */
    public static void main(String[] args) {
        int numProcesos = 0;
        String arg1 = "", arg2 = "";
        if(args.length == 1) {
            arg1 = DEFAULT_INICIO;
            arg2 = DEFAULT_FIN;
            numProcesos = Integer.parseInt(args[0]);
        } else if(args.length == 3) {
            arg1 = args[0];
            arg2 = args[1];
            numProcesos = Integer.parseInt(args[2]);
        } else {
            throw new RuntimeException(
                "\n--ERROR--\n"+
                "Cantidad de parámetros inválida: "+ args.length +"\n"+
                "Ejecute: java procesos.MaestroProcesos [<valor-inicio> <valor-final>] <numero-de-procesos>"
            );
        }
        String[] infoCalculoPrimos = new String[]{"java", "procesos.TrabajadorPrimo", arg1, arg2};

        System.out.println("---Ejecución con multiproceso: "+ numProcesos +"------------------------------");
        try {
            DatosEjecucion resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, numProcesos);
            System.out.println(resultados);
        } catch(RuntimeException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución con multiprocesos:\n"+ e.getMessage());
        }
        
        System.out.println("\n---Ejecución secuencial----------------------------------");
        try {
            DatosEjecucion resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, 1);
            System.out.println(resultados);
        } catch(RuntimeException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución sin multiprocesos:\n"+ e.getMessage());
        }
    }
    
    /**
     * Invoca <code>procesosSimultaneos</code> cantidad de procesos con definidos por <code>infoProceso</code>.
     * <p>
     * La función está compuesta por 4 secciones:
     * <ol>
     * <li>Una comprobación inicial para valiar el número de procesos introducido.
     * <li>Llamada a {@link #dividirIntervalo(int, int, int)}.
     * <li>Bucle <code>for</code> que ejecuta y almacena cada uno de los procesos en la lista <code>procesosActivos</code>.
     * <li>Bucle <code>for</code> para recibir la salida y resultado de cada proceso.
     * </ol>
     * 
     * @param infoProceso Los datos que apuntan al archivo objetivo y contiene los argumentos deseados.
     * @param procesosSimultaneos Cantidad de procesos a realizar.
     * @return <code>DatosEjecucion</code> con toda la información de la ejecución.
     * @see procesos.DatosEjecucion
     * @see procesos.ResultadoProceso
     * @since 1.5
     */
    private static DatosEjecucion arrancarTrabajadorPrimo(final String[] infoProceso, final int procesosSimultaneos) {
        if(procesosSimultaneos <= 0) throw new RuntimeException("Se ha introducido un número de procesos igual o inferior a 0.");
        List<Process> procesosActivos = new ArrayList<>();
        DatosEjecucion datos = new DatosEjecucion(procesosSimultaneos, new int[]{Integer.parseInt(infoProceso[2]), Integer.parseInt(infoProceso[3])});
        
        for(int[] intervalo : dividirIntervalo(procesosSimultaneos, datos.getRango()[0], datos.getRango()[1]))
            datos.agregarResultadoProceso(new ResultadoProceso(intervalo, datos.getProcesosEjecutados().size() + 1));
        
        datos.setInicioMillis(System.currentTimeMillis());
        try {

            for(int i = 0; i < procesosSimultaneos; i++) {
                Process proceso = Runtime.getRuntime().exec(new String[]{
                    infoProceso[0],
                    infoProceso[1],
                    String.valueOf(datos.getProcesosEjecutados().get(i).getRango()[0]),
                    String.valueOf(datos.getProcesosEjecutados().get(i).getRango()[1])
                });
                procesosActivos.add(proceso);
            }

            for(int i = 0; i < procesosSimultaneos; i++) {
                BufferedReader output = new BufferedReader(new InputStreamReader(procesosActivos.get(i).getInputStream()));
                int primosEncontrados = Integer.parseInt(output.readLine());
                datos.getProcesosEjecutados().get(i).setSalida(procesosActivos.get(i).waitFor());
                output.close();
                datos.anyadirQtyPrimos(primosEncontrados);
                datos.getProcesosEjecutados().get(i).setPrimosEncontrados(primosEncontrados);
            }

        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        datos.setFinMillis(System.currentTimeMillis());

        return datos;
    }

    /**
     * Dividir un intervalo en tantas divisiones como solicitadas.
     * 
     * @param divisiones Cantidad de rangos en los que dividir.
     * @param inicio Límite inferior del intervalo.
     * @param fin Límite superior del intervalo.
     * @return <code>ArrayList&lt;int[]&gt;</code> con el intervalo dividido.
     * @since 1.5
     */
    public static ArrayList<int[]> dividirIntervalo(final int divisiones, final int inicio, final int fin) {
        ArrayList<int[]> intervalos = new ArrayList<>();
        int longitudRango = fin - inicio;
        int salto = longitudRango / divisiones;
        int limiteInf = inicio, limiteSup = 0;

        for(int i = 0; i < divisiones; i++) {
            if(i != 0) limiteInf = limiteSup + 1;
            limiteSup = limiteInf + salto > fin ? fin : limiteInf + salto;
            intervalos.add(new int[]{limiteInf, limiteSup});
        }
        return intervalos;
    }

    /**
     * @deprecated
     */
    @Deprecated
    public MaestroProcesos(){}
}