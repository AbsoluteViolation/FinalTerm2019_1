package zadanie4;

import sk.upjs.jpaz2.Turtle;

public class HomeTurtle extends Turtle {

	/**
	 * metoda pre vytvorenie emailovej adresy
	 * 
	 * @param name - string s ulozenym Menom
	 * @return upraveny string
	 */
	public String toEmailAddress(String name) {
		String upraveneMeno = name;

		// Vsetky velke pismena prehod na male
		upraveneMeno = upraveneMeno.toLowerCase();

		// Nahrad medzeru bodkou
		upraveneMeno = upraveneMeno.replace(" ", ".");

		// pridaj na koniec @upjs.sk
		upraveneMeno = upraveneMeno.concat("@upjs.sk");

		return upraveneMeno;

	}

	/**
	 * metoda pocita skratky ktore su zlozene z minimalne 3 za sebou iducich velkych
	 * literalov
	 * 
	 * @param r - string nenulovej dlzky obsahujuci acronyms
	 * @return pocet skratiek
	 */
	public int countAcronyms(String r) {

		// pridaj na koniec retazca medzeru ak by nahodou retazec koncil skratkou
		r = r.concat(" ");

		// pomocne premenne
		int pocitajSkratky = 0;
		int pocitajPismenka = 0;

		for (int i = 0; i < r.length(); i++) {

			// Ak najdes velke pismenko v stringu prirad 1 do pomocnej premmennej
			// pocitajPismenka
			if (r.charAt(i) >= 'A' && r.charAt(i) <= 'Z') {
				pocitajPismenka++;
			} else {
				// ak je viac pismenok ako 3 alebo 3 pismenka velke pocitaj to ako Skratku
				if (pocitajPismenka >= 3) {
					pocitajSkratky++;
				}

				// nastav pomocnu premmennu na 0
				pocitajPismenka = 0;
			}
		}

		return pocitajSkratky;
	}

	/**
	 * nahradi duplicitu iba jednym znakom
	 * 
	 * @param s - zadany string
	 * @return -upraveny string
	 */
	public String sanitize(String s) {
		// pridaj na koniec retazca medzeru
		s = s.concat(" ");

		StringBuilder sbZmenseny = new StringBuilder();

		for (int i = 0; i < s.length() - 1; i++) {

			// ak sa slova rovnaju pokracuj v cykle
			if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) != ' ') {
				continue;
			}

			// pridaj znak do string builderu
			sbZmenseny.append(s.charAt(i));
		}

		return sbZmenseny.toString();
	}

	/**
	 * prevod char na prisluchajucu znakovu identitu okrem '0' - '9'; 'a' - 'z'; 'A'
	 * - 'Z';
	 * 
	 * @param s vstupny string
	 * @return string s znakovymi identitami
	 */
	public String charsToEntities(String s) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			// aj je znak cislo
			boolean znakJeCislo = s.charAt(i) >= '0' && s.charAt(i) <= '9';
			// znak je medzi 'a' - 'z'
			boolean znakJeMaleAazZ = s.charAt(i) >= 'a' && s.charAt(i) <= 'z';
			// znak je medzi 'A' - 'Z'
			boolean znakJeVelkeAazZ = s.charAt(i) >= 'A' && s.charAt(i) <= 'Z';

			// ak znak nie je cislo a nie je male pismeno a nie je velke pismeno
			if (!znakJeCislo && !znakJeMaleAazZ && !znakJeVelkeAazZ) {
				int znak = s.charAt(i);

				sb.append("&#");
				sb.append(znak);
				sb.append(";");
			} else {
				sb.append(s.charAt(i));
			}
		}

		return sb.toString();
	}

	/**
	 * prehod znakovu identitu na char
	 * 
	 * @param s vstupny retazec so znakovou identitou
	 * @return string bez znakovej identity
	 */
	public String entitiesToChars(String s) {
		StringBuilder sb = new StringBuilder();
		String cislo = new String();

		for (int i = 0; i < s.length(); i++) {
			boolean znakAmpersant = s.charAt(i) == '&';

			// ak je na s.charAt(i) ampersant znamena to ze zacina znakova identita
			if (znakAmpersant) {

				// premenna i dostane rovnaku hodnotu ako index
				int j = i;
				// vypocet na ktorom mieste bude;
				int substring = 0;

				// opakuj az dokym nenajdes ;
				while (s.charAt(j) != ';') {
					j++;
					substring++;
				}

				/*
				 * Kedze vieme ze cislo prevodom do ASCII moze byt bud 2 mieste alebo 3 miestne
				 * je nutne zistit ake dlhe je v nasej znakovej identite Preto som pouzil
				 * premmenu substring aby som nasiel dlzku znakovej identity
				 */

				// aby som nemal v substringu &# a na konci ;
				// ak je cislo &#80; vrati iba 80
				// ak je cislo &#269; vrati iba 269
				cislo = s.substring(i + 2, i + substring);

				// substring cislo prehod na integer
				int c = Integer.parseInt(cislo);

				// pridaj integer ako char do stringbuildera sb
				sb.append((char) c);

				// aby cyklus pokracoval od ; teda za znakovou identitou
				i = i + substring;
			} else {
				sb.append(s.charAt(i));
			}
		}

		return sb.toString();
	}

	public String bolduj(String r) {
		String novyR = "";
		String odHviezdy = "";

		boolean jeHviezdicka = false;
		
		//prechadzaj kazdym pismenkom pola
		for (int i = 0; i < r.length(); i++) {
			
			//ak najdes * niekde v retazci 
			if(r.charAt(i) == '*') {
				
				//aby to nezobralo tu hviezdicku
				i++;
				
				//nastav jeHviezdicka = true, teda vsetko sa zmeni na velke
				jeHviezdicka = true;
				
				while(jeHviezdicka) {
					odHviezdy = odHviezdy + r.charAt(i);
					
					//dalsie miesto v stringu
					i++;
					
					//ak dalsie miesto v stringu je hviezdicka zmen odHviezdy na velke pismena
					if(r.charAt(i) == '*') {
						odHviezdy = odHviezdy.toUpperCase();
						
						//nastav je hviezdicka = false aby uz nesiel while
						jeHviezdicka = false;
					}
				}
				
				//pripoj odHviezdy k novyR
				novyR = novyR + odHviezdy;
			}else {
				//ak nenaslo hviezdicku len pridaj char do retazca tak ako je
				novyR = novyR + r.charAt(i);
			}
		}
		
		//vrat cely string
		return novyR;
	}
}
