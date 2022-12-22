package sk.paz1a.practicals.zadanie1;

import java.awt.Color;

import sk.upjs.jpaz2.Turtle;

public class HomeTurtle extends Turtle {

	public void flower(double radius) {

		this.setFillColor(Color.red);
		this.penUp();

		for (int i = 0; i < 12; i++) {

			this.step(radius);
			this.dot(radius / 2);
			this.step(-1 * radius);
			this.turn(360 / 12);

		}
		this.setFillColor(Color.yellow);
		this.dot(radius);

		this.isPenDown();

	}

	public void arrow(double size) {
		this.penUp();
		this.setFillColor(Color.orange);
		this.openPolygon();

		this.turn(90);
		this.step(size / 2);

		for (int i = 0; i < 2; i++) {
			this.turn(-120);
			this.step(size);
		}

		this.turn(-120);
		this.step(size / 2);
		this.turn(-90);
		this.closePolygon();
		this.penDown();
	}

	public void navigationArrow(double size) {
		for (int i = 0; i < 5; i++) {
			this.arrow(size);
			this.penUp();
			this.step(size / 2);
			this.penDown();
		}
		this.penUp();
		this.step(-5 * size / 2);
		this.penDown();
	}

	public void hexagon(double size) {
		this.penUp();
		this.step(size);
		this.penDown();
		this.turn(120);
		for (int i = 0; i < 6; i++) {
			this.step(size);
			this.turn(360 / 6);
		}
		this.turn(-120);
		this.penUp();
		this.step(-size);
		this.penDown();
	}

	public void beehive(double size) {
		this.setPenColor(Color.green);
		this.hexagon(size);

		this.penUp();

		for (int i = 0; i < 6; i++) {

			this.turn(360 / 6);
			this.step(size);
			this.turn(-360 / 6);
			this.step(size);

			this.penDown();
			this.hexagon(size);

			this.penUp();
			this.step(-size);
			this.turn(360 / 6);
			this.step(-size);
		}
	}

	public void smartWatch(double radius, int hh, int mm) {

		this.setFillColor(Color.blue);
		this.dot(radius);

		this.setFillColor(Color.lightGray);
		this.dot(radius * 3 / 4);

		for (int i = 0; i < 12; i++) {
			this.setDirection(360 - 30 * i);
			this.penUp();
			this.step(radius / 2);
			this.penDown();
			this.step(radius / 4);

			this.penUp();
			this.step(-radius / 4);
			this.step(-radius / 2);
			this.penDown();
		}

		this.setDirection(0);

		this.setPenColor(Color.red);
		this.setPenWidth(3);
		this.turn(360 / 60 * mm);
		this.step(2 * radius / 3);
		this.step(-2 * radius / 3);

		this.setDirection(0);
		this.setPenWidth(5);
		this.turn((360.0 / 12 * hh) + (30.0 / 60 * mm));
		this.step(radius / 3);
		this.step(-radius / 3);

		this.setFillColor(Color.blue);
		this.dot(radius / 10);

		this.setDirection(0);
		this.setPenColor(Color.black);
	}

	public void flagOfSouthKorea(int stepCount, double height) {
		// ... inicializacne prikazy
		double startX = this.getX();
		double startY = this.getY();
		double startAngle = this.getDirection();

		// cierne kraje vlajky
		this.penUp();
		this.step(height);
		this.penDown();
		this.turn(90);
		this.step(3 * height / 2);
		this.turn(90);
		this.step(2 * height);
		this.turn(90);
		this.step(3 * height);
		this.turn(90);
		this.step(2 * height);
		this.turn(90);
		this.step(3 * height / 2);

		this.setPosition(startX, startY);

		for (int i = 0; i < stepCount; i++) {
			// ... prikazy na nastavenie farby podla aktualnej pozicie
			if (this.getY() < startY) {
				this.setPenColor(Color.red);
			}
			if (this.getY() > startY) {
				this.setPenColor(Color.blue);
			}

			double redCircleCenterX = startX - height / 4;
			boolean distanceToRedCircle = this.distanceTo(redCircleCenterX, startY) <= height / 4;

			if (distanceToRedCircle) {
				this.setPenColor(Color.red);
			}

			double blueCircleCenterX = startX + height / 4;
			boolean distanceToBlueCircle = this.distanceTo(blueCircleCenterX, startY) <= height / 4;

			if (distanceToBlueCircle) {
				this.setPenColor(Color.blue);
			}

			this.turn(Math.random() * 360);
			this.step(5);

			// ... prikazy, resp. podmienka, ktore zabezpecia, ze korytnacka
			// nevyjde mimo definovanej obdlznikovej oblasti
			boolean distancetoCenter = this.distanceTo(startX, startY) <= height / 2;

			if (!(distancetoCenter)) {
				this.step(-5);
			}
		}

		// ... prikazy, ktore obnovia vychodiskovy stav

		this.setPosition(startX, startY);
		this.setDirection(startAngle);
	}

	public void sipka(double sirka, double dlzka) {
		double startX = this.getX();
		double startY = this.getY();
		double startAngle = this.getDirection();

		this.openPolygon();
		this.penUp();

		this.turn(90);
		this.step(sirka / 2);

		this.turn(-90);
		this.step(dlzka);
		this.turn(-30);
		this.step(sirka);
		this.turn(-120);
		this.step(sirka);
		this.turn(-30);
		this.step(dlzka);
		this.turn(-150);
		this.step(sirka);
		this.turn(120);
		this.step(sirka);

		this.penDown();
		this.closePolygon();

		this.setPosition(startX, startY);
		this.setDirection(startAngle);
	}

	public void sipkovnica(int pocetSipok, double sirka, double dlzkaPrvej, double medzera) {
		double startX = this.getX();
		double startY = this.getY();
		double startAngle = this.getDirection();
		
		
		for (int i = 0; i < pocetSipok; i++) {
			if (i % 2 == 0) {
				this.setFillColor(Color.orange);
			} else {
				this.setFillColor(Color.black);
			}
			
			this.sipka(sirka, dlzkaPrvej);
			this.penUp();
			this.step(dlzkaPrvej + medzera);
			
			dlzkaPrvej = dlzkaPrvej * 0.7;
			
			this.penDown();
		}
		
		this.setPosition(startX, startY);
		this.setDirection(startAngle);
	}
	
}
