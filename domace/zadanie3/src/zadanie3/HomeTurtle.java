package zadanie3;

import java.awt.Color;

import sk.upjs.jpaz2.Turtle;

public class HomeTurtle extends Turtle {
	public void squardot(double size) {
		double priemer = size;

		while (priemer > 1) {
			// vypocet strany stvorca v kruznici
			double stranaStvorca = priemer * 2 / Math.sqrt(2);

			this.setFillColor(Color.lightGray);
			this.dot(priemer);

			this.setFillColor(Color.gray);
			// zapamataj suradnice
			double surX = this.getX();
			double surY = this.getY();
			double direction = this.getDirection();

			this.openPolygon();
			this.penUp();

			this.turn(-45);
			// posun sa o uhlopriecku
			this.step(priemer);
			this.turn(135);

			for (int i = 0; i < 4; i++) {
				this.step(stranaStvorca);
				this.turn(90);
			}

			this.penDown();
			this.closePolygon();

			// vrat sa na suradnice
			this.setPosition(surX, surY);
			this.setDirection(direction);

			// priemer vpisanej kruznice do stvorca
			priemer = stranaStvorca / 2;
		}
	}

	/**
	 * nekonecny rad , vrati
	 * 
	 * @param c - realne cislo
	 * @return
	 */
	public int divergence(double c) {
		// pomocna premmenna i sluzi na danu postupnost
		int i = 1;
		double sucet = 0;
		
		
		while (true) {
			// ak je delitelne 2 sprav vzorcek
			if (i % 2 == 0) {
				sucet = sucet + (1.0 / i);
			}
			// ak nie je delitelne 2 sprav vzorcek
 			if (i % 2 == 1) {
				sucet = sucet + ((i - 1.0) / i);
			}

			// ak je sucet vacsi ako param c return sucet
			if (sucet >= c) {
				return i;
			}
			// i = i + 1
			i++;
		}
	}

	public boolean isUniDigitNumber(int n) {
		// ak je cislo zaporne prehodim si ho do absolutnej hodnoty
		int cislo = Math.abs(n);
		int zvysok = cislo % 10;

		// ak ma cislo iba jednu cifru
		if (cislo / 10 == 0)
			return true;

		while (cislo > 0) {
			cislo = cislo / 10;

			// ak prebehne vsetky cislice a z int sa stane 0 vieme povedat ze cislo je
			// unidigitnumber --true
			if (cislo == 0)
				return true;

			// ak je nahodou na inom mieste v cisle ina cislica vrat false
			if (cislo % 10 != zvysok)
				return false;
		}

		// konecna podmienka --eclipse hlasil chybu, napravil som, aj ked sa metoda do
		// tohto kroku nikdy nedostane hihi
		return false;
	}

	public int countHammingDistance(int number1, int number2) {
		// v pripade zaporneho cisla zmen na cele
		int cislo1 = Math.abs(number1);
		int cislo2 = Math.abs(number2);
		int pocetCifier = 0;

		// opakuj dokym obidve cisla nebudu 0
		while (cislo1 > 0 || cislo2 > 0) {

			// ak sa zvysok po deleni 10 oboch cisel nerovna tak pocetCifier++;
			if (cislo1 % 10 != cislo2 % 10) {
				pocetCifier++;
			}

			cislo1 = cislo1 / 10;
			cislo2 = cislo2 / 10;
		}

		return pocetCifier;
	}
}
