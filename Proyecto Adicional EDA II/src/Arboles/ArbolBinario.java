package Arboles;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Clase abstracta que representa a un �rbol binario general.
 * @author Flutt
 *
 */
public abstract class ArbolBinario {
	protected Nodo raiz;
	
	/**
	 * M�todo auxiliar del m�todo "Print". Es de naturaleza recursiva.
	 * Permite imprimir elementos enteros
	 * @param n El nodo que se desea imprimir.
	 */
    private void PrintAux(Nodo n) {
    	if (this.raiz == null) {
    		System.out.println("Estructura vac�a");
    	} else if (n.getIzquierdo() != null && n.getDerecho() != null) {
    		System.out.printf("Padre: %d, I: %d, D: %d\n", n.getClave(), n.getIzquierdo().getClave(), n.getDerecho().getClave());
    		PrintAux(n.getIzquierdo());
    		PrintAux(n.getDerecho());
    	}  else if (n.getIzquierdo() != null){
    		System.out.printf("Padre: %d, I: %d\n", n.getClave(), n.getIzquierdo().getClave());
    		PrintAux(n.getIzquierdo());
    	} else if (n.getDerecho() != null) {
    		System.out.printf("Padre: %d, D: %d\n", n.getClave(), n.getDerecho().getClave());
    		PrintAux(n.getDerecho());
    	} else if (n == raiz) {
    		System.out.printf("Padre: %d \n", n.getClave());
    	}
    }
    
    /**
     * M�todo que permite efectuar la impresi�n de una estructura general de �rbol.
     */
    protected void Print() {
    	PrintAux(this.raiz);
    }
    
    public abstract void Menu();
    
    /**
     * M�todo booleano que permite identificar si un nodo tiene hijos o no.
     * @return Un valor de true si el nodo tiene al menos un hijo; false en caso contrario.
     */
    public boolean TieneHijos() {
    	if (this.raiz.getIzquierdo() != null || this.raiz.getDerecho() != null) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
