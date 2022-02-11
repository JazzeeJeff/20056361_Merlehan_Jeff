package projectBookInventory;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.HashMap;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Edit book form handler. Collects data from form and creates a book object
 * that replaces the old one. Displays details of updated book with a button to
 * return to main admin list.
 * 
 * @author Jeff Merlehan - 20056361
 *
 */
public class EditFormHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		System.out.println("In EditHandler");
		BOOKDao books = new BOOKDao();

		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

		// get ID number
		int ID = Integer.parseInt(params.get("id"));
		System.out.println(ID);
		String line;
		String request = "";
		while ((line = in.readLine()) != null) {
			request = request + line;
		}
		HashMap<String, String> map = Util.requestStringToMap(request);
		// assign form inputs
		String idst = map.get("id");
		int id = Integer.parseInt(idst);
		String title = map.get("title");
		String author = map.get("author");
		String yearst = map.get("year");
		int year = Integer.parseInt(yearst);
		String editionst = map.get("edition");
		int edition = Integer.parseInt(editionst);
		String publisher = map.get("publisher");
		String isbn = map.get("isbn");
		String cover = map.get("cover");
		String condition = map.get("condition");
		String pricest = map.get("price");
		Double price = Double.parseDouble(pricest);
		String notes = map.get("notes");

		Book editBook;
		// tries to edit book as long as the ISBN number is valid and creates new book
		// object to overwrite old one
		try {
			boolean isbnValid = books.validateIsbn10(isbn);
			if (isbnValid == true) {
				editBook = books.getBookPS(ID);
				System.out.println(editBook);
				Book book = new Book(editBook.getId(), title, author, year, edition, publisher, isbn, cover, condition,
						price, notes);
				System.out.println(book);
				books.updateBookPS(book);

				out.write("<html>" + "<head> <title>Book Library</title> "
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
						+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
						+ "</head>" 
						+ "<body>" 
						+ "<h1> Book Edited</h1>" 
						+ "<table class=\"table\">" 
						+ "<thead>"
						+ "  <tr>" 
						+ "    <th>ID</th>" 
						+ "    <th>Title</th>" 
						+ "    <th>Genre</th>"
						+ "    <th>Year</th>" 
						+ "    <th>Edition</th>" 
						+ "    <th>Publisher</th>" 
						+ "    <th>ISBN</th>"
						+ "    <th>Cover</th>" 
						+ "    <th>Condition</th>" 
						+ "    <th>Price £</th>"
						+ "    <th>Notes</th>" 
						+ "  </tr>" 
						+ "</thead>" 
						+ "<tbody>");

				out.write("  <tr>" 
						+ "    <td>" + book.getId() + "</td>" 
						+ "    <td>" + book.getTitle() + "</td>"
						+ "    <td>" + book.getAuthor() + "</td>" 
						+ "    <td>" + book.getYear() + "</td>" 
						+ "    <td>" + book.getEdition() + "</td>" 
						+ "    <td>" + book.getPublisher() + "</td>" 
						+ "    <td>" + book.getIsbn() + "</td>" 
						+ "    <td>" + book.getCover() + "</td>" 
						+ "    <td>" + book.getCondition() + "</td>" 
						+ "    <td>" + book.getPrice() + "</td>" 
						+ "    <td>" + book.getNotes() + "</td>" 
						+ "  </tr>");

				out.write("</tbody>" 
						+ "</table>" 
						+ "<a href=\"/admin\">Back to List </a>" 
						+ "</body>" 
						+ "</html>");
				// if ISBN number is invalid, prompts user to go back to main admin page
			} else if (isbnValid == false) {
				out.write("<html>" 
						+ "<head> <title>Add Book</title> </head>"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
						+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
						+ "<body>" 
						+ "<h1>Invalid ISBN 10</h1>"
						+ "<button class=\"btn btn-primary\" onclick=\"window.location.href='/admin';\">Return</button>"
						+ "</body>" 
						+ "</html>");
				out.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		out.close();
	}

}
