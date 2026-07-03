package Controllers;

import ED.ColaDinamica;
import ED.Nodo;
import Models.Solicitud;
import Criterios.CriterioSolicitud;
import Exceptions.DatoInvalidoException;

// @author emami
public class GestorFestival {
    // Mi cola principal donde guardo todas las solicitudes del festival
    private ColaDinamica<Solicitud> colaSolicitudes;

    public GestorFestival() {
        this.colaSolicitudes = new ColaDinamica<>();
    }

    // === 1. REGISTRAR UNA SOLICITUD ===
    public void registrarSolicitud(Solicitud nuevaSolicitud) throws DatoInvalidoException {
        // Corroboro si el codigo de la solicitud ya existe en la cola (CP02)
        if (existeCodigo(nuevaSolicitud.getCodSol())) {
            throw new DatoInvalidoException("Error: Ya existe una solicitud registrada con el codigo " + nuevaSolicitud.getCodSol());
        }
        
        // Uso insertar que es el metodo real de mi cola
        colaSolicitudes.insertar(nuevaSolicitud);
    }

    // === 2. MOSTRAR TODAS LAS SOLICITUDES PENDIENTES ===
    public void mostrarTodasLasPendientes() throws DatoInvalidoException {
        // Excepcion si la cola esta vacia
        if (colaSolicitudes.colaVacia()) {
            throw new DatoInvalidoException("La cola esta vacia. No hay solicitudes pendientes para mostrar.");
        }

        // Creo una cola auxiliar para no destruir los datos al descolar
        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        System.out.println("\n=============================================");
        System.out.println("   LISTADO DE TODAS LAS SOLICITUDES PENDIENTES  ");
        System.out.println("=============================================");

        while (!colaSolicitudes.colaVacia()) {
            // El metodo quitar() me devuelve un Nodo, asi que le saco la Solicitud con getDato()
            Nodo<Solicitud> nodoActual = colaSolicitudes.quitar();
            Solicitud actual = nodoActual.getDato(); 

            // Solo muestro en pantalla las que siguen con estado Pendiente
            if (actual.getEstado().equals("Pendiente")) {
                actual.describirSolicitud();
                System.out.println("---------------------------------------------");
            }
            // La guardo en la auxiliar para no perderla
            colaAuxiliar.insertar(actual);
        }
        // Al final restauro mi cola original con los datos de la auxiliar
        this.colaSolicitudes = colaAuxiliar;
    }

    // === 3. PROCESAR SOLICITUDES ATENDIDAS ===
    public void procesarSolicitudes() throws DatoInvalidoException {
        if (colaSolicitudes.colaVacia()) {
            throw new DatoInvalidoException("No hay solicitudes en la cola para procesar.");
        }

        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        int atendidas = 0;

        while (!colaSolicitudes.colaVacia()) {
            Nodo<Solicitud> nodoActual = colaSolicitudes.quitar();
            Solicitud actual = nodoActual.getDato();
            
            // Simulacion: Atiendo automaticamente las de prioridad Baja (1) y Media (2)
            if (actual.getPrioridad() < 3) {
                actual.marcarComoAtendida();
            }

            // Filtro en vivo: si sigue pendiente va a la cola, si cambio a atendida la descarto
            if (actual.getEstado().equals("Pendiente")) {
                colaAuxiliar.insertar(actual);
            } else if (actual.getEstado().equals("Atendida")) {
                atendidas++; // La cuento para el informe
            }
        }
        // Mi cola original ahora solo se queda con las solicitudes que siguen pendientes
        this.colaSolicitudes = colaAuxiliar;
        System.out.println("\n>>> Procesamiento completado. Se quitaron " + atendidas + " solicitudes atendidas.");
    }

    // === 4. INFORMAR CANTIDAD DE SOLICITUDES PENDIENTES ===
    public int obtenerCantidadPendientes() {
        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        int contador = 0;

        // Recorro toda la cola contando las pendientes y pasando todo a la auxiliar
        while (!colaSolicitudes.colaVacia()) {
            Nodo<Solicitud> nodoActual = colaSolicitudes.quitar();
            Solicitud actual = nodoActual.getDato();

            if (actual.getEstado().equals("Pendiente")) {
                contador++;
            }
            colaAuxiliar.insertar(actual);
        }
        this.colaSolicitudes = colaAuxiliar; // Restauro el orden original
        return contador;
    }

