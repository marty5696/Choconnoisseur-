import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.*;
import static java.lang.System.out;

public class Menu {

    static boolean exit;
    static final String DB_URL = "jdbc:mysql://localhost:3306/chocolatemilk_review";
    static final String USER = "root";
    static final String PASS = "";

    private static Connection connection = null;
    static Scanner input = new Scanner(System.in);

    public static void main (String [] args) throws SQLException {

        mainMenuOptions();
        input.close();

    }

    public static Connection connect() throws SQLException {

        return DriverManager.getConnection(DB_URL, USER, PASS);

    }

    public static void mainMenuOptions() {

        int numEntered;

        try { 

            do {

                out.println("Choconnoisseur Menu");
                out.println();
                out.println("Enter 1 for Accounts ");
                out.println("Enter 2 for Merchandise ");
                out.println("Enter 3 for Merchsales ");
                out.println("Enter 4 for Product Reviews ");
                out.println("Enter 5 for Merchandise Report ");
                out.println("Enter 6 to Exit"); 
                out.println();
                out.println("Enter number here: ");
                numEntered = input.nextInt(); 
                input.nextLine();

                switch(numEntered) {
                case 1: 
                    accountsTable();
                    out.println("Account Options");
                    break;
                case 2: 
                    merchandiseTable();
                    out.println("Merchandise Options");
                    out.println();
                    break;
                case 3: 
                    merchSalesTable();
                    out.println("Merchsales Options");
                    out.println();
                    break;
                case 4: 
                    productReviewsTable();
                    out.println("Product Reviews Options");
                    out.println();
                    break;
                case 5: 
                    merchandiseMerchSalesReviewsTable();
                    out.println("Merchandise Report");
                    out.println();
                    break;
                case 6: 
                    exit = true;
                    out.println("Have a nice day! "); 
                    out.println();
                    break;
                default:
                    out.println("Invalid entry, choose from one the options listed.");
                    out.println();
                    break;
                }

            }
            while (numEntered != 6);  
        } 
        catch (Exception e) { 
            out.println("Invalid entry");
            e.printStackTrace();
        }
    }

    //ACCOUNTS TABLE

    public static void accountsTable() throws SQLException  {

        int accountOpts;

        try { 

            do {

                out.println("Accounts Table");
                out.println();
                out.println("Enter 1 for Add Account");
                out.println("Enter 2 for Edit Account");
                out.println("Enter 3 for  Remove Account");
                out.println("Enter 4 for View Account");
                out.println("Enter 5 back to Main Menu"); 
                out.println();
                out.println("Enter number here: ");
                accountOpts = input.nextInt();
                input.nextLine();
                out.println();

                switch (accountOpts) {
                case 1: 
                    out.println("Add Accounts");
                    insertInfo();
                    return;
                case 2: 
                    out.println("Edit Accounts");
                    updateAccounts();
                    out.println();
                    return;
                case 3: 
                    out.println("Remove Accounts");
                    deleteAccounts(); 
                    out.println();
                    return;
                case 4: 
                    out.println("View Accounts");
                    selectAccounts();    
                    out.println();
                    return;
                case 5: 
                    out.println("Back to Main Menu ");
                    out.println();
                    break; 
                default:
                    out.println("Invalid entry, choose from one the options listed.");
                    out.println();
                    break;
                }
            }
            while (accountOpts != 5);  
        } 
        catch (Exception e) { 
            out.println("Invalid entry");
            e.printStackTrace();
        }
    }

    private static void insertInfo() throws SQLException   {

        out.println();

        connection = connect();

        String SQL = "INSERT INTO Accounts(last, first, address, city, state, email) VALUES(?,?,?,?,?,?)";
        PreparedStatement prepInsertStatement = connection.prepareStatement(SQL);

        out.println("Enter last name: ");
        String lastName = input.nextLine();
        prepInsertStatement.setString(1, lastName);

        out.println("Enter first name: ");
        String firstName = input.nextLine();
        prepInsertStatement.setString(2, firstName);

        out.println("Enter address: ");
        String address = input.nextLine();
        prepInsertStatement.setString(3, address);

        out.println("Enter city: ");
        String city = input.nextLine();
        prepInsertStatement.setString(4,  city);

        out.println("Enter state: ");
        String state = input.nextLine();
        prepInsertStatement.setString(5, state);

        out.println("Enter email: ");
        String email = input.nextLine();
        prepInsertStatement.setString(6, email);

        int result = prepInsertStatement.executeUpdate();
        if(result == 1)
            out.println("Account added. ");
        else
            out.println("Account not added. ");
        out.println();

    }

