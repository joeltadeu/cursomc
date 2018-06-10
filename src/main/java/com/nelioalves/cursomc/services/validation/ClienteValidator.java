package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;
import com.nelioalves.cursomc.services.validation.util.ValidationUtil;

public class ClienteValidator implements ConstraintValidator<ClienteValidation, ClienteNewDTO> {
	
	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> errors = new ArrayList<>();
		
		if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !ValidationUtil.isValidCPF(dto.getCpfOuCnpj())) {
			errors.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !ValidationUtil.isValidCNPJ(dto.getCpfOuCnpj())) {
			errors.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		for (FieldMessage field : errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName())
					.addConstraintViolation();
		}
		return errors.isEmpty();
	}
}
