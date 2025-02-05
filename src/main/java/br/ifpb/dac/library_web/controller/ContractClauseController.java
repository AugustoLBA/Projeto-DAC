package br.ifpb.dac.library_web.controller;


import br.ifpb.dac.library_web.dto.ContractClauseRequest;
import br.ifpb.dac.library_web.dto.ContractClauseResponse;
import br.ifpb.dac.library_web.mapper.ContractClauseMapper;
import br.ifpb.dac.library_web.service.ContractClauseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/contract-clauses")
public class ContractClauseController {

    private final ContractClauseService contractClauseService;

    // Cria uma nova cláusula de contrato
    @PostMapping
    public ResponseEntity<ContractClauseResponse> save(@Valid @RequestBody ContractClauseRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ContractClauseMapper.toResponse(contractClauseService.save(ContractClauseMapper.toContractClause(request))));
    }

    // Busca uma cláusula de contrato por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContractClauseResponse> findById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ContractClauseMapper.toResponse(contractClauseService.findById(id)));
    }

    // Busca todas as cláusulas de contrato
    @GetMapping
    public ResponseEntity<List<ContractClauseResponse>> findAll() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(ContractClauseMapper.toResponseList(contractClauseService.findAll()));
    }

    // Atualiza uma cláusula de contrato
    @PutMapping("/{id}")
    public ResponseEntity<ContractClauseResponse> updateClause(@PathVariable Long id,@Valid @RequestBody ContractClauseRequest request) {

        return ResponseEntity.ok(ContractClauseMapper.toResponse(contractClauseService.update(id,request)));
    }

    // Deleta uma cláusula de contrato
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contractClauseService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
