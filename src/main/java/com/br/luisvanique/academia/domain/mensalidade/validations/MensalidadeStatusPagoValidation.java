package com.br.luisvanique.academia.domain.mensalidade.validations;

import org.springframework.stereotype.Component;

import com.br.luisvanique.academia.domain.mensalidade.exception.StatusDePagamentoInvalidoException;

@Component
public class MensalidadeStatusPagoValidation implements MensalidadeStatusPagoValidator{
	
	private final Integer statusPago = 2;

	@Override
	public void validate(Integer status) {
		if(status != statusPago) {
			throw new StatusDePagamentoInvalidoException("Status do Pagamento inválido!");
		}
	}

}
