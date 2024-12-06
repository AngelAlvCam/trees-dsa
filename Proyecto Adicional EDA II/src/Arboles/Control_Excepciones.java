package Arboles;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Control_Excepciones {
	public static int IntInput() {
		try {
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			return input;
		} catch (InputMismatchException e) {
			System.out.println("E: Seleccione con valores enteros");
			return IntInput();
		}
	}
}
