
package Views;

/**
 *
 * @author emami
 */
public class Menu {
    private String titulo;  
    private String[] opciones;

    // ============== Constructores ============== //
    
    public Menu(int cantOpc) {
        this.titulo=null;
        this.opciones = new String[cantOpc];
    }
    
    // ========================== Metodo publicos ========================== //

    // ============== Metodo principal ============== //
    
    public int ejecutar(){
        visualizar();
        int opcion=leerOpcion();
        
        return opcion;
    }
    
    // ============== Metodo para cargar los datos ============== //
    
   public void cargarDato(String titulo, String[] opciones){
        setTitulo(titulo);
        setOpciones(opciones);
    }
    
    // ========================== Metodo privados ========================== //
    
    // ============== Metodo para leer ============== //
    
    private int leerOpcion(){
        int opcion=-1;
        
        do{
            Consola.emitirMensaje("Respuesta:");
            opcion=Consola.leerInt();
            
            
            if(!Validar.ValidarIntRang(opcion, 1, opciones.length)){
                Consola.emitirMensajeLN("Opcion no valida.");
            }
            
        } while(!Validar.ValidarIntRang(opcion, 1, opciones.length));
        
        return opcion;
    }
    
    // ============== Metodo para mostrar ============== //
    
    private void visualizar(){
        Consola.emitirMensajeLN("");
        Consola.emitirMensajeLN(" ========== "+this.titulo+" ========== ");
        
        for(int i=1 ; i <= opciones.length; i++) {
            Consola.emitirMensajeLN(i+"_"+opciones[i-1]);
        }
    }
    
    // ============== Getter ============== //
    
    public String getTitulo() {
        return titulo;
    }
    
    public String[] getOpciones() {
        return opciones;
    }
    
    // ============== Setter ============== //

    private void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    private void setOpciones(String[] opciones) {
        this.opciones = opciones;
    }
}