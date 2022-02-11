package projectBookInventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

/**
 * Contains all the methods called from the menu to update, edit, delete and
 * insert book object to db
 * 
 * @author Jeff Merlehan - 20056361
 *
 */

public class BOOKDao {

	public BOOKDao() {
	}
	/**
	 * Creates db connection
	 * @return dbConnection 
	 */
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = "jdbc:sqlite:book.sqlite";
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	/**
	 * ArrayList used to retrieve all rows in db
	 * 
	 * @return returns an array list of all objects stored in "collections" database
	 * @throws SQLException
	 */
	public ArrayList<Book> getAllBOOKs() throws SQLException {
		System.out.println("Retrieving all books ...");
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;
		String query = "SELECT * FROM collection;";
		ArrayList<Book> books = new ArrayList<>();

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery = " + query);
			result = statement.executeQuery(query);
			while (result.next()) {

				int id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				double price = result.getDouble("Price");
				String notes = result.getString("Notes");

				books.add(new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes));
			}
		} catch (Exception e) {
			System.out.println("get all books: " + e);
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return books;
	}

	/**
	 * Method used to retrieve a book from db
	 * 
	 * @param book_id an id allocated to each book object
	 * @return requested book obj
	 * @throws SQLException
	 */
	public Book getBookPS(int book_id) throws SQLException {

		Book temp = null;
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		String SQL_GET = "SELECT * FROM collection WHERE ID =?;";

		try {
			dbConnection = getDBConnection();
			stmt = dbConnection.prepareStatement(SQL_GET);
			System.out.println("DBQuery: " + SQL_GET);
			// set parameters
			stmt.setInt(1, book_id);
			// execute SQL query
			result = stmt.executeQuery();

			while (result.next()) {
				// allocate book details
				int id = result.getInt("ID");
				String title = result.getString("Title");
				String author = result.getString("Author");
				int year = result.getInt("Year");
				int edition = result.getInt("Edition");
				String publisher = result.getString("Publisher");
				String isbn = result.getString("ISBN");
				String cover = result.getString("Cover");
				String condition = result.getString("Condition");
				double price = result.getDouble("Price");
				String notes = result.getString("Notes");
				// create object
				temp = new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);

			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	/**
	 * Method for deleting a book from db
	 * 
	 * @param book_id
	 * @throws SQLException
	 */
	public boolean deleteBookPS(int Book_id) throws SQLException {
		System.out.println("Deleting book");
		Connection dbConnection = null;
		PreparedStatement stmt = null;
		int result = 0;
		// Prepare statement
		String SQL_DELETE = "DELETE FROM collection WHERE ID =?;";
		try {
			dbConnection = getDBConnection();
			stmt = dbConnection.prepareStatement(SQL_DELETE);
			System.out.println(SQL_DELETE);
			// set parameters
			stmt.setInt(1, Book_id);
			// execute SQL query
			result = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method for updating book already in db
	 * 
	 * @param book obj
	 * @return nothing
	 * @throws SQLException
	 */
	public Boolean updateBookPS(Book book) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement statement = null;

		String SQL_UPDATE = "UPDATE collection SET ID = ?, Title = ?, Author= ?, Year= ?, Edition= ?, Publisher= ?, ISBN= ?, Cover= ?, Condition= ?, Price= ?, Notes= ? WHERE ID = ?;";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement(SQL_UPDATE);
			System.out.println(SQL_UPDATE);
			// set parameters
			statement.setInt(1, book.getId());
			statement.setString(2, book.getTitle());
			statement.setString(3, book.getAuthor());
			statement.setInt(4, book.getYear());
			statement.setInt(5, book.getEdition());
			statement.setString(6, book.getPublisher());
			statement.setString(7, book.getIsbn());
			statement.setString(8, book.getCover());
			statement.setString(9, book.getCondition());
			statement.setDouble(10, book.getPrice());
			statement.setString(11, book.getNotes());
			statement.setInt(12, book.getId());
			// execute SQL update
			statement.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;

		} finally {

			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return true;
	}

	/**
	 * Method for adding a book to db
	 * 
	 * @return a result for adding book to db
	 * @throws SQLException
	 */

	public boolean addBookPS(Book in) throws SQLException {
		Connection dbConnection = null;
		PreparedStatement statement = null;
		String SQL_ADD = "INSERT INTO collection (ID, Title, Author, Year, Edition, Publisher, ISBN, Cover, Condition, Price, Notes) VALUES (?, ?, ? , ?, ?, ?, ?, ?, ?, ?, ?);";
		boolean ok = false;
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.prepareStatement(SQL_ADD);
			System.out.println(SQL_ADD);
			// set parameters
			statement.setInt(1, in.getId());
			statement.setString(2, in.getTitle());
			statement.setString(3, in.getAuthor());
			statement.setInt(4, in.getYear());
			statement.setInt(5, in.getEdition());
			statement.setString(6, in.getPublisher());
			statement.setString(7, in.getIsbn());
			statement.setString(8, in.getCover());
			statement.setString(9, in.getCondition());
			statement.setDouble(10, in.getPrice());
			statement.setString(11, in.getNotes());						
			// execute SQL query
			statement.executeUpdate();
			ok = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		return ok;
	}

	/**
	 * Method for checking password given against password stored in db
	 * 
	 * @param user - username entered
	 * @param pass - password entered
	 * @return boolean result for matching password entry
	 * @throws SQLException
	 */
	public boolean getPass(String user, String pass) throws SQLException {

		boolean temp = false;
		Connection dbConnection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM users WHERE username ='" + user + "';";

		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			System.out.println("DBQuery: " + query);
			// execute SQL query
			result = statement.executeQuery(query);

			while (result.next()) {
				// assign username and password
				String username = result.getString("username");
				String password = result.getString("password");

				// checks password with db
				if (password.equals(pass)) {
					System.out.println("Password Correct. Welcome " + user);
					temp = true;
				} else {
					System.out.println("Username/Password incorrect");
					temp = false;
				}

			}
		} finally {
			if (result != null) {
				result.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return temp;
	}

	/**
	 * takes isbn string and validates correct format
	 * 
	 * @param isbn isbn number entered for book needing validating
	 * @return boolean response
	 */
	public boolean validateIsbn10(String isbn) {

		if (isbn == null) {
			return false;
		}

		// remove any hyphens
		isbn = isbn.replaceAll("-", "");

		// checks ISBN is 10 digits long
		if (isbn.length() != 10) {
			return false;
		}

		try {
			int total = 0;
			for (int i = 0; i < 9; i++) {
				int digit = Integer.parseInt(isbn.substring(i, i + 1));
				total += ((10 - i) * digit);
			}

			String checksum = Integer.toString((11 - (total % 11)) % 11);
			if ("10".equals(checksum)) {
				checksum = "X";
			}

			return checksum.equals(isbn.substring(9));
		} catch (NumberFormatException nfe) {
			// to catch invalid ISBNs that have non-numeric characters in them
			return false;
		}
	}

}
