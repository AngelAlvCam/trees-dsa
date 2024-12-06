package Arboles;

/**
 * Estructura fundamental de los árboles binarios.
 * @author Flutt
 *
 */
public class Nodo {
	/**
	 * Clave que contiene el nodo
	 */
	private Object clave;
	
	/**
	 * Referencia al hijo izquierdo del nodo
	 */
	private Nodo izquierdo;
	
	/**
	 * Referencia al hijo derecho del nodo
	 */
	private Nodo derecho;
	
	/**
	 * Referencias al nodo padre del nodo
	 */
	private Nodo padre;
	
	/**
	 * Constructor no vacío de la clase "Nodo". Recibe como argumento a un objeto
	 * de la clase "Object" (o alguna subclase) para settearlo en el atributo clave.
	 * @param clave
	 */
	public Nodo(Object clave) {
		this.clave = clave;
		this.izquierdo = null;
		this.derecho = null;
	}
	
	/**
	 * Método getter para el atributo clave
	 * @return Un objeto que es la clave del nodo que llama al método.
	 */
	public Object getClave() {
		return clave;
	}

	/**
	 * Método setter para el atributo clave
	 * @param clave Un objeto que se quiere colocar como atributo clave del nodo 
	 * que llama al método
	 */
	public void setClave(Object clave) {
		this.clave = clave;
	}
	
	/**
	 * Método getter para el nodo hijo izquierdo
	 * @return Una referencia al nodo hijo izquierdo del nodo que llama al método
	 */
	public Nodo getIzquierdo() {
		return izquierdo;
	}
	
	/**
	 * Método setter para el nodo hijo izquierdo
	 * @param izquierdo Un objeto de la clase Nodo que se quiere colocar como hijo izquierdo
	 * del nodo que llama al método
	 */
	public void setIzquierdo(Nodo izquierdo) {
		this.izquierdo = izquierdo;
	}

	/**
	 * Método getter para el nodo hijo derecho
	 * @return Una referencia al nodo hijo derecho del nodo que llama al método
	 */
	public Nodo getDerecho() {
		return derecho;
	}

	/**
	 * Método setter para el nodo hijo derecho
	 * @param derecho Un objeto de la clase Nodo que se quiere colocar como hijo derecho
	 * del nodo que llama al método
	 */
	public void setDerecho(Nodo derecho) {
		this.derecho = derecho;
	}
	
	/**
	 * Método getter para el nodo padre
	 * @return Una referencia al nodo padre del nodo que llama al método
	 */
	public Nodo getPadre() {
		return padre;
	}

	/**
	 * Método setter para el nodo padre
	 * @param padre Un objeto de la clase Nodo que se quiere colocar como nodo padre
	 * del nodo que llama al método
	 */
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
		
	
	
}
