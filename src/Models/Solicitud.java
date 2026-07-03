/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Views.Consola;
import Views.Validar;
import Exceptions.DatoInvalidoException;

/**
 *
 * @author emami
 */
public abstract class Solicitud {
    // Chau contador estatico. Ahora el codigo se ingresa a mano por consola
    protected int codSol;
    protected String nombreAsist;
    protected String tipoSoli;
    protected String descrip;
    protected int prioridad;
    protected String estado;

    // Constructor vacio: inicializa todo por defecto antes de cargar por teclado
    public Solicitud() {
        this.codSol = 0; // Se va a pisar cuando llamemos a cargarDatos()
        this.nombreAsist = "";
        this.tipoSoli = "";
        this.descrip = "";
        this.prioridad = 0;
        this.estado = "Pendiente";
    }
    
    // Constructor parametrizado (por si lo necesitamos mas adelante)
    public Solicitud(int codSol, String nombreAsist, String tipoSoli, String descrip, int prioridad) {
        this.codSol = codSol;
        this.nombreAsist = nombreAsist;
        this.tipoSoli = tipoSoli;
        this.descrip = descrip;
        this.prioridad = prioridad;
        this.estado = "Pendiente";
    }
    
    public String getPrioridadTexto() {
        switch (this.prioridad) {
            case 1: return "Baja";
            case 2: return "Media";
            case 3: return "Alta";
            default: return "Desconocida";
        }
    }  

    // === METODO DE CARGA PRINCIPAL ===
    // Ojo: ahora avisa que puede tirar la excepcion si cargan un codigo invalido
    public void cargarDatos() throws DatoInvalidoException {
        Consola.emitirMensajeLN("\n--- Cargando Datos de la Nueva Solicitud ---");
        
        // Lo primero que llamo es a mi nuevo metodo para meter el codigo manual
        ingresarCodSol(); 
        
        ingresarNombreAsist();
        ingresarDescrip();
        ingresarPrioridad();
    }
  
    // Nuevo metodo: Pide el codigo y valida que no sea negativo o cero
    private void ingresarCodSol() throws DatoInvalidoException {
        Consola.emitirMensaje("Ingrese el codigo de la solicitud: ");
        int codIngresado = Consola.leerInt();
        
        // Si mete un codigo invalido, lanzo el error directo para cortar la carga
        if (codIngresado <= 0) {
            throw new DatoInvalidoException("Error: El codigo de la solicitud debe ser un numero positivo mayor a cero.");
        }
        
        this.codSol = codIngresado;
    }

    private void ingresarNombreAsist(){
        this.nombreAsist = Validar.leerString(
                "Ingrese el nombre del asistente: ", 
                "Error: El nombre no puede quedar vacio."
        );
    }

    private void ingresarDescrip(){
        this.descrip = Validar.leerString(
                "Ingrese la descripcion de la solicitud: ", 
                "Error: La descripcion no puede quedar vacia."
        );
    }  
  
    private void ingresarPrioridad(){
        do {
            Consola.emitirMensaje("Ingrese la prioridad (1=Baja, 2=Media, 3=Alta): ");
            this.prioridad = Consola.leerInt();
            if (!Validar.ValidarIntRang(this.prioridad, 1, 3)) {
                Consola.emitirMensajeLN("Error: Prioridad invalida. Debe ingresar un numero del 1 al 3.");
            }
        } while (!Validar.ValidarIntRang(this.prioridad, 1, 3));
    }
 
    public abstract void describirSolicitud();
  
    public void marcarComoAtendida() {
        this.estado = "Atendida";
    }
  
    // === GETTERS Y SETTERS ===
    public int getCodSol() {
        return codSol;
    }

    public void setCodSol(int codSol) {
        this.codSol = codSol;
    }

    public String getNombreAsist() {
        return nombreAsist;
    }

    public void setNombreAsist(String NombreAsist) {
        this.nombreAsist = NombreAsist;
    }

    public String getTipoSoli() {
        return tipoSoli;
    }

    public void setTipoSoli(String tipoSoli) {
        this.tipoSoli = tipoSoli;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Cod: " + codSol+ " | Asistente: " + nombreAsist + 
               " | Tipo: " + tipoSoli + " | Prioridad: " + getPrioridadTexto() + " | Estado: " + estado;
    }
}