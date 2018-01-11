package example;

public class MainExample {

	public static void main(String args[]) {
		
		Pet myPet = new Pet();
		myPet.name = "miskin";
		
		Owner myOwner = new Owner();
		myOwner.name = "mehmet";
		myOwner.pet = myPet;
		
		myPet.owner = myOwner;
		
		int a = 0;
		
	}
}