    private static void updateAccounts() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "UPDATE Accounts SET last = ?, first = ?, address = ?, city = ?, state = ?, "
                + "email = ? WHERE account_id = ?";
        PreparedStatement prepUpdateStatement = connection.prepareStatement(SQL);

        out.println("Enter Account to Update: ");
        int accountUpdate = input.nextInt();
        prepUpdateStatement.setInt(7, accountUpdate);
        input.nextLine();

        out.println("Update last name: ");
        String lastName = input.nextLine();
        prepUpdateStatement.setString(1, lastName);

        out.println("Update first name: ");
        String firstName = input.nextLine();
        prepUpdateStatement.setString(2, firstName);

        out.println("Update address: ");
        String address = input.nextLine();
        prepUpdateStatement.setString(3, address);

        out.println("Update city: ");
        String city = input.nextLine();
        prepUpdateStatement.setString(4,  city);

        out.println("Update state: ");
        String state = input.nextLine();
        prepUpdateStatement.setString(5, state);

        out.println("Update email: ");
        String email = input.nextLine();
        prepUpdateStatement.setString(6, email);

        int updateRow = prepUpdateStatement.executeUpdate();
        if(updateRow == 1)
            out.println("Update Successful. ");
        else
            out.println("Update Not Successful. ");

    }

    private static void deleteAccounts() throws SQLException  {

        out.println();

        connection = connect();

        String SQL = "DELETE FROM Accounts WHERE account_id = ? ";
        PreparedStatement prepDeleteStatement = connection.prepareStatement(SQL);

        out.println("Enter an account_id to remove account: "); 
        int enterAccount = input.nextInt();
        prepDeleteStatement.setInt(1, enterAccount);

        int deleteAccount = prepDeleteStatement.executeUpdate();
        if(deleteAccount == 1)
            out.println("Account deleted. ");
        else
            out.println("Account doesn't exist or not deleted. ");

    }

    private static void selectAccounts() throws SQLException  {

        out.println();

        connection = connect();

        String SQL = "SELECT * FROM accounts WHERE account_id = ? ";

        PreparedStatement prepSelectStatement = connection.prepareStatement(SQL);    

        out.println("Enter account ID to review account: ");
        int accountId = input.nextInt();
        prepSelectStatement.setInt(1, accountId);
        out.println();

        ResultSet rsViewAccount = prepSelectStatement.executeQuery();
        if(!rsViewAccount.isBeforeFirst()) {

            out.println("No rows in Accounts");
        }

        else {

            while (rsViewAccount.next()) {

                out.println("Last name: " + rsViewAccount.getString(2));
                out.println("First name: " + rsViewAccount.getString(3));
                out.println("Address: " + rsViewAccount.getString(4));
                out.println("City: " + rsViewAccount.getString(5));
                out.println("State: " + rsViewAccount.getString(6));
                out.println("Email: " + rsViewAccount.getString(7));

            }
        }
    }

    //MERCHANDISE TABLE

    private static void merchandiseTable()    {

        int merchTableOpts;

        try { 

            do { 

                out.println("Merchandise Table");
                out.println();
                out.println("Enter 1 for Add Merch");
                out.println("Enter 2 for Edit Merch");
                out.println("Enter 3 for  Remove Merch");
                out.println("Enter 4 for View Merch");
                out.println("Enter 5 back to Main Menu"); 
                out.println();
                out.println("Enter number here: ");
                merchTableOpts = input.nextInt();
                input.nextLine();
                out.println();

                switch (merchTableOpts) {
                case 1: 
                    out.println("Add Merch");
                    insertMerch();
                    return;
                case 2: 
                    out.println("Edit Merch");
                    updateMerch();
                    out.println();
                    return;
                case 3: 
                    out.println("Remove Merch");
                    deleteMerch(); 
                    out.println();
                    return;
                case 4: 
                    out.println("View Merch");
                    selectMerch();    
                    out.println();
                    return;
                case 5: 
                    out.println("Back to Main Menu");
                    out.println();
                    break; 
                default:
                    out.println("Invalid entry, choose from one the options listed.");
                    out.println();
                    break;
                }
            }
            while (merchTableOpts != 5);  
        } 
        catch (Exception e) { 
            out.println("Invalid entry");
            e.printStackTrace();
        }
    }

    private static void insertMerch() throws SQLException   {

        out.println();

        connection = connect();

        String SQL = "INSERT INTO Merchandise(item_type, item_cost, category, size) VALUES(?,?,?,?)";
        PreparedStatement prepInsertStatement = connection.prepareStatement(SQL);

        out.println("Enter item type: ");
        String itemType = input.nextLine();
        prepInsertStatement.setString(1, itemType);

        out.println("Enter item cost: ");
        float itemCost = input.nextFloat();   
        prepInsertStatement.setFloat(2, itemCost);
        input.nextLine();

        out.println("Enter category: ");
        String category = input.nextLine();
        prepInsertStatement.setString(3, category);

        out.println("Enter size: ");
        String size = input.nextLine();  
        prepInsertStatement.setString(4,  size);

        int result = prepInsertStatement.executeUpdate();
        if(result == 1)
            out.println("Item added. ");
        else
            out.println("Item not added. ");
        out.println();

    }

    private static void updateMerch() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "UPDATE Merchandise SET item_type = ?, item_cost = ?, category = ?, size = ? "
                + " WHERE item_id = ?";
        PreparedStatement prepUpdateStatement = connection.prepareStatement(SQL);

        out.println("Enter item to Update: ");
        int chooseItem = input.nextInt();
        prepUpdateStatement.setInt(5, chooseItem);
        input.nextLine();

        out.println("Update item type: ");
        String itemUpdate = input.nextLine();
        prepUpdateStatement.setString(1, itemUpdate);

        out.println("Update item cost: ");
        float firstName = input.nextFloat();
        prepUpdateStatement.setFloat(2, firstName);
        input.nextLine();

        out.println("Update category: ");
        String category = input.nextLine();
        prepUpdateStatement.setString(3, category);

        out.println("Update size: ");
        String size = input.nextLine();   
        prepUpdateStatement.setString(4,  size);

        int updateItem = prepUpdateStatement.executeUpdate();
        if(updateItem == 1)
            out.println("Update Successful. ");
        else
            out.println("Update Not Successful. ");
    }

    private static void deleteMerch() throws SQLException  {

        out.println();

        connection = connect();

        String SQL = "DELETE FROM Merchandise WHERE item_id = ? ";
        PreparedStatement prepDeleteStatement = connection.prepareStatement(SQL);

        out.println("Enter an item_id to remove account: "); 
        int enterMerchItem = input.nextInt();
        prepDeleteStatement.setInt(1, enterMerchItem);

        int deleteItem = prepDeleteStatement.executeUpdate();
        if(deleteItem == 1)
            out.println("Item deleted. ");
        else
            out.println("Item doesn't exist or not deleted. ");

    }

    private static void selectMerch() throws SQLException  {

        out.println();

        connection = connect();

        String SQL = "SELECT * FROM merchandise WHERE item_id = ? ";

        PreparedStatement prepSelectStatement = connection.prepareStatement(SQL);    

        out.println("Enter item ID to review merchandise: ");
        int itemId = input.nextInt();
        prepSelectStatement.setInt(1, itemId);
        out.println();

        ResultSet rsViewAccount = prepSelectStatement.executeQuery();
        if(!rsViewAccount.isBeforeFirst()) {

            out.println("No rows in Merchandise");
        }

        else {

            while (rsViewAccount.next()) {

                out.println("Item type: " + rsViewAccount.getString(2));
                NumberFormat costAmountFormat = NumberFormat.getCurrencyInstance(); 
                double costAmount = rsViewAccount.getDouble(3);
                out.println("Item cost: " + costAmountFormat.format(costAmount)); 
                out.println("Category: " + rsViewAccount.getString(4));
                out.println("Size: " + rsViewAccount.getString(5));

            }
        }
    }

    //MERCHSALES TABLE

    private static void merchSalesTable()  {

        int merchSalesTable;

        try { 

            do {

                out.println("Merchandise Sales Table");
                out.println();
                out.println("Enter 1 for Add Sale");
                out.println("Enter 2 for Edit Sale");
                out.println("Enter 3 for  Remove Sale");
                out.println("Enter 4 for View Sale");
                out.println("Enter 5 back to Main Menu"); 
                out.println();
                out.println("Enter number here: ");
                merchSalesTable = input.nextInt();
                input.nextLine();
                out.println();

                switch (merchSalesTable) {
                case 1: 
                    out.println("Add Sale");
                    insertSale();
                    return;
                case 2: 
                    out.println("Edit Sale");
                    updateSale();
                    out.println();
                    return;
                case 3: 
                    out.println("Remove Sale");
                    deleteSale(); 
                    out.println();
                    return;
                case 4: 
                    out.println("View Sale");
                    selectSale();    
                    out.println();
                    return;
                case 5: 
                    out.println("Back to Main Menu");
                    out.println();
                    break; 
                default:
                    out.println("Invalid entry, choose from one the options listed.");
                    out.println();
                    break;
                }
            }
            while (merchSalesTable != 5);  
        } 
        catch (Exception e) { 
            out.println("Invalid entry");
            e.printStackTrace();
        }
    }

    private static void insertSale() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "INSERT INTO Merchsales(date_sold, sale_amount, item_id, account_id) VALUES(?,?,?,?)";
        PreparedStatement prepInsertStatement = connection.prepareStatement(SQL);

        out.println("Enter date sold: ");
        String dateSold = input.nextLine();
        prepInsertStatement.setString(1, dateSold);

        out.println("Enter sale amount: ");
        float saleAmount = input.nextFloat();   
        prepInsertStatement.setFloat(2, saleAmount);
        input.nextLine();

        out.println("Enter item ID: ");
        String itemId = input.nextLine();
        prepInsertStatement.setString(3, itemId);

        out.println("Enter account ID: ");
        String accountId = input.nextLine();  
        prepInsertStatement.setString(4,  accountId);

        int result = prepInsertStatement.executeUpdate();
        if(result == 1)
            out.println("Item added. ");
        else
            out.println("Item not added. ");
        out.println();

    }

    private static void updateSale() throws SQLException {
        out.println();

        connection = connect();

        String SQL = "UPDATE Merchsales SET date_sold = ?, sale_amount = ?, item_id = ?, account_id = ? "
                + " WHERE sale_id = ?";
        PreparedStatement prepUpdateStatement = connection.prepareStatement(SQL);

        out.println("Enter sale ID to Update: ");
        int saleUpdate = input.nextInt();
        prepUpdateStatement.setInt(5, saleUpdate);
        input.nextLine();

        out.println("Update date sold: ");
        String dateSoldUpdate = input.nextLine();
        prepUpdateStatement.setString(1, dateSoldUpdate);

        out.println("Update sale amount: ");
        double saleAmountUpdate = input.nextDouble();
        prepUpdateStatement.setDouble(2, saleAmountUpdate);
        input.nextLine();

        out.println("Update item ID: ");
        int itemIdUpdate = input.nextInt();
        prepUpdateStatement.setInt(3, itemIdUpdate);
        input.nextLine();

        out.println("Update account ID: ");
        int accountIdUpdate = input.nextInt();   
        prepUpdateStatement.setInt(4,  accountIdUpdate);
        input.nextLine();

        int updateItem = prepUpdateStatement.executeUpdate();
        if(updateItem == 1)
            out.println("Update Successful. ");
        else
            out.println("Update Not Successful. ");


    }

    private static void deleteSale() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "DELETE FROM Merchsales WHERE sale_id = ? ";
        PreparedStatement prepDeleteStatement = connection.prepareStatement(SQL);

        out.println("Enter an sales_id to remove account: "); 
        int enterSaleItem = input.nextInt();
        prepDeleteStatement.setInt(1, enterSaleItem);

        int deleteItem = prepDeleteStatement.executeUpdate();
        if(deleteItem == 1)
            out.println("Sale deleted. ");
        else
            out.println("Sale doesn't exist or not deleted. ");


    }

    private static void selectSale() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "SELECT * FROM merchsales WHERE sale_id = ? ";

        PreparedStatement prepSelectStatement = connection.prepareStatement(SQL);    

        out.println("Enter sale ID to review sale: ");
        int saleId = input.nextInt();
        prepSelectStatement.setInt(1, saleId);
        out.println();

        ResultSet rsViewAccount = prepSelectStatement.executeQuery();
        if(!rsViewAccount.isBeforeFirst()) {

            out.println("No item with this sale ID exists. ");
        }

        else {

            while (rsViewAccount.next()) {

                out.println("Date sold: " + rsViewAccount.getString(2));
                NumberFormat saleAmountFormat = NumberFormat.getCurrencyInstance(); //review 676-678
                double saleAmount = rsViewAccount.getDouble(3);
                out.println("Sale amount: " + saleAmountFormat.format(saleAmount)); 
                out.println("Item ID: " + rsViewAccount.getInt(4));
                out.println("Account ID: " + rsViewAccount.getInt(5));

            }
        }
    }


    private static void productReviewsTable() {

        int productReviewsTable;

        try { 

            do {

                out.println("Product Reviews Table");
                out.println();
                out.println("Enter 1 for Add Review");
                out.println("Enter 2 for Edit Review");
                out.println("Enter 3 for  Remove Review");
                out.println("Enter 4 for View Review");
                out.println("Enter 5 back to Main Menu"); 
                out.println();
                out.println("Enter number here: ");
                productReviewsTable = input.nextInt();
                input.nextLine();
                out.println();

                switch (productReviewsTable) {
                case 1: 
                    out.println("Add Review");
                    insertReview();
                    return;
                case 2: 
                    out.println("Edit Sale");
                    updateReview();
                    out.println();
                    return;
                case 3: 
                    out.println("Remove Sale");
                    deleteReview(); 
                    out.println();
                    return;
                case 4: 
                    out.println("View Sale");
                    selectReview();    
                    out.println();
                    return;
                case 5: 
                    out.println("Back to Main Menu");
                    out.println();
                    break; 
                default:
                    out.println("Invalid entry, choose from one the options listed.");
                    out.println();
                    break;
                }
            }
            while (productReviewsTable != 5);  
        } 
        catch (Exception e) { 
            out.println("Invalid entry");
            e.printStackTrace();
        }
    }

    private static void insertReview() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "INSERT INTO Productreviews(brand, description_milk, cost, location_id, country, state, city, business_name, grocery_store) VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepInsertStatement = connection.prepareStatement(SQL);

        out.println("Enter brand: ");
        String brand = input.nextLine();
        prepInsertStatement.setString(1, brand);

        out.println("Enter description (up to 30 characters): ");
        String description = input.nextLine();   
        prepInsertStatement.setString(2, description);

        out.println("Enter cost: ");
        double cost = input.nextDouble();
        prepInsertStatement.setDouble(3, cost);
        input.nextLine();

        out.println("Enter location ID (area code): ");
        int locationId = input.nextInt();  
        prepInsertStatement.setInt(4,  locationId);
        input.nextLine();

        out.println("Enter country: ");
        String country = input.nextLine();  
        prepInsertStatement.setString(5,  country);

        out.println("Enter state: ");
        String state = input.nextLine();  
        prepInsertStatement.setString(6,  state);

        out.println("Enter city: ");
        String city = input.nextLine();  
        prepInsertStatement.setString(7,  city);

        out.println("Enter business name: ");
        String businessName = input.nextLine();  
        prepInsertStatement.setString(8,  businessName);

        out.println("Enter grocery store Y or N?: ");
        String groceryStore = input.nextLine();  
        prepInsertStatement.setString(9,  groceryStore);

        int result = prepInsertStatement.executeUpdate();
        if(result == 1)
            out.println("Review added. ");
        else
            out.println("Review not added. ");
        out.println();

    }

    private static void updateReview() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "UPDATE Productreviews SET brand = ?, description_milk = ?, cost = ?, location_id = ?, country = ?, state = ?, city = ?, business_name = ?, grocery_store = ? "
                + " WHERE review_numbs = ?";
        PreparedStatement prepUpdateStatement = connection.prepareStatement(SQL);

        out.println("Enter review ID to Update: ");
        int reviewUpdate = input.nextInt();
        prepUpdateStatement.setInt(10, reviewUpdate);
        input.nextLine();

        out.println("Update brand: ");
        String brandUpdate = input.nextLine();
        prepUpdateStatement.setString(1, brandUpdate);

        out.println("Update description (up to 30 characters): ");
        String descriptionUpdate = input.nextLine();
        prepUpdateStatement.setString(2, descriptionUpdate);

        out.println("Update cost: ");
        double cost = input.nextDouble();
        prepUpdateStatement.setDouble(3, cost);
        input.nextLine();

        out.println("Update location ID (area code): ");
        int locationIdUpdate = input.nextInt();   
        prepUpdateStatement.setInt(4,  locationIdUpdate);
        input.nextLine();

        out.println("Update country: ");
        String countryUpdate = input.nextLine();   
        prepUpdateStatement.setString(5,  countryUpdate);

        out.println("Update state: ");
        String stateUpdate = input.nextLine();   
        prepUpdateStatement.setString(6,  stateUpdate);

        out.println("Update city: ");
        String cityUpdate = input.nextLine();   
        prepUpdateStatement.setString(7,  cityUpdate);

        out.println("Update business name: ");
        String businessNameUpdate = input.nextLine();   
        prepUpdateStatement.setString(8,  businessNameUpdate);

        out.println("Update grocery store Y/N: ");
        String groceryStoreUpdate = input.nextLine();   
        prepUpdateStatement.setString(9,  groceryStoreUpdate);

        int updateItem = prepUpdateStatement.executeUpdate();
        if(updateItem == 1)
            out.println("Update Successful. ");
        else
            out.println("Update Not Successful. ");

    }

    private static void deleteReview() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "DELETE FROM Productreviews WHERE review_numbs = ? ";
        PreparedStatement prepDeleteStatement = connection.prepareStatement(SQL);

        out.println("Enter an review ID to remove review: "); 
        int reviewIdDelete = input.nextInt();
        prepDeleteStatement.setInt(1, reviewIdDelete);

        int deleteItem = prepDeleteStatement.executeUpdate();
        if(deleteItem == 1)
            out.println("Sale deleted. ");
        else
            out.println("Sale doesn't exist or not deleted. ");

    }

    private static void selectReview() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "SELECT * FROM Productreviews WHERE review_numbs = ? ";

        PreparedStatement prepSelectStatement = connection.prepareStatement(SQL);    

        out.println("Enter review ID to view review: ");
        int reviewIdView = input.nextInt();
        prepSelectStatement.setInt(1, reviewIdView);
        out.println();

        ResultSet rsViewAccount = prepSelectStatement.executeQuery();
        if(!rsViewAccount.isBeforeFirst()) {

            out.println("Item to review doesn't exist. ");
        }

        else {

            while (rsViewAccount.next()) {

                out.println("Brand: " + rsViewAccount.getString(2));
                out.println("Description: " + rsViewAccount.getString(3));
                NumberFormat costAmountFormat = NumberFormat.getCurrencyInstance();
                double costAmount = rsViewAccount.getDouble(4);
                out.println("Cost: " + costAmountFormat.format(costAmount));
                out.println("Location/Area Code: " + rsViewAccount.getString(5));
                out.println("Country: " + rsViewAccount.getString(6));
                out.println("State: " + rsViewAccount.getString(7));
                out.println("City: " + rsViewAccount.getString(8));
                out.println("Business name: " + rsViewAccount.getString(9));
                out.println("Grocery store: " + rsViewAccount.getString(10));

            }

        }
    }

    private static void merchandiseMerchSalesReviewsTable() throws SQLException {

        out.println();

        connection = connect();

        String SQL = "SELECT size, date_sold, sale_amount from merchandise inner join merchsales on merchandise.ITEM_ID = merchsales.ITEM_ID ";

        PreparedStatement prepJoinStatement = connection.prepareStatement(SQL);

        ResultSet rs = prepJoinStatement.executeQuery(SQL);
        out.println("Size Date Sold    Sale Amount");

        while (rs.next()) {
            
            String size = rs.getString(1);
            String dateSold = rs.getString(2);
            NumberFormat saleAmountFormat = NumberFormat.getCurrencyInstance();
            double saleAmount = rs.getDouble(3);
            out.println(size + "    " + dateSold + "   " + saleAmountFormat.format(saleAmount));

        }
    }
}








