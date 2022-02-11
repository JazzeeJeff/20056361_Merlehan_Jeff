package projectBookInventory;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;
/**
 * Displays form pre-filled with details of current book to be edited. Sends details to edit form handler.
 * @author Jeff Merlehan
 *
 */
public class EditBookHandler implements HttpHandler{
	public void handle(HttpExchange he) throws IOException {
		System.out.println("EditBookHandler called");
	    he.sendResponseHeaders(200,0);
	    BufferedWriter out = new BufferedWriter(  
	        new OutputStreamWriter(he.getResponseBody() ));
	    
	    // Get param from URL
	    Map <String,String> params = Util.requestStringToMap
	    (he.getRequestURI().getQuery());  		     		    

	    //get ID number
	    int ID = Integer.parseInt(params.get("id"));

	    BOOKDao books = new BOOKDao();	    
	    	   
	    try {
	    //creates book object for book to be edited	
		Book editBook = books.getBookPS(ID);
		//prints out current book to be edited							
		System.out.println(editBook);
		//prints out a form pre-filled with the details for easy editing
			out.write(
					"<html>" +
					"<head> <title>Add Book</title> </head>" +
					"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
				    "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.15.1/css/all.css\"" +		
					"<body>" +
					"<h1>Add Book</h1>" +				
					"<form  name=\"New Book\" method=\"post\" action=\"/editBook?id=" + editBook.getId() + "\">" +
				    "<label for=\"id\">Book ID:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getId() + "\" name=\"id\"/> <br/>" +
				    
				    "<label for=\"title\">Title:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getTitle() + "\" name=\"title\"/> <br/>" +
				    
					"<label for=\"author\">Author:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getAuthor() + "\" name=\"author\"/> <br/>" +
					
					"<label for=\"year\">Year:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getYear() + "\" name=\"year\"/> <br/>" +
					
					"<label for=\"edition\">Edition:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getEdition() + "\" name=\"edition\"/> <br/>" +
					
					"<label for=\"publisher\">Publisher:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getPublisher() + "\" name=\"publisher\"/> <br/>" +
					
					"<label for=\"isbn\">ISBN:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getIsbn() + "\" name=\"isbn\"/> <br/>" +
					
					"<label for=\"cover\">Cover:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getCover() + "\" name=\"cover\"/> <br/>" +
					
					"<label for=\"condition\">Condition:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getCondition() + "\" name=\"condition\"/> <br/>" +
					
					"<label for=\"price\">Price:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getPrice() + "\" name=\"price\"/> <br/>" +
					
					"<label for=\"notes\">Notes:</label>" + 
					"<input type=\"text\" value=\"" + editBook.getNotes() + "\" name=\"notes\"/> <br/>" +	
					
				    "<input class=\"btn btn-primary\" type=\"submit\" value=\"Edit Book\" />" +					
				    "</form>" +
				    "<button class=\"btn btn-primary\" onclick=\"window.location.href='/admin';\">Return</button>" +
				    "</body>" +
				    "</html>");
			out.close();										
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    	    	    
	    
	}

}
