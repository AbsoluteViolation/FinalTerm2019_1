package zadanie4;

import java.awt.Color;
import java.awt.event.MouseEvent;

import sk.upjs.jpaz2.Turtle;
import sk.upjs.jpaz2.WinPane;

public class ClickPane extends WinPane {
	//instancne premenne
	private Turtle rasto;
	private int pocitadlo;
	private boolean mnohouholnik;
	private double surXPrvehoBodu;
	private double surYPrvehoBodu;

	public ClickPane() {
		pocitadlo = 0;
		mnohouholnik = false;

		this.rasto = new Turtle();
		this.add(rasto);
	}
	//getter
	public boolean getMnohouholnik() {
		return mnohouholnik;
	}
	//setter
	public void setMnohouholnik(boolean mnohouholnik) {
		this.mnohouholnik = mnohouholnik;
	}
	/**
	 * nakresli bod
	 */
	public void bod() {
		rasto.setFillColor(Color.orange);
		this.rasto.dot(10);
	}

	@Override
	protected void onMousePressed(int x, int y, MouseEvent detail) {
		//ak stlacim lave tlacidlo a nekreslim mnohoulnik
		if (detail.getButton() == MouseEvent.BUTTON1 && mnohouholnik == false) {
			
			//zacal sa kreslit mnohouholnik
			this.setMnohouholnik(true);
			rasto.setPosition(x, y);
			
			//uloz suradnice prveho bodu
			surXPrvehoBodu = x;
			surYPrvehoBodu = y;
			pocitadlo = 1;

			bod();
			rasto.setDirection(90);
			rasto.printCenter(Integer.toString(pocitadlo));

		} else {
			//ak stlacim lave tlacidlo a kreslim mnohouholnik
			if (detail.getButton() == MouseEvent.BUTTON1 && mnohouholnik == true) {
				//ak je rozdiel suradnic kliknuteho bodu a prveho bodu mensi ako 10 viem ze som klikol do prveho bodu
				if (Math.abs(x - surXPrvehoBodu) <= 10 && Math.abs(y - surYPrvehoBodu) <= 10) {
					
					//nastavim false
					this.setMnohouholnik(false);
					
					//aby sa vo vnutri posledneho bodu nevykreslovala ciara
					rasto.penUp();
					rasto.setDirection(rasto.directionTowards(surXPrvehoBodu, surYPrvehoBodu)); 
					rasto.step(10);
					rasto.penDown();
					
					//aby sa vo vnutri do prveho bodu nenakreslila ciara
					double vzdialenost = rasto.distanceTo(surXPrvehoBodu, surYPrvehoBodu);
					rasto.step(vzdialenost - 10);
					
					rasto.setPosition(surXPrvehoBodu, surYPrvehoBodu);
					pocitadlo = 0;

				} else {
					//ak nie som v nutri prveho bodu
					pocitadlo++;
					
					//aby som nekreslil ciaru vnutri bodu
					rasto.penUp();
					rasto.setDirection(rasto.directionTowards(x, y)); 
					rasto.step(10);
					rasto.penDown();
					rasto.moveTo(x, y);
					bod();
					rasto.setDirection(90);
					rasto.printCenter(Integer.toString(pocitadlo));
					
				}

			}
		}
	}

}
