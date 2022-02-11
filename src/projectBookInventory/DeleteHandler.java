package projectBookInventory;

/** 
 * This class deletes a book 
 * @author Jeff Merlehan  - 20056361
 */

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class DeleteHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		System.out.println("DeleteHandler Called");
		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

		// Get param from URL
		Map<String, String> params = Util.requestStringToMap(he.getRequestURI().getQuery());

		// get ID number
		int ID = Integer.parseInt(params.get("id"));

		BOOKDao books = new BOOKDao();

		try {
			// get the book details before deleting from the Database
			Book deletedBook = books.getBookPS(ID);
			// delete from database;
			books.deleteBookPS(ID);
			// produce page displaying the book that has been deleted
			out.write("<html>" + "<head> <title>Book Library</title> "
					+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
					+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
					+ "</head>" 
					+ "<body>" 
					+ "<h1> Book Deleted</h1>" 
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
					+ "    <td>" + deletedBook.getId() + "</td>" 
					+ "    <td>" + deletedBook.getTitle() + "</td>" 
					+ "    <td>" + deletedBook.getAuthor() + "</td>" 
					+ "    <td>" + deletedBook.getYear() + "</td>" 
					+ "    <td>" + deletedBook.getEdition() + "</td>" 
					+ "    <td>" + deletedBook.getPublisher() + "</td>" 
					+ "    <td>" + deletedBook.getIsbn() + "</td>" 
					+ "    <td>" + deletedBook.getCover() + "</td>" 
					+ "    <td>" + deletedBook.getCondition() + "</td>" 
					+ "    <td>" + deletedBook.getPrice() + "</td>" 
					+ "    <td>" + deletedBook.getNotes() + "</td>" 
					+ "  </tr>");

			out.write("</tbody>" 
					+ "</table>" 
					+ "<a href=\"/admin\">Back to List </a>" 
					+ "</body>" 
					+ "</html>");
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		out.close();

	}

}
