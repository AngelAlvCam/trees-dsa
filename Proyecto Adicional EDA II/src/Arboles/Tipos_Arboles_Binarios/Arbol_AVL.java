package Arboles.Tipos_Arboles_Binarios;

import Arboles.ArbolBinario;
import Arboles.Control_Excepciones;
import Arboles.Nodo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa a un árbol binario autobalanceable basado en la
 * la definición de Adelson-Velskii y Landis.
 * @author Flutt
 *
 */
public class Arbol_AVL extends ArbolBinario {
	
	/**
	 * Constructor vacío de la clase "Arbol_AVL". 
	 * Inicializa al atributo "raiz" con "null", por lo tanto, no se obliga
	 * al usuario a inicializar el árbol con un elemento.
 	 */
	public Arbol_AVL() {
		this.raiz = null;
	}
	
	/**
	 * Método booleano que realiza la búsqueda en el árbol de algún elemento con una
	 * clave definida por el usuario.
	 * @param elemento Un valor entero que denota a la clave que se desea encontrar en el árbol.
	 * @return Un valor booleano de "true" si existe un nodo con la clave argumentada; "false" en
	 * caso contrario.
	 */
	private boolean Buscar(int elemento) {
		if (GetNodo(elemento) != null) {
			System.out.println("El elemento " + elemento + " SI se encuentra en el árbol");
			return true;
		} else {
			System.out.println("El elemento " + elemento + " NO se encuentra en el árbol");
			return false;
		}
	}
	
	/**
	 * Método que permite agregar un nodo al árbol, con clave idéntica al valor argumentado.
	 * @param elemento Un valor entero que se desea añadir al árbol en forma de nodo.
	 */
	private void Agregar(int elemento) {
		if (this.raiz == null) { // Si la raíz es nula, el elemento se añade a la raíz.
			this.raiz = new Nodo(elemento);
			System.out.println("Nodo "+ elemento +" añadido");
		} else {
			AgregarAux(this.raiz, elemento);
			this.raiz = balancear(this.raiz);
		}
	}
	
	private void Agregar(String bloque) {
		String[] aux = bloque.split(" ");
		if (VerificarBloque(bloque)) {
			for (String s : aux) {
				int n = Integer.parseInt(s);
				Agregar(n);
			}
		} else {
			System.out.println("Secuencia inválida");
		}
	}
	
	private boolean VerificarBloque(String bloque) {
		String[] aux = bloque.split(" ");
		boolean stat = false;
		try {
			for (String s : aux) {
				Integer.parseInt(s);
				stat = true;
			}
		} catch (NumberFormatException e) {
			return false;
		}
		return stat;
	}
	
	/**
	 * Método auxiliar del método Agregar. Se trata de un método de naturaleza recursiva que evalúa
	 * cada nodo que corresponde para añadir a la clave. 
	 * A diferencia del método de eliminación, en este no se verífica primero si existe algún nodo con
	 * la clave que se desea añadir porque esto se verifica de manera intuitiva durante el proceso de eliminación.
	 * @param n
	 * @param elemento
	 */
	private void AgregarAux(Nodo n, int elemento) {
		if ((Integer)n.getClave() < elemento) {
			if (n.getDerecho() == null) {
				Nodo nuevo_nodo = new Nodo(elemento);
				n.setDerecho(nuevo_nodo);
				n.getDerecho().setPadre(n);
				System.out.println("Nodo "+ elemento +" añadido");
			} else {
				AgregarAux(n.getDerecho(), elemento);
			}
		} else if ((Integer)n.getClave() > elemento) {
			if (n.getIzquierdo() == null) {
				Nodo nuevo_nodo = new Nodo(elemento);
				n.setIzquierdo(nuevo_nodo);
				n.getIzquierdo().setPadre(n);
				System.out.println("Nodo "+ elemento +" añadido");
			} else {
				AgregarAux(n.getIzquierdo(), elemento);
			}
		} else {
			System.out.println("No se permiten nodos repetidos");
		}
	}
	
	/**
	 * Este es un método auxiliar en la clase que permite realizar el balanceo
	 * del árbol.
	 * @param n El nodo padre del subarbol que se desea balancear. En todas las
	 * llamadas, este parámetro resulta ser el nodo padre del árbol.
	 * @return Una referencia a un objeto Nodo que denota al árbol balanceado.
	 */
	private Nodo balancear(Nodo n) {
		ArrayList<Integer> aux = new ArrayList();
		ListaOrdenada(n, aux);
		return Array_a_Arbol(aux, 0, aux.size() - 1);
	}
	
