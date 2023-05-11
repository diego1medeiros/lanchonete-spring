package br.com.lanchonete.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataData {

	public static String formatarData(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String dataRetorno = sdf.format(data);
		return dataRetorno;
	}
}
