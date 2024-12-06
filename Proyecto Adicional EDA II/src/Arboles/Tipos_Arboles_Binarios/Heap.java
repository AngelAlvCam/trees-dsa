package Arboles.Tipos_Arboles_Binarios;

import Arboles.ArbolBinario;
import Arboles.Control_Excepciones;
import Arboles.Nodo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase representa a un heap, en una implementación que mezcla al uso de nodos y arreglos.
 * @author Flutt
 *
 */
public class Heap extends ArbolBinario {
	
	/**
	 * Colección de tipo "ArrayList" con elementos de la clase "Nodo", tal que esta colección permite
	 * tener la administración de los nodos a traves de funciones.
	 */
	ArrayList<Nodo> nodos = new ArrayList<Nodo>();
	
	/**
	 * Valor entero que denota la cantidad de nodos en el árbol. Aumenta con la operación de adición
	 * y disminuye con la operación de eliminación de raíz.
	 */
	int elementos;

	/**
	 * Constructor vacío de la clase Heap. Se inicializa al nodo raíz en null y se asigna un valor de 0
	 * al atributo entero "elementos".
	 */
	public void Heap() {
		this.raiz = null;
		elementos = 0;
	}
	
	/**
	 * Este método se encarga de agregar algún elemento entero a la estructura de "Heap". 
	 * @param elemento Un elemento entero que se desea añadir al heap.
	 */
	private void Agregar(int elemento) {
		/*
		 * Si la raíz es nula significa que no hay elementos, por lo tanto, el primer nodo se
		 * asigna a la raíz.
		 */
		if (this.raiz == null) { 
			this.raiz = new Nodo(elemento);
			nodos.add(raiz);
			elementos++;
			System.out.println("Nodo "+ elemento +" añadido");
		} else {
			Nodo n = new Nodo(elemento);
			if (nodos.contains(n) != true) {
				nodos.add(n);
				/*
				 * temp almacena al nodo padre del último elemento añadido. La función
				 * 
				 * 		f(elementos) = (elementos-1)/2
				 * 
				 * sirve para obtener el nodo padre de un nodo en función del índice que lo representa
				 * en el arreglo. En este caso, el índice del último elemento añadido en el arreglo está
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
				AgregarAux(n); // Con esta llamada se distribuyen las claves de tal manera que se cumpla con la definición de Heap
				elementos++;
				System.out.println("Nodo "+ elemento +" añadido");
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
	 * Método auxiliar del método "Agregar". Este método, en esencia, realiza el proceso recurisivo, desde el nodo añadido hasta la raíz,
	 * para recolocar las claves involucradas de tal manera que se cumpla con la definición de heap.
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
	 * Este método se encarga de controlar el proceso de eliminación de la raíz. 
	 */
	private void EliminarRaiz() {
		if (this.raiz != null) {
			if (nodos.size() != 1) {
				Nodo aux = nodos.get(elementos - 1); // Se obtiene al último elemento del heap
				
				if (aux.getPadre().getIzquierdo() == aux) { // Si el último elemento es hijo izquierdo
					aux.getPadre().setIzquierdo(null);
				} else if (aux.getPadre().getDerecho() == aux) { // Si el último elemento es hijo derecho
					aux.getPadre().setDerecho(null);
				}
				
				this.raiz.setClave((Integer)aux.getClave());	// Se asigna a la raíz la clave del último elemento
				EliminarRaizAux(this.raiz);
				nodos.remove(aux);
			} else {
				this.raiz = null;
			}
			elementos--;
			System.out.println("Raíz eliminada");
		} else {
			System.out.println("Este heap no tiene nodos");
		}
	}
	
	/**
	 * Método de naturaleza recursiva que permite distribuir la clave nueva colocada en la raíz para que
	 * se cumpla con la definición de heap. 
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
		System.out.println("Menú de heap");
		Heap arbol = new Heap();
		Scanner sc = new Scanner(System.in);
		boolean stat = true;
		int op, elemento;
		while (stat) {
			System.out.println("1) Agregar un nodo");
			System.out.println("2) Agregar varios nodos");
			System.out.println("3) Eliminar la raíz");
			System.out.println("4) Imprimir estructura");
			System.out.println("5) Salir");
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
				arbol.EliminarRaiz();
				break;
			case 4:
				arbol.Print();
				break;
			case 5:
				stat = false;
				break;
			default:
				System.out.println("E: Opción inválida");
				break;
			}
		}
	}
	
}
