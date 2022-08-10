package com.springbatch.contasbancarias.dominio;

import java.util.Objects;

public enum TipoConta {
	PRATA, OURO, PLATINA, DIAMANTE, INVALIDA;
	
	public static TipoConta fromFaixaSalarial(Double faixaSalarial) {
		if (Objects.isNull(faixaSalarial))
			return INVALIDA;
		if (faixaSalarial <= 3000)
			return PRATA;
		else if (faixaSalarial > 3000 && faixaSalarial <= 5000)
			return OURO;
		else if (faixaSalarial > 5000 && faixaSalarial <= 10000)
			return PLATINA;
		else
			return DIAMANTE;
	}
}
