import java.sql.SQLException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        MainMenu menu = new MainMenu();
        menu.returnMainMenu(input);
    }
}