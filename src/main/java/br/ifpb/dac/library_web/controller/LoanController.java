package br.ifpb.dac.library_web.controller;

import br.ifpb.dac.library_web.dto.LoanRequest;
import br.ifpb.dac.library_web.dto.LoanResponse;
import br.ifpb.dac.library_web.entity.Loan;
import br.ifpb.dac.library_web.mapper.LoanMapper;
import br.ifpb.dac.library_web.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/loans")
public class LoanController {
    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanResponse> save(@Valid @RequestBody LoanRequest loanRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(LoanMapper.toResponse(loanService.save(LoanMapper.toLoan(loanRequest))));
    }
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(LoanMapper.toResponse(loanService.findById(id)));
    }
    @GetMapping
    public ResponseEntity<List<LoanResponse>> findAllLoans() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(LoanMapper.toResponseList(loanService.findAll()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LoanResponse> delete(@PathVariable Long id) {
        loanService.findById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> update(@PathVariable Long id,@Valid @RequestBody LoanRequest loanRequest) {
        return ResponseEntity.ok(LoanMapper.toResponse(loanService.update(id,loanRequest)));
    }
}
