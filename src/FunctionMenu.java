import java.sql.*;
import java.util.Scanner;

public class FunctionMenu {
    protected String jbtc = "jdbc:mysql://localhost:3306/huyle1";
    protected String username = "root";
    protected String password = "123456789";
    protected Connection connection;

    protected FunctionMenu() {
        try {
            connection = DriverManager.getConnection(jbtc, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected int m;

    protected void showFunctionMenu() {
        System.out.println("1. Filter books by author");
        System.out.println("2. Filter books by category");
        System.out.println("3. Sort books by publication year in descending order");
        System.out.println("4. Sort books by descending price");
        System.out.println("0. Back menu");
        System.out.print("Choose a function: ");
    }
    protected void checkM(int m, Scanner input) {
        while(m < 0 || m > 4) {
            System.out.print("Function not yet developed, choose again: ");
            m = input.nextInt();
        }
        this.m = m;
    }
    protected void filterBooksByAuthor(Scanner input) {
        System.out.println("Enter the author name to filter: ");
        String authorName = input.nextLine();

        String sql = "SELECT * FROM book WHERE author LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + authorName + "%"); // Use wildcard characters for partial matching
            ResultSet resultSet = statement.executeQuery();

            System.out.println("--- Books by author " + authorName + " ---");
            System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                    "IBNS_code", "book_title", "author", "publication_year", "genre", "publisher", "selling_price", "page_number");
            boolean found = false;
            while (resultSet.next()) {
                found = true;
                String IBNS_code = resultSet.getString("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String publication_year = resultSet.getString("publication_year");
                String genre = resultSet.getString("genre");
                String publisher = resultSet.getString("publisher");
                String selling_price = resultSet.getString("selling_price");
                String page_number = resultSet.getString("page_number");
                System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                        IBNS_code, book_title, author, publication_year, genre, publisher, selling_price, page_number);
            }
            if (!found) {
                System.out.println("No books found for author '" + authorName + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void filterBooksByCategory(Scanner input) {
        System.out.println("Enter the category to filter books: ");
        String category = input.nextLine();

        String sql = "SELECT * FROM book WHERE genre LIKE ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + category + "%"); // Use wildcard characters for partial matching
            ResultSet resultSet = statement.executeQuery();

            System.out.println("--- Books in category: " + category + " ---");
            System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                    "IBNS_code","book_title","author","publication_year","genre","publisher","selling_price","page_number");
            boolean found = false;
            while (resultSet.next()) {
                found = true;
                String IBNS_code = resultSet.getString("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String publication_year = resultSet.getString("publication_year");
                String genre = resultSet.getString("genre");
                String publisher = resultSet.getString("publisher");
                String selling_price = resultSet.getString("selling_price");
                String page_number = resultSet.getString("page_number");
                System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                        IBNS_code, book_title, author, publication_year, genre, publisher, selling_price, page_number);
            }
            if (!found) {
                System.out.println("No books found for category '" + category + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void sortBooksByPublicationYear(Scanner input) {
        String sql = "SELECT * FROM book ORDER BY publication_year DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("--- Books sorted by publication year in descending order ---");
            System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                    "IBNS_code", "book_title", "author", "publication_year", "genre", "publisher", "selling_price", "page_number");

            while (resultSet.next()) {
                String IBNS_code = resultSet.getString("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String publication_year = resultSet.getString("publication_year");
                String genre = resultSet.getString("genre");
                String publisher = resultSet.getString("publisher");
                String selling_price = resultSet.getString("selling_price");
                String page_number = resultSet.getString("page_number");
                System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                        IBNS_code, book_title, author, publication_year, genre, publisher, selling_price, page_number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    protected void sortBooksByDescendingPrice(Scanner input) {
        String sql = "SELECT * FROM book ORDER BY selling_price DESC";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("--- Books sorted by descending price ---");
            System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                    "IBNS_code", "book_title", "author", "publication_year", "genre", "publisher", "selling_price", "page_number");

            while (resultSet.next()) {
                String IBNS_code = resultSet.getString("IBNS_code");
                String book_title = resultSet.getString("book_title");
                String author = resultSet.getString("author");
                String publication_year = resultSet.getString("publication_year");
                String genre = resultSet.getString("genre");
                String publisher = resultSet.getString("publisher");
                String selling_price = resultSet.getString("selling_price");
                String page_number = resultSet.getString("page_number");
                System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",
                        IBNS_code, book_title, author, publication_year, genre, publisher, selling_price, page_number);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void function(int m, Scanner input) {
        switch (m) {
            case 1:
                filterBooksByAuthor(input);
                break;
            case 2:
                filterBooksByCategory(input);
                break;
            case 3:
                sortBooksByPublicationYear(input);
                break;
            case 4:
                sortBooksByDescendingPrice(input);
                break;
            default:
                System.out.println("Invalid option!");
                break;
        }

        if (m != 0) {
            System.out.print("Click 1 to return to the menu, click 0 to go back to the main menu: ");
            int userChoice = input.nextInt();
            while (userChoice < 0 || userChoice > 1) {
                System.out.print("Click 1 to return to the menu, click 0 to go back to the main menu: ");
                userChoice = input.nextInt();
            }
            this.m = userChoice;
        }
    }

    protected void returnFunctionMenu(Scanner input) {
        do {
            showFunctionMenu();
            m = input.nextInt();
            input.nextLine();
            checkM(m, input);
            function(m, input);
        } while (m != 0);
    }
}
