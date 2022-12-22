package zadanie5;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import sk.upjs.jpaz2.*;

public class KorytnaciSvet extends WinPane {
	/**
	 * Referencia na pole korytnaciek
	 */
	private Turtle[] korytnacky = null;

	/**
	 * Inicializacna metoda (konstruktor)
	 */
	public KorytnaciSvet() {
		this.korytnacky = new Turtle[0];
	}

	/**
	 * Metoda na pridanie korytnacky na zadanych suradniciach
	 */
	public void pridajKorytnacku(int x, int y) {
		Turtle novaKorytnacka = new Turtle();
		this.add(novaKorytnacka);
		novaKorytnacka.setPosition(x, y);

		Turtle[] noveKorytnacky = new Turtle[this.korytnacky.length + 1];
		System.arraycopy(this.korytnacky, 0, noveKorytnacky, 0, this.korytnacky.length);
		noveKorytnacky[noveKorytnacky.length - 1] = novaKorytnacka;

		this.korytnacky = noveKorytnacky;
	}

	@Override
	protected void onMouseClicked(int x, int y, MouseEvent detail) {
		if (!(detail.isAltDown() || detail.isControlDown() || detail.isShiftDown())) {
			this.pridajKorytnacku(x, y);
		}
	}

	/**
	 * Nakresli ciary ku tazisku
	 */
	public void vystrelNaTazisko() {
		// suradnice taziska
		double taziskoX = 0;
		double taziskoY = 0;

		// pomocna premenna sucet pre vypocet suctu X suradnice kazdej korytnacky
		double sucetX = 0;
		double sucetY = 0;

		// sucet x y suradnic kazdej korytnacky
		for (int i = 0; i < korytnacky.length; i++) {
			sucetX = sucetX + korytnacky[i].getX();
			sucetY = sucetY + korytnacky[i].getY();
		}

		// vypocet taziska -> priemer
		taziskoX = sucetX / korytnacky.length;
		taziskoY = sucetY / korytnacky.length;

		// nakresli ciaru a vrat ju na miesto
		for (int i = 0; i < korytnacky.length; i++) {
			double startX = korytnacky[i].getX();
			double startY = korytnacky[i].getY();

			korytnacky[i].moveTo(taziskoX, taziskoY);
			korytnacky[i].setPosition(startX, startY);
			korytnacky[i].setDirection(0);
		}
	}

	/**
	 * 
	 * @param x    suradnica x bomby
	 * @param y    suradnica y bomby
	 * @param sila vybuchu
	 * 
	 * @return ako daleko sa posunula korytnacka ktora bola najblizsie pri bombe
	 *         hihi
	 */
	public double explozia(double x, double y, double sila) {

		// ak sa nahodou sila bude rovnat 0
		if (sila == 0)
			return 0;

		double najvacsiPosun = 0;

		for (int i = 0; i < korytnacky.length; i++) {
			double vzdialenostOdBomby = korytnacky[i].distanceTo(x, y);

			// nastav korytnacku do smeru bomby
			korytnacky[i].setDirection(korytnacky[i].directionTowards(x, y));
			// otoc korytnacku od bomby
			korytnacky[i].turn(180);

			// vypocet posunu od bomby
			double posun = Math.pow(sila, 2) / Math.pow(vzdialenostOdBomby, 4);

			korytnacky[i].penUp();
			korytnacky[i].step(posun);
			korytnacky[i].penDown();

			// ak je posun vacsi ako niektory z predchadzajucich uloz posun
			if (najvacsiPosun < posun)
				najvacsiPosun = posun;

		}

		return najvacsiPosun;
	}

