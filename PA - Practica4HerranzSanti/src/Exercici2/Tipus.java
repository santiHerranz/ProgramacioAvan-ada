package Exercici2;

public enum Tipus {

	COCO(1),
	ATMELLA(2),
	AVELLANA(3),
	LLIMONA(4),
	XOCOLATA(5),
	CANELA(6);
	
	private int value;
	
	private Tipus(int value) {
        this.value = value;
	}
	public int intValue(){
		return value;
	}
	
	public static Tipus fromInteger(int x) {
        switch(x) {
        case 1:
            return COCO;
        case 2:
            return ATMELLA;
        case 3:
            return AVELLANA;
        case 4:
            return LLIMONA;
        case 5:
            return XOCOLATA;
        case 6:
            return CANELA;
        }
        return null;
    }	

}
