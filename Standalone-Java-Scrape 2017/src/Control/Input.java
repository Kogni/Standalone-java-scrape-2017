package Control;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Input {
    Control class_control;

    public Input(Control class_control) {
	this.class_control = class_control;
    }

    public void Load_URLs() {
	// System.out.println( this.getClass().toString()+" LoadSettings A "+Source );
	try {
	    // System.out.println( this.getClass().toString()+" LoadSettings B" );

	    File filen = new File("URLs.txt");
	    if (!filen.exists()) {

		return;
	    }
	    FileInputStream fstream = new FileInputStream(filen);
	    DataInputStream in = new DataInputStream(fstream);
	    int Teller = 0;

	    String Innlest = in.readLine();
	    while (Innlest != null) {
		class_control.Start_new_scrape(Innlest);
		Innlest = in.readLine ( );
	    }
	    in.close();

	} catch (NullPointerException e) {
	    // System.out.println( this.getClass().toString()+" LoadSettings C" );
	} catch (Exception e) {
	    // System.out.println( this.getClass().toString()+" LoadSettings D" );
	}

	// System.out.println( this.getClass().toString()+" LoadSettings Bx2 done loading" );
	// System.out.println( this.getClass().toString()+" LoadSettings finished. Working="+Working );
    }
}
