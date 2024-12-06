package Principal;

import Arboles.ArbolBinario;
import Arboles.Control_Excepciones;
import Arboles.Tipos_Arboles_Binarios.*;

public class Principal {
	public static void main(String args[]) {
		boolean stat = true;
		int op;
		ArbolBinario[] tipos = {new Arbol_AVL(), new Heap(), new Arbol_Expresion_Aritmetica()};
		while (stat) {
			System.out.println("1) Menu Arbol_AVL");
			System.out.println("2) Menu Heap");
			System.out.println("3) Menu Arbol de expresión aritmética");
			System.out.println("4) Salir");
			System.out.print("OP: ");
			op = Control_Excepciones.IntInput();
			switch(op) {
			case 1:
				tipos[op - 1].Menu();
				break;
			case 2:
				tipos[op - 1].Menu();
				break;
			case 3:
				tipos[op - 1].Menu();
				break;
			case 4:
				stat = false;
				break;
			default:
				System.out.println("E: Opción inválida");
				break;
			}
		}
	}
}
