package zadanie7;

public class FrequencyTable {

	// ??? Konstruktor + clear + getWordCount: 2 body
	private String[] slova;
	private int[] pocet;

	/**
	 * Konstruktor: vytvori prazdnu frekvencnu tabulku
	 */
	public FrequencyTable() {
		slova = new String[0];
		pocet = new int[0];
	}

	/**
	 * Vyprazdni obsah tabulky (vratene vlozenych slov)
	 */
	public void clear() {
		String[] prazdneSlova = new String[0];
		int[] prazdnyPocet = new int[0];

		slova = prazdneSlova;
		pocet = prazdnyPocet;
	}

	/**
	 * Vrati pocet vyskytov slova. V pripade, ze sa slovo v tabulke nenachadza,
	 * vrati 0
	 *
	 * @param word retazec, ktoreho pocet vyskytov chceme zistit
	 * @return pocet vyskytov zadaneho slova (retazca)
	 */
	public int getNumberOfOccurrences(String word) {

		// ak je tam slovo vrat pocet vyskytov
		for (int i = 0; i < slova.length; i++) {
			if (slova[i].equals(word))
				return pocet[i];
		}

		// ak tam neni take slovo vrat 0
		return 0;
	}

	/**
	 * Poznaci novy vyskyt slova vo frekvencnej tabulke (zvysi pocitadlo priradene
	 * danemu slovu o 1). Ak take slovo sa v tabulke nenachadza, nastavi pocet jeho
	 * vyskytov na 1
	 *
	 * @param word slovo, ktoreho vyskyt chceme poznacit vo frekvencnej tabulke
	 */
	public void addOccurrence(String word) {

		boolean uzTamSlovoJe = false;
		for (int i = 0; i < slova.length; i++) {

			// ak tam slovo je zvysi jeho pocetVyskytov
			if (slova[i].equals(word)) {
				uzTamSlovoJe = true;
				pocet[i]++;
			}
		}

		// ak tam neni tak ho prida a nastavi pocet vyskytov na 1
		if (!uzTamSlovoJe) {
			String[] noveSlova = new String[slova.length + 1];
			System.arraycopy(slova, 0, noveSlova, 0, slova.length);
			noveSlova[noveSlova.length - 1] = word;

			int[] novyPocet = new int[pocet.length + 1];
			System.arraycopy(pocet, 0, novyPocet, 0, pocet.length);
			novyPocet[novyPocet.length - 1] = 1;

			this.slova = noveSlova;
			this.pocet = novyPocet;
		}

	}

	/**
	 * Vrati pocet slov vo frekvencnej tabulke (vsetky maju nenulovy pocet vyskytov)
	 * 
	 * @return pocet slov vo frekvencnej tabulke
	 */
	public int getWordCount() {
		return slova.length;
	}

	/**
	 * Vrati slova vo frekvencnej tabulke v novovytvorenom poli retazcov
	 * 
	 * @return referencia na novovytvorene pole retazcov so slovami v tabulke
	 */
	public String[] getWords() {
		return slova;
	}

	/**
	 * Vrati obsah frekvencnej tabulky ako retazec vo formate
	 * [slovo1=pocetVyskytov1, slovo2=pocetVyskytov2]
	 */
	public String toString() {

		// staci nam len slova pretoze slova a pocet maju rovnaku dlzku
		if (slova.length == 0) {
			return "[]";
		}

		String vypis = new String();

		vypis = vypis + "[";

		// aby nebola ciarka na konci
		for (int i = 0; i < slova.length - 1; i++) {
			vypis = vypis + slova[i] + "=" + pocet[i] + ", ";
		}

		vypis = vypis + slova[slova.length - 1] + "=" + pocet[pocet.length - 1];
		vypis = vypis + "]";

		return vypis;
	}
}