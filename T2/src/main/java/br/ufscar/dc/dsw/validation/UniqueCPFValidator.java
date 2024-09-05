package br.ufscar.dc.dsw.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.domain.Profissional;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, Profissional> {

    @Autowired
    private IProfissionalDAO dao;

    @Override
    public boolean isValid(Profissional profissional, ConstraintValidatorContext context) {
        if (dao != null) {
            Profissional existingProfissional = dao.findByCpf(profissional.getCpf());
            
            // Verifica se o CPF pertence a um profissional diferente
            return existingProfissional == null || existingProfissional.getId().equals(profissional.getId());
        } else {
            return true;
        }
    }
}
