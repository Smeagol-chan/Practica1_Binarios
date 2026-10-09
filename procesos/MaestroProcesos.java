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
 * @since 1.0
 */
public class MaestroProcesos {
    /**
     * {@value #TOTAL_MULTIPROCESOS} TOTAL_MULTIPROCESOS Define la cantidad de multoprocesos a realizar.
     * {@value #DEFAULT_INICIO} DEFAULT_INICIO Valor por defecto para el primer argumento.
     * {@value #DEFAULT_FIN} DEFAULT_FIN Valor por defecto para el segundo argumento.
     */
    private static final int TOTAL_MULTIPROCESOS = 4;
    private static final String DEFAULT_INICIO = "0";
    private static final String DEFAULT_FIN = "500000";

    /**
     * Invoca <code>arrancarTrabajadorPrimo</code> primero empleando multiprocesamiento y 
     * después con un único proceso, mostrando el resultado de cada ejecución.
     * <p>
     * La información de cada ejecución se compone de el tiempo total en segundo, la cantidad
     * de procesos que lo componen y el rango de números que cada uno ha procesado. Todo se 
     * almacena en un objeto {@link procesos.DatosEjecucion}.
     * 
     * @param args
     * @see #arrancarTrabajadorPrimo(String[], int)
     * @see procesos.DatosEjecucion
     * @since 1.0
     */
    public static void main(String[] args) {
        String arg1 = args.length < 1 ? DEFAULT_INICIO : args[0], arg2 = args.length < 2 ? DEFAULT_FIN : args[1];
        String[] infoCalculoPrimos = new String[]{"java", "procesos.TrabajadorPrimo", arg1, arg2};

        System.out.println("---Ejecución con multiproceso------------------------------");
        try {
            DatosEjecucion resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, TOTAL_MULTIPROCESOS);
            System.out.println(resultados);
        } catch(RuntimeException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución con multiprocesos:\n"+ e.getMessage());
        }
        
        System.out.println("\n---Ejecución sin multiproceso------------------------------");
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
     * La función está compuesta por cinco secciones:
     * <ol>
     * <li>Una comprobación inicial para valiar el número de procesos introducido.
     * <li>Un <code>scope</code> para calcular el rango que cada proceso recogerá.
     * <li>Bucle <code>for</code> que ejecuta y almacena cada uno de los procesos en la lista <code>procesosActivos</code>.
     * <li>Bucle <code>foreach</code> para liberar el buffer de cada proceso activo.
     * <li>Bucle <code>for</code> para recibir la salida de cada proceso.
     * </ol>
     * 
     * @param infoProceso Los datos que apuntan al archivo objetivo y contiene los argumentos deseados.
     * @param procesosSimultaneos Cantidad de procesos a realizar.
     * @return <code>DatosEjecucion</code> con toda la información de la ejecución.
     * @see src.DatosEjecucion
     * @see src.ResultadoProceso
     * @since 1.0
     */
    private static DatosEjecucion arrancarTrabajadorPrimo(final String[] infoProceso, final int procesosSimultaneos) {
        if(procesosSimultaneos <= 0) throw new RuntimeException("Se ha introducido un número de procesos igual o inferior a 0.");
        List<Process> procesosActivos = new ArrayList<>();
        DatosEjecucion datos = new DatosEjecucion(procesosSimultaneos, new int[]{Integer.parseInt(infoProceso[2]), Integer.parseInt(infoProceso[3])});
        
        {
            int longitudRango = datos.getRango()[1] - datos.getRango()[0];
            int salto = longitudRango / procesosSimultaneos;
            int limiteInf = datos.getRango()[0], limiteSup = 0;
            for(int i = 0; i < procesosSimultaneos; i++) {
                if(i != 0) limiteInf = limiteSup + 1;
                limiteSup = limiteInf + salto > datos.getRango()[1] ? datos.getRango()[1] : limiteInf + salto;
                datos.agregarResultadoProceso(new ResultadoProceso(new int[]{limiteInf, limiteSup}, datos.getProcesosEjecutados().size() + 1));
            }
        }
        
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

            for(Process proceso : procesosActivos) {
                BufferedReader output = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                output.close();
            }

            for(int i = 0; i < procesosSimultaneos; i++)
                datos.getProcesosEjecutados().get(i).setSalida(procesosActivos.get(i).waitFor());

        } catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        datos.setFinMillis(System.currentTimeMillis());

        return datos;
    }
}