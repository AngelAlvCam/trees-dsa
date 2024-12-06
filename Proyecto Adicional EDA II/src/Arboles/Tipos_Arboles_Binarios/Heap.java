package Arboles.Tipos_Arboles_Binarios;

import Arboles.ArbolBinario;
import Arboles.Control_Excepciones;
import Arboles.Nodo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase representa a un heap, en una implementaci�n que mezcla al uso de nodos y arreglos.
 * @author Flutt
 *
 */
public class Heap extends ArbolBinario {
	
	/**
	 * Colecci�n de tipo "ArrayList" con elementos de la clase "Nodo", tal que esta colecci�n permite
	 * tener la administraci�n de los nodos a traves de funciones.
	 */
	ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	
	/**
	 * Valor entero que denota la cantidad de nodos en el �rbol. Aumenta con la operaci�n de adici�n
	 * y disminuye con la operaci�n de eliminaci�n de ra�z.
	 */
	int elementos;

	/**
	 * Constructor vac�o de la clase Heap. Se inicializa al nodo ra�z en null y se asigna un valor de 0
	 * al atributo entero "elementos".
	 */
	public void Heap() {
		this.raiz = null;
		elementos = 0;
	}
	
	/**
	 * Este m�todo se encarga de agregar alg�n elemento entero a la estructura de "Heap". 
	 * @param elemento Un elemento entero que se desea a�adir al heap.
	 */
	private void Agregar(int elemento) {
		/*
		 * Si la ra�z es nula significa que no hay elementos, por lo tanto, el primer nodo se
		 * asigna a la ra�z.
		 */
		if (this.raiz == null) { 
			this.raiz = new Nodo(elemento);
			nodos.add(raiz);
			elementos++;
			System.out.println("Nodo "+ elemento +" a�adido");
		} else {
			Nodo n = new Nodo(elemento);
			if (nodos.contains(n) != true) {
				nodos.add(n);
				/*
				 * temp almacena al nodo padre del �ltimo elemento a�adido. La funci�n
				 * 
				 * 		f(elementos) = (elementos-1)/2
				 * 
				 * sirve para obtener el nodo padre de un nodo en funci�n del �ndice que lo representa
				 * en el arreglo. En este caso, el �ndice del �ltimo elemento a�adido en el arreglo est�
				 * dado por "elementos" (no "elementos - 1"). 
				 * 
				 */
				Nodo temp = nodos.get((elementos-1)/2); 
				
				/*
				 * En este bloque de instrucciones se asignan las referencias necesarias, tanto de padres e hijos
				 * para conectar al nuevo nodo correctamente.
				 */
				if (temp.getIzquierdo() == null) {
					temp.setIzquierdo(n);
					n.setPadre(temp);
				} else if (temp.getDerecho() == null) {
					temp.setDerecho(n);
					n.setPadre(temp);
				}
				AgregarAux(n); // Con esta llamada se distribuyen las claves de tal manera que se cumpla con la definici�n de Heap
				elementos++;
				System.out.println("Nodo "+ elemento +" a�adido");
			} else {
				System.out.println("No se permiten nodos repetidos");
			}
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
	 * M�todo auxiliar del m�todo "Agregar". Este m�todo, en esencia, realiza el proceso recurisivo, desde el nodo a�adido hasta la ra�z,
	 * para recolocar las claves involucradas de tal manera que se cumpla con la definici�n de heap.
	 * @param n Un objeto de la clase Nodo cuya clave se desea recolocar en el lugar correcto.
	 */
	private void AgregarAux(Nodo n) {
		if (n.getPadre() != null) {
			Nodo r = n.getPadre();
			if ((Integer)n.getClave() > (Integer)r.getClave()) {
				int aux = (Integer)n.getClave();
				n.setClave((Integer)r.getClave());
				r.setClave(aux);
				AgregarAux(r);
			}
		}
	}
	
	/**
	 * Este m�todo se encarga de controlar el proceso de eliminaci�n de la ra�z. 
	 */
	private void EliminarRaiz() {
		if (this.raiz != null) {
			if (nodos.size() != 1) {
				Nodo aux = nodos.get(elementos - 1); // Se obtiene al �ltimo elemento del heap
				
				if (aux.getPadre().getIzquierdo() == aux) { // Si el �ltimo elemento es hijo izquierdo
					aux.getPadre().setIzquierdo(null);
				} else if (aux.getPadre().getDerecho() == aux) { // Si el �ltimo elemento es hijo derecho
					aux.getPadre().setDerecho(null);
				}
				
				this.raiz.setClave((Integer)aux.getClave());	// Se asigna a la ra�z la clave del �ltimo elemento
				EliminarRaizAux(this.raiz);
				nodos.remove(aux);
			} else {
				this.raiz = null;
			}
			elementos--;
			System.out.println("Ra�z eliminada");
		} else {
			System.out.println("Este heap no tiene nodos");
		}
	}
	
	/**
	 * M�todo de naturaleza recursiva que permite distribuir la clave nueva colocada en la ra�z para que
	 * se cumpla con la definici�n de heap. 
	 * @param n
	 */
	private void EliminarRaizAux(Nodo n) {
		
		if (n.getIzquierdo() != null && n.getDerecho() != null) {
			if ((Integer)n.getClave() < (Integer)n.getIzquierdo().getClave() || (Integer)n.getClave() < (Integer)n.getDerecho().getClave()) {
				if ((Integer)n.getIzquierdo().getClave() > (Integer)n.getDerecho().getClave()) {
					int temp = (Integer)n.getClave();
					int temp2 = (Integer)n.getIzquierdo().getClave();
					
					n.setClave(temp2);
					n.getIzquierdo().setClave(temp);
					
					EliminarRaizAux(n.getIzquierdo());
					
				} else if ((Integer)n.getDerecho().getClave() > (Integer)n.getIzquierdo().getClave()) {
					int temp = (Integer)n.getClave();
					int temp2 = (Integer)n.getDerecho().getClave();
					
					n.setClave(temp2);
					n.getDerecho().setClave(temp);
					
					EliminarRaizAux(n.getDerecho());
					
				}
			}
		}
	}
	
	@Override
	public void Menu() {
		System.out.println("Men� de heap");
		Heap arbol = new Heap();
		Scanner sc = new Scanner(System.in);
		boolean stat = true;
		int op, elemento;
		while (stat) {
			System.out.println("1) Agregar un nodo");
			System.out.println("2) Agregar varios nodos");
			System.out.println("3) Eliminar la ra�z");
			System.out.println("4) Imprimir estructura");
			System.out.println("5) Salir");
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
				arbol.EliminarRaiz();
				break;
			case 4:
				arbol.Print();
				break;
			case 5:
				stat = false;
				break;
			default:
				System.out.println("E: Opci�n inv�lida");
				break;
			}
		}
	}
	
}
