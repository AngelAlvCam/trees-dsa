package Arboles.Tipos_Arboles_Binarios;

import Arboles.ArbolBinario;
import Arboles.Control_Excepciones;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import Arboles.Nodo;
import java.util.Scanner;

/**
 * Clase que representa a un �rbol de expresi�n aritm�tica que opera con entes
 * binarios.
 * @author Flutt
 *
 */
public class Arbol_Expresion_Aritmetica extends ArbolBinario{
	
	public Arbol_Expresion_Aritmetica() {
		this.raiz = null;
	}
	
    /**
     * M�todo auxiliar del m�todo "postorden".
     * @param n, un nodo gen�rico que se actualiza y procesa de forma recursiva.
     */
    private void postorden(Nodo n, Queue<Character> not_postfija) {
	   if (n != null) {
		   postorden(n.getIzquierdo(), not_postfija);
		   postorden(n.getDerecho(), not_postfija);
		   not_postfija.add((char) n.getClave());
	   }
    }
    
    /**
     * M�todo que convierte a una expresi�n aritm�tica v�lida en un �rbol de expresi�n
     * aritm�tica.
     * @param expresionAritmetica Un objeto de la clase "String" que representa a una expresi�n
     * aritm�tica que se desea colocar en un �rbol de expresi�n aritm�tica.
     */
    private void Lectura(String expresionAritmetica) {
		char[] expresionAux = expresionAritmetica.toCharArray();
		this.raiz = LecturaAux(expresionAux, 0, expresionAritmetica.length()-1, '(');
	}
	
    /**
     * M�todo que permite obtener el resultado de la operaci�n aritm�tica expresada en un �rbol.
     * @return El valor entero que denota el resultado de la operaci�n arim�tica.
     */
	private int Resolver() {
		if (this.raiz != null) {
			Queue<Character> not_postfija = new LinkedList<>();
			postorden(this.raiz, not_postfija);
			Stack<Integer> pila = new Stack<Integer>();
			while (not_postfija.isEmpty() == false) {
				Character aux = not_postfija.poll();
				if (EsOperador(aux) == true) {
					int b = Integer.parseInt(String.valueOf(pila.pop()));
					int a = Integer.parseInt(String.valueOf(pila.pop()));
					int c = Operacion(a, b, aux);
					pila.push(c);
				} else {
					pila.push(Integer.parseInt(String.valueOf(aux)));
				}
			}
			System.out.println("Resultado: " + pila);
			return pila.pop();
		} else {
			System.out.println("El �rbol de expresi�n no ha sido definido");
			return 0;
		}
	}
	
