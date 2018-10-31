package com.apap.tutorial7.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="car")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarModel implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=50)
	@Column(name="brand",nullable=false)
	private String brand;
	
	@NotNull
	@Size(max=50)
	@Column(name="type",nullable=false, unique=true)
	private String type;
	
	@NotNull
	@Column(name="price",nullable=false)
	private Long price;
	
	@NotNull
	@Column(name="amount",nullable=false)
	private int amount;
	
	/*public CarModel(Long id, String brand, String type, Long price, Integer amount) {
		this.id = id;
		this.brand = brand;
		this.type = type;
		this.price = price;
		this.amount = amount;
	}*/

	public void setId(Long id) {
		this.id=id;
	}
	public long getId() {
		return id;
	}
	public void setBrand(String brand) {
		this.brand=brand;
	}
	public String getBrand() {
		return brand;
	}
	public void setType(String type) {
		this.type=type;
	}
	public String getType() {
		return type;
	}
	public void setPrice(Long price) {
		this.price=price;
	}
	public long getPrice() {
		return price;
	}
	public void setAmount(Integer amount) {
		this.amount=amount;
	}
	public int getAmount() {
		return amount;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dealer_id",referencedColumnName="id",nullable=false)
	@OnDelete(action=OnDeleteAction.NO_ACTION)
	//@JsonIgnore
	private DealerModel dealer;
	
	/*public void setDealer(Long dealer) {
		this.dealer = (long)dealer;
	}
	
	public long getDealer () {
		return dealer;
	}*/
	
	public void setDealer(DealerModel dealer) {
		this.dealer=dealer;
	}
	public DealerModel getDealer() {
		return dealer;
	}
}
