package Models;
import Views.Validar;
/**
 * @author emami
 */
public class SolicitudAsistencia extends Solicitud {
    private String zonaPredio; 
   
    public SolicitudAsistencia() {
        super();
        this.zonaPredio = "";
    }

  
    public SolicitudAsistencia(int codSol, String nombreAsist, String descrip, int prioridad, String zonaPredio) {
        super(codSol, nombreAsist, "Asistencia en Predio", descrip, prioridad);
        this.zonaPredio = zonaPredio;
    }

    @Override
    public void cargarDatos(){
       this.zonaPredio = Validar.leerString(
            "Ingrese la zona del predio (ej: Campo, VIP, Gastronomia): ", 
            "Error: La zona del predio no puede estar vacia."
        ); 
    }
        
    private void ingresarZonaPredio(){
     this.zonaPredio = Validar.leerString(
            "Ingrese la zona del predio (ej: Campo, VIP, Gastronomia): ", 
            "Error: La zona del predio no puede estar vacia."
        );   
    }
    
    public String getZonaPredio() {
        return zonaPredio;
    }

    public void setZonaPredio(String zonaPredio) {
        this.zonaPredio = zonaPredio;
    }

    @Override
    public void describirSolicitud() {
        System.out.println("=== DETALLE: SOLICITUD DE ASISTENCIA ===");
        System.out.println(super.toString()); // Muestra los datos comunes
        System.out.println("- Ubicación exacta: " + zonaPredio);
        System.out.println("- Tipo de asistencia requerida: " + descrip);
    }
}