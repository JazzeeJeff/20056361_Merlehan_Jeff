package projectBookInventory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.HashMap;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * handler for adding new book
 * @author Jeff Merlehan - 20056361
 *
 */
public class AddBookFormHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		System.out.println("In AddBookFormHandler");
		BOOKDao books = new BOOKDao();
		//creates new text reader
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

		String line;
		String request = "";
		while ((line = in.readLine()) != null) {
			request = request + line;
		}		
		// Takes inputs from form and converts if needed to int or double
		HashMap<String, String> map = Util.requestStringToMap(request);

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
		// creates new book obj with details from form
		Book book = new Book(id, title, author, year, edition, publisher, isbn, cover, condition, price, notes);

		// checks validity of ISBN before adding book. If boolean returns false reloads form to try again		
		try {
			boolean isbnValid = books.validateIsbn10(isbn);
			if (isbnValid == false) {
				System.out.println(isbnValid);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
				he.sendResponseHeaders(200, 0);
				out.write(
						"<html>" 
						+ "<head> <title>Add Book</title> </head>"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
						+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
						+ "<body>" 
						+ "<h1>Add Book</h1>" 
						+ "<h2>Invalid ISBN-10</h2>"
						+ "<form  name=\"New Book\" method=\"post\" action=\"/addBook\">"
						+ "<label for=\"id\">Book ID:</label>" 
						+ "<input type=\"text\" name=\"id\"/> <br/>" 
						+ "<label for=\"title\">Title:</label>" + "<input type=\"text\" name=\"title\"/> <br/>" 
						+ "<label for=\"author\">Author:</label>" + "<input type=\"text\" name=\"author\"/> <br/>" 
						+ "<label for=\"year\">Year:</label>" + "<input type=\"text\" name=\"year\"/> <br/>" 
						+ "<label for=\"edition\">Edition:</label>" + "<input type=\"text\" name=\"edition\"/> <br/>" 
						+ "<label for=\"publisher\">Publisher:</label>" + "<input type=\"text\" name=\"publisher\"/> <br/>" 
						+ "<label for=\"isbn\">ISBN:</label>" + "<input type=\"text\" name=\"isbn\"/> <br/>" 
						+ "<label for=\"cover\">Cover:</label>" + "<input type=\"text\" name=\"cover\"/> <br/>" 
						+ "<label for=\"condition\">Condition:</label>" + "<input type=\"text\" name=\"condition\"/> <br/>" 
						+ "<label for=\"price\">Price:</label>" + "<input type=\"text\" name=\"price\"/> <br/>" 
						+ "<label for=\"notes\">Notes:</label>" + "<input type=\"text\" name=\"notes\"/> <br/>" 
						+ "<input class=\"btn btn-primary\" type=\"submit\" value=\"Add Book\" />" 
						+ "</form>"
						+ "<button class=\"btn btn-primary\" onclick=\"window.location.href='/admin';\">Return</button>"
						+ "</body>" 
						+ "</html>");
				out.close();
			} else if (isbnValid == true) {
				// boolean true prints out a success page with the book details displayed
				books.addBookPS(book);
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
				he.sendResponseHeaders(200, 0);
				out.write("<html>" + "<head> <title>Book Library</title> "
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
						+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
						+ "</head>" 
						+ "<body>" 
						+ "<h1> Book Added!</h1>" 
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

				out.write(
						"  <tr>" 
						+ "    <td>" + book.getId()	+ "</td>" 
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

				out.write(
						"</tbody>" 
						+ "</table>" 
						+ "<a href=\"/admin\">Back to List </a>" 
						+ "</body>" 
						+ "</html>");
				//closes stream
				out.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

}
