package shopping.domain.product;

import javax.persistence.Entity;

@Entity
public class Book extends Product {

	private static final long serialVersionUID = 1L;
	
	private long isbn;
    private String author;
    private String publisher;
    
    
    public Book() {
		super(ProductType.BOOK);
	}
    
    
    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
