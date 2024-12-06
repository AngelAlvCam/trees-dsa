package Arboles.Tipos_Arboles_Binarios;

import Arboles.ArbolBinario;
import Arboles.Control_Excepciones;
import Arboles.Nodo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que representa a un �rbol binario autobalanceable basado en la
 * la definici�n de Adelson-Velskii y Landis.
 * @author Flutt
 *
 */
public class Arbol_AVL extends ArbolBinario {
	
	/**
	 * Constructor vac�o de la clase "Arbol_AVL". 
	 * Inicializa al atributo "raiz" con "null", por lo tanto, no se obliga
	 * al usuario a inicializar el �rbol con un elemento.
 	 */
	public Arbol_AVL() {
		this.raiz = null;
	}
	
	/**
	 * M�todo booleano que realiza la b�squeda en el �rbol de alg�n elemento con una
	 * clave definida por el usuario.
	 * @param elemento Un valor entero que denota a la clave que se desea encontrar en el �rbol.
	 * @return Un valor booleano de "true" si existe un nodo con la clave argumentada; "false" en
	 * caso contrario.
	 */
	private boolean Buscar(int elemento) {
		if (GetNodo(elemento) != null) {
			System.out.println("El elemento " + elemento + " SI se encuentra en el �rbol");
			return true;
		} else {
			System.out.println("El elemento " + elemento + " NO se encuentra en el �rbol");
			return false;
		}
	}
	
