package shopping.domain.product;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@MappedSuperclass
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
    private Long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private ProductType type;			// 상품종류

    @NotBlank
    @Column(nullable=false)
    private String title;				// 상품명
    
    private int price;					// 가격
    
    private double discountRate;		// 할인율
    
    @Transient
    private int discountPrice;			// 할인가격
    
    
    Product() { }
    
    Product(ProductType type) {
		this.type = type;
	}
    

	public Long getId() {
        return id;
    }

    public ProductType getType() {
        return type;
    }
    public void setType(ProductType type) {
        this.type = type;
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    
    public double getDiscountRate() {
        return discountRate;
    }
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
    
    public double getDiscountPrice() {
        return discountPrice;
    }
    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

	@Override
	public String toString() {
		return "Product [id=" + id + ", type=" + type + ", title=" + title + "]";
	}

}
