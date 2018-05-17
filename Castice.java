package txt_do_poctu;

public class Castice {
private  typ_castice typ;

private  double energy;


public Castice (typ_castice typ, double energy) { //pomocný objekt charakterizující typ a vlastnosti èástice
this.typ = typ;

this.energy = energy;

	
	
}
public double getEnergy() {
	
	return energy;
	
	
}

public typ_castice getTyp() {
	
	return typ;


}
}