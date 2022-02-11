package projectBookInventory;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Outputs a login form for admin page
 * 
 * @author Jeff Merlehan
 *
 */
public class FormHandler implements HttpHandler {
	public void handle(HttpExchange he) throws IOException {
		String response = "Welcome to OOP";
		he.sendResponseHeaders(200, 0);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		//displays a login form 
		out.write("<html>" + "<head> <title>Admin Login Page</title> </head>"
				+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
				+ "<body>" 
				+ "<div>" 
				+ "<h1> Administrator Login </h1>"
				+ "<form name=\"loginForm\" method=\"post\" action=\"/login\">"
				+ "<label for=\"username\">Username:</label>" + "<input type=\"text\" name=\"username\"/> <br/>"
				+ "<label for=\"password\">Password:</label>" + "<input type=\"password\" name=\"password\"/> <br/>"
				+ "<input class=\"btn btn-primary\" type=\"submit\" value=\"Login\" />" 
				+ "</form>"
				+ "<button class=\"btn btn-primary\" onclick=\"window.location.href='/';\">Return</button>" 
				+ "</div>"
				+ "</body>" 
				+ "</html>");
		out.close();

	}

}
