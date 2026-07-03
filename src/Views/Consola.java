
package Views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 *
 * @author pc
 */
public class Consola {
    private static final Scanner teclado = new Scanner(System.in);
    /* 
     * Todos los métodos de esta clase son estáticos, y por lo tanto pueden ser invocados sin tener que 
     crear objetos de la clase. Es suficiente con nombrar la clase al invocar el método: int x = Consola.readInt(); 
     */

    /**
     * Lee un string desde teclado. El string termina con un salto de linea
     *
     * @return el string leido (sin el salto de linea)
     */
   public static String leerString() {
        // Una sola línea limpia que lee todo hasta el Enter de forma nativa
        return teclado.nextLine();
    }
    
    // Tu método leerInt() se queda exactamente igual, no lo toques:
    public static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un entero. Por favor, pruebe otra vez!");
            }
        }
    }
    /**
     * Lee un integer desde teclado. La entrada termina con un salto de linea
     *
     * @return el valor cargado, como un int
     */
  
    public static long leerLong() {
        while (true) {
            try {
                // Usamos Long.parseLong en lugar de Integer.parseInt
                return Long.parseLong(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un numero largo valido. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Lee un Integer desde teclado
     *
     * @return el Integer leido
     */
    public static Integer leerInteger() {
        while (true) {
            try {
                return Integer.parseInt(leerDato());
            } catch (NumberFormatException e) {
                Consola.mostrarMensaje("No es un entero. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Muestra un Integer
     */
    public static void mostrarInteger(String mensaje, Integer valor) {
        Consola.mostrarMensaje(mensaje + valor);
    }

    public static float leerFloat() {
        while (true) {
            try {
                return Float.parseFloat(leerString().trim());
            } catch (NumberFormatException e) {
                Consola.mostrarMensaje("No es un float. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Lee un double desde teclado. La entrada termina con un salto de linea
     *
     * @return el valor cargado, como un double
     */
    public static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(leerString().trim());
            } catch (NumberFormatException e) {
                System.out.println("No es un flotante. " + "Por favor, pruebe otra vez!");
            }
        }
    }
   /**
     * Emite mensaje y nueva linea
     *
     */
    public static void emitirMensajeLN(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Emite mensaje y nueva linea
     *
     */
    public static void emitirMensaje(String mensaje) {
        System.out.print(mensaje);
    }

    /**
     * Lee un string desde teclado. El string termina con un salto de linea
     *
     * @return el string leido (sin el salto de linea)
     */
    public static String leerDatoString() {
        while (true) {
            try {
                return leerDato();
            } catch (NumberFormatException e) {
                Consola.mostrarMensaje("No es un String. Por favor, pruebe otra vez!");
            }
        }
    }

    /**
     * Muestra
     *
     * @param mensaje un String
     */
    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static char leerChar() {
        while (true) {
            try {
                return (char) leerDato().charAt(0);
            } catch (NumberFormatException e) {
                Consola.mostrarMensaje("No es un integer. Por favor, pruebe otra vez!");
            }
        }
    }

    public static String ajustarString(String cad, int longitud) {
        if (cad.length() > longitud) {
            cad = cad.substring(0, longitud);
        } else {
            for (int i = cad.length(); i < longitud; i++) {
                cad = cad + " ";
            }
        }
        return cad;
    }

    public static boolean confirmar() {
        mostrarMensaje("Confirmar S/N: ");
        char opc = ' ';
        while (opc != 'n' && opc != 'N' && opc != 's' && opc != 'N') {
            mostrarMensaje("...Ingrese s/S/n/N para continuar...");
            opc = leerChar();
        }
        return (opc != 'n' && opc != 'N');
    }

    public static String leerDato() {
        String sdato = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader flujoE = new BufferedReader(isr);

        try {
            sdato = flujoE.readLine();
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }

        return sdato;
    }
}