	/**
	 * Neviem ci ma korytnacka aj kreslit alebo len pocitat tak moje korytnacky
	 * prezmenu kreslia
	 * 
	 * @param x suradnica k bodu
	 * @param y suradnica k bodu
	 * @return cas najrychlejsiej korytnacky k bodu
	 */
	public double casDoPrichodu(double x, double y) {
		double najmensiCasPrichodu = 0;

		for (int i = 0; i < korytnacky.length; i++) {

			double sekundy = 0;

			// uhol natocenia korytnacky
			double uholNatocenia = korytnacky[i].getDirection();

			// uhol k bodu c
			double uholKBodu = korytnacky[i].directionTowards(x, y);

			// rozdiel uhla k bodu a uhla korytnacky --> absolutna hodnota pretoze mi moze
			// vyjst -
			double rozdiel = Math.abs(uholKBodu - uholNatocenia);

			// ak je rozdiel vacsi ako 180 moze sa otacat druhym smerom ak nieje normalne
			if (rozdiel >= 180) {
				sekundy = 360 - rozdiel;
				korytnacky[i].turn(rozdiel);
			} else {
				sekundy = rozdiel;
				korytnacky[i].turn(rozdiel);
			}

			// kolko pixelov/sekund ma prejst korytnacka k bodu
			double vzdialenostKBodu = korytnacky[i].distanceTo(x, y);

			korytnacky[i].step(vzdialenostKBodu);

			// spocitaj sekundy
			sekundy = sekundy + vzdialenostKBodu;

			// prvu hodnotu pridaj do premennej, kazdu dalsiu porovnavaj
			if (i == 0) {
				najmensiCasPrichodu = sekundy;
			} else {
				// ak je hodnota na indexe mensia ako niektora ktoru sme uz nasli uloz ju
				if (najmensiCasPrichodu > sekundy)
					najmensiCasPrichodu = sekundy;
			}
		}

		return najmensiCasPrichodu;
	}

	/**
	 * nakresli stvorec zaondeny
	 * 
	 * @param dlzkaStrany kde maju byt korytnacky
	 */
	public void doStvorca(double dlzkaStrany) {
		// stred plochy
		double surStvorcaX = this.getWidth() / 2;
		double surStvorcaY = this.getHeight() / 2;

		// body kde bude strana stvorca --> viem da sa aj jednoduchsie ale preistotu co
		// je vela to je malo
		double bodVlavoHoreX = surStvorcaX - (dlzkaStrany / 2);
		double bodVlavoHoreY = surStvorcaY - (dlzkaStrany / 2);

		double bodVlavoDoleX = surStvorcaX + (dlzkaStrany / 2);
		double bodVlavoDoleY = surStvorcaY - (dlzkaStrany / 2);

		double bodVpravoHoreX = surStvorcaX - (dlzkaStrany / 2);
		double bodVpravoHoreY = surStvorcaY + (dlzkaStrany / 2);

		double bodVpravoDoleX = surStvorcaX + (dlzkaStrany / 2);
		double bodVpravoDoleY = surStvorcaY + (dlzkaStrany / 2);

		// rozdelim si korytnacky na 4 skupiny
		int skupina = korytnacky.length / 4;

		// prva skupina chodte na hornu stranu stvroca
		// for zacina od 1 aby zostali prazdne rohy vo stvorci
		for (int i = 1; i < skupina + 1; i++) {
			// skupina + 1 lebo rozostup je medzera medzi korytnackami
			double rozostup = dlzkaStrany / (skupina + 1);
			// korytnacku 0 presun na poziciu s rozostupom na y
			korytnacky[i - 1].setPosition(bodVlavoHoreX, bodVlavoHoreY + rozostup * i);
		}

		// druha skupina napravo
		for (int i = 1; i < skupina + 1; i++) {
			double rozostup = dlzkaStrany / (skupina + 1);
			// kedze druha skupina neposielam korytnacku 0 ale napr korytnacku 4
			korytnacky[i + skupina - 1].setPosition(bodVpravoHoreX + rozostup * i, bodVpravoHoreY);
		}

		// tretia skupina dole
		for (int i = 1; i < skupina + 1; i++) {
			double rozostup = dlzkaStrany / (skupina + 1);
			// kedze 3 skupina posielam korytnacku 8 napr
			korytnacky[i + skupina + skupina - 1].setPosition(bodVpravoDoleX, bodVpravoDoleY - rozostup * i);
		}

		// 4 skupina vlavo
		for (int i = 1; i < skupina + 1; i++) {
			double rozostup = dlzkaStrany / (skupina + 1);
			// kedze posledna skupina posielam korytnacku napr ak je skup 4 -> 0 + 4 + 4 + 4
			// teda 12
			korytnacky[i + skupina + skupina + skupina - 1].setPosition(bodVlavoDoleX - rozostup * i, bodVlavoDoleY);
		}

	}