	/**
	 * M�todo que permite agregar un nodo al �rbol, con clave id�ntica al valor argumentado.
	 * @param elemento Un valor entero que se desea a�adir al �rbol en forma de nodo.
	 */
	private void Agregar(int elemento) {
		if (this.raiz == null) { // Si la ra�z es nula, el elemento se a�ade a la ra�z.
			this.raiz = new Nodo(elemento);
			System.out.println("Nodo "+ elemento +" a�adido");
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
			System.out.println("Secuencia inv�lida");
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
	 * M�todo auxiliar del m�todo Agregar. Se trata de un m�todo de naturaleza recursiva que eval�a
	 * cada nodo que corresponde para a�adir a la clave. 
	 * A diferencia del m�todo de eliminaci�n, en este no se ver�fica primero si existe alg�n nodo con
	 * la clave que se desea a�adir porque esto se verifica de manera intuitiva durante el proceso de eliminaci�n.
	 * @param n
	 * @param elemento
	 */
	private void AgregarAux(Nodo n, int elemento) {
		if ((Integer)n.getClave() < elemento) {
			if (n.getDerecho() == null) {
				Nodo nuevo_nodo = new Nodo(elemento);
				n.setDerecho(nuevo_nodo);
				n.getDerecho().setPadre(n);
				System.out.println("Nodo "+ elemento +" a�adido");
			} else {
				AgregarAux(n.getDerecho(), elemento);
			}
		} else if ((Integer)n.getClave() > elemento) {
			if (n.getIzquierdo() == null) {
				Nodo nuevo_nodo = new Nodo(elemento);
				n.setIzquierdo(nuevo_nodo);
				n.getIzquierdo().setPadre(n);
				System.out.println("Nodo "+ elemento +" a�adido");
			} else {
				AgregarAux(n.getIzquierdo(), elemento);
			}
		} else {
			System.out.println("No se permiten nodos repetidos");
		}
	}
	
	/**
	 * Este es un m�todo auxiliar en la clase que permite realizar el balanceo
	 * del �rbol.
	 * @param n El nodo padre del subarbol que se desea balancear. En todas las
	 * llamadas, este par�metro resulta ser el nodo padre del �rbol.
	 * @return Una referencia a un objeto Nodo que denota al �rbol balanceado.
	 */
	private Nodo balancear(Nodo n) {
		ArrayList<Integer> aux = new ArrayList();
		ListaOrdenada(n, aux);
		return Array_a_Arbol(aux, 0, aux.size() - 1);
	}
	
	/**
	 * M�todo de naturaleza recursiva que se encarga de transformar a un ArrayList de
	 * elementos enteros ordenados en un �rbol binario de b�squeda balanceado.
	 * @param arr Una colecci�n de tipo ArrayList de enteros ordenados de menor a mayor que se
	 * repartir� en un �rbol binario de forma balanceada.
	 * @param minIn El �ndice m�nimo de los elementos de la colecci�n que corresponden a un sub�rbol.
	 * @param maxIn El �ndice m�ximo de los elementos de la colecci�n que corresponden a un sub�rbol
	 * @return Una referencia a un objeto Nodo que denota al �rbol balanceado con los elementos proporcionados
	 * en la colecci�n de tipo ArrayList.
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
     * M�todo de naturaleza recursiva que obtiene un ArrayList con los elementos del �rbol, ordenados
     * de menor a mayor.
     * Se trata de un m�todo cuya algoritmia es equivalente al recorrido en inorden.
     * @param n Un objeto de la clase Nodo que denota el sub�rbol que se est� registrando en la colecci�n.
     * @param aux Un objeto de la calse ArrayList que se llenar� con los elementos del �rbol, tal que �stos
     * est�n ordenados de menor a mayor.
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
		System.out.println("Men� de �rbol autobalanceado");
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
				System.out.println("Ingrese el elemento entero por a�adir:");
				System.out.print("ELEMENTO: ");
				elemento = Control_Excepciones.IntInput();
				arbol.Agregar(elemento);
				break;
			case 2:
				System.out.println("Ingrese los elementos a a�adir separados por espacios en blanco");
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
				System.out.println("E: Opci�n inv�lida");
				break;
			}
		}
	}
	
	/**
	 * RECUPERADO DE LAS PR�CTICAS 8-9
	 * Es un m�todo iterativo con el cual se realiza la eliminaci�n de un nodo dado por m, el cual, 
	 * a su vez, se encuentra en funci�n del primitivo entero "elemento" argumentado. 
	 * La eliminaci�n solo se realiza si el nodo argumentado existe.
	 * @param m un objeto de la clase "Nodo" que representa al nodo que se desea eliminar.
	 */
	private void Eliminar(int elemento) {
		if (this.raiz != null) {
			Nodo m = GetNodo(elemento);
			if (m != null) {	//Si el nodo existe en el �rbol
				if (this.TieneHijos() == true) {
					Nodo k = GetMin_Max(m);	//La posici�n que ceder� su clave a m
					Nodo r = k.getPadre();		
					if (k == m) {	//Si es un nodo hoja
						if (r.getIzquierdo() == m) {	//Si m es el hijo izquierdo de r
							r.setIzquierdo(null);
						} else {
							r.setDerecho(null);	//Si m es el hijo derecho de r
						}
						m = k = null;
					} else if ((Integer)r.getClave() < (Integer)k.getClave()) {	// Si se obtuvo el nodo m�s a la derecha del sub�rbol izquierdo
						r.setDerecho(k.getIzquierdo());
						m.setClave((Integer)k.getClave());
						k = null;
					} else if ((Integer)r.getClave() > (Integer)k.getClave()) {	// Si se obtuvo el nodo m�s a la izquierda del sub�rbol izquierdo
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
			System.out.println("Este �rbol no tiene nodos");
		}
	}
	
	/**
	 * RECUPERADO DE LA PR�CTICA 8-9
	 * M�todo que se encarga de retornar a un objeto de la clase Nodo con la clave argumentada si
	 * es que existe. Es equivalente a un m�todo de b�squeda, pero se define de esta manera, pues es
	 * utilizado como m�todo auxiliar en otros m�todos.
	 * @param n Un elemento entero que denota la clave que se desea eliminar del �rbol.
	 * @return Un objeto de la clase Nodo que contiene con atributo "clave" al valor entero dado por el argumento n.
	 */
	private Nodo GetNodo(int n) {
		Nodo m = GetNodoAux(this.raiz, n);
		return m;
	}
	
	/**
	 * RECUPERADO PR�CTICAS 8-9
	 * M�todo auxiliar del m�todo GetNodo. Este m�todo se encarga de buscar al nodo del �rbol que tenga como atributo clave un
	 * valor entero id�ntico a n.
	 * @param r Un objeto de la clase Nodo que se desea eval�ar.
	 * @param n Un valor entero que se desea encontrar en el Nodo r argumentado.
	 * @return Un objeto de la clase Nodo que contiene como atributo clave al valor de n; en el caso que la clave no exista en el
	 * �rbol se retorna null.
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
	 * RECUPERADO PR�CTICAS 8-9
	 * M�todo iterativo que retorna al nodo m�ximo del sub�rbol izquierdo o al nodo
	 * m�nimo del subarbol derecho, seg�n lo requiera el caso. Tambi�n se puede retornar un nodo
	 * id�ntico al argumentado si se trata de un nodo hoja.
	 * @param m 
	 * @return el nodo m�ximo del sub�rbol izquierdo, el nodo m�nimo del sub�rbol derecho, o
	 * un nodo id�ntico a m si el nodo argumentado es un nodo hoja.
	 */
	private Nodo GetMin_Max(Nodo m) { //Retorna al nodo m�s a la derecha o m�s a la izquierda seg�n el caso 
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
