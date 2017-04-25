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

public class Interpreter_Ebay  {
    public Interpreter_Ebay(Control control) {

	this.class_control = control;
    }

    Control class_control;


    public String get_manufacturer() {

	// ebay
	String manufacturer = "";
	int start = class_control.product_content.indexOf("Brand\"><span itemprop=\"name\">");
	if (start > 0) {

	    manufacturer = class_control.product_content.substring(start + "Brand\"><span itemprop=\"name\">".length());
	    // System.out.println(this.getClass().toString() + " manufacturer 1=" + manufacturer);
	    int end = manufacturer.indexOf("</span");
	    manufacturer = manufacturer.substring(0, end);
	    //System.out.println(this.getClass().toString() + " manufacturer 2=" + manufacturer);
	} else {
	    /*
	     <h2 itemprop="brand" itemscope="itemscope" itemtype="http://schema.org/brand"><span itemprop="name">wws</span>
	     */
	    start = class_control.product_content.indexOf("<h2 itemprop=\"brand\"");
	    manufacturer = class_control.product_content.substring(start + "<h2 itemprop=\"brand\"".length());
	    //System.out.println(this.getClass().toString() + " manufacturer 1=" + manufacturer);
	    
	    int end = manufacturer.indexOf("</span");
	    manufacturer = manufacturer.substring(0, end);
	    //System.out.println(this.getClass().toString() + " manufacturer 2=" + manufacturer);
	    
	    start = manufacturer.indexOf("<span itemprop=\"name\">");
	    manufacturer = manufacturer.substring(start + "<span itemprop=\"name\">".length());
	    //System.out.println(this.getClass().toString() + " manufacturer 3=" + manufacturer);
	}
	return manufacturer;
    }

    public String get_location() {
	String location = "";
	// ebay
	// http://www.ebay.co.uk/itm/Wargaming-terrain-Swamp-den-28mm-1-35-mdf-laser-cut-terrain-/292043962333?hash=item43ff2d37dd:g:vQ0AAOSw~AVYuUA-
	// http://www.ebay.co.uk/itm/292043962333
	int start = class_control.product_content.indexOf("Brand:");
	int end = 0;
	location = class_control.aktivt_produkt.get_source().toString();
	// System.out.println(this.getClass().toString() + " location 1=" + location);
	end = location.indexOf("?hash=");
	if (end > 0) {
	    location = location.substring(0, end);
	} else {
	    end = location.indexOf("?var=&hash=");
	    if (end > 0) {
		location = location.substring(0, end);
	    }
	}
	// System.out.println(this.getClass().toString() + " location 2=" + location);
	start = location.indexOf("http://www.ebay.co.uk/itm/");
	location = location.substring(start + "http://www.ebay.co.uk/itm/".length());
	// System.out.println(this.getClass().toString() + " location 3=" + location);
	start = location.indexOf("/");
	location = location.substring(start + "/".length());
	// System.out.println(this.getClass().toString() + " location 4=" + location);
	location = "http://www.ebay.co.uk/itm/" + location;
	// System.out.println(this.getClass().toString() + " location 5=" + location);

	return location;
    }