	/**
	 * Método de naturaleza recursiva que se encarga de transformar a un ArrayList de
	 * elementos enteros ordenados en un árbol binario de búsqueda balanceado.
	 * @param arr Una colección de tipo ArrayList de enteros ordenados de menor a mayor que se
	 * repartirá en un árbol binario de forma balanceada.
	 * @param minIn El índice mínimo de los elementos de la colección que corresponden a un subárbol.
	 * @param maxIn El índice máximo de los elementos de la colección que corresponden a un subárbol
	 * @return Una referencia a un objeto Nodo que denota al árbol balanceado con los elementos proporcionados
	 * en la colección de tipo ArrayList.
	 */
    private Nodo Array_a_Arbol(ArrayList<Integer> arr, int minIn, int maxIn) {   	  
        if (minIn > maxIn) { 
            return null; 
        } else {
	        int media = (minIn + maxIn) / 2; 
	        Nodo aux = new Nodo(arr.get(media)); 
	        aux.setIzquierdo(Array_a_Arbol(arr, minIn, media - 1)); 
	        if (aux.getIzquierdo() != null) {
	        	aux.getIzquierdo().setPadre(aux);
	        }
	        aux.setDerecho(Array_a_Arbol(arr, media + 1, maxIn)); 
	        if (aux.getDerecho() != null) {
	        	aux.getDerecho().setPadre(aux);
	        }
	        return aux; 
        }
    } 
    
    /**
     * Método de naturaleza recursiva que obtiene un ArrayList con los elementos del árbol, ordenados
     * de menor a mayor.
     * Se trata de un método cuya algoritmia es equivalente al recorrido en inorden.
     * @param n Un objeto de la clase Nodo que denota el subárbol que se está registrando en la colección.
     * @param aux Un objeto de la calse ArrayList que se llenará con los elementos del árbol, tal que éstos
     * estén ordenados de menor a mayor.
     */
    private void ListaOrdenada(Nodo n, ArrayList<Integer> aux) {
	   if (n != null) {
		   ListaOrdenada(n.getIzquierdo(), aux);
		   aux.add((Integer)n.getClave());
		   ListaOrdenada(n.getDerecho(), aux);
	   }
    }

	@Override
	public void Menu() {
		System.out.println("Menú de árbol autobalanceado");
		Arbol_AVL arbol = new Arbol_AVL();
		boolean stat = true;
		Scanner sc = new Scanner(System.in);
		int op, elemento;
		while (stat) {
			System.out.println("1) Agregar un nodo");
			System.out.println("2) Agregar varios nodos");
			System.out.println("3) Eliminar un nodo");
			System.out.println("4) Buscar un nodo");
			System.out.println("5) Imprimir estructura");
			System.out.println("6) Salir");
			System.out.print("OP: ");
			op = Control_Excepciones.IntInput();
			switch(op) {
			case 1:
				System.out.println("Ingrese el elemento entero por añadir:");
				System.out.print("ELEMENTO: ");
				elemento = Control_Excepciones.IntInput();
				arbol.Agregar(elemento);
				break;
			case 2:
				System.out.println("Ingrese los elementos a añadir separados por espacios en blanco");
				System.out.println("ELEMENTOS: ");
				String aux = sc.nextLine();
				arbol.Agregar(aux);
				break;
			case 3:
				System.out.println("Ingrese el elemento entero a eliminar:");
				System.out.print("ELEMENTO: ");
				elemento = Control_Excepciones.IntInput();
				arbol.Eliminar(elemento);
				break;
			case 4:
				System.out.println("Ingrese el elemento entero a buscar:");
				System.out.print("ELEMENTO: ");
				elemento = Control_Excepciones.IntInput();
				arbol.Buscar(elemento);
				break;
			case 5:
				arbol.Print();
				break;
			case 6:
				stat = false;
				break;
			default:
				System.out.println("E: Opción inválida");
				break;
			}
		}
	}
	
