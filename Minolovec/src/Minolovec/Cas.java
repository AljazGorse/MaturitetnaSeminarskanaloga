package Minolovec;

import java.text.SimpleDateFormat;
import java.util.*;

public class Cas {
	
	Calendar koledar;
	SimpleDateFormat cas = new SimpleDateFormat("dd.MM.yyyy 'ob' HH:mm:ss");
	
	String datum;
	
	String datum() {
		datum = cas.format(Calendar.getInstance().getTime());
		return datum;
	}
	
}