    public int get_price() {

	// ebay .com
	// produktpris
	String price_product = "";
	// itemprop="price" style="" content="19.5">US $19.50</span>
	int start = class_control.product_content
		.indexOf("convbinPrice\" style=\"white-space: nowrap;font-weight:bold;\">NOK ".toLowerCase());
	if (start > 0) {
	    price_product = class_control.product_content.substring(start);
	    // System.out.println(this.getClass().toString() + " price_product 1=" + price_product);
	    int end = price_product.indexOf("<span>(including shipping)");
	    price_product = price_product.substring(0, end);
	    // System.out.println(this.getClass().toString() + " price_product 2=" + price_product);
	    price_product = price_product
		    .substring("convbinPrice\" style=\"white-space: nowrap;font-weight:bold;\">NOK ".length());
	    // System.out.println(this.getClass().toString() + " price_product 1=" + price_product);
	} else {
	    start = class_control.product_content.indexOf("convertedBinPrice\":\"NOK ");
	    if (start > 0) {
		price_product = class_control.product_content.substring(start);
		// System.out.println(this.getClass().toString() + " price_product 1=" + price_product);
		int end = price_product.indexOf("\",\"");
		price_product = price_product.substring(0, end);
		// System.out.println(this.getClass().toString() + " price_product 2=" + price_product);
		price_product = price_product.substring("convertedBinPrice\":\"NOK ".length());
		// System.out.println(this.getClass().toString() + " price_product 1=" + price_product);
	    }

	}
	// fraktpris
	String price_frakt = "";
	// <span id="fshippingCost" class="notranslate sh-cst "><span>US $6.50</span>
	start = class_control.product_content.indexOf("<span id=\"convetedPriceId\">NOK ".toLowerCase());
	if (start > 0) {
	    price_frakt = class_control.product_content.substring(start);
	    // System.out.println(this.getClass().toString() + " price_frakt 1=" + price_frakt);
	    int end = price_frakt.indexOf("</span>");
	    price_frakt = price_frakt.substring(0, end);
	    // System.out.println(this.getClass().toString() + " price_frakt 2=" + price_frakt);
	    start = price_frakt.indexOf("<span>");
	    price_frakt = price_frakt.substring(start + "<span>".length());
	    // System.out.println(this.getClass().toString() + " price_frakt 3=" + price_frakt);
	    price_frakt = price_frakt.substring(start + " id=\"convetedPriceId\">NOK ".length());
	    // System.out.println(this.getClass().toString() + " price_frakt 4=" + price_frakt);
	}
	double produkt = 0;
	double frakt = 0;
	try {
	    produkt = Double.parseDouble(price_product);
	    frakt = Double.parseDouble(price_frakt);
	} catch (Exception e) {
	    //e.printStackTrace();
	}
	// System.out.println(this.getClass().toString() + " produkt=" + produkt+" frakt="+frakt+" price="+(int) (produkt + frakt));
	int price = (int) (produkt + frakt);

	return price;
    }

    public void get_images() {

    }

    public String get_Model() {
	String Model = "";
	
	//House model miniature building diorama ae32567870959 1

	if (class_control.aktivt_produkt.get_content().contains("house")) {
	    Model = Model + "house ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("buildings")) {
	    Model = Model + "miniature building ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("Static grass")) {
	    Model = Model + "static grass ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("Diorama")) {
	    Model = Model + "diorama ";
	}
	
	if ( class_control.aktivt_produkt.get_Manufacturer() != null ){
	    Model = Model + class_control.aktivt_produkt.get_Manufacturer()+" ";
	}

	String itemnumber = class_control.aktivt_produkt.get_Location();
	try {
	    //System.out.println(this.getClass().toString() + " get_Model 1 itemnumber=" + itemnumber);
	    itemnumber = itemnumber.substring(itemnumber.indexOf("itm/") + "itm/".length());
	    //System.out.println(this.getClass().toString() + " get_Model 2 itemnumber=" + itemnumber);
	    int start = itemnumber.indexOf("?");
	    if (start > 0) {
		itemnumber = itemnumber.substring(0, start);
		// System.out.println(this.getClass().toString() + " get_Model itemnumber=" + itemnumber);
	    }
	    Model = Model + "e" + itemnumber;
	    // System.out.println(this.getClass().toString() + " get_Model Model=" + Model);
	} catch (Exception e) {
	    e.printStackTrace();
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
	    // System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status 1 dato=" + dato);
	    dato = dato.substring(dato.indexOf(" and ") + " and ".length());
	    // System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status 2 dato=" + dato);
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
		// System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status 3 frakttid=" + frakttid);
	    } catch (ParseException e) {
		Out_Of_Stock_Status = "Feil i datogjenkjenning";
		e.printStackTrace();
	    }
	    // System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status senest=" + senest);
	} else {
	    Out_Of_Stock_Status = "Ukjent frakttid";
	    // System.out.println(this.getClass().toString() + " get_Out_Of_Stock_Status ingen dato funnet");
	}
	return Out_Of_Stock_Status;
    }

}
