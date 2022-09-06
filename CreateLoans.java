import java.util.*;

public class CreateLoans implements LoanConstants {
   public static void main(String[] args) {
       double primeInterestRate;
       try (Scanner sc = new Scanner(System.in)) {
        int accNo,choice,term;
           String name;
           double loanAmt;
           System.out.println(" This is "+LoanConstants.COMPANY);
           System.out.println(" Input the recent prime interest rate as a Decimal ");
           primeInterestRate=sc.nextDouble();
           Loan loans[]=new Loan[5];
           for(int i=0;i<loans.length;i++)
           {
               System.out.println("Is this a [1] Business loan or a [2] Personal loan");
               choice=sc.nextInt();
               if(choice==1)
               {
                System.out.println("Enter account number");
                accNo=sc.nextInt();
                
                   System.out.println("Enter name:");
                   name=sc.next();
              
                   while(true)
                   {
                       System.out.println("Enter loan amount:");
                       loanAmt=sc.nextDouble();
                       if(loanAmt>LoanConstants.MAXLOAN)
                       {
                           System.out.println(" Invalid. Must be Less or Equal to "+LoanConstants.MAXLOAN);
                       }
                       else
                           break;
                   }
             
while(true)

{
                   System.out.println("Enter term:");
                   term=sc.nextInt();
                   if(term!=LoanConstants.SHORT_TERM && term!=LoanConstants.MEDIUM_TERM && term!=LoanConstants.LONG_TERM)
                   {
                       System.out.println("Must be either [3] or [7] or [15]");
                   }
                   else
                   {
                       break;
                   }
}

                   BusinessLoan bl=new BusinessLoan(accNo,name,loanAmt,term, primeInterestRate);
                   loans[i]=bl;
               }
               else if(choice==2)
               {
                System.out.println("Enter account number");
                accNo=sc.nextInt();

                   System.out.println("Enter name:");
                   name=sc.next();
                  
                       System.out.println("Enter loan amount:");
                       loanAmt=sc.nextDouble();
              

                   System.out.println("Enter years of term:");
                   term=sc.nextInt();
                   PersonalLoan pl=new PersonalLoan(accNo,name,loanAmt,term, primeInterestRate);
                   loans[i]=pl;
               }
           }
          
           System.out.println("\n");
           System.out.println(LoanConstants.COMPANY);
           for(int i=0;i<loans.length;i++)
           {
               System.out.println(loans[i]);
           }
    }
      
   }
}