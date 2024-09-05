package br.ufscar.dc.dsw.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, Usuario> {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        if (dao != null) {
            Usuario existingUsuario = dao.findByEmail(usuario.getEmail());

            // Verifica se o e-mail pertence a um usuário diferente
            return existingUsuario == null || existingUsuario.getId().equals(usuario.getId());
        } else {
            return true;
        }
    }
}
