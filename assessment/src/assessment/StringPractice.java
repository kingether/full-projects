package assessment;

public class StringPractice {

	public static void main(String ... args) {
		
		
		var str = " JD ".repeat(2); 
		System.out.println("First: " + str);
        System.out.print("Start");
        System.out.print(str.strip());
        System.out.println("End");
        
        System.out.print("Start");
        System.out.print(str.stripLeading());
        System.out.println("End");
        
        System.out.print("Start");
        System.out.print(str.stripTrailing());
        System.out.println("End");
	}
}
