 import java.util.Scanner;

public class IntegerDivision {

        public static void main(String[] args) {
            
                Scanner sc = new Scanner(System.in);
                System.out.print("Input Integer Number 1: ");
                int n1=sc.nextInt();
                System.out.print("Input Integer Number 2: ");
                int n2=sc.nextInt();
                try {
                        double result = (double)n1/n2;
                        if(n2==0)
                                throw new ArithmeticException();
                        System.out.printf("%d / %d = %.2f",n1,n2,result);
                }
                catch(ArithmeticException ex) {
                        System.out.println("Exception has shown when dividing "+n1+" by "+n2);
                }
                sc.close();
        }

}