	/**
	 * M�todo de caracter booleano que permite reconocer si un caracter es un operador aritm�tico o no.
	 * @param c Un caracter que se desea evaluar para saber si es operador aritm�tico.
	 * @return Un valor booleano de true si el caracter argumentado es un operador; false en caso contrario.
	 */
	private boolean EsOperador(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * M�todo con tipo de retorno entero que realiza una operaci�n binaria en funci�n del operador argumentado.
	 * @param a Un valor entero que es el operando izquierdo.
	 * @param b Un valor entero que es el operando derecho.
	 * @param operador Un caracter que denota a un operador binario.
	 * @return El resultado entero de la operaci�n dada por a (operador) b
	 */
	private int Operacion(int a, int b, char operador) {
		if (operador == '+') {
			return a + b;
		} else if (operador == '-') {
			return a - b;
		} else if (operador == '*') {
			return a * b;
		} else { // if (operador == '/') 
			return a / b;
		}
	}
	
	/**
	 * M�todo de naturaleza recursiva que permite obtener una referencia de Nodo a un �rbol de expresi�n
	 * aritm�tica en funci�n de una expresi�n dada en forma de arreglo de caracteres. 
	 * @param expresion Un arreglo de caracteres que denota a una expresi�n aritm�tica.
	 * @param min El �ndice m�nimo del arreglo de expresi�n que se tratar� en una iteraci�n.
	 * @param max El �ndice m�ximo del arreglo de expresi�n que se tratar� en una iteraci�n.
	 * @param parentesis Un caracter que denota el parentesis de apertura.
	 * @return Una referencia de tipo Nodo a un �rbol de expresi�n aritm�tica.
	 */
	private Nodo LecturaAux(char[] expresion, int min, int max, char parentesis) {
		if (N_Parentesis(expresion, min, max) > 2){
			int index = IndexAux(expresion, min, max, parentesis);
			Nodo nuevo = new Nodo(expresion[index]);
			nuevo.setIzquierdo(LecturaAux(expresion, min+1, index-1, parentesis));
			nuevo.setDerecho(LecturaAux(expresion, index+1, max-1, parentesis));
			return nuevo;
		} else {
			int medio = (min + max) / 2;
			Nodo nuevo = new Nodo(expresion[medio]);
			if (min < max)
				nuevo.setIzquierdo(new Nodo(expresion[medio-1]));
				nuevo.setDerecho(new Nodo(expresion[medio+1]));
			return nuevo;
		}
	}
	
	/**
	 * M�todo que permite conocer la cantidad total de par�ntesis de apertura y cerradura de una
	 * un arreglo de caracteres de expresi�n en un rango delimitado por dos �ndices.
	 * @param expresion El arreglo de caracteres que denota a una expresi�n aritm�tica.
	 * @param min Un valor entero que denota el �ndice m�nimo en la revisi�n.
	 * @param max Un valor entero que denota el �ndice m�ximo en la revisi�n.
	 * @return Un valor entero que representa a la cantidad total de parentesis en una subexpresi�n.
	 */
	private int N_Parentesis(char[] expresion, int min, int max) {
		int temp = min;
		int ocurrencia = 0;
		while (temp < max) {
			if (expresion[temp] == '(' || expresion[temp] == ')') {
				ocurrencia++;
			}
			temp++;
		}
		return ocurrencia;
	}
	
	/**
	 * M�todo que permite conocer el parentesis inverso al argumentado. Sirve para conocer el momento
	 * en que comienza otra subexpresi�n.
	 * @param p Un caracter de par�ntesis de apertura o cerradura
	 * @return El caracter inverso al caracter argumentado.
	 */
	private char ParentesisInverso(char p) {
		if (p == '(') {
			return ')';
		} else {
			return '(';
		}
	}
	
	/**
	 * M�todo que permite conocer el �ndice de un elemento de la expresi�n que sera
	 * a�adido como nodo padre.
	 * @param expresion Un arreglo de caracteres que denota a una expresi�n aritm�tica.
	 * @param min El �ndice m�nimo de la subexpresi�n que se est� evaluando.
	 * @param max El �ndice m�ximo de la subexpresi�n que se est� evaluando.
	 * @param parentesis Un caracter que representa al parentesis de �pertura.
	 * @return
	 */
	private int IndexAux(char[] expresion, int min, int max, char parentesis) {
		int temp = min;
		while (expresion[temp] != ParentesisInverso(parentesis)) {
			temp++;
		}
		temp++;
		return temp; // Indice del operador
	}
	
	@Override
	public void Menu() {
		System.out.println("Men� de heap");
		Arbol_Expresion_Aritmetica arbol = new Arbol_Expresion_Aritmetica();
		Scanner sc = new Scanner(System.in);
		boolean stat = true;
		int op;
		String expresion;
		while (stat) {
			System.out.println("1) Ingresar expresion");
			System.out.println("2) Resolver expresion");
			System.out.println("3) Imprimir estructura");
			System.out.println("4) Salir");
			System.out.print("OP: ");
			op = Control_Excepciones.IntInput();
			switch(op) {
			case 1:
				System.out.println("Ingrese la expresi�n arim�tica:");
				System.out.print("EXPRESION: ");
				expresion = sc.nextLine();
				arbol.Lectura(expresion);
				break;
			case 2:
				arbol.Resolver();
				break;
			case 3:
				arbol.Print();
				break;
			case 4:
				stat = false;
				break;
			default:
				System.out.println("E: Opci�n inv�lida");
				break;
			}
		}
	}
	
	private void PrintAux(Nodo n) {
    	if (this.raiz == null) {
    		System.out.println("Estructura vac�a");
    	} else if (n.getIzquierdo() != null && n.getDerecho() != null) {
    		System.out.printf("Padre: %c, I: %c, D: %c\n", n.getClave(), n.getIzquierdo().getClave(), n.getDerecho().getClave());
    		PrintAux(n.getIzquierdo());
    		PrintAux(n.getDerecho());
    	}  else if (n.getIzquierdo() != null){
    		System.out.printf("Padre: %c, I: %c\n", n.getClave(), n.getIzquierdo().getClave());
    		PrintAux(n.getIzquierdo());
    	} else if (n.getDerecho() != null) {
    		System.out.printf("Padre: %c, D: %c\n", n.getClave(), n.getDerecho().getClave());
    		PrintAux(n.getDerecho());
    	} else if (n == raiz) {
    		System.out.printf("Padre: %c \n", n.getClave());
    	}
    }
    
	@Override
    public void Print() {
    	PrintAux(this.raiz);
    }

}
