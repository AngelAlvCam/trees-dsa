package Arboles;

/**
 * Estructura fundamental de los �rboles binarios.
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
	 * Constructor no vac�o de la clase "Nodo". Recibe como argumento a un objeto
	 * de la clase "Object" (o alguna subclase) para settearlo en el atributo clave.
	 * @param clave
	 */
	public Nodo(Object clave) {
		this.clave = clave;
		this.izquierdo = null;
		this.derecho = null;
	}
	
	/**
	 * M�todo getter para el atributo clave
	 * @return Un objeto que es la clave del nodo que llama al m�todo.
	 */
	public Object getClave() {
		return clave;
	}

	/**
	 * M�todo setter para el atributo clave
	 * @param clave Un objeto que se quiere colocar como atributo clave del nodo 
	 * que llama al m�todo
	 */
	public void setClave(Object clave) {
		this.clave = clave;
	}
	
	/**
	 * M�todo getter para el nodo hijo izquierdo
	 * @return Una referencia al nodo hijo izquierdo del nodo que llama al m�todo
	 */
	public Nodo getIzquierdo() {
		return izquierdo;
	}
	
	/**
	 * M�todo setter para el nodo hijo izquierdo
	 * @param izquierdo Un objeto de la clase Nodo que se quiere colocar como hijo izquierdo
	 * del nodo que llama al m�todo
	 */
	public void setIzquierdo(Nodo izquierdo) {
		this.izquierdo = izquierdo;
	}

	/**
	 * M�todo getter para el nodo hijo derecho
	 * @return Una referencia al nodo hijo derecho del nodo que llama al m�todo
	 */
	public Nodo getDerecho() {
		return derecho;
	}

	/**
	 * M�todo setter para el nodo hijo derecho
	 * @param derecho Un objeto de la clase Nodo que se quiere colocar como hijo derecho
	 * del nodo que llama al m�todo
	 */
	public void setDerecho(Nodo derecho) {
		this.derecho = derecho;
	}
	
	/**
	 * M�todo getter para el nodo padre
	 * @return Una referencia al nodo padre del nodo que llama al m�todo
	 */
	public Nodo getPadre() {
		return padre;
	}

	/**
	 * M�todo setter para el nodo padre
	 * @param padre Un objeto de la clase Nodo que se quiere colocar como nodo padre
	 * del nodo que llama al m�todo
	 */
	public void setPadre(Nodo padre) {
		this.padre = padre;
	}
		
	
	
}
