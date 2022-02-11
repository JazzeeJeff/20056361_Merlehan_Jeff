package projectBookInventory;

import java.util.Scanner;
import java.util.ArrayList;
import java.sql.SQLException;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.io.IOException;

/**
 * Main handles the main menu and contains both the createBook and updateBook
 * method
 * 
 * @since 2020-12-20
 * @author Jeff Merlehan - 20056361
 *
 */

public class main {
	static final private int PORT = 8005;

	public static void main(String[] args) throws SQLException, IOException {
		//creates new server running on port 8005 and HttpContexts listed below for different forms and functions
		HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
		server.createContext("/", new RootHandler());
		server.createContext("/form", new FormHandler());
		server.createContext("/login", new LoginFormHandler());
		server.createContext("/admin", new AdminHandler());
		server.createContext("/add", new AddBookHandler());
		server.createContext("/addBook", new AddBookFormHandler());
		server.createContext("/delete", new DeleteHandler());
		server.createContext("/edit", new EditBookHandler());
		server.createContext("/editBook", new EditFormHandler());
		server.setExecutor(null);
		server.start();
		System.out.println("The server is listening on port " + PORT);
		//new scanner for menu selection
		Scanner in = new Scanner(System.in);
		String selection;
		BOOKDao books = new BOOKDao();

		// Display the menu
		do {
			System.out.println(
					"-----------------------------------\n          Book inventory \n     Choose from these options\n-----------------------------------");
			System.out.println("1\t Retrieve all books");
			System.out.println("2\t Search for book");
			System.out.println("3\t Insert new book into database");
			System.out.println("4\t Update book details");
			System.out.println("5\t Delete book from database");
			System.out.println("6\t Exit");
			System.out.println("------------------------------");
			System.out.print("Please enter your choice: ");

			// Get user's choice
			selection = in.nextLine();

			// Takes users choice and and performs desired action/calls relevant methods
			switch (selection) {
			case "1":
				System.out.println("Retrieve all books");
				ArrayList<Book> allBooks = books.getAllBOOKs();
				for (int i = 0; i < allBooks.size(); i++) {
					System.out.println(allBooks.get(i));
				}
				break;
			case "2":
				System.out.println("Enter book ID");
				int ID = Integer.parseInt(in.nextLine());
				System.out.println(books.getBookPS(ID));
				break;
			case "3":
				System.out.println("Insert new book into database");
				Book book = createBook();
				books.addBookPS(book);
				System.out.println(book);
				break;
			case "4":
				System.out.println("Update book details");
				System.out.println("Enter Book ID to update");
				int uID = Integer.parseInt(in.nextLine());
				System.out.println(books.getBookPS(uID));
				Book updatedBook = updateBook(books.getBookPS(uID));
				books.updateBookPS(updatedBook);
				System.out.println(updatedBook);
				break;
			case "5":
				System.out.println("Delete book from database");
				System.out.println("Enter ID of Book to remove");
				int dID = Integer.parseInt(in.nextLine());
				books.deleteBookPS(dID);
				System.out.println("Book ID " + dID + " deleted");
				break;
			case "6":
				System.out.println("Exit");
				break;			

			default:
				System.out.println("Invalid choice");
			}
		} while (!selection.equals("6"));
	}

	/**
	 * Method to create a new book object
	 * 
	 * @param none
	 * @return a new book obj
	 */
	private static Book createBook() {
		int id;
		String title;
		String author;
		int year;
		int edition;
		String publisher;
		String isbn;
		String cover;
		String condition;
		double price;
		String notes;
		//new scanner to input data of new book
		Scanner in = new Scanner(System.in);
		System.out.println("please enter an ID");
		id = Integer.parseInt(in.nextLine());
		System.out.println("Please enter Title");
		title = in.nextLine();
		System.out.println("please enter the Author");
		author = in.nextLine();
		System.out.println("please enter a year");
		year = Integer.parseInt(in.nextLine());
		System.out.println("please enter an edition");
		edition = Integer.parseInt(in.nextLine());
		System.out.println("please enter the Publisher");
		publisher = in.nextLine();
		System.out.println("please enter the ISBN");
		isbn = in.nextLine();
		System.out.println("please enter the cover");
		cover = in.nextLine();
		System.out.println("please enter the condition");
		condition = in.nextLine();
		System.out.println("please enter a price");
		price = Double.parseDouble(in.nextLine());
		System.out.println("please enter any notes");
		notes = in.nextLine();

		return new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);

	}

	/**
	 * Method used to update book object in db, doesnt allow updating the ID
	 * 
	 * @param book object
	 * @return returns an updated book object
	 */
	private static Book updateBook(Book book) {
		String title;
		String author;
		int year;
		int edition;
		String publisher;
		String isbn;
		String cover;
		String condition;
		double price;
		String notes;
		//creates scanner to input book data, if nothing is updated it will use the current data 
		Scanner in = new Scanner(System.in);
		System.out.println("Updating Book with ID " + book.getId());

		System.out.println("Please enter title");
		title = in.nextLine();
		if (title.equals(""))
			title = book.getTitle();

		System.out.println("Please enter author");
		author = in.nextLine();
		if (author.equals(""))
			author = book.getAuthor();

		System.out.println("Please enter year");
		String strYear = in.nextLine();
		if (strYear.equals(""))
			year = book.getYear();
		else
			year = Integer.parseInt(strYear);

		System.out.println("Please enter edition");
		String strEdition = in.nextLine();
		if (strEdition.equals(""))
			edition = book.getEdition();
		else
			edition = Integer.parseInt(strEdition);

		System.out.println("Please enter publisher");
		publisher = in.nextLine();
		if (publisher.equals(""))
			publisher = book.getPublisher();

		System.out.println("Please enter ISBN");
		isbn = in.nextLine();
		if (isbn.equals(""))
			isbn = book.getIsbn();

		System.out.println("Please enter cover type");
		cover = in.nextLine();
		if (cover.equals(""))
			cover = book.getCover();

		System.out.println("Please enter the condition");
		condition = in.nextLine();
		if (condition.equals(""))
			condition = book.getCondition();

		System.out.println("Please enter price");
		String strPrice = in.nextLine();
		if (strPrice.equals(""))
			price = book.getPrice();
		else
			price = Double.parseDouble(strPrice);

		System.out.println("Please enter notes");
		notes = in.nextLine();
		if (notes.equals(""))
			notes = book.getNotes();
		
		return new Book(book.getId(), title, author, year, edition, publisher, isbn, cover, condition, price, notes);

	}

}