    // === 5. BUSCAR SOLICITUD PENDIENTE POR ASISTENTE ===
    public boolean buscarPendientePorAsistente(String nombreAsistente) throws DatoInvalidoException {
        if (colaSolicitudes.colaVacia()) {
            throw new DatoInvalidoException("La cola esta vacia. No hay solicitudes para buscar.");
        }

        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        boolean encontrado = false;

        while (!colaSolicitudes.colaVacia()) {
            Nodo<Solicitud> nodoActual = colaSolicitudes.quitar();
            Solicitud actual = nodoActual.getDato();

            // Busco por nombre ignorando mayusculas/minusculas usando contains
            if (actual.getEstado().equals("Pendiente") && 
                actual.getNombreAsist().toLowerCase().contains(nombreAsistente.toLowerCase())) {
                encontrado = true;
            }
            colaAuxiliar.insertar(actual);
        }
        this.colaSolicitudes = colaAuxiliar;
        return encontrado;
    }

    // === 6. FILTRAR PENDIENTES POR PRIORIDAD (OBJETO FUNCIÓN) ===
    public void mostrarPendientesPorCriterio(CriterioSolicitud criterio) throws DatoInvalidoException {
        if (colaSolicitudes.colaVacia()) {
            throw new DatoInvalidoException("La cola esta vacia. No hay solicitudes para filtrar.");
        }

        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        int encontradas = 0;

        System.out.println("\n--- RESULTADOS DEL FILTRADO POR OBJETO FUNCIÓN ---");
        while (!colaSolicitudes.colaVacia()) {
            Nodo<Solicitud> nodoActual = colaSolicitudes.quitar();
            Solicitud actual = nodoActual.getDato();

            // Aca aplico el polimorfismo: el objeto funcion me dice si cumple la condicion o no
            if (actual.getEstado().equals("Pendiente") && criterio.cumple(actual)) {
                actual.describirSolicitud();
                System.out.println("---------------------------------------------");
                encontradas++;
            }
            colaAuxiliar.insertar(actual);
        }
        this.colaSolicitudes = colaAuxiliar;

        if (encontradas == 0) {
            throw new DatoInvalidoException("No se encontraron solicitudes pendientes con la prioridad seleccionada.");
        }
    }

    // === 7. CONSULTAR LA SOLICITUD AL FRENTE DE LA COLA ===
    public Solicitud obtenerFrente() throws DatoInvalidoException {
        if (colaSolicitudes.colaVacia()) {
            throw new DatoInvalidoException("La cola esta vacia. No hay ninguna solicitud al frente.");
        }
        
        // saco el primero, guardo su dato, y despues recorro y rearmo la cola entera en la auxiliar
        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        
        Nodo<Solicitud> primerNodo = colaSolicitudes.quitar();
        Solicitud alFrente = primerNodo.getDato();
        
        // Lo pongo primero en la nueva cola
        colaAuxiliar.insertar(alFrente);
        
        // Paso todos los demas atras de el
        while(!colaSolicitudes.colaVacia()){
            colaAuxiliar.insertar(colaSolicitudes.quitar().getDato());
        }
        
        // Reasigno la cola original ya restaurada
        this.colaSolicitudes = colaAuxiliar;
        
        return alFrente; 
    }

    // === METODO AUXILIAR PRIVADO ===
    // Metodo para buscar si el codigo ya existe recorriendo la cola sin romperla
    private boolean existeCodigo(int codigo) {
        // Excepcion si la cola esta vacia, el codigo no existe
        if (colaSolicitudes.colaVacia()) {
            return false;
        }

        // Creo una cola auxiliar para no destruir los datos al descolar
        ColaDinamica<Solicitud> colaAuxiliar = new ColaDinamica<>();
        boolean existe = false;

        while (!colaSolicitudes.colaVacia()) {
            // El metodo quitar() me devuelve un Nodo, asi que le saco la Solicitud con getDato()
            Nodo<Solicitud> nodoActual = colaSolicitudes.quitar();
            Solicitud actual = nodoActual.getDato();

            // Corroboro si los codigos coinciden
            if (actual.getCodSol() == codigo) {
                existe = true;
            }
            // La guardo en la auxiliar para no perderla
            colaAuxiliar.insertar(actual);
        }

        // Al final restauro mi cola original con los datos de la auxiliar
        this.colaSolicitudes = colaAuxiliar;
        return existe;
    }
}