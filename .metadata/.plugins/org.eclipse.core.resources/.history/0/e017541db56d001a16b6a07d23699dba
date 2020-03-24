package afleveringfravaer;

public class FravaersSystem {
	/**
	 * Fravaerstallene udskrives på skærmen
	 *
	 * @param fravaer
	 */
	public void udskrivFravaer(int[][] fravaer) {
		System.out.printf("Elev/Måned: %5s", "1     2     3     4     5     6     7     8     9     10    11    12");
		System.out.println();
		for (int i = 0; i < fravaer.length; i++) {
			System.out.printf("%1d", i);
			System.out.print("      ");
			for (int j = 0; j < fravaer[i].length; j++) {
				System.out.printf("%6d", fravaer[i][j]);

			}
			System.out.printf("\n");

		}
	}

	/**
	 * Returnerer det samlede antal fravaersdage over 12 måneder for eleven med
	 * elevnr.
	 *
	 * @param fravaer
	 * @param elevNr
	 * @return
	 */
	public int samletFravaer(int[][] fravaer, int elevNr) {
		int sum = 0;
		for (int i = 0; i < fravaer[elevNr].length; i++) {

			sum += fravaer[elevNr][i];
		}
		return sum;
	}

	/**
	 * Returnerer det gennemsnitlige antal fraværsdage pr måned for eleven med
	 * elevNr.
	 *
	 * @param fravaer
	 * @param elevNr
	 * @return
	 */
	public double gennemsnit(int[][] fravaer, int elevNr) {
		double sum = 0;
		double gennemsnit;
		for (int i = 0; i < fravaer[elevNr].length; i++) {

			sum += fravaer[elevNr][i];

		}
		gennemsnit = sum / 12;
		return gennemsnit;
	}

	/**
	 * Returnerer antallet af elever, der ikke har haft fravær i de 12 måneder.
	 *
	 * @param fravaer
	 * @return
	 */
	public int antalUdenFravaer(int[][] fravaer) {
		int elevNr = 0;
		int x = 0;
		for (int i = 0; i < fravaer.length; i++) {
			x = 0;
			for (int j = 0; j < fravaer[i].length; j++) {
				if (fravaer[i][j] == 0) {
					x++;
					if (x == 12) {
						elevNr++;
					}
				}
			}
		}

		return elevNr;
	}

	/**
	 * Returnerer elevNr for den elev der har haft mest fravær igennem de 12
	 * måneder. Hvis flere elever har haft højst fravær, returneres elevnummer for
	 * en af disse.
	 *
	 * @param fravaer
	 * @return
	 */
	public int mestFravaer(int[][] fravaer) {
		int elevNr = 0;
		int max = 0;
		int sum = 0;
		for (int i = 0; i < fravaer.length; i++) {
			sum = 0;
			for (int j = 0; j < fravaer[i].length; j++) {
				sum += fravaer[i][j];
				if (sum > max) {
					max = sum;
					elevNr = i;
				}
			}
		}
		return elevNr;
	}

	/**
	 * Registrerer at elenven med elevNr ikke har haft fravær i nogen af de 12
	 * måneder.
	 *
	 * @param fravaer
	 * @param elevNr
	 */
	public void nulstil(int[][] fravaer, int elevNr) {
		for (int i = 0; i < fravaer[elevNr].length; i++) {
			fravaer[elevNr][i]= 0;
		}
	}
}
