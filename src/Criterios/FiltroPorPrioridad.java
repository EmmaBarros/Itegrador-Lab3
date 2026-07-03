
package Criterios;
import Models.Solicitud;
/**
 *
 * @author emami
 */
public class FiltroPorPrioridad implements CriterioSolicitud {
    private int prioridadBuscada;

    public FiltroPorPrioridad(int prioridadBuscada) {
        this.prioridadBuscada = prioridadBuscada;
    }

    @Override
    public boolean cumple(Solicitud solicitud) {
       return solicitud.getPrioridad() == this.prioridadBuscada;
    }
}
