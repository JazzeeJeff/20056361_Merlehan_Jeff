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

public class LoginFormHandler implements HttpHandler {

	public void handle(HttpExchange he) throws IOException {
		BOOKDao books = new BOOKDao();
		System.out.println("In LoginFormHandler");

		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

		String line;
		String request = "";
		while ((line = in.readLine()) != null) {
			request = request + line;
		}

		HashMap<String, String> map = Util.requestStringToMap(request);
		// get username and password
		String user = map.get("username");
		String pass = map.get("password");

		//System.out.println("Username: " + user + " Password: " + pass);

		try {
			// call method to check password
			boolean loginAttempt = books.getPass(user, pass);
			System.out.println("loginAttempted " + loginAttempt);
			// if false result prints unsuccessful screen
			if (loginAttempt == false) {
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
				he.sendResponseHeaders(200, 0);
				out.write("<html>" 
						+ "<head> <title> Unsuccessful </title> </head>"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
						+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
						+ "<h1>Unsuccessful login attempt</h1>"
						+ "<form name=\"loginForm\" method=\"post\" action=\"/login\">"
						+ "<label for=\"username\">Username:</label>" 
						+ "<input type=\"text\" name=\"username\"/> <br/>"
						+ "<label for=\"password\">Password:</label>"
						+ "<input type=\"password\" name=\"password\"/> <br/>"
						+ "<input class=\"btn btn-primary\" type=\"submit\" value=\"Login\" />" 
						+ "</form>"
						+ "<button class=\"btn btn-primary\" onclick=\"window.location.href='/';\">Return</button>"
						+ "</body>" 
						+ "</html>");

				out.close();
			} else if (loginAttempt == true) {
				// if true prints welcome screen with username and a continue button				
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
				he.sendResponseHeaders(200, 0);
				out.write("<html>" 
						+ "<head> <title> Welcome </title> </head>"
						+ "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
						+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\""
						+ "<body>" 
						+ "<h1>Welcome, " + user + "</h1>"
						+ "<button class=\"btn btn-primary\" onclick=\"window.location.href='/admin';\">Continue</button>"
						+ "</body>" 
						+ "</html>");
				out.close();
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
