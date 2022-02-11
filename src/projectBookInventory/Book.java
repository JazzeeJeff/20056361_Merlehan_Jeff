package projectBookInventory;

/**
 * This class creates a book
 * 
 * @author Jeff Merlehan - 20056361
 *
 */
public class Book {
	private int id;
	private String title;
	private String author;
	private int year;
	private int edition;
	private String publisher;
	private String isbn;
	private String cover;
	private String condition;
	private double price;
	private String notes;

	/**
	 * 
	 * @param id
	 * @param title
	 * @param author
	 * @param year
	 * @param edition
	 * @param publisher
	 * @param isbn
	 * @param cover
	 * @param condition
	 * @param price
	 * @param notes
	 */

	public Book(int id, String title, String author, int year, int edition, String publisher, String isbn, String cover,
			String condition, double price, String notes) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.edition = edition;
		this.publisher = publisher;
		this.isbn = isbn;
		this.cover = cover;
		this.condition = condition;
		this.price = price;
		this.notes = notes;
	}

	/**
	 * @return the book id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id sets the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the book title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title sets the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author of the book
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author sets the author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the year of the book
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year sets the year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the book edition
	 */
	public int getEdition() {
		return edition;
	}

	/**
	 * @param edition sets the edition
	 */
	public void setEdition(int edition) {
		this.edition = edition;
	}

	/**
	 * @return the book publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher sets the publisher
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the book isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn sets the isbn
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the book cover type
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover sets the cover
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}

	/**
	 * @return the book condition
	 */
	public String getCondition() {
		return condition;
	}

	/**
	 * @param condition sets the condition
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * @return the book price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price sets the price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the notes on the book
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes sets the notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Book ID=" + this.id + "\nTitle=" + this.title + "\nAuthor=" + this.author + "\nYear=" + this.year
				+ "\nEdition=" + this.edition + "\nPublisher=" + this.publisher + "\nISBN=" + this.isbn + "\nCover="
				+ this.cover + "\nCondition=" + this.condition + "\nPrice=" + this.price + "\nNotes=" + this.notes
				+ "\n--------------------";
	}

}
