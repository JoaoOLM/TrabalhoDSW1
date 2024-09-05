package br.ufscar.dc.dsw.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.domain.Empresa;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, Empresa> {

    @Autowired
    private IEmpresaDAO dao;

    @Override
    public boolean isValid(Empresa empresa, ConstraintValidatorContext context) {
        if (dao != null) {
            Empresa existingEmpresa = dao.findByCNPJ(empresa.getCNPJ());
            
            // Verifica se o CNPJ pertence a uma empresa diferente
            return existingEmpresa == null || existingEmpresa.getId().equals(empresa.getId());
        } else {
            return true;
        }
    }
}
