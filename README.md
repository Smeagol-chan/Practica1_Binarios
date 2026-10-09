# Práctica 1: Cálculo de primos

## Ejecución

1. Descargar el directorio <code>procesos/</code>:
   >.  
   >└── procesos  
   > &emsp;&emsp;├── TrabajadorPrimo.java  
   > &emsp;&emsp;├── MaestroProcesos.java  
   > &emsp;&emsp;├── DatosEjecucion.java  
   > &emsp;&emsp;├── ResultadoProceso.java  
   > &emsp;&emsp;└── TipoSalida.java

2. Compilar el código:
   ```
   ~> javac .\procesos\*.java .
   ```
   Esto habrá generado las clases dentro del mismo directorio, quedando así:
   >.  
   >└── procesos  
   > &emsp;&emsp;├── TrabajadorPrimo.java  
   > &emsp;&emsp;├── MaestroProcesos.java  
   > &emsp;&emsp;├── DatosEjecucion.java  
   > &emsp;&emsp;├── ResultadoProceso.java  
   > &emsp;&emsp;├── TipoSalida.java  
   > &emsp;&emsp;├── TrabajadorPrimo.class  
   > &emsp;&emsp;├── MaestroProcesos.class  
   > &emsp;&emsp;├── DatosEjecucion.class  
   > &emsp;&emsp;├── ResultadoProceso.class  
   > &emsp;&emsp;└── TipoSalida.class

3. Pueden ser ejecutadas dos clases:
   - TrabajadorPrimo
     ```
     // Ambos parámetros han de ser introducidos.
     ~> java procesos.TrabajadorPrimo PARAMETRO_1 PARAMETRO_2
     ```
   - MaestroProcesos
     ```
     // Estos parámetros sí que son opcionales.
     // De no hacerlo, los parámetros almacenarán 0 y 1_000_000 respectivamente.
     ~> java procesos.MaestroProcesos [PARAMETRO_1] [PARAMETRO_2]
     ```
4. Este último paso no es necesario, pero si se desea generar un HTML a partir del JavaDoc:
   ```
   // Genera el proyecto HTML sin restricciones de visibilidad en .\doc\
   ~> javadoc -private -d doc procesos
   ```
   Quedaría de la siguiente manera:
   >.  
   >├── doc  
   >&nbsp;|&emsp;&emsp;└── *   
   >└── procesos  
   > &emsp;&emsp;├── TrabajadorPrimo.java  
   > &emsp;&emsp;├── MaestroProcesos.java  
   > &emsp;&emsp;├── DatosEjecucion.java  
   > &emsp;&emsp;├── ResultadoProceso.java  
   > &emsp;&emsp;├── TipoSalida.java  
   > &emsp;&emsp;├── TrabajadorPrimo.class  
   > &emsp;&emsp;├── MaestroProcesos.class  
   > &emsp;&emsp;├── DatosEjecucion.class  
   > &emsp;&emsp;├── ResultadoProceso.class  
   > &emsp;&emsp;└── TipoSalida.class

   >> ⚠️ **Cuidado**
   >> 
   >> Si al ejecutar <code>javadoc</code> en windows no te reconoce el comando o avisa de que el flag (<code>-private</code>) no lo entiende, tienes que revisar las variables de entorno.
   >> Tienes que crear una variable nueva de nombre <code>javadoc</code> con la dirección de la carpeta donde se encuentra como valor (por defecto se encuentra en <code>C:\Archivos de programa\Java\jdk-27\bin</code>).
   >> Recomiendo crearla como variable del sistema. Una vez hecho, busca entre las variables del sistema una llamada <code>Path</code>, edítala y agrega un nuevo campo. Ahí tienes que decirle
   >> la ubicación donde se encuentra JavaDoc (<code>C:\Archivos de programa\Java\jdk-27\bin\javadoc.exe</code>), pero acabas de declarar una variable que la referencia solo tienes que escribirle <code>%javadoc%</code>.
   >>
   >> Abre una nueva instancia de la terminal y comprueba que el comando se reconoce. Reinicia el ordenador de no ser así.

## Clases

### TrabajadorPrimo  - [Ver](./procesos/TrabajadorPrimo.java)
Muestra por pantalla todos los primos existentes dentro de un rango determinado. Cuenta con método <code>main</code> para poder ser ejecutado.

### MaestroProcesos - [Ver](./procesos/MaestroProcesos.java)
Ejecuta <code>TrabajadorPrimo</code> tres veces y muestra los datos de ejecución, con 8, 4 y sin multiproceso respectivamente. También tiene <code>main</code> para poder ser ejecutado.

### DatosEjecucion - [Ver](./procesos/DatosEjecucion.java)
Almacena el momento de inicio y final de ejecución, el rango numérico a analizar y todos los procesos que lo componen de un programa dado.

### ResultadoProceso - [Ver](./procesos/ResultadoProceso.java)
Almacena un identificador, el intervalo de números analizados y la salida del proceso.

### TipoSalida - [Ver](./procesos/TipoSalida.java)
Contiene una transcripción con todos los tipos de salida que <code>TrabajadorPrimo</code> puede devolver.
