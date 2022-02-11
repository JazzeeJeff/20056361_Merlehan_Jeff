package projectBookInventory;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Outputs the admin page list of books as well as links to log out, add book,
 * edit book and delete book
 * 
 * @author Jeff Merlehan - 20056361
 *
 */
public class AdminHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		BOOKDao books = new BOOKDao();
		try {
			ArrayList<Book> allBOOKS = books.getAllBOOKs();
			//Displays all books in database along with the admin features
			out.write("<html>" + "<head> <title>Book Repository</title> "
					+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
					+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
					+ "</head>" 
					+ "<body>" 
					+ "<h1> Books in Stock!</h1>"
					+ "<button class=\"btn btn-danger\" onclick=\"window.location.href='/';\">Logout</button>"
					+ "<button class=\"btn btn-success\" onclick=\"window.location.href='/add';\">Add</button>"
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

			int i;
			for (Book d : allBOOKS) {

				out.write(
						"  <tr>" 
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
						+ "    <td>" + d.getNotes() + "</td>" 
						+ "	   <td> <a href=\"/edit?id=" + d.getId() + "\" class=\"/btn btn-primary btn-sm md-2\"> Edit </a> </td>"
						+ "	   <td> <a href=\"/delete?id=" + d.getId() 	+ "\" class=\"/btn btn-primary btn-sm md-2\"> Delete </a> </td>" 
						+ "  </tr>");
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
