package ED;
public class ColaDinamica<T>  {
    private Nodo<T> frente, fondo;
    public  ColaDinamica() {
        frente = null;
        fondo = null;
    }

    public boolean colaVacia() {
        return (frente == null );
    }

    public void insertar(T  elem) {
        Nodo<T> nuevo = new Nodo<>(elem);
        if (colaVacia()) {
            frente = fondo = nuevo;
        } else {
            fondo.setPs(nuevo);
            fondo = nuevo;
        }
    }
    public void insertar(Nodo<T> nuevo) {
        
        if (colaVacia()) {
            frente = fondo = nuevo;
        } else {
            fondo.setPs(nuevo);
            fondo = nuevo;
        }
      }
       
    public Nodo<T> quitar() {
       
        Nodo<T> regresa = frente;
        if(!colaVacia())
        frente =frente.getPs();
          
        else
            System.out.println("Vacia");
        return (regresa);
    } 
}