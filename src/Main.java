import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sca = new Scanner(System.in);
        System.out.println("text :");
        String s = sca.nextLine();
        String t = (s.equals("morteza")) ? "moorteza" : "nazari";
        System.out.println(t);
    }
}