	/**
	 * 
	 * @param x suradnica bodu odkial pocitam histogram
	 * @param y suradnica bodu odkial pocitam histogram
	 * @param d dlzka zony
	 * @return pole rozdelene na zony podla toho ako sa v nich korytnacky nachadzaju
	 */
	public int[] histogram(double x, double y, double d) {
		double maxOdBoduC = 0;

		// najdem korytnacku ktora je najdalej od bodu c
		for (int i = 0; i < korytnacky.length; i++) {
			double vzdialenost = korytnacky[i].distanceTo(x, y);

			if (maxOdBoduC < vzdialenost)
				maxOdBoduC = vzdialenost;
		}

		// rozdelim si zony + 1 --> pretoze vysledok po celosiselnom deleni napr. 113 /
		// 10 = 11 zv. 3 co znamena ze je tam 12 zon
		int pole[] = new int[(int) (maxOdBoduC / d) + 1];

		// priradzujem hodnotu do pole[i] podla toho kolko korytnaciek sa v zone
		// nachadza
		for (int i = 0; i < korytnacky.length; i++) {
			for (int j = 0; j < pole.length; j++) {

				// korytnacka je v zone prave vtedy ked je v intervale
				boolean korytnackaJeVIntervale = korytnacky[i].distanceTo(x, y) >= j * d
						&& korytnacky[i].distanceTo(x, y) < j * d + d;

				if (korytnackaJeVIntervale) {
					pole[j] = pole[j] + 1;
				}
			}
		}

		return pole;
	}

	public void testHistogram(double x, double y, double d) {
		int[] p = this.histogram(x, y, d);
		System.out.print("histogram(" + x + ", " + y + ", " + d + "): ");
		System.out.println(Arrays.toString(p));
	}

	/**
	 * V nasom korytnacom svete sa nam budu korytnacky ODSTRELOVAT!!!!
	 * 
	 * @param idxPrvehoStrelca prvy strelec
	 * @param farbaStriel      farba
	 */
	public void prestrelka(int idxPrvehoStrelca, Color farbaStriel) {

		int[] zasiahnuteKorytnacky = new int[korytnacky.length];

		// idxKor je prave strielajuca korytnacka
		int idxKor = idxPrvehoStrelca;

		// opakuj dokym vsetky korytnacky nebudu zasiahnute
		while (!vsetkySuZasiahnute(zasiahnuteKorytnacky)) {
			Color farbaStrielajucej = new Color(0, 0, 0);
			farbaStrielajucej = korytnacky[idxKor].getPenColor();

			double najblizsia = 0;
			int idxNajblizsiej = 0;

			double surStrielajucejKorytnackyX = korytnacky[idxKor].getX();
			double surStrielajucejKorytnackyY = korytnacky[idxKor].getY();

			// najdem najblizsiu korytnacku
			for (int i = 0; i < korytnacky.length; i++) {

				// nechceme aby korytnacka strelila samu seba
				if (i == idxKor)
					continue;
				// ak uz korytnacka je zasiahnuta ziadna ina do nej nebude strielat
				if (zasiahnuteKorytnacky[i] == 1)
					continue;

				double vzdialenost = korytnacky[i].distanceTo(surStrielajucejKorytnackyX, surStrielajucejKorytnackyY);

				// ak je v premmenj najblizia 0 znamena ze cyklu neprebehol ani raz a preto
				// potrebujeme tam ulozit hodnotu inu ako 0
				if (najblizsia == 0) {
					najblizsia = vzdialenost;
					idxNajblizsiej = i;
				} else {
					// ak sme nasli vzdialenost mensiu ako doposial uloz ju
					if (najblizsia > vzdialenost) {
						najblizsia = vzdialenost;
						idxNajblizsiej = i;
					}
				}
			}

			korytnacky[idxKor].setPenColor(farbaStriel);

			// korytnacka namieri na najblizsiu
			korytnacky[idxKor].setDirection(korytnacky[idxKor].directionTowards(korytnacky[idxNajblizsiej].getX(),
					korytnacky[idxNajblizsiej].getY()));

			// a BANG!
			korytnacky[idxKor].step(najblizsia);

			korytnacky[idxKor].setPosition(surStrielajucejKorytnackyX, surStrielajucejKorytnackyY);

			korytnacky[idxKor].setPenColor(farbaStrielajucej);

			// teraz striela postrelena korytnacka
			idxKor = idxNajblizsiej;

			// korytnacka je postrelena uz do nej nestrielame bo asi skape
			zasiahnuteKorytnacky[idxNajblizsiej] = 1;

		}
	}

	/**
	 * pomocna metoda na zistenie ci su vsetky zasiahnute
	 * 
	 * @param pole
	 * @return
	 */
	public boolean vsetkySuZasiahnute(int[] pole) {

		for (int i = 0; i < pole.length; i++) {
			if (pole[i] == 0)
				return false;
		}

		return true;
	}
}