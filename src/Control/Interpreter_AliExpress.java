package Control;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Interpreter_AliExpress {
    public Interpreter_AliExpress(Control control) {

	this.class_control = control;
    }

    Control class_control;

    public String get_manufacturer() {

	// aliexpress
	/*
	 * <li class="property-item" id="product-prop-2" data-attr="201658849" data-title="KIWARM"> <span class="propery-title">Brand Name:</span>
	 * <span class="propery-des" title="KIWARM">KIWARM</span> </li>
	 */
	String manufacturer = "";
	int start = class_control.product_content.indexOf("Brand Name:</span>".toLowerCase());
	if (start > 0) {

	    manufacturer = class_control.product_content.substring(start + "Brand Name:</span>".length());
	    // System.out.println(this.getClass().toString() + " manufacturer 1=" + manufacturer);

	    start = manufacturer.indexOf(">".toLowerCase());
	    manufacturer = manufacturer.substring(start + ">".length());
	    // System.out.println(this.getClass().toString() + " manufacturer 2=" + manufacturer);

	    int end = manufacturer.indexOf("</span");
	    manufacturer = manufacturer.substring(0, end);
	    // System.out.println(this.getClass().toString() + " manufacturer 3=" + manufacturer);
	} else {
	    System.out.println(this.getClass().toString() + " manufacturer fant ingen");
	}
	// System.out.println(this.getClass().toString() + " manufacturer 5 ="+manufacturer);
	return manufacturer;
    }

    public String get_location() {
	String location = "";
	// ebay
	// http://www.ebay.co.uk/itm/Wargaming-terrain-Swamp-den-28mm-1-35-mdf-laser-cut-terrain-/292043962333?hash=item43ff2d37dd:g:vQ0AAOSw~AVYuUA-
	// http://www.ebay.co.uk/itm/292043962333
	int start;
	int end = 0;
	location = class_control.aktivt_produkt.get_content();
	// System.out.println(this.getClass().toString() + " location 1=" + location);

	start = location.indexOf("https://m.aliexpress.com/item/");
	location = location.substring(start);
	// System.out.println(this.getClass().toString() + " location 2=" + location);

	end = location.indexOf(".html\"");
	location = location.substring(0, end + ".html".length());
	// System.out.println(this.getClass().toString() + " location 3=" + location);

	return location;
    }

    public int get_price() {

	// ebay .com
	// produktpris
	String price_product = "";
	// <span class="p-del-price-title">Price:</span><del class="p-del-price-content notranslate"><span class="p-symbol">US $</span><span
	// id="j-sku-price" class="p-price">2.19</span>
	// System.out.println(this.getClass().toString() + " get_price "+class_control.product_content.indexOf("Price:".toLowerCase()));
	// System.out.println(this.getClass().toString() + " get_price "+class_control.product_content.indexOf("Price:</span>".toLowerCase()));
	int start = class_control.product_content.indexOf("Price:</span>".toLowerCase());
	if (start > 0) {
	    price_product = class_control.product_content.substring(start + "Price:</span>".length());
	    //System.out.println(this.getClass().toString() + " get_price A price_product 1=" + price_product);

	    // p-price-extra-info
	    start = class_control.product_content.indexOf("\"p-price\">".toLowerCase());
	    if (start >= 0) {
		price_product = class_control.product_content.substring(start + "\"p-price\">".length());
		//System.out.println(this.getClass().toString() + " get_price B price_product 2A=" + price_product);

		int end = price_product.indexOf("</span>");
		price_product = price_product.substring(0, end);
		//System.out.println(this.getClass().toString() + " get_price C price_product 3A=" + price_product);
	    } else {
		//System.out.println(this.getClass().toString() + " get_price C price_product FAIL@2A");
		start = class_control.product_content.indexOf("\"p-price\"".toLowerCase());
		if (start >= 0) {
		    price_product = class_control.product_content.substring(start + "p-price".length());
		    //System.out.println(this.getClass().toString() + " get_price B price_product 2B=" + price_product);

		    int end = price_product.indexOf("</span>");
		    price_product = price_product.substring(0, end);
		    //System.out.println(this.getClass().toString() + " get_price C price_product 3B=" + price_product);
		    
		    start = price_product.indexOf("price\">".toLowerCase());
		    price_product = price_product.substring(start + "p-price".length());
		    //System.out.println(this.getClass().toString() + " get_price C price_product 4B=" + price_product);
		} else {
		    System.out.println(this.getClass().toString() + " get_price C price_product FAIL@2B");
		}
	    }
	}
	// fraktpris
	String price_frakt = "";
	// <span id="fshippingCost" class="notranslate sh-cst "><span>US $6.50</span>
	start = class_control.product_content.indexOf("<span id=\"convetedPriceId\">NOK ".toLowerCase());
	if (start > 0) {
	    price_frakt = class_control.product_content.substring(start);
	    //System.out.println(this.getClass().toString() + " get_price price_frakt 1=" + price_frakt);
	    int end = price_frakt.indexOf("</span>");
	    price_frakt = price_frakt.substring(0, end);
	    //System.out.println(this.getClass().toString() + " get_price price_frakt 2=" + price_frakt);
	    start = price_frakt.indexOf("<span>");
	    price_frakt = price_frakt.substring(start + "<span>".length());
	    //System.out.println(this.getClass().toString() + " get_price price_frakt 3=" + price_frakt);
	    price_frakt = price_frakt.substring(start + " id=\"convetedPriceId\">NOK ".length());
	    //System.out.println(this.getClass().toString() + " get_price price_frakt 4=" + price_frakt);
	}
	double produkt = 0;
	double frakt = 0;
	try {
	    produkt = Double.parseDouble(price_product);
	    frakt = Double.parseDouble(price_frakt);
	} catch (Exception e) {
	    // e.printStackTrace();
	}
	//System.out.println(this.getClass().toString() + " get_price produkt=" + produkt + " frakt=" + frakt + " price="+ (int) (produkt + frakt));
	double price_usd = (produkt + frakt);
	price_usd = price_usd * this.class_control.nok_usd;
	//System.out.println(this.getClass().toString() + " get_price produkt=" + price_usd);

	int price = (int) price_usd;
	price = price * 3;

	return price;
    }

    public String get_Model() {
	String Model = "";

	// House model miniature building diorama ae32567870959 1

	if (class_control.aktivt_produkt.get_Categories().contains("people")) {
	    Model = Model + "scale model people ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("static grass")) {
	    Model = Model + "static grass ";
	} else if (class_control.aktivt_produkt.get_content().contains("house")) {
	    Model = Model + "house miniature building ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("buildings")) {
	    Model = Model + "miniature building ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("Diorama")) {
	    Model = Model + "diorama ";
	}
	// System.out.println(this.getClass().toString() + " get_Model 1 Model=" + Model);

	if (class_control.aktivt_produkt.get_Manufacturer() != null) {
	    Model = Model + class_control.aktivt_produkt.get_Manufacturer() + " ";
	}
	// System.out.println(this.getClass().toString() + " get_Model 2 get_Manufacturer=" + class_control.aktivt_produkt.get_Manufacturer());
	// System.out.println(this.getClass().toString() + " get_Model 3 Model=" + Model);

	String itemnumber = class_control.aktivt_produkt.get_Location();
	// System.out.println(this.getClass().toString() + " get_Model 1 itemnumber=" + itemnumber);
	// https://m.aliexpress.com/item/32791046131.html
	try {

	    itemnumber = itemnumber.substring(itemnumber.indexOf("item/") + "item/".length());
	    // System.out.println(this.getClass().toString() + " get_Model 2 itemnumber=" + itemnumber);

	    int end = itemnumber.indexOf(".html");
	    itemnumber = itemnumber.substring(0, end);
	    // System.out.println(this.getClass().toString() + " get_Model 3 itemnumber=" + itemnumber);

	    Model = Model + "ae" + itemnumber;
	    // System.out.println(this.getClass().toString() + " get_Model 3 Model=" + Model);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	try {
	    if (Model.length() > 50) {
		throw new Exception();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return "fail model";
	}
	return Model;
    }

    public String get_Out_Of_Stock_Status() {
	String Out_Of_Stock_Status = "";

	// <div class="">Estimated between <span class="vi-acc-del-range"><b>Tue. Mar. 21 and Tue. Mar. 28</b></span></div>
	int start = class_control.product_content
		.indexOf("Estimated between <span class=\"vi-acc-del-range\"><b>".toLowerCase());
	int end = 0;
	if (start > 0) {
	    String dato = class_control.product_content
		    .substring(start + "Estimated between <span class=\"vi-acc-del-range\"><b>".length());
	    end = dato.indexOf("</b>");
	    dato = dato.substring(0, end);
	    System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status 1 dato=" + dato);
	    dato = dato.substring(dato.indexOf(" and ") + " and ".length());
	    System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status 2 dato=" + dato);
	    Date idag = new Date();
	    dato = dato + " " + (idag.getYear() + 1900);
	    DateFormat format = new SimpleDateFormat("EEE. MMM. d yyyy", Locale.ENGLISH);
	    Date senest = null;
	    long frakttid = 0;
	    try {
		senest = format.parse(dato);
		long diffInSeconds = (senest.getTime() - idag.getTime()) / 1000;
		long diff[] = new long[] { 0, 0, 0, 0 };
		/* sec */diff[3] = (diffInSeconds >= 60 ? diffInSeconds % 60 : diffInSeconds);
		/* min */diff[2] = (diffInSeconds = (diffInSeconds / 60)) >= 60 ? diffInSeconds % 60 : diffInSeconds;
		/* hours */diff[1] = (diffInSeconds = (diffInSeconds / 60)) >= 24 ? diffInSeconds % 24 : diffInSeconds;
		/* days */diff[0] = (diffInSeconds = (diffInSeconds / 24));
		frakttid = diff[0];

		if (frakttid <= 15) {
		    Out_Of_Stock_Status = "Bestillingsvare, 15 dager frakt";
		} else if (frakttid <= 30) {
		    Out_Of_Stock_Status = "Bestillingsvare, 30 dager frakt";
		} else if (frakttid <= 45) {
		    Out_Of_Stock_Status = "Bestillingsvare, 45 dager frakt";
		} else if (frakttid > 45) {
		    Out_Of_Stock_Status = "Bestillingsvare, 60 dager frakt";
		} else {
		    Out_Of_Stock_Status = "Ukjent tid";
		}
		System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status 3 frakttid=" + frakttid);
	    } catch (ParseException e) {
		Out_Of_Stock_Status = "Feil i datogjenkjenning";
		e.printStackTrace();
	    }
	    System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status senest=" + senest);
	} else {
	    Out_Of_Stock_Status = "Ukjent frakttid, anta 45 dager";
	    // System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status ingen dato funnet");
	}
	return Out_Of_Stock_Status;
    }

}
