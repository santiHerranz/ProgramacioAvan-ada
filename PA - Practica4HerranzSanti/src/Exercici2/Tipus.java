package Exercici2;

public enum Tipus {

	VAINILLA(1),
	XOCOLATA(2),
	COCO(3),
	ATMELLA(4),
	AVELLANA(5),
	LLIMONA(6),
	CANELA(7);
	
	private int value;
	
	private Tipus(int value) {
        this.value = value;
	}
	public int intValue(){
		return value;
	}
	
	public String toString(){
//		return String.format("%s:%s", this.value, super.toString()) ;
		return String.format("%s", super.toString()) ;
	}
	
	public static Tipus fromInteger(int x) {
        switch(x) {
        case -1:
            return null;
        case 1:
            return VAINILLA;
        case 2:
            return XOCOLATA;
        case 3:
            return COCO;
        case 4:
            return ATMELLA;
        case 5:
            return AVELLANA;
        case 6:
            return LLIMONA;
        case 7:
            return CANELA;
        }
        return null;
    }	

}
