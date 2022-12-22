package zadanie7;

import java.util.Arrays;

/**
 * Trieda realizujuca dynamicke pole (zoznam) cisel.
 */
public class NumberList {
	// Hodnotenie: konstruktory a metody bez uvedeneho hodnotania celkom 3 body

	private int[] numberList;

	/**
	 * Konstruktor zoznamu cisel - vytvori prazdny zoznam
	 */
	public NumberList() {
		numberList = new int[0];
	}

	/**
	 * Konstruktor zoznamu cisel, pricom zoznam sa inicializuje hodnotami PODLA pola
	 * 
	 * @param numbers pole, podla ktoreho sa ma inicializovat zoznam cisel
	 */
	public NumberList(int[] numbers) {
		numberList = new int[numbers.length];

		System.arraycopy(numbers, 0, numberList, 0, numbers.length);
	}

	/**
	 * Konstruktor zoznamu cisel, pricom zoznam sa inicializuje hodnotami PODLA
	 * ineho zoznamu
	 * 
	 * @param numberList zoznam, podla ktoreho sa ma inicializovat zoznam cisel
	 */
	public NumberList(NumberList numberList) {
		this.numberList = new int[numberList.size()];

		for (int i = 0; i < this.numberList.length; i++) {
			this.numberList[i] = numberList.get(i);
		}
	}

	/**
	 * Vrati cislo aktualne ulozene na zadanom indexe v zozname
	 * 
	 * @param index index prvku zoznamu, ktoreho hodnotu chceme vratit
	 * @return cislo na zadanom indexe v zozname
	 */
	public int get(int index) {
		return this.numberList[index];
	}

	/**
	 * Nastavi hodnotu prvku zoznamu na zadanom (uz existujucom) indexe
	 * 
	 * @param index index prvku
	 * @param value nova hodnota pre prvom na zadanom indexe
	 */
	public void set(int index, int value) {
		this.numberList[index] = value;
	}

	/**
	 * Prida na koniec zoznamu novy prvok so zadanou hodnotou
	 * 
	 * @param value hodnota prvku pridaneho na koniec zoznamu
	 */
	public void add(int value) {
		// pomocne pole nove cisla dam mu dlzky o 1 vacsiu
		int[] noveCisla = new int[this.numberList.length + 1];

		// vsetko skopirujem
		System.arraycopy(numberList, 0, noveCisla, 0, numberList.length);

		// nakoniec pridam novu hodnotu
		noveCisla[numberList.length] = value;

		this.numberList = noveCisla;
	}

	/**
	 * Vrati aktualny pocet prvkov v zozname cisel
	 * 
	 * @return pocet prvkov v zozname cisel
	 */
	public int size() {
		return this.numberList.length;
	}

	/**
	 * Vyprazdni zoznam
	 */
	public void clear() {
		int[] prazdny = new int[0];

		this.numberList = prazdny;
	}

	/**
	 * Odstrani zo zoznamu prvok na zadanom indexe a vrati hodnotu odstraneneho
	 * prvku
	 * 
	 * @param index index odstranovaneho prvku v zozname
	 * @return hodnota odstranovaneho prvku v zozname
	 */
	public int remove(int index) {
		// ulozim si prvok aby som ho mohol nakonci returnut
		int odsranenyPrvok = this.numberList[index];

		// spravim si pole o jedno mensie
		int[] reduced = new int[this.numberList.length - 1];

		// kopirujem vsetko pred vymazanym prvkom
		System.arraycopy(numberList, 0, reduced, 0, index);

		// kopirujem vsetko za vymazanym prvkom
		System.arraycopy(numberList, index + 1, reduced, index, reduced.length - index);

		this.numberList = reduced;

		return odsranenyPrvok;
	}

	/**
	 * Vrati obsah zoznamu vo forme formatovaneho retazca [prvok1, prvok2, prvok3,
	 * prvok4]
	 */
	@Override
	public String toString() {
		return Arrays.toString(numberList);
	}

}