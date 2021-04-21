package br.com.hotmart.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author l.rocha
 *
 */
public abstract class Utils {

	/**
	 * 
	 * @return New Date in Pattern(dd-MM-yyyy HH:mm:ss)
	 */
	public static String getDateFormatted() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return LocalDateTime.now().format(formatter);
	}

}
