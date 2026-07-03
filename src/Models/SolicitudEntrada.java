package Models;
import Exceptions.DatoInvalidoException;
import Views.Validar;
/**
 * @author emami
 */
public class SolicitudEntrada extends Solicitud {
    private String numComprobante;

    public SolicitudEntrada() {
        super();
        this.numComprobante = "";
    }

    public SolicitudEntrada(int codSol, String nombreAsist, String descrip, int prioridad, String numComprobante) {
        super(codSol, nombreAsist, "Reclamo Entradas", descrip, prioridad);
        this.numComprobante = numComprobante;
    }
    
    @Override
    public void cargarDatos()throws DatoInvalidoException {
      super.cargarDatos();
      ingresarNumComprobante();
    }
    

    private void ingresarNumComprobante()throws DatoInvalidoException {
        this.numComprobante = Validar.leerString(
            "Ingrese el numero de comprobante de la entrada: ", 
            "Error: El numero de comprobante no puede estar vacio."
        );
    }
    
    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    @Override
    public void describirSolicitud() {
        System.out.println("=== DETALLE: RECLAMO DE ENTRADAS ===");
        System.out.println(super.toString()); // Muestra los datos comunes
        System.out.println("- Comprobante de Compra: " + numComprobante);
        System.out.println("- Motivo del reclamo: " + descrip);
    }
}