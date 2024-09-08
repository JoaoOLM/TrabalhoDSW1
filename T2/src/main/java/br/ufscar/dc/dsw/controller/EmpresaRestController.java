package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Empresa;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaRestController {

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private void parse(@Valid Empresa empresa, JSONObject json) {
        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                empresa.setId(((Integer) id).longValue());
            } else {
                empresa.setId((Long) id);
            }
        }

        empresa.setEmail((String) json.get("email"));
        empresa.setNome((String) json.get("nome"));
        empresa.setPassword((String) json.get("password"));
        empresa.setRole("ROLE_EMPRESA");
        empresa.setCNPJ((String) json.get("CNPJ"));
        empresa.setDescricao((String) json.get("descricao"));
        empresa.setCidade((String) json.get("cidade"));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Empresa> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Empresa empresa = new Empresa();
                parse(empresa, json);
                empresa.setPassword(encoder.encode(empresa.getPassword()));
                empresaService.salvar(empresa);
                return ResponseEntity.ok(empresa);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> lista() {
        List<Empresa> lista = empresaService.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> lista(@PathVariable("id") long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @GetMapping("/cidades/{nome}")
    public ResponseEntity<List<String>> listaPorCidade(@PathVariable("nome") String cidade) {
        List<Empresa> empresas = empresaService.buscarPorCidade(cidade);
        List<String> lista = new ArrayList<>();
        for (Empresa r : empresas) {
            lista.add(r.getNome() + "/" + r.getCNPJ());
        }
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Empresa empresa = empresaService.buscarPorId(id);
                if (empresa == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(empresa, json);
                    empresaService.salvar(empresa);
                    return ResponseEntity.ok(empresa);
                }
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        } else {
            empresaService.excluir(id);
            return ResponseEntity.noContent().build();
        }
    }
}
