package com.maesquiv.descargaarchivos;

import java.lang.reflect.Field;
import java.util.Map;

public class MapperRowSheetUtil {
	public static <T> T convertMapToObject(Map<String, Object> mapa, Class<T> clase) throws Exception {
		T objeto = clase.getDeclaredConstructor().newInstance();
		for(Map.Entry<String, Object> entry: mapa.entrySet()) {
			String nombreCampo = entry.getKey();
			Object valorCampo = entry.getValue();
			
			Field campo = clase.getDeclaredField(nombreCampo);
			campo.setAccessible(true);
			campo.set(objeto, valorCampo);
		}
		return objeto;
	}
}
