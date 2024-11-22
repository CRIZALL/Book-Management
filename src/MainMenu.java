import java.sql.SQLException;
import java.util.Scanner;
public class MainMenu extends BookMenu {
    protected int n;

    protected MainMenu() throws SQLException {
    }

    protected void showMainMenu() {
        System.out.println("1. Book Management");
        System.out.println("2. function");
        System.out.println("0. Exit");
        System.out.print("Choose a menu: ");
    }
    protected void checkN(int n, Scanner input) {
        while(n < 0 || n > 2) {
            System.out.print("Menu not yet developed, choose again: ");
            n = input.nextInt();
        }
        this.n = n;
    }
    protected void returnMainMenu(Scanner input) {
        do{
            showMainMenu();
            n = input.nextInt();
            checkN(n, input);
            switch(n) {
                case 1:
                    returnBookMenu(input);
                    ;break;
                case 2:
                    returnFunctionMenu(input);
                    ;break;
            }
        }while(n != 0);
    }
}