	/**
	 * RECUPERADO DE LAS PRÁCTICAS 8-9
	 * Es un método iterativo con el cual se realiza la eliminación de un nodo dado por m, el cual, 
	 * a su vez, se encuentra en función del primitivo entero "elemento" argumentado. 
	 * La eliminación solo se realiza si el nodo argumentado existe.
	 * @param m un objeto de la clase "Nodo" que representa al nodo que se desea eliminar.
	 */
	private void Eliminar(int elemento) {
		if (this.raiz != null) {
			Nodo m = GetNodo(elemento);
			if (m != null) {	//Si el nodo existe en el árbol
				if (this.TieneHijos() == true) {
					Nodo k = GetMin_Max(m);	//La posición que cederá su clave a m
					Nodo r = k.getPadre();		
					if (k == m) {	//Si es un nodo hoja
						if (r.getIzquierdo() == m) {	//Si m es el hijo izquierdo de r
							r.setIzquierdo(null);
						} else {
							r.setDerecho(null);	//Si m es el hijo derecho de r
						}
						m = k = null;
					} else if ((Integer)r.getClave() < (Integer)k.getClave()) {	// Si se obtuvo el nodo más a la derecha del subárbol izquierdo
						r.setDerecho(k.getIzquierdo());
						m.setClave((Integer)k.getClave());
						k = null;
					} else if ((Integer)r.getClave() > (Integer)k.getClave()) {	// Si se obtuvo el nodo más a la izquierda del subárbol izquierdo
						r.setIzquierdo(k.getDerecho());
						m.setClave((Integer)k.getClave());
						k = null;
					}
					this.raiz = balancear(this.raiz);
				} else {
					this.raiz = null;
				}
				System.out.println("Nodo eliminado");
			} else {
				System.out.println("El nodo argumentado no existe");
			}
		} else {
			System.out.println("Este árbol no tiene nodos");
		}
	}
	
	/**
	 * RECUPERADO DE LA PRÁCTICA 8-9
	 * Método que se encarga de retornar a un objeto de la clase Nodo con la clave argumentada si
	 * es que existe. Es equivalente a un método de búsqueda, pero se define de esta manera, pues es
	 * utilizado como método auxiliar en otros métodos.
	 * @param n Un elemento entero que denota la clave que se desea eliminar del árbol.
	 * @return Un objeto de la clase Nodo que contiene con atributo "clave" al valor entero dado por el argumento n.
	 */
	private Nodo GetNodo(int n) {
		Nodo m = GetNodoAux(this.raiz, n);
		return m;
	}
	
	/**
	 * RECUPERADO PRÁCTICAS 8-9
	 * Método auxiliar del método GetNodo. Este método se encarga de buscar al nodo del árbol que tenga como atributo clave un
	 * valor entero idéntico a n.
	 * @param r Un objeto de la clase Nodo que se desea evalúar.
	 * @param n Un valor entero que se desea encontrar en el Nodo r argumentado.
	 * @return Un objeto de la clase Nodo que contiene como atributo clave al valor de n; en el caso que la clave no exista en el
	 * árbol se retorna null.
	 */
	private Nodo GetNodoAux(Nodo r, int n) {
		if (r != null) {
			if ((Integer)r.getClave() == n) {
				return r;
			} else if (n < (Integer)r.getClave()) {
				return GetNodoAux(r.getIzquierdo(), n);
			} else { // if (n > r.valor)
				return GetNodoAux(r.getDerecho(), n);
			}
		} else {
			return null;
		}
	}
	
	/**
	 * RECUPERADO PRÁCTICAS 8-9
	 * Método iterativo que retorna al nodo máximo del subárbol izquierdo o al nodo
	 * mínimo del subarbol derecho, según lo requiera el caso. También se puede retornar un nodo
	 * idéntico al argumentado si se trata de un nodo hoja.
	 * @param m 
	 * @return el nodo máximo del subárbol izquierdo, el nodo mínimo del subárbol derecho, o
	 * un nodo idéntico a m si el nodo argumentado es un nodo hoja.
	 */
	private Nodo GetMin_Max(Nodo m) { //Retorna al nodo más a la derecha o más a la izquierda según el caso 
		if (m.getIzquierdo() != null) {			
			m = m.getIzquierdo();
			while (m.getDerecho() != null) {
				m = m.getDerecho();
			}
			return m;	
		} else if (m.getDerecho() != null) {
			m = m.getDerecho();
			while (m.getIzquierdo() != null) {
				m = m.getIzquierdo();
			}
			return m;
		} else {	//Si m es un nodo hoja
			return m;
		}
	}
}
