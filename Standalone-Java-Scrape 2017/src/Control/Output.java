package Control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import Objects.Object_Product;

public class Output {

    Control class_control;

    public Output(Control class_control) {
	this.class_control = class_control;
    }

    public void print_object(Object_Product produkt) throws Exception {
	System.out.println(this.getClass().toString() + " print_object A");
	try {

	    File ProductInfo = new File(produkt.get_SEO_URL()+"\\"+produkt.get_SEO_URL()+".txt");
	    System.out.println(this.getClass().toString() + " print_object b "+produkt.get_SEO_URL());
	    if (!ProductInfo.getParentFile().exists())
		ProductInfo.getParentFile().mkdirs();

	    ProductInfo.delete();
	    if (!ProductInfo.exists()) {
		ProductInfo.createNewFile();
	    }

	    PrintStream utfil;
	    FileOutputStream appendFilen = new FileOutputStream(ProductInfo, true);
	    utfil = new PrintStream(appendFilen);

	    utfil.println("Product name: " + produkt.get_product_name());
	    utfil.println("Manufacturer: " + produkt.get_Manufacturer());
	    utfil.println("Description: " + produkt.get_description());
	    utfil.println("Meta tag title: " + produkt.get_Meta_Tag_title());
	    utfil.println("Meta tag description: " + produkt.get_Meta_Tag_Description());
	    utfil.println("Meta tag keywords: " + produkt.get_Meta_Tag_Keywords());
	    utfil.println("Product tags: " + produkt.get_Product_Tags());
	    utfil.println("Model: " + produkt.get_Model());
	    utfil.println("Location: " + produkt.get_Location());
	    utfil.println("Price: " + produkt.get_Price());
	    utfil.println("Quantity: " + produkt.get_Quantity());
	    utfil.println("Substract status: " + produkt.get_Subtract_Stock());
	    utfil.println("Out of stock status: " + produkt.get_Out_Of_Stock_Status());
	    utfil.println("SEO URL: " + produkt.get_SEO_URL());
	    utfil.println("Status: " + produkt.get_Status());
	    utfil.println("Sort order: " + produkt.get_Sort_Order());
	    utfil.println("Kategorier: " + produkt.get_Categories());
	    utfil.println("Attribute materiale: " + produkt.get_attribute_materiale());
	    utfil.println("Attribute størrelse: " + produkt.get_attribute_stoerrelse());
	    utfil.println("Attribute antall: " + produkt.get_attribute_antall());
	    utfil.println("Attribute maletilstand: " + produkt.get_attribute_maletilstand());
	    utfil.println("Attribute passende skalaer: " + produkt.get_attribute_skalaer());
	    utfil.println("Image featured: " + produkt.get_image_featured());
	    saveImage(produkt.get_image_featured(), produkt.get_SEO_URL() + "-1");
	    // saveImage2(produkt.get_image_featured(), "test");

	    utfil.println("Image additional: " + produkt.get_image_additional());
	    utfil.println("URL source: " + produkt.get_source());
	    utfil.println("");
	    utfil.println("Content: " + produkt.get_content());

	    utfil.close();
	    utfil = null;

	} catch (Exception e) {
	    System.out.println(this.getClass().toString() + " print_object funka ikke");
	    e.printStackTrace();
	}
    }

    public void saveImage(String photoUrl, String photoId) {
	System.out.println(this.getClass().toString() + " saveImage " + photoUrl);
	if (photoUrl != null) {
	    try (InputStream in = new URL(photoUrl).openStream()) {
		Files.copy(in, Paths.get("" + photoId + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    public void saveImage2(String imageUrl, String destinationFile) throws Exception {
	try {
	    File dir = new File(destinationFile);
	    if (!dir.getParentFile().exists())
		dir.getParentFile().mkdirs();

	    if (!dir.exists()) {
		dir.createNewFile();
	    }

	    URL url = new URL(imageUrl);
	    InputStream is = url.openStream();
	    OutputStream os = new FileOutputStream(destinationFile);
	    try {

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
		    os.write(b, 0, length);
		}
	    } catch (IOException T) {
		File ProductInfo = new File(destinationFile + ".txt");
		try {

		    if (!ProductInfo.getParentFile().exists())
			ProductInfo.getParentFile().mkdirs();

		    if (!ProductInfo.exists()) {
			ProductInfo.createNewFile();
		    }
		    PrintStream utfil;
		    FileOutputStream appendFilen = new FileOutputStream(ProductInfo, true);
		    utfil = new PrintStream(appendFilen);
		    utfil.println("Bilde kunne ikke lagres: " + imageUrl);

		} catch (Exception E) {
		}
	    }

	    is.close();
	    os.close();
	} catch (IOException T) {
	    // Class_Controller.ReportError(T);
	}
    }
}
