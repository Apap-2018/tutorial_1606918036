package com.apap.tutorial7.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "dealer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max=50)
	@Column(name="alamat",nullable=false)
	private String alamat;
	
	@NotNull
	@Size(max=13)
	@Column(name="no_telp",nullable=false)
	private String noTelp;
	
	/*public DealerModel(Long id, String alamat, String no_Telp) {
		this.id = id;
		this.alamat = alamat;
		this.noTelp = no_Telp;
	}*/

	public void setId(Long id) {
		this.id=id;
	}
	public Long getId() {
		return id;
	}
	public void setAlamat(String alamat) {
		this.alamat=alamat;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setNoTelp(String no_telp) {
		this.noTelp=no_telp;
	}
	public String getNoTelp() {
		return noTelp;
	}
	
	@OneToMany(mappedBy = "dealer", fetch=FetchType.LAZY, cascade=CascadeType.PERSIST)
	@JsonIgnore
	private List<CarModel> listCar;
	
	public void setListCar(List<CarModel> listCar) {
		this.listCar = listCar;
	}
	public List<CarModel> getListCar(){
		return listCar;
	}
	
	
}
