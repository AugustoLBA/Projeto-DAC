package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.ContractRequest;
import br.ifpb.dac.library_web.dto.ContractResponse;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.mapper.ContractMapper;
import br.ifpb.dac.library_web.service.ContractService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/contracts")
public class ContractController {
    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<ContractResponse> saveContract(@Valid @RequestBody ContractRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ContractMapper.toResponse(contractService.save(ContractMapper.toContract(contractRequest))));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getContractById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ContractMapper.toResponse(contractService.findById(id)));
    }
    @GetMapping
    public ResponseEntity<List<ContractResponse>> getAllContracts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ContractMapper.toResponseList(contractService.findAll()));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ContractResponse> updateContract(@PathVariable Long id, @Valid @RequestBody ContractRequest contractRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ContractMapper.toResponse(contractService.update(id, contractRequest)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ContractResponse> deleteContract(@PathVariable Long id) {
        contractService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
