package midtermskuska2022;

import sk.upjs.jpaz2.Turtle;

public class Midtemarka extends Turtle {
	public int countOccurencesOfMaxDigit(int n) {

		int nCopy = n;
		int najvacsiaCislica = 0;
		int pocitadlo = 0;

		while (nCopy != 0) {
			if (nCopy % 10 > najvacsiaCislica)
				najvacsiaCislica = nCopy % 10;

			nCopy = nCopy / 10;
		}

		nCopy = n;

		while (nCopy != 0) {
			if (najvacsiaCislica == nCopy % 10)
				pocitadlo++;

			nCopy = nCopy / 10;
		}

		return pocitadlo;
	}

	public String odCislovac(String s, String nahrada) {
		String vycisteny = new String();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {

				vycisteny = vycisteny + nahrada.charAt(i);

			} else {
				vycisteny = vycisteny + s.charAt(i);
			}
		}

		return vycisteny;
	}
}
