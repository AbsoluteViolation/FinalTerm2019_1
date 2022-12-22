package zadanie6;

import sk.upjs.jpaz2.Turtle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class FileTurtle extends Turtle {
	public boolean isWinner(String name, String resultsFilename) {

		File f = new File(resultsFilename);

		try (Scanner sc = new Scanner(f)) {

			int pocetHlasov = 0;
			int pocetHlasovKandidata = 0;

			while (sc.hasNextLine()) {
				// uloz riadok do stringu a rozdel ho na mensie stringy
				String[] rozdelenyRiadok = sc.nextLine().split(" ");

				boolean menoKandidata = false;

				// daj vstup na male a potom aj vlastne to pole stringov porovnavaj a vsetko daj
				// na male aby sme zistili ci tam fakt je to meno
				name = name.toLowerCase();

				// zisti najprv ci je v riadku meno kandidata
				for (int i = 0; i < rozdelenyRiadok.length; i++) {

					// stringy sa porovnavaju cez equals nie cez = ak by ste nevedeli preco
					// toto robim 3 hodiny uz a boli ma dusaaaaaaaaaaa
					if (name.equals(rozdelenyRiadok[i].toLowerCase())) {
						menoKandidata = true;
					}
				}

				// najdi string v ktorom sa nachadza iba cislo
				for (int i = 0; i < rozdelenyRiadok.length; i++) {

					boolean jeTuCislo = false;

					// prechadzaj string ze ci su tam len cisla
					for (int j = 0; j < rozdelenyRiadok[i].length(); j++) {
						if (rozdelenyRiadok[i].charAt(j) <= '9' && rozdelenyRiadok[i].charAt(j) >= '0')
							jeTuCislo = true;
					}

					// ak najdem string s cislami pripocita ku hlasom
					if (jeTuCislo) {
						pocetHlasov = pocetHlasov + Integer.parseInt(rozdelenyRiadok[i]);

						// ak riadok obsahuje aj meno kandidata pridaj mu hlasy
						if (menoKandidata) {
							pocetHlasovKandidata = pocetHlasovKandidata + Integer.parseInt(rozdelenyRiadok[i]);
						}
					}
				}

			}
			// ak ma viac ako polovicu hlasov je vitaz vrat tru chapes
			if (pocetHlasov / 2 < pocetHlasovKandidata) {
				return true;
			} else {
				return false;
			}

		} catch (FileNotFoundException e) {
			System.out.println("Nenaslo subor");
			return false;
		}
	}

	public String[] vycitanka(int n, int k, String priebeh) {

		// sprav si file
		File f = new File(priebeh);

		// predVycitankou je nas vystup metody
		String[] predVycitankou = new String[n];
		// povycitanke je pole ktore dostaneme na vstupe
		String[] poVycitanke = new String[n];

		try (Scanner sc = new Scanner(f)) {

			// nacitaj mena a rozdel ich do pola
			poVycitanke = sc.nextLine().split(" ");

			// debug --> System.out.println("pred :" + Arrays.toString(poVycitanke));

			// ktore meno sa zapisuje
			int pocitadloDalsiehoMena = 0;

			// idem od zadu pretoze ak niekto vypadol prvy bol vo vycitanke v suboji so 4 a
			// ak bol 2 vypadnuty tak bol v suboji s tromi atd..
			for (int i = n; i > 0; i--) {
				//urci poradie na ktorom bude clovek
				int miesto = (k - 1) % i;
				
				//prechadzaj polom a zistuj ci je tam volne miesto
				
				for (int j = 0; j < n; j++) {
					
					//ak som prebehol vsetky miesta a je tam prazdne miesto uloz tam cloveka
					if (miesto == 0 && predVycitankou[j] == null) {
						
						//na dany index uloz prveho, druhe, tretieho atd 
						predVycitankou[j] = poVycitanke[pocitadloDalsiehoMena];
						
						//bude dalsi clovek na rade
						pocitadloDalsiehoMena++;

						// debug --> System.out.println("krok:" + Arrays.toString(predVycitankou));

						break;
					} else {
						//ak najde prazdne[] tak odcitaj 1 
						if (predVycitankou[j] == null) {
							miesto--;
						}
					}

				}
			}

			return predVycitankou;

		} catch (FileNotFoundException e) {
			System.out.println("nenaslo subor");
			return predVycitankou;
		}

	}

	public void renameVariables(String inputFilename, String outputFilename) {
		//inicializacia filov
		File input = new File(inputFilename);
		File output = new File(outputFilename);

		try {
			Scanner sc = new Scanner(input);
			
			//nacitaj prvy riadok
			String prvyRiadok = sc.nextLine();

			sc.close();
			
			//index zatvoriek
			int prvaZatvorka = 0;
			int poslednaZatvorka = 0;
			
			//najdi na akych indexoch su zatvorky 
			for (int i = 0; i < prvyRiadok.length(); i++) {
				if (prvyRiadok.charAt(i) == '(') {
					prvaZatvorka = i;
				}

				if (prvyRiadok.charAt(i) == ')') {
					poslednaZatvorka = i;
					break;
				}
			}
			
			//zober z riadka iba to co je medzi zatvorkami
			String medziZatvorkami = prvyRiadok.substring(prvaZatvorka + 1, poslednaZatvorka);
			
			//rozdel premmenne
			String[] rozdelZatvorku = medziZatvorkami.split(",");

			// zo zadania je tam 5
			String[] premenne = new String[5];
			//pole za ktore nahradi meno premennej
			String[] nahrada = { "a", "b", "c", "d", "e" };
			
			//uloz iba nazvy premennych bez datovych typov
			for (int i = 0; i < rozdelZatvorku.length; i++) {
				String[] premenna = rozdelZatvorku[i].trim().split(" ");

				// 1 pretoze chcem len nazov premennej
				premenne[i] = premenna[1];
				System.out.println(premenne[i]);
			}
			
			
			PrintWriter pw = new PrintWriter(output);
			Scanner scan = new Scanner(input);
			
			//nacitaj riadok
			while (scan.hasNextLine()) {
				String riadok = scan.nextLine();
				
				//nahrad v riadku vsetky premmenne ktore su na vstupe 
				for (int i = 0; i < premenne.length; i++) {
					
					//ak su len 2 premmenne tretie miesto v poli bude null cize to mi uz netreba prechadzat
					if (premenne[i] != null) {
						//nahrad
						riadok = riadok.replaceAll(premenne[i], nahrada[i]);

					}
					//debug --> System.out.println("replaced:" + riadok);
				}
				
				//zapis do subora riadok a pis postupne na dalsi subor
				pw.print(riadok + '\n');
			}
			
			//zavri PrintWriter a Scanner
			pw.close();
			scan.close();

		} catch (FileNotFoundException e) {
			System.out.println("nenasiel som subor");
		}
	}
}
