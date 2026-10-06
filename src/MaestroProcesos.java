package procesos;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class MaestroProcesos {
    private static final int TOTAL_MULTIPROCESOS = 4;
    private static final String DEFAULT_INICIO = "0";
    private static final String DEFAULT_FIN = "1000";

    public static void main(String[] args) {
        String arg1 = args.length < 1 ? DEFAULT_INICIO : args[0], arg2 = args.length < 2 ? DEFAULT_FIN : args[1];
        String[] infoCalculoPrimos = new String[]{"java", "procesos.TrabajadorPrimo", arg1, arg2};

        System.out.println("---Ejecución con multiproceso------------------------------");
        try(DatosEjecucion resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, TOTAL_MULTIPROCESOS)) {
            System.out.println(resultados);
        } catch(RuntimeException | NumberFormatException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución con multiprocesos:\n"+ e.getMessage());
        }
        
        System.out.println("\n---Ejecución sin multiproceso------------------------------");
        try(DatosEjecucion resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, 1)) {
            System.out.println(resultados);
        } catch(RuntimeException | NumberFormatException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución sin multiprocesos:\n"+ e.getMessage());
        }
    }
    
    private static DatosEjecucion arrancarTrabajadorPrimo(final String[] infoProceso, final int procesosSimultaneos) {
        if(procesosSimultaneos <= 0) throw new RuntimeException("Se ha introducido un número de procesos igual o inferior a 0.");
        List<Process> procesosActivos = new ArrayList<>();
        DatosEjecucion datos = new DatosEjecucion(procesosSimultaneos, {Integer.parseInt(infoProceso[2]), Integer.parseInt(infoProceso[3])});
        
        {
            int longitudRango = datos.getRango()[1] - datos.getRango()[0];
            int salto = longitudRango / procesosSimultaneos;
            int limiteInf = datos.getRango()[0], limiteSup;
            for(int i = 0; i < procesosSimultaneos; i++) {
                if(i != 0) limiteInf = limiteSup + 1;
                limiteSup = limiteInf + salto > datos.getRango()[1] ? datos.getRango()[1] : limiteInf + salto;
                datos.agregarResultadoProceso(new ResultadoProceso({limiteInf, limiteSup}, datos.getCantidadProcesos().size() + 1));
            }
        }
        
        datos.setInicioMillis(System.currentTimeMillis());
        try {
            for(int i = 0; i < procesosSimultaneos; i++) {
                Process proceso = Runtime.getRuntime().exec({
                    infoProceso[0],
                    infoProceso[1],
                    String.valueOf(datos.getProcesosEjecutados().get(i).getRango()[0]),
                    String.valueOf(datos.getProcesosEjecutados().get(i).getRango()[1])
                });
                procesosActivos.add(proceso);
            }

            for(int i = 0; i < procesosSimultaneos; i++)
                datos.getProcesosEjecutados().get(i).setSalida(procesosActivos.get(i).waitFor());

        } catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        datos.setFinMillis(System.currentTimeMillis());

        return datos;
    }
}