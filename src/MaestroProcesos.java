package procesos;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class MaestroProcesos {
    private static final int TOTAL_MULTIPROCESOS = 4;
    private static final String DEFAULT_INICIO = "0";
    private static final String DEFAULT_FIN = "20000";

    public static void main(String[] args) {
        String arg1 = args.length < 1 ? DEFAULT_INICIO : args[0], arg2 = args.length < 2 ? DEFAULT_FIN : args[1];
        String[] infoCalculoPrimos = new String[]{"java", "procesos.TrabajadorPrimo", arg1, arg2};

        System.out.println("---Ejecución con multiproceso------------------------------");
        try(String[] resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, TOTAL_MULTIPROCESOS)) {
            for(String dato : resultados)
                System.out.println("\t>"+ dato);
        } catch(RuntimeException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución con multiprocesos:\n"+ e.getMessage());
        }
        
        System.out.println("\n---Ejecución sin multiproceso------------------------------");
        try(String[] resultados = arrancarTrabajadorPrimo(infoCalculoPrimos, 1)) {
            for(String dato : resultados)
                System.out.println("\t>"+ dato);
        } catch(RuntimeException e) {
            System.out.println("--ERROR--\nHa surgido un error durante la ejecución sin multiprocesos:\n"+ e.getMessage());
        }
    }
    
    private static String[] arrancarTrabajadorPrimo(final String[] infoProceso, final int procesosSimultaneos) {
        if(procesosSimultaneos <= 0) throw new RuntimeException("Se ha introducido un número de procesos igual o inferior a 0.");
        List<Process> procesosActivos = new ArrayList<>();
        List<int[]> subRangos = new ArrayList<>();
        long inicioProceso, finProceso;
        int salida;
        
        {
            int longitudRango = Integer.parseInt(infoProceso[3]) - Integer.parseInt(infoProceso[2]);
            int salto = longitudRango / procesosSimultaneos;
            int limiteInf = Integer.parseInt(infoProceso[2]), limiteSup = limiteInf + salto;
            for(int i = 0; i < procesosSimultaneos; i++) {
                if(i != 0) {
                    limiteInf = limiteSup + 1;
                    limiteSup = (i == (procesosSimultaneos - 1) ? Integer.parseInt(infoProceso[3]) : (limiteInf + salto));
                }
                subRangos.add({limiteInf, limiteSup});
            }
        }
        
        inicioProceso = System.currentTimeMillis();
        try {

        } catch(IOException e) {
            throw new RuntimeException(e.getMessage);
        }
        finProceso = System.currentTimeMillis() + inicioProceso;

        // Reemplazar por un objeto DatosEjecución.
        return {
            "Salida: "+ tablaEquivalenciasSalida(salida),
            "Rango: ["+ infoProceso[2] +", "+ infoProceso[3] +"]",
            "Número de procesos: "+ procesosSimultaneos,
            "Tiempo de ejecución: "+ (finProceso / 1000) +" segundos"
        };
    }
}