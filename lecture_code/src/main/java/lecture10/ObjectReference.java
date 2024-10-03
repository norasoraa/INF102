package lecture10;

public class ObjectReference {

	public static void main(String[] args) {
		equals1();
		equals2();
		equals3();
		equals4();
		equals5();
		equals6();
	}
	
	static void equals1(){
		String myName = new String("Martin");
		String foreleser = new String("Martin");
		System.out.println(myName == foreleser);
	}

	static void equals2(){
		String myName = new String("Martin");
		String foreleser = new String("Martin");
		System.out.println(myName.equals(foreleser));
	}

	static void equals3(){
		String myName = "Martin";
		String foreleser = "Martin";
		System.out.println(myName == foreleser);
	}
	
	static void equals4() {
		Integer num1 = new Integer(23);
		Integer num2 = new Integer(23);
		System.out.println(num1 == num2);
	}

	static void equals5() {
		Integer num1 = 23;
		Integer num2 = 23;
		System.out.println(num1 == num2);
	}

	static void equals6() {
		Integer num1 = 323;
		Integer num2 = 323;
		System.out.println(num1 == num2);
	}


}
