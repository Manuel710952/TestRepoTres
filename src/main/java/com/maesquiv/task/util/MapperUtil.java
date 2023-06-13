package com.maesquiv.task.util;

import com.maesquiv.dto.PagosDTO;
import com.maesquiv.task.constants.ConstantsFileJobPagos;

public class MapperUtil {
	public static PagosDTO stringToPagos(String[] line) {
		PagosDTO pago = new PagosDTO();
		try {
			pago.setFolio(Integer.parseInt(line[ConstantsFileJobPagos.INDEX_FOLIO]));
			pago.setDescripcion(line[ConstantsFileJobPagos.INDEX_DESCRIPCION]);
			pago.setImporte(Double.parseDouble(line[ConstantsFileJobPagos.INDEX_IMPORTE]));
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Error de columna no valido");
		}
		return pago;
	}
}
