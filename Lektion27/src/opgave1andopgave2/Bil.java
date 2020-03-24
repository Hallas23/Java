package opgave1andopgave2;

public class Bil {
	private String registeringsnummer;
	private String mærke;
	private String model;
	private String farve;

	public Bil(String registeringsnummer, String mærke, String model, String farve) {
		this.registeringsnummer = registeringsnummer;
		this.mærke = mærke;
		this.model = model;
		this.farve = farve;
	}

	public String getRegisteringsnummer() {
		return registeringsnummer;
	}

	public void setRegisteringsnummer(String registeringsnummer) {
		this.registeringsnummer = registeringsnummer;
	}

	public String getMærke() {
		return mærke;
	}

	public void setMærke(String mærke) {
		this.mærke = mærke;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFarve() {
		return farve;
	}

	public void setFarve(String farve) {
		this.farve = farve;
	}

	@Override
	public String toString() {
		return "Bil [registeringsnummer=" + registeringsnummer + ", mærke=" + mærke + ", model=" + model + ", farve="
				+ farve + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((farve == null) ? 0 : farve.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((mærke == null) ? 0 : mærke.hashCode());
		result = prime * result + ((registeringsnummer == null) ? 0 : registeringsnummer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bil other = (Bil) obj;
		if (farve == null) {
			if (other.farve != null)
				return false;
		} else if (!farve.equals(other.farve))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (mærke == null) {
			if (other.mærke != null)
				return false;
		} else if (!mærke.equals(other.mærke))
			return false;
		if (registeringsnummer == null) {
			if (other.registeringsnummer != null)
				return false;
		} else if (!registeringsnummer.equals(other.registeringsnummer))
			return false;
		return true;
	}
	
	
	
	

}
