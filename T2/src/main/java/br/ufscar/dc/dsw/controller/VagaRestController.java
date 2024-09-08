package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import br.ufscar.dc.dsw.domain.Vaga;
import br.ufscar.dc.dsw.service.spec.IEmpresaService;
import br.ufscar.dc.dsw.service.spec.IVagaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vagas")
public class VagaRestController {

    @Autowired
    private IVagaService vagaService;

    @Autowired
    private IEmpresaService empresaService;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private void parse(@Valid Vaga vaga, JSONObject json) {
        Object id = json.get("id");
        if (id != null) {
            if (id instanceof Integer) {
                vaga.setId(((Integer) id).longValue());
            } else {
                vaga.setId((Long) id);
            }
        }

        vaga.setDescricao((String) json.get("descricao"));
        vaga.setRemuneracao(Double.parseDouble(json.get("remuneracao").toString()));

        // Parsing empresa from JSON and setting it
        Long empresaId = Long.parseLong(json.get("empresaId").toString());
        Empresa empresa = empresaService.buscarPorId(empresaId);
        vaga.setEmpresa(empresa);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date utilDate = dateFormat.parse(json.get("dataLimiteInscricao").toString());
            Date sqlDate = new Date(utilDate.getTime());
            vaga.setDataLimiteInscricao(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        vaga.setDataCriacao(new Timestamp(System.currentTimeMillis()));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Vaga> cria(@RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Vaga vaga = new Vaga();
                parse(vaga, json);
                vagaService.salvar(vaga);
                return ResponseEntity.ok(vaga);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> lista() {
        List<Vaga> lista = vagaService.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> lista(@PathVariable("id") long id) {
        Vaga vaga = vagaService.buscarPorId(id);
        if (vaga == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vaga);
    }

    @GetMapping("/empresas/{id}")
    public ResponseEntity<List<Vaga>> listaPorEmpresa(@PathVariable("id") long id) {
        Empresa empresa = empresaService.buscarPorId(id);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        List<Vaga> vagas = vagaService.buscarPorEmpresa(empresa);
        if (vagas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(vagas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
        try {
            if (isJSONValid(json.toString())) {
                Vaga vaga = vagaService.buscarPorId(id);
                if (vaga == null) {
                    return ResponseEntity.notFound().build();
                } else {
                    parse(vaga, json);
                    vagaService.salvar(vaga);
                    return ResponseEntity.ok(vaga);
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
        Vaga vaga = vagaService.buscarPorId(id);
        if (vaga == null) {
            return ResponseEntity.notFound().build();
        } else {
            vagaService.excluir(id);
            return ResponseEntity.noContent().build();
        }
    }
}
