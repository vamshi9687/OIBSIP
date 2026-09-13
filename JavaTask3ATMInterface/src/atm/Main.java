package atm;

public class Main {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("        OIBSIP JAVA PROJECT");
        System.out.println("=================================");
        System.out.println("Name  : Vamshi B");
        System.out.println("Track : Java Programming");
        System.out.println("Task  : Task 3 - ATM Interface");
        System.out.println("=================================");

        Bank bank = new Bank();

        ATM atm = new ATM(bank);

        atm.start();
    }
}