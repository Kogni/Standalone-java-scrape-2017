package Control;

public class Interpreter_regardless {
    Control class_control;

    public Interpreter_regardless(Control control) {

	this.class_control = control;
    }

    public String get_materials() {
	String materials = "";
	if ((class_control.product_content.contains("mdf ")) || (class_control.product_content.contains(" mdf"))) {
	    materials = materials + "MDF trefiberplater, ";
	}
	if (class_control.product_content.contains("cashmere")) {
	    materials = materials + "kasjmir, ";
	}
	if (class_control.product_content.contains("plastic")) {
	    materials = materials + "plast, ";
	} else {
	    if (class_control.product_content.contains("abs")) {
		materials = materials + "ABS plast, ";
	    }
	}

	return materials;
    }

    public String get_Antall() {
	String description = "";
	int fra;
	int til;
	String quant = null;
	if (class_control.product_content.contains("pcs")) {
	    fra = 0;
	    til = class_control.product_content.indexOf("pcs") + 3;
	    quant = class_control.product_content.substring(fra, til);
	    fra = quant.indexOf(" ");
	    // System.out.println(this.getClass().toString() + " get_description A quant=" + quant);
	    while (fra >= 0) {
		quant = quant.substring(fra + 1);
		// System.out.println(this.getClass().toString() + " get_description B quant=" + quant);
		fra = quant.indexOf(" ");

		String temp = quant;
		int fra2 = temp.indexOf(" ");
		temp = temp.substring(fra2 + 1);
		// System.out.println(this.getClass().toString() + " get_description C0 fra=" + fra+" temp="+temp);
		if (temp.indexOf(" ") >= 0) {
		    // System.out.println(this.getClass().toString() + " get_description C1 fra=" + fra+" temp="+temp+" quant="+quant);
		} else {
		    // System.out.println(this.getClass().toString() + " get_description C2 fra=" + fra+" temp="+temp+" quant="+quant);
		    break;
		}
		// System.out.println(this.getClass().toString() + " get_description D fra=" + fra+" quant="+quant);
	    }

	}

	// System.out.println(this.getClass().toString() + " get_description E quant=" + quant);
	fra = quant.indexOf(">");
	// System.out.println(this.getClass().toString() + " get_description E1 fra=" + fra);
	while (fra >= 0) {
	    quant = quant.substring(fra + 1);
	    // System.out.println(this.getClass().toString() + " get_description E2 quant=" + quant);
	    fra = quant.indexOf(">");
	    // System.out.println(this.getClass().toString() + " get_description E3 fra=" + fra+" quant="+quant);
	}
	// System.out.println(this.getClass().toString() + " get_description E4 fra=" + fra);

	int i = 0;
	while (i < quant.length() && !Character.isDigit(quant.charAt(i)))
	    i++;
	int j = i;
	while (j < quant.length() && Character.isDigit(quant.charAt(j)))
	    j++;
	int firstInt = Integer.parseInt(quant.substring(i, j));
	// System.out.println(this.getClass().toString() + " get_description F firstInt=" + firstInt);
	int loc_first_int = quant.indexOf(firstInt + "");
	// System.out.println(this.getClass().toString() + " get_description G loc_first_int=" + loc_first_int);
	quant = quant.substring(loc_first_int);
	// System.out.println(this.getClass().toString() + " get_description H quant=" + quant);

	i = 0;
	while (i < quant.length() && !Character.isAlphabetic(quant.charAt(i)))
	    i++;
	j = i;
	while (j < quant.length() && Character.isAlphabetic(quant.charAt(j)))
	    j++;
	String firstStr = quant.substring(i, j);
	// System.out.println(this.getClass().toString() + " get_description I firstInt=" + firstStr);
	loc_first_int = quant.indexOf(firstStr + "");
	// System.out.println(this.getClass().toString() + " get_description J loc_first_int=" + loc_first_int);
	quant = quant.substring(0, loc_first_int);
	// System.out.println(this.getClass().toString() + " get_description K quant=" + quant);

	return quant;
    }

    public String get_description() {
	String description = "";
	int fra;
	int til;

	if (class_control.product_content.contains("unassembled")) {
	    description = description + "<br><b>Alle deler må settes sammen selv, manual medfølger</b>";
	}

	// skala
	if (class_control.product_content.contains("28mm")) {
	    description = description + "<br><b>Skala:</b> 28mm";
	}
	if (class_control.product_content.contains("1:75")) {
	    description = description + "<br><b>Skala:</b> 1:75/OO-scale";
	}
	if (class_control.product_content.contains("1:100")) {
	    description = description + "<br><b>Skala:</b> 1:100/~13mm";
	}
	if (class_control.product_content.contains("1:150")) {
	    description = description + "<br><b>Skala:</b> 1:150/N-scale/<10mm";
	}
	return description;
    }

    public String get_kategorier() {
	String kategorier = "";
	// diorama produkttype
	if (class_control.product_content.contains("people")) {
	    kategorier = kategorier + "Miniature diorama > Scale model people, ";
	} else if ((class_control.product_content.contains("flock"))
		|| (class_control.product_content.contains("static grass"))) {
	    kategorier = kategorier + "Miniature diorama > Terreng > Static grass flock, ";
	    kategorier = kategorier + "Deler til DIY > Deler til diorama, ";
	} else if ((class_control.product_content.contains("shack"))
		|| (class_control.product_content.contains("bungalow"))
		|| (class_control.product_content.contains("house"))) {
	    kategorier = kategorier + "Miniature diorama > Miniature buildings, ";
	    kategorier = kategorier + "Deler til DIY > Deler til diorama, ";
	}

	// scale
	if (class_control.product_content.contains("28mm")) {
	    kategorier = kategorier + "Produkter etter skala > 28mm scale / o-scale, ";
	}
	if (class_control.product_content.contains("wargaming")) {
	    kategorier = kategorier + "28mm wargaming, ";
	}
	//Produkter etter skala  >  0-10mm, N-skala, Z-skala, T-skala 	10 	
	if (class_control.aktivt_produkt.get_Scale().contains("1:150")) {
	    kategorier = kategorier + "Produkter etter skala  >  0-10mm, N-skala, Z-skala, T-skala, ";
	}
	//Produkter etter skala  >  130mm+, 1:12 dukkehus 	130 	
	//Produkter etter skala  >  15mm scale / HO-scale 	15 	
	//Produkter etter skala  >  20mm scale / 1:72 	20 	
	//Produkter etter skala  >  28mm scale / o-scale 	28 	
	//Produkter etter skala  >  40mm / L-gauge 	40 	
	//Produkter etter skala  >  54mm / G scale 	54 	
	//Produkter etter skala  >  90mm 	90 	
	
	
	return kategorier;
    }

    public void get_images() {

    }

    public String get_product_name() {
	// input er categories, som allerede er sjekket i en annen metode
	String product_name = "";

	if ((class_control.aktivt_produkt.get_Categories().contains("people"))) {
	    product_name = product_name + "Mennesker";
	    System.out.println(this.getClass().toString() + " get_product_name 1 la til manufacturer: " + product_name);
	} else if (class_control.aktivt_produkt.get_Categories().contains("buildings")) {
	    product_name = product_name + "Miniatyrhus";
	    if (class_control.aktivt_produkt.get_attribute_materiale().contains("MDF")) {
		product_name = product_name + " i MDF";
	    }
	    if (class_control.aktivt_produkt.get_Categories().contains("wargaming")) {
		product_name = product_name + " for 28mm scala";
	    }
	    System.out.println(this.getClass().toString() + " get_product_name 1 la til manufacturer: " + product_name);
	} else if (class_control.aktivt_produkt.get_Categories().contains("Static grass")) {
	    product_name = product_name + "Static grass";
	} else {
	    System.out.println(this.getClass().toString() + " get_product_name 2 gjenkjenner ikke produkttype="
		    + class_control.aktivt_produkt.get_Categories());
	}
	
	//System.out.println(this.getClass().toString() + " get_product_name antall="+ class_control.aktivt_produkt.get_attribute_antall());
	if (class_control.aktivt_produkt.get_attribute_antall() != null) {
	    //product_name = class_control.aktivt_produkt.get_attribute_antall() + "x " + product_name;
	    product_name = product_name +", "+class_control.aktivt_produkt.get_attribute_antall() + "x, ";
	    //System.out.println(this.getClass().toString() + " get_product_name 1 la til manufacturer: " + product_name);
	}
	
	if (class_control.aktivt_produkt.get_Scale() != null) {
	    //product_name = class_control.aktivt_produkt.get_adjektiver() + product_name;
	    product_name = product_name+", "+class_control.aktivt_produkt.get_Scale();
	    // System.out.println(this.getClass().toString() + " get_product_name 1 la til manufacturer: " + product_name);
	}

	if (class_control.aktivt_produkt.get_adjektiver() != null) {
	    //product_name = class_control.aktivt_produkt.get_adjektiver() + product_name;
	    product_name = product_name+", "+class_control.aktivt_produkt.get_adjektiver();
	    // System.out.println(this.getClass().toString() + " get_product_name 1 la til manufacturer: " + product_name);
	}

	if ((class_control.aktivt_produkt.get_Manufacturer() != null)
		&& (class_control.aktivt_produkt.get_Manufacturer().length() > 0)) {
	    product_name = product_name + " fra " + class_control.aktivt_produkt.get_Manufacturer();
	    // System.out.println(this.getClass().toString() + " get_product_name 3 la til manufacturer: " + product_name);
	}

	// System.out.println(this.getClass().toString() + " get_product_name 4 product_name=" + product_name);
	return product_name;
    }

    public String get_Meta_Tag_Keywords() {
	String Meta_Tag_Keywords = "";
	if (class_control.aktivt_produkt.get_Categories().contains("buildings")) {
	    Meta_Tag_Keywords = Meta_Tag_Keywords
		    + "model diorama building, mini architecture models, miniature building models, miniature model buildings, architectural miniature models, diorama architecture, miniature architecture, diorama building, house miniature, miniature architectural models, miniature buildings, miniature house, miniature building, ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("people")) {
	    Meta_Tag_Keywords = Meta_Tag_Keywords
		    + "scale model people, diorama people, tiny people figurines, tiny people figures, miniature human figures, small plastic human figures, miniature model people, tiny people figurines, miniature people figures, miniature figures people, people miniatures, human miniature figures, tiny people figures, plastic people figurines, tiny plastic people, small plastic people, miniature people models, plastic people figures, miniature plastic people, ";
	}
	if (class_control.aktivt_produkt.get_Categories().contains("wargaming")) {
	    Meta_Tag_Keywords = Meta_Tag_Keywords + "wargaming, ";
	}
	if (class_control.aktivt_produkt.get_Categories().contains("diorama")) {
	    Meta_Tag_Keywords = Meta_Tag_Keywords + "diorama, ";
	}
	if (class_control.aktivt_produkt.get_Categories().contains("28mm")) {
	    Meta_Tag_Keywords = Meta_Tag_Keywords + "28mm, Heroic-Scale, Games Workshop, Warhammer, O-Scale, 30mm, ";
	}
	if (class_control.aktivt_produkt.get_Categories().contains("Static grass")) {
	    Meta_Tag_Keywords = Meta_Tag_Keywords
		    + "scale model grass, static grass, model railway static grass, grass for model railways, ho scale grass, model train grass, ";
	}
	return Meta_Tag_Keywords;
    }

    public String get_Product_Tags() {

	String Product_Tags = "";

	if (class_control.aktivt_produkt.get_Categories().contains("people")) {
	    Product_Tags = Product_Tags + "scale model people, figurer, ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("Static grass")) {
	    Product_Tags = Product_Tags + "static grass, ";
	} else if (class_control.aktivt_produkt.get_content().contains("house")) {
	    Product_Tags = Product_Tags + "house miniature building, ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("buildings")) {
	    Product_Tags = Product_Tags + "miniature building, ";
	} else if (class_control.aktivt_produkt.get_Categories().contains("Diorama")) {
	    Product_Tags = Product_Tags + "diorama, ";
	}
	if ((class_control.product_content.contains("snow")) || (class_control.product_content.contains(" ice"))) {
	    Product_Tags = Product_Tags + "vinter, ";
	}
	
	if (class_control.aktivt_produkt.get_Scale().contains("28mm")) {
	    Product_Tags = Product_Tags + "28mm, Heroic-Scale, Games Workshop, Warhammer, O-Scale, 30mm, ";
	}
	if (class_control.aktivt_produkt.get_Scale().contains("1:75")) {
	    Product_Tags = Product_Tags + "HO-scale, OO-scale, 1:72, 20mm, ";
	}
	if (class_control.aktivt_produkt.get_Scale().contains("1:100")) {
	    Product_Tags = Product_Tags + "12mm, 15mm, ";
	}
	if (class_control.aktivt_produkt.get_Scale().contains("1:150")) {
	    Product_Tags = Product_Tags + "1:150, N-scale, ";
	}

	return Product_Tags;
    }

    public String get_adjektiver() {
	String adjektiver = "";

	if ((class_control.product_content.contains("snow")) || (class_control.product_content.contains(" ice"))) {
	    adjektiver = adjektiver + "Snødekket ";
	}

	return adjektiver;
    }

    public String get_SEO_URL() {
	String SEO_URL = "";

	SEO_URL = class_control.aktivt_produkt.get_Model();
	SEO_URL = SEO_URL.replaceAll(" ", "-");
	return SEO_URL;
    }

    public String get_scale() {
	String scales = "";
	if (class_control.product_content.contains("28mm")) {
	    return "28mm";
	}
	if (class_control.product_content.contains("1:75")) {
	    return "1:75";
	}
	if (class_control.product_content.contains("1:100")) {
	    return "1:100";
	}
	if (class_control.product_content.contains("1:150")) {
	    return "1:150";
	}
	return scales;
    }
    
    public String get_scales() {
	String scales = "";
	if (class_control.aktivt_produkt.get_Scale().contains("28mm")) {
	    scales = scales + "28mm, Heroic-Scale, Games Workshop, Warhammer, O-Scale, 30mm, ";
	}
	if (class_control.aktivt_produkt.get_Scale().contains("1:75")) {
	    scales = scales + "HO-scale, OO-scale, 1:72, 20mm, ";
	}
	if (class_control.aktivt_produkt.get_Scale().contains("1:100")) {
	    scales = scales + "12mm, 15mm, ";
	}
	if (class_control.aktivt_produkt.get_Scale().contains("1:150")) {
	    scales = scales + "1:150, N-scale, 1:140, 10mm, ";
	}
	return scales;
    }

}
