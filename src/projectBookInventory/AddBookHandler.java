package projectBookInventory;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Displays a form to add details of new book to db and button to return to
 * previous admin page
 * 
 * @author Jeff Merlehan - 20056361
 *
 */
public class AddBookHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		// form to input new book details
		out.write("<html>" + "<head> <title>Add Book</title> </head>"
				+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
				+ "<body>" 
				+ "<h1>Add Book</h1>" 
				+ "<form  name=\"New Book\" method=\"post\" action=\"/addBook\">"
				+ "<label for=\"id\">Book ID:</label>" + "<input type=\"text\" name=\"id\"/> <br/>" 

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
				+ "</body>" + "</html>");
		out.close();
	}

}
