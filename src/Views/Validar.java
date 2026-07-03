
package Views;
/**
 *
 * @author emami
 */
public class Validar {
   /**
     * Valida que un entero sea mayor a cero.
     * Útil para IDs, cantidades, o números de tarjeta.
     */
  
    public static boolean ValidarIntRang(int valor,int min,int max){
        return valor > 0 && valor >= min && valor <= max;
    }
 
    public static boolean validarString(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    public static boolean validarStringNum(String texto){
       if(texto == null || texto.isEmpty()){
           return false;
       } 
       return texto.matches("^[0-9]+$");
    }


    public static String leerString(String msj, String error) {
        String texto;
        
        do{
            Consola.emitirMensaje(msj);
            texto = Consola.leerString();
            
            if(!Validar.validarString(texto)){
                Consola.emitirMensajeLN(error);
            }
            
        }while(!Validar.validarString(texto));
        
        return texto;
    }

    
}
