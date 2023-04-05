import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

public class RPNStacker {
    public static void main (String[] args) {
        ArrayList<String> input = readFile("Calc1.stk");

        Iterator<String> iterator = input.iterator();
        Stack<Integer> stackpile = new Stack<Integer>();

        Integer calc = 0;

        while(iterator.hasNext()) {
            String rpnElementString = iterator.next();

            if (stackpile.size() < 2) {
                calc = Integer.parseInt(rpnElementString);
            } else {
                Integer second = stackpile.pop();
                Integer first = stackpile.pop();

                if (rpnElementString.equals("+")) calc = first + second;
                else if (rpnElementString.equals("-")) calc = first - second;
                else if (rpnElementString.equals("*")) calc = first * second;
                else if (rpnElementString.equals("/")) calc = first / second;

            }

            stackpile.add(calc);
        }

        Integer finalCalc = stackpile.pop();
        System.out.println("Result: " + finalCalc);
    }

    static ArrayList<String> readFile(String fileName) {
        File fileCalc = new File(fileName);
        ArrayList<String> input = new ArrayList<String>();

        try (Scanner in = new Scanner(fileCalc, StandardCharsets.UTF_8)) {
            while(in.hasNextLine()) {
                String x = in.nextLine();
                input.add(x);
            }
        } catch (IOException e ){
            e.printStackTrace();
        }
        return input;
    }
}