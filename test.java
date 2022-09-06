import java.util.ArrayList;
import java.util.Scanner;


interface LoanConstants{
int SHORT_TERM = 1;
int MID_TERM = 3;
int LONG_TERM = 5;
int MAX_LOAN_AMT = 50000;
String company = "Java Programmers Loan (JPL)";
}

abstract class Loan implements LoanConstants{
int loanNo;
String custLname;
double amt;
double rate;
int term;
public Loan(int loanNo, String custLname, double amt, int term) {
super();
this.loanNo = loanNo;
this.custLname = custLname;
if(amt<=LoanConstants.MAX_LOAN_AMT){
this.amt = amt;
}
if(amt>LoanConstants.MAX_LOAN_AMT){
System.out.println("Loan amount cannot exceed "+LoanConstants.MAX_LOAN_AMT);
System.exit(0);
}
if(term!=LoanConstants.SHORT_TERM && term!=LoanConstants.MID_TERM && term!=LoanConstants.LONG_TERM)
this.term = LoanConstants.SHORT_TERM;
else
this.term = term;
}
@Override
public String toString() {
return "LoanNo= " + loanNo + "\nCustomer Lastname= " + custLname + "\nPrincipal Amount= " + amt + "\nInterest Rate= " + rate + "\nTerm= "+ term ;
}
public boolean equals(Loan l){
if(this.amt==l.amt && this.rate==l.rate && this.term==l.term){
return true;
}
return false;
}
  
}

class BusinessLoan extends Loan{

public BusinessLoan(int loanNo, String custLname, double amt,double rate, int term) {
super(loanNo, custLname, amt, term);
this.rate = rate;
}
  
}

class PersonalLoan extends Loan{

public PersonalLoan(int loanNo, String custLname, double amt, double rate,int term) {
super(loanNo, custLname, amt, term);
this.rate = rate;
}
  
}

public class CreateLoans {

public static void main(String[] args) {
Scanner s = new Scanner(System.in);
Loan l[] = new Loan[5];
double total=0;
double btotal = 0, ptotal = 0;
System.out.print("Enter the current prime interest rate: ");
double prate = s.nextDouble();
s.nextLine();
for(int i=0;i<2;i++){
System.out.print("\nPress 1.Business Loan 2.Personal Loan\nEnter your choice: ");
int ch = s.nextInt();
s.nextLine();
int loanNo = 1001 + i ;
System.out.print("\nEnter customer last name: ");
String custLname = s.nextLine();
System.out.print("\nEnter loan amount: ");
double amt = s.nextDouble();
System.out.print("\nEnter the loan term (in years): ");
int term = s.nextInt();
s.nextLine();
if(ch==1){
double rate = prate*1.01;
l[i] = new BusinessLoan(loanNo, custLname, amt, rate, term);
double sum = l[i].amt*l[i].rate*l[i].term/100 + l[i].amt;
btotal += sum;
}
if(ch==2){
double rate = prate*1.02;
l[i] = new PersonalLoan(loanNo, custLname, amt, rate, term);
double sum = l[i].amt*l[i].rate*l[i].term/100 + l[i].amt;
ptotal += sum;
}
}
for(int i=0;i<5;i++){
System.out.println(l[i].toString());
double sum = l[i].amt*l[i].rate*l[i].term/100 + l[i].amt;
total += sum;
System.out.println("Amount to be payed at due date: "+sum);
}
System.out.println("\nTOTAL LOAN JPL HAS LOANED OUT IN BUSINESS LOAN CATEGORY: "+btotal);
System.out.println("TOTAL LOAN JPL HAS LOANED OUT IN PERSONAL LOAN CATEGORY: "+ptotal);
System.out.println("\n\nTOTAL LOAN JPL HAS LOANED OUT: "+total);
}

}