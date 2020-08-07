package whackamole;

import java.awt.Color;
import java.awt.Graphics;

public class Bomb extends Animal {

	public Bomb(Hole hole_animal) {
		super(hole_animal);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		Graphics g = hole_animal.getGraphics();
		g.setColor(Color.RED);
		g.drawLine(0, 0, hole_animal.getWidth(), hole_animal.getHeight());
		g.drawLine(0, hole_animal.getHeight(), hole_animal.getWidth(), 0);

	}

	@Override
	public void clickedEffect() {
		// TODO Auto-generated method stub
		synchronized (hole_animal) {
			hole_animal.getHole_thr().interrupt();
			hole_animal.setHasAnimal(false);
			hole_animal.getGarden().stop();
		}
		hole_animal.repaint();
		hole_animal.getGarden().holes_taken--;

	}

	@Override
	public void timeEffect() {
		// TODO Auto-generated method stub
		hole_animal.getGarden().holes_taken--;
		hole_animal.repaint();

	}

}
