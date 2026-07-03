package Controllers;

import Views.*;
import Controllers.GestorFestival;
import Models.Solicitud;
import Models.SolicitudEntrada;
import Models.SolicitudAsistencia;
import Criterios.FiltroPorPrioridad;
import Exceptions.DatoInvalidoException;

/**
 * @author emami
 */
public class GestorFestivalMenu {
    private GestorFestival gestor;
    private Menu menuPrincipal;

    public GestorFestivalMenu() {
        // Inicializamos el motor lógico y el componente de menú
        this.gestor = new GestorFestival();
        this.menuPrincipal = new Menu(8);
        
        // Configuramos los textos del menú
        String titulo = "SISTEMA DE GESTION DE SOLICITUDES - FESTIVAL";
        String[] opciones = {
            "Registrar una nueva solicitud",
            "Mostrar todas las solicitudes pendientes",
            "Procesar solicitudes atendidas",
            "Informar cantidad de solicitudes pendientes",
            "Buscar solicitud pendiente por asistente",
            "Filtrar solicitudes pendientes por prioridad (Objeto Funcion)",
            "Consultar la solicitud al frente de la cola",
            "Salir del sistema"
        };
        this.menuPrincipal.cargarDato(titulo, opciones);
    }

    /**
     * Lanza el bucle principal que mantiene vivo el menú interactivo.
     */
    public void iniciar() {
        int opcionSeleccionada;

        do {
            opcionSeleccionada = menuPrincipal.ejecutar();

            switch (opcionSeleccionada) {
                case 1:
                    registrarNuevaSolicitudSubmenu();
                    break;

                case 2:
                    try {
                        gestor.mostrarTodasLasPendientes();
                    } catch (DatoInvalidoException e) {
                        Consola.emitirMensajeLN(e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        gestor.procesarSolicitudes();
                    } catch (DatoInvalidoException e) {
                        Consola.emitirMensajeLN(e.getMessage());
                    }
                    break;

                case 4:
                    int cantPendientes = gestor.obtenerCantidadPendientes();
                    Consola.emitirMensajeLN("\n[INFO] Cantidad de solicitudes pendientes actualmente: " + cantPendientes);
                    break;

                case 5:
                    try {
                        String nombreBuscar = Validar.leerString(
                            "Ingrese el nombre del asistente a buscar: ", 
                            "Error: El nombre no puede estar vacio."
                        );
                        boolean encontrado = gestor.buscarPendientePorAsistente(nombreBuscar);
                        if (encontrado) {
                            Consola.emitirMensajeLN("\n[OK] ¡Si! Existe al menos una solicitud PENDIENTE para el asistente: " + nombreBuscar);
                        } else {
                            Consola.emitirMensajeLN("\n[INFO] No se encontraron solicitudes pendientes para: " + nombreBuscar);
                        }
                    } catch (DatoInvalidoException e) {
                        Consola.emitirMensajeLN(e.getMessage());
                    }
                    break;

                case 6:
                    try {
                        Consola.emitirMensajeLN("\n¿Que prioridad desea filtrar?");
                        Consola.emitirMensajeLN("1 - Baja\n2 - Media\n3 - Alta");
                        int prioFiltrar = Consola.leerInt();
                        
                        // Uso del OBJETO FUNCIÓN
                        FiltroPorPrioridad filtro = new FiltroPorPrioridad(prioFiltrar);
                        gestor.mostrarPendientesPorCriterio(filtro);
                        
                    } catch (DatoInvalidoException e) {
                        Consola.emitirMensajeLN(e.getMessage());
                    }
                    break;

                case 7:
                    try {
                        Solicitud alFrente = gestor.obtenerFrente();
                        Consola.emitirMensajeLN("\n=== SOLICITUD AL FRENTE DE LA COLA ===");
                        alFrente.describirSolicitud();
                    } catch (DatoInvalidoException e) {
                        Consola.emitirMensajeLN(e.getMessage());
                    }
                    break;

                case 8:
                    Consola.emitirMensajeLN("\nCerrando el sistema del festival. ¡Hasta luego!");
                    break;
            }

        } while (opcionSeleccionada != 8);
    }

    /**
     * Submenú interno para crear la subclase correcta.
     */
    private void registrarNuevaSolicitudSubmenu() {
        Consola.emitirMensajeLN("\n--- SELECCIONE EL TIPO DE SOLICITUD ---");
        Consola.emitirMensajeLN("1 - Reclamo de Entradas");
        Consola.emitirMensajeLN("2 - Asistencia en Predio");
        
        int tipo = Consola.leerInt();
        Solicitud nueva = null;

        if (tipo == 1) {
            nueva = new SolicitudEntrada();
        } else if (tipo == 2) {
            nueva = new SolicitudAsistencia();
        } else {
            Consola.emitirMensajeLN("Tipo invalido. Operacion cancelada.");
            return;
        }

        // Carga polimórfica automática
        nueva.cargarDatos();
        gestor.registrarSolicitud(nueva);
        Consola.emitirMensajeLN("\n[OK] Solicitud registrada exitosamente con el Codigo: " + nueva.getCodSol());
    }
}