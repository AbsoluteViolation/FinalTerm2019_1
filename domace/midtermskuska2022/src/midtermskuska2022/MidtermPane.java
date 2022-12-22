package midtermskuska2022;

import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.WinPane;

public class MidtermPane extends WinPane {

	Turtle[] korytnacky;

	public MidtermPane() {

		korytnacky = new Turtle[11];

		for (int i = 0; i < 11; i++) {

			korytnacky[i] = new Turtle();
			this.add(korytnacky[i]);

			double surX = Math.random() * getWidth();
			double surY = Math.random() * getHeight();

			korytnacky[i].setPosition(surX, surY);
		}

	}

	public double dalekoBlizko(double x, double y, double d) {
		double pocetKorytnaciek = 0;

		for (int i = 0; i < korytnacky.length; i++) {

			double smerNatocenia = korytnacky[i].directionTowards(x, y);

			korytnacky[i].setDirection(smerNatocenia);

			if (korytnacky[i].distanceTo(x, y) <= d) {
				pocetKorytnaciek++;
			}else {
				korytnacky[i].turn(180);
			}
		}
		return pocetKorytnaciek;
	}

}
