package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64.Encoder;
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

import br.ufscar.dc.dsw.domain.Profissional;
import br.ufscar.dc.dsw.domain.Profissional.Sexo;
import br.ufscar.dc.dsw.service.spec.IProfissionalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalRestController {

    @Autowired
    private IProfissionalService profissionalService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private void parse(@Valid Profissional profissional, JSONObject json) {
        Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				profissional.setId(((Integer) id).longValue());
			} else {
				profissional.setId((Long) id);
			}
		}

        profissional.setEmail((String) json.get("email"));
        profissional.setNome((String) json.get("nome"));
        profissional.setPassword((String) json.get("password"));
        profissional.setRole("ROLE_PROFISSIONAL");
        profissional.setCpf((String) json.get("cpf"));   
        profissional.setTelefone((String) json.get("telefone"));   
        profissional.setSexo(Sexo.valueOf(json.get("sexo").toString().toUpperCase()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = dateFormat.parse(json.get("dataNascimento").toString());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            profissional.setDataNascimento(sqlDate);
        } catch (ParseException e) {
            // Trate a exceção conforme necessário
            e.printStackTrace();
        }

    }

    @PostMapping
	@ResponseBody
    public ResponseEntity<Profissional> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Profissional profissional = new Profissional();
                parse(profissional, json);
                profissional.setPassword(encoder.encode(profissional.getPassword()));
                profissionalService.salvar(profissional);
                return ResponseEntity.ok(profissional);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> lista() {
        List<Profissional> lista = profissionalService.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> lista(@PathVariable("id") long id) {
        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Profissional profissional = profissionalService.buscarPorId(id);
                if (profissional == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(profissional, json);
                    profissionalService.salvar(profissional);
                    return ResponseEntity.ok(profissional);
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
        Profissional profissional = profissionalService.buscarPorId(id);
        if (profissional == null) {
            return ResponseEntity.notFound().build();
        } else {
            profissionalService.excluir(id);
            return ResponseEntity.noContent().build();
        }
    }
}
