package com.refushi.model;

public class Item {

	private String id ;
	private String name ;
	private String email ;
	private String phone_number ;
	private String fax ;
	private String address ;
	private String latitude ;
	private String longitude ;
	private String contact_name ;
	private String others ;
	private String website ;
	private String social_media ;
	private String description ;
	private String short_description ;
	private String locale ;
	private String photo ;
	private String open_hour ;
	private String close_hour ;



	private boolean isRecommandation ;


	public Item(){
		super();
	}


	public Item(String id, String name, String email, String phone_number,
			String fax, String address, String latitude, String longitude,
			String contact_name, String others, String website,
			String social_media, String description, String short_description,
			String locale, String photo, String open_hour, String close_hour,
			boolean isRecommandation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.fax = fax;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.contact_name = contact_name;
		this.others = others;
		this.website = website;
		this.social_media = social_media;
		this.description = description;
		this.short_description = short_description;
		this.locale = locale;
		this.photo = photo;
		this.open_hour = open_hour;
		this.close_hour = close_hour;
		this.isRecommandation = isRecommandation;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getContact_name() {
		return contact_name;
	}


	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}


	public String getOthers() {
		return others;
	}


	public void setOthers(String others) {
		this.others = others;
	}


	public String getWebsite() {
		return website;
	}


	public void setWebsite(String website) {
		this.website = website;
	}


	public String getSocial_media() {
		return social_media;
	}


	public void setSocial_media(String social_media) {
		this.social_media = social_media;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getShort_description() {
		return short_description;
	}


	public void setShort_description(String short_description) {
		this.short_description = short_description;
	}


	public String getLocale() {
		return locale;
	}


	public void setLocale(String locale) {
		this.locale = locale;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getOpen_hour() {
		return open_hour;
	}


	public void setOpen_hour(String open_hour) {
		this.open_hour = open_hour;
	}


	public String getClose_hour() {
		return close_hour;
	}


	public void setClose_hour(String close_hour) {
		this.close_hour = close_hour;
	}


	public boolean isRecommandation() {
		return isRecommandation;
	}


	public void setRecommandation(boolean isRecommandation) {
		this.isRecommandation = isRecommandation;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", email=" + email
				+ ", phone_number=" + phone_number + ", fax=" + fax
				+ ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", contact_name=" + contact_name
				+ ", others=" + others + ", website=" + website
				+ ", social_media=" + social_media + ", description="
				+ description + ", short_description=" + short_description
				+ ", locale=" + locale + ", photo=" + photo + ", open_hour="
				+ open_hour + ", close_hour=" + close_hour
				+ ", isRecommandation=" + isRecommandation + "]";
	}


}
