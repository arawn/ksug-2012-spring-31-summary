package shopping.domain.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
public class Album extends Product {

	private static final long serialVersionUID = 1L;

	private String singer;
    
    @ElementCollection
    private Set<Song> songs = new HashSet<Song>();
    
    
    public Album() {
		super(ProductType.ALBUM);
	}
    
    
	public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
    
	public Set<Song> getSongs() {
		return songs;
	}
	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
    
}
