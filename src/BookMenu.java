import java.sql.*;
import java.util.Scanner;
public class BookMenu extends FunctionMenu {
    protected String jbtc = "jdbc:mysql://localhost:3306/huyle1";
    protected String username = "root";
    protected String password = "123456789";
    protected Connection connection;

    protected BookMenu() throws SQLException {
        connection = DriverManager.getConnection(jbtc, username, password);
        Statement statement = connection.createStatement();
    }
    protected void showBookMenu() {
        System.out.println("1. Add new book");
        System.out.println("2. Update book infomation");
        System.out.println("3. Delete book");
        System.out.println("4. Show all the book");
        System.out.println("0. Back menu");
        System.out.print("Choose a function: ");
    }

    protected void insertBook(String IBNS_code , String book_title , String author, String publication_year, String genre, String publisher, String selling_price, String page_number) {
        String sql = "INSERT INTO book " +
                "(IBNS_code, book_title, author, publication_year, genre, publisher, selling_price, page_number)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,IBNS_code);
            statement.setString(2,book_title);
            statement.setString(3,author);
            statement.setString(4,publication_year);
            statement.setString(5,genre);
            statement.setString(6,publisher);
            statement.setString(7,selling_price);
            statement.setString(8,page_number);
            statement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void addBook(Scanner input) {
        System.out.print("Typing sum of book: ");
        int sum = input.nextInt();
        input.nextLine();
        for (int i = 0;i <sum; i++) {
            System.out.println("Infomation of book" + (i + 1));
            System.out.println("--------------------------------");
            System.out.println("IBNS_code:");
            String IBNS_code = input.nextLine();
            System.out.println("book_title:");
            String book_title = input.nextLine();
            System.out.println("author:");
            String author = input.nextLine();
            input.nextLine();
            System.out.println("publication_year:");
            String publication_year = input.nextLine();
            System.out.println("genre:");
            String genre = input.nextLine();
            System.out.println("publisher:");
            String publisher = input.nextLine();
            System.out.println("selling_price:");
            String selling_price = input.nextLine();
            System.out.println("page_number:");
            String page_number = input.nextLine();
            insertBook(IBNS_code, book_title, author, publication_year, genre, publisher, selling_price, page_number);
            System.out.println("Insert new book successfully");
        }
    }
    protected void updateBook(Scanner input, Connection connection) {
        System.out.println("Enter the IBNS_code to update: ");
        String updateCode = input.next();
        input.nextLine();

        String checkIfExistsQuery = "SELECT * FROM book WHERE IBNS_code = ?";
        try (PreparedStatement checkIfExistsStatement = connection.prepareStatement(checkIfExistsQuery)) {
            checkIfExistsStatement.setString(1, updateCode);
            ResultSet resultSet = checkIfExistsStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Enter new information for the book:");

                System.out.print("New IBNS_code: ");
                String IBNS_code = input.nextLine();

                System.out.print("New book_title: ");
                String book_title = input.nextLine();

                System.out.print("New author: ");
                String author = input.nextLine();

                System.out.print("New publication year: ");
                int publicationYear = input.nextInt();
                input.nextLine();

                System.out.print("New genre: ");
                String genre = input.nextLine();

                System.out.print("New publisher: ");
                String publisher = input.nextLine();

                System.out.print("New selling_price: ");
                double selling_price = input.nextDouble();
                input.nextLine();

                System.out.print("New page_number: ");
                int page_number = input.nextInt();
                input.nextLine();

                String updateQuery = "UPDATE book SET IBNS_code = ?, book_title = ?, author = ?, " +
                        "publication_year = ?, genre = ?, publisher = ?, selling_price = ?, page_number = ? WHERE IBNS_code = ?";

                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                    updateStatement.setString(1, IBNS_code);
                    updateStatement.setString(2, book_title);
                    updateStatement.setString(3, author);
                    updateStatement.setInt(4, publicationYear);
                    updateStatement.setString(5, genre);
                    updateStatement.setString(6, publisher);
                    updateStatement.setDouble(7, selling_price);
                    updateStatement.setInt(8, page_number);
                    updateStatement.setString(9, updateCode);

                    updateStatement.executeUpdate();
                    System.out.println("Book updated successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Book with code " + updateCode + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void deleteBook(String code) {
        String sql = "DELETE FROM book WHERE IBNS_code = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, code);
            statement.executeUpdate();
            System.out.println("Deleted book successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void showListBook() {
        String sql = "SELECT * FROM book";
        try {
            Statement statement = connection.createStatement();
            ResultSet query = statement.executeQuery(sql);
            System.out.println("-----List of book-----");
            System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n","IBNS_code","book_title","author","publication_year","genre","publisher","selling_price","page_number");
            while (query.next()) {
                String IBNS_code = query.getString("IBNS_code");
                String book_title = query.getString("book_title");
                String author = query.getString("author");
                String publication_year = query.getString("publication_year");
                String genre = query.getString("genre");
                String publisher = query.getString("publisher");
                String selling_price = query.getString("selling_price");
                String page_number = query.getString("page_number");
                System.out.printf("%-10s | %-10s | %-20s | %-17s | %-17s | %-17s | %-15s | %-12s\n",IBNS_code,book_title,author,publication_year,genre,publisher,selling_price,page_number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void Book(int m, Scanner input) {
        switch(m) {
            case 1: addBook(input);
                break;
            case 2:
                updateBook(input, connection);
                break;
            case 3:
                System.out.println("Delete book");
                System.out.println("Enter the IBNS_code: ");
                String deleteCode = input.next();
                deleteBook(deleteCode);
                break;
            case 4:
                showListBook();
                break;
        }
        if(m != 0) {
            System.out.print("Click 1 return menu, click 0 back to main menu: ");
            m = input.nextInt();
            while(m < 0 || m > 1) {
                System.out.print("Click 1 return menu, click 0 back to main menu: ");
                m = input.nextInt();
            }
            this.m = m;
        }
    }

    protected void returnBookMenu(Scanner input) {
        do{
            showBookMenu();
            m = input.nextInt();
            checkM(m, input);
            Book(m, input);
        }while(m != 0);
    }
}
