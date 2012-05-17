package shopping.domain.product;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Movie extends Product {

	private static final long serialVersionUID = 1L;

	@NotBlank
    private String director;
    
    @ElementCollection
    private List<String> actors;
    
    
    public Movie() {
		super(ProductType.MOVIE);
	}
    
	public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public List<String> getActors() {
        return actors;
    }
    public void setActors(List<String> actors) {
        this.actors = actors;
    }
    
}
