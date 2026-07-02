    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ED;

/**
 *
 * @author Kelly
 */
public class Nodo<T> {
  private T dato;
    private Nodo<T> ps;

    public Nodo() {
        this.dato = null;
        this.ps = null;
    }
    
    
    public Nodo(T e){
        dato=e;
        ps=null;
                       }

    public T getDato() {
        return dato;
    }

    public Nodo<T> getPs() {
        return ps;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setPs(Nodo<T> ps) {
        this.ps = ps;
    }

    
    
  
}
