package com.nelioalves.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteNewDTO;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.resources.exceptions.FieldMessage;
import com.nelioalves.cursomc.services.validation.util.ValidationUtil;

public class ClienteValidator implements ConstraintValidator<ClienteValidation, ClienteNewDTO> {

	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ClienteRepository repository;

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> errors = new ArrayList<>();

		if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod())
				&& !ValidationUtil.isValidCPF(dto.getCpfOuCnpj())) {
			errors.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod())
				&& !ValidationUtil.isValidCNPJ(dto.getCpfOuCnpj())) {
			errors.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		Optional<Cliente> optionalCliente = repository.findByEmail(dto.getEmail());
		if (optionalCliente.isPresent() && !optionalCliente.get().getId().equals(uriId) ) {
			errors.add(new FieldMessage("email", "E-mail já existente"));
		}

		for (FieldMessage field : errors) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage()).addPropertyNode(field.getFieldName())
					.addConstraintViolation();
		}
		return errors.isEmpty();
	}
}
