package org.nhl.supermarket.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by ruben on 02/10/14.
 */
@Entity
@Table(name = "Product")
public class Product {
	@Column(name = "Id")
    private int id;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "Price")
    private BigDecimal price;
	
	@Column(name = "Discount")
    private BigDecimal discount = new BigDecimal("1");

    public Product(int id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price.multiply(discount);
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
    
    public static Product Create(Database database, int id, String name, BigDecimal price) {
    	Product product = new Product(id, name, price);
    	database.saveObject(product);
    	return product;
    }
    
    public static void Destroy(Database database, Product product) {
    	database.deleteObject(product);
    }
}
