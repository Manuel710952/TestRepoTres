package com.maesquiv.descargaarchivos;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContantesMapper {
	public static final Map<Integer, String> RELACION_CAMPO_CELDA = Collections.unmodifiableMap(
			new HashMap<Integer, String>() {{
				put(1, "mes");
				put(2, "anio");
				put(3, "mn");
				put(4, "usd");
				put(5, "udis");
			}}
	);
	
	public static final String FORMAT_VALOR_PORCENTAJE = "#.####";
	public static final int ROW_INDEX_INICIO = 4;
	public static final int COLUMN_INDEX_FINAL = 5;
}
