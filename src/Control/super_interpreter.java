package Control;

public class super_interpreter {
    
    Control class_control;
    
    Interpreter_regardless interpret_regardless;
    Interpreter_Ebay interpret_ebay;
    Interpreter_AliExpress interpret_AE;

    public super_interpreter(Control control) {
	class_control=control;
	
	interpret_ebay = new Interpreter_Ebay(class_control);
	interpret_AE = new  Interpreter_AliExpress(class_control);
	interpret_regardless = new Interpreter_regardless(class_control);
    }

    public String get_kategorier() {
	return interpret_regardless.get_kategorier();
    }

    public String get_manufacturer() {
	String manufacturer = null;
	if ( class_control.aktivt_produkt.get_source().toString().contains("ebay")){
	    manufacturer = interpret_ebay.get_manufacturer();
	} else if ( class_control.aktivt_produkt.get_source().toString().contains("aliexpres")){
	    manufacturer = interpret_AE.get_manufacturer();
	}
	return manufacturer;
    }

    public String get_scales() {
	return interpret_regardless.get_scales();
    }

    public String get_materials() {
	return interpret_regardless.get_materials();
    }

    public String get_location() {
	String location = null;
	if ( class_control.aktivt_produkt.get_source().toString().contains("ebay")){
	    location = interpret_ebay.get_location();
	} else if ( class_control.aktivt_produkt.get_source().toString().contains("aliexpres")){
	    location = interpret_AE.get_location();
	}
	return location;
    }

    public int get_price() {
	int price = 0;
	if ( class_control.aktivt_produkt.get_source().toString().contains("ebay")){
	    price = interpret_ebay.get_price();
	} else if ( class_control.aktivt_produkt.get_source().toString().contains("aliexpres")){
	    price = interpret_AE.get_price();
	}
	return price;
    }

    public void get_images() {
	interpret_regardless.get_images();
    }

    public String get_adjektiver() {
	return interpret_regardless.get_adjektiver();
    }

    public String get_product_name() {
	return interpret_regardless.get_product_name();
    }

    public String get_Meta_Tag_Keywords() {
	return interpret_regardless.get_Meta_Tag_Keywords();
    }

    public String get_Product_Tags() {
	return interpret_regardless.get_Product_Tags();
    }

    public String get_Model() {
	String Model = null;
	if ( class_control.aktivt_produkt.get_source().toString().contains("ebay")){
	    Model = interpret_ebay.get_Model();
	} else if ( class_control.aktivt_produkt.get_source().toString().contains("aliexpres")){
	    Model = interpret_AE.get_Model();
	}
	return Model;
    }

    public String get_SEO_URL() {
	return interpret_regardless.get_SEO_URL();
    }

    public String get_Out_Of_Stock_Status() {
	String Out_Of_Stock_Status = null;
	if ( class_control.aktivt_produkt.get_source().toString().contains("ebay")){
	    Out_Of_Stock_Status = interpret_ebay.get_Out_Of_Stock_Status();
	} else if ( class_control.aktivt_produkt.get_source().toString().contains("aliexpres")){
	    Out_Of_Stock_Status = interpret_AE.get_Out_Of_Stock_Status();
	}
	return Out_Of_Stock_Status;
    }

    public String get_description() {
	return interpret_regardless.get_description();
    }

    public String get_Antall() {
	return interpret_regardless.get_Antall();
    }

    public String get_Scale() {
	return interpret_regardless.get_scale();
    }

}
