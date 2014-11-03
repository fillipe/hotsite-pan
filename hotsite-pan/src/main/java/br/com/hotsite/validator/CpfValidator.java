package br.com.hotsite.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.hotsite.util.UsuarioUtil;

@FacesValidator("cpfValidator")
public class CpfValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		UsuarioUtil usuarioUtil = new UsuarioUtil();
		if (!usuarioUtil.validarCpf(value.toString())) {
			FacesMessage mensagem = new FacesMessage("CPF inválido");
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(mensagem);
		}
	}

}
