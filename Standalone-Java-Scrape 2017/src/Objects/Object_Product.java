package Objects;

import java.net.URL;

public class Object_Product {

    String product_name;
    String description;
    String Meta_Tag_Title;
    String Meta_Tag_Description;
    String Meta_Tag_Keywords;
    String Product_Tags;
    String model;
    String Location;
    int Price;
    int Quantity = 0;
    String Subtract_Stock;
    String Out_Of_Stock_Status;
    String SEO_URL;
    String Status;
    String Sort_Order;
    String Categories;
    String attribute_materiale;
    String attribute_stoerrelse;
    String attribute_antall;
    String attribute_maletilstand;
    String attribute_scale;
    String attribute_skalaer;
    String image_featured;
    String image_additional;
    String Manufacturer;

    private URL source;
    String content;
    String adjektiver;

    public void set_source(URL target) {
	source = target;
    }

    public String get_SEO_URL() {
	return SEO_URL;
    }

    public String get_product_name() {
	return product_name;
    }

    public void set_product_name(String product_nam) {
	this.product_name = product_nam;
    }

    public String get_description() {
	return this.description;
    }

    public String get_Meta_Tag_title() {
	return this.product_name;
    }

    public String get_Meta_Tag_Description() {
	return this.Meta_Tag_Description;
    }

    public String get_Meta_Tag_Keywords() {
	return this.Meta_Tag_Keywords;
    }

    public String get_Product_Tags() {
	return this.Product_Tags;
    }

    public String get_Model() {
	return this.model;
    }

    public String get_Location() {
	return this.Location;
    }

    public int get_Price() {
	return this.Price;
    }

    public int get_Quantity() {
	return this.Quantity;
    }

    public String get_Subtract_Stock() {
	return "No";
    }

    public String get_Out_Of_Stock_Status() {
	return this.Out_Of_Stock_Status;
    }

    public String get_Status() {
	return "Disabled";
    }

    public int get_Sort_Order() {
	return this.Price;
    }

    public String get_Categories() {
	return this.Categories;
    }

    public String get_attribute_materiale() {
	return this.attribute_materiale;
    }

    public String get_attribute_stoerrelse() {
	return this.attribute_stoerrelse;
    }

    public String get_attribute_antall() {
	return this.attribute_antall;
    }

    public String get_attribute_maletilstand() {
	return this.attribute_maletilstand;
    }

    public String get_attribute_skalaer() {
	return this.attribute_skalaer;
    }

    public String get_image_featured() {
	return this.image_featured;
    }

    public String get_image_additional() {
	return this.image_additional;
    }

    public URL get_source() {
	return source;
    }

    public String get_content() {
	return this.content;
    }

    public void set_content(String product_content) {
	content = product_content;
    }

    public void set_attribute_skalaer(String attribute_skalaer) {
	this.attribute_skalaer = attribute_skalaer;
    }

    public void set_attribute_materiale(String attribute_materiale) {
	this.attribute_materiale = attribute_materiale;
    }

    public void set_description(String description) {
	this.description = description;
    }

    public String get_Manufacturer() {
	return Manufacturer;
    }

    public void set_manufacturer(String Manufacturer) {
	this.Manufacturer = Manufacturer;
    }

    public void set_location(String location) {
	this.Location=location;
    }

    public void set_price(int price) {
	this.Price = price;
    }

    public void set_kategorier(String Categories) {
	this.Categories = Categories;
    }

    public void set_Meta_Tag_Keywords(String Meta_Tag_Keywords) {
	this.Meta_Tag_Keywords = Meta_Tag_Keywords;
    }

    public void set_Product_Tags(String Product_Tags) {
	this.Product_Tags=Product_Tags;
    }

    public void set_Model(String model) {
	this.model = model;
    }

    public void set_SEO_URL(String SEO_URL) {
	this.SEO_URL = SEO_URL;
    }

    public void set_Out_Of_Stock_Status(String Out_Of_Stock_Status) {
	this.Out_Of_Stock_Status = Out_Of_Stock_Status;
    }

    public void set_adjektiver(String adjektiver) {
	this.adjektiver = adjektiver;
    }

    public String get_adjektiver() {
	return adjektiver;
    }

    public void set_Antall(String antall) {
	this.attribute_antall = antall;
    }

    public void set_attribute_scale(String scale) {
	this.attribute_scale = scale;
    }

    public String get_Scale() {
	return attribute_scale;
    }
}
