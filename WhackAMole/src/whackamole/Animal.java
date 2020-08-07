package whackamole;

public abstract class Animal {
	Hole hole_animal;

	public Animal(Hole hole_animal) {
		super();
		this.hole_animal = hole_animal;
	}

	public Hole getHole_animal() {
		return hole_animal;
	}

	public void setHole_animal(Hole hole_animal) {
		this.hole_animal = hole_animal;
	}

	public abstract void draw();

	public abstract void clickedEffect();

	public abstract void timeEffect();

}
