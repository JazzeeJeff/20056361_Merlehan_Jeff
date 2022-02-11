package projectBookInventory;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Contains initial page containing a list of all books in database and link to
 * admin login
 * 
 * @author Jeff Merlehan - 20056361
 *
 */
public class RootHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		BOOKDao books = new BOOKDao();
		try {
			//an array list stores all books from db
			ArrayList<Book> allBOOKS = books.getAllBOOKs();

			out.write("<html>" + "<head> <title>Book Repository</title> "
					+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
					+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
					+ "</head>" 
					+ "<body>" 
					+ "<div class=\"/ text-center\">" 
					+ "<h1> Books in Stock!</h1>" 
					+ "</div>"
					+ "<div class=\"d-flex justify-content-between\"></div>"
					+ "<button class=\"btn btn-primary btn-lg md-2\" onclick=\"window.location.href='/form';\">Login</button>"
					+ "</div>" 
					+ "<table class=\"table table-striped\">" 
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
			//for loop goes through each listing in array list and prints out details for each one
			for (Book d : allBOOKS) {
				out.write("  <tr>" 
						+ "    <td>" + d.getId() + "</td>" 
						+ "    <td>" + d.getTitle() + "</td>" 
						+ "    <td>" + d.getAuthor() + "</td>" 
						+ "    <td>" + d.getYear() + "</td>" 
						+ "    <td>" + d.getEdition() + "</td>" 
						+ "    <td>" + d.getPublisher() + "</td>" 
						+ "    <td>" + d.getIsbn() + "</td>"
						+ "    <td>" + d.getCover() + "</td>" 
						+ "    <td>" + d.getCondition() + "</td>" 
						+ "    <td>" + d.getPrice() + "</td>" 
						+ "    <td>" + d.getNotes() + "</td>" + "  </tr>");
			}
			out.write("</tbody>" 
					+ "</table>" 
					+ "</body>" 
					+ "</html>");
			
		} catch (SQLException se) {
			System.out.println(se.getMessage());
		}
		out.close();

	}

}