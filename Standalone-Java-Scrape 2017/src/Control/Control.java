package Control;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Date;

import Objects.Object_Product;

public class Control {

    API class_API;
    Output class_Output;
    super_interpreter class_Interpreter;
    Input class_Input;
    Brain_Currency class_Brain_Currency;

    Object_Product aktivt_produkt;
    String product_content;
    
    double nok_usd = 1;

    public Control() {
	//System.out.println(this.getClass().toString() + " Control");

	class_API = new API(this);
	class_Output = new Output(this);
	class_Interpreter = new super_interpreter(this);
	class_Input = new Input(this);
	class_Brain_Currency = new Brain_Currency(this);

	//test_scrap_url();
	class_Brain_Currency.Currency_Startup();
	class_Input.Load_URLs();
    }

    private void test_scrap_url() {
	// gjør alle standardprosesser, men bruk 1 bestemt URL only.
	//System.out.println(this.getClass().toString() + " test_scrap_url");

	Start_new_scrape("http://www.ebay.com/itm/Wargaming-terrain-Swamp-den-28mm-1-35-mdf-laser-cut-terrain-/292043962333?hash=item43ff2d37dd:g:vQ0AAOSw~AVYuUA-");
	Start_new_scrape("http://www.ebay.com/itm/4x-15mm-Bungalows-Wargame-House-Scenery-Modular-Laser-cut-MDF-FLOW-Tanks-ETc-/162373237784?hash=item25ce32fc18:g:NKIAAOSwUKxYiyt9");
	Start_new_scrape("http://www.ebay.com/itm/Wargaming-terrain-Hermit-28mm-1-35-mdf-laser-cut-house-scenery-/292043967371?hash=item43ff2d4b8b:g:ogQAAOSwfVpYuUFa");

    }

    void Start_new_scrape(String URL) {
	// standard rutine for å scrape 1 produkt
	if ( ! URL.contains(".com/")){
	    URL = URL.replace(".co.uk/", ".com/");
	    //System.out.println(this.getClass().toString() + " URL skal være fra Ebay.COM");
	    //return;
	}
	try {
	    URL target = set_targetURL(URL);

	    Scrap_URL(target);
	    aktivt_produkt.set_attribute_scale(class_Interpreter.get_Scale());
	    //scale før kategori
	    aktivt_produkt.set_kategorier(class_Interpreter.get_kategorier());
	    aktivt_produkt.set_manufacturer(class_Interpreter.get_manufacturer());
	    aktivt_produkt.set_attribute_materiale(class_Interpreter.get_materials());
	    aktivt_produkt.set_location(class_Interpreter.get_location());
	    aktivt_produkt.set_price(class_Interpreter.get_price());
	    class_Interpreter.get_images();
	    //scale før skalaer
	    aktivt_produkt.set_attribute_skalaer(class_Interpreter.get_scales());
	    aktivt_produkt.set_adjektiver(class_Interpreter.get_adjektiver());
	    aktivt_produkt.set_Antall(class_Interpreter.get_Antall());
	    //manufacturer og antall før get_product_name
	    aktivt_produkt.set_product_name(class_Interpreter.get_product_name()); 
	    //categories før meta tag keywords
	    aktivt_produkt.set_Meta_Tag_Keywords(class_Interpreter.get_Meta_Tag_Keywords());
	    aktivt_produkt.set_Product_Tags(class_Interpreter.get_Product_Tags());
	    aktivt_produkt.set_Model(class_Interpreter.get_Model());
	    //get_Model før get_SEO_URL 
	    aktivt_produkt.set_SEO_URL(class_Interpreter.get_SEO_URL());
	    aktivt_produkt.set_Out_Of_Stock_Status(class_Interpreter.get_Out_Of_Stock_Status());
	    aktivt_produkt.set_description(class_Interpreter.get_description());

	    class_Output.print_object(aktivt_produkt);
	    //System.out.println(this.getClass().toString() + " test_scrap_url done");
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    private URL set_targetURL(String url) {
	// Gjør om input-string til URL
	// Denne må kjøres før Scrap_URL
	//System.out.println(this.getClass().toString() + " set_targetURL " + url);
	try {

	    URL target = new URL(url);

	    return target;
	} catch (MalformedURLException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    private void Scrap_URL(URL target) {
	// Lager nytt aktivt_produkt
	// bruk URL til å sette igang standard scraping-prosess
	//System.out.println(this.getClass().toString() + " Scrap_URL " + target.toString());
	try {

	    aktivt_produkt = new Object_Product();

	    class_API.set_source(target);
	    get_content_from_URL(target);
	    aktivt_produkt.set_content(product_content);
	    
	    //identify
	    class_API.set_product_name(class_API.generate_prouct_name());
	    
	} catch (Exception e) {

	    e.printStackTrace();
	}
    }

    private void get_content_from_URL(URL target) throws Exception {

	URL url = target;
	String temp = url.toString().replaceAll(" ", "+");

	try {
	    url = new URL(temp);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	try {
	    StringBuffer DataImported = new StringBuffer();
	    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	    URLConnection conn = url.openConnection();
	    conn.setRequestProperty("User-Agent",
		    "Mozilla/5.0 (X11; U; Linux x86_64; en-GB; rv:1.8.1.6) Gecko/20070723 Iceweasel/2.0.0.6 (Debian-2.0.0.6-0etch1)");
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String str;
	    // System.gc();
	    while ((str = in.readLine()) != null) {
		DataImported.append(str);
	    }
	    String Content = "";
	    if (DataImported != null) {
		Content = DataImported.toString();
	    }
	    product_content = Content.toLowerCase();
	} catch (ConnectException e) { // time-out
	    e.printStackTrace();
	} catch (SocketException e) {
	    e.printStackTrace();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (IllegalArgumentException e) {
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    public void SetCurrency(Double currency) {
	nok_usd = currency;
    }

}
