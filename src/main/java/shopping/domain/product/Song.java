package shopping.domain.product;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Song {

    private String title;
    private Date time;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    
}
