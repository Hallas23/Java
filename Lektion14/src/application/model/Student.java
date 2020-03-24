package application.model;

import java.io.Serializable;

/**
 * Klasse der beskriver en studerende
 *
 * @author mad
 *
 */
public class Student implements Serializable {
	/*
	 * Attributter der beskriver den studerendes tilstand
	 */
	private String name;
	private boolean active;
	private int age;

	/*
	 * Constructor, når den studerende oprettes, skal den have et navn. Ved
	 * oprettelse er den studerende ikke aktiv
	 */
	public Student(String name, int age) {
		this.name = name;
		this.age = age;
		this.active = false;
	}

	/*
	 * Constructor, når den studerende oprettes, skal den have et navn. Ved
	 * oprettelse er den studerende ikke aktiv
	 */
	public Student(String name, int age, boolean active) {
		this.name = name;
		this.age = age;
		this.active = active;
	}

	/*
	 * Man kan få oplyst den studerendes alder, ved at kalde metoden getAlder
	 */
	public int getAge() {
		return age;
	}

	/*
	 * Den studerendes alder kan ændres ved kald af setAlder metoden
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * Den studerendes navn kan ændres ved kald af setNavn metoden
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Man kan få oplyst den studerendes navn, ved at kalde metoden getNavn
	 */
	public String getName() {
		return name;
	}

	/*
	 * Den studerendes kan få ændret aktivitets status ved at kalde metoden
	 * setStatus
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/*
	 * Man kan få oplyst den studerendes aktivitet, ved at kalde metoden isAktiv
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * den studerende bliver et år ældre
	 */
	public void increaseAge() {
		age++;
	}

	@Override
	public String toString() {
		return "Navn: \t" + name + " Alder: \t" + age + " Aktiv: \t" + active;
	}

}
