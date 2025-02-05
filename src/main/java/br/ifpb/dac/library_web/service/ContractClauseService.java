package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.ContractClauseRequest;
import br.ifpb.dac.library_web.dto.ContractClauseResponse;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.entity.ContractClause;
import br.ifpb.dac.library_web.mapper.ContractClauseMapper;
import br.ifpb.dac.library_web.repository.ContractClauseRepository;
import br.ifpb.dac.library_web.repository.ContractRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractClauseService {

    private final ContractClauseRepository contractClauseRepository;
    private final ContractRepository contractRepository;

    // Cria uma nova cláusula de contrato
    @Transactional
    public ContractClause save( ContractClause request) {


        // Verifica se o contrato existe
        Contract contract = contractRepository.findById(request.getContract().getId())
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + request.getContract().getId()));
        request.setContract(contract);
        return contractClauseRepository.save(request);
    }

    // Busca uma cláusula de contrato por ID
    @Transactional
    public ContractClause findById(Long id) {
        return contractClauseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract clause not found with id: " + id));

    }

    // Busca todas as cláusulas de contrato
    @Transactional
    public List<ContractClause> findAll() {
       return contractClauseRepository.findAll();
    }

    // Atualiza uma cláusula de contrato
    @Transactional
    public ContractClause update(Long id, ContractClauseRequest request) {
        ContractClause existingClause = contractClauseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract clause not found with id: " + id));

        existingClause.setDescription(request.getDescription());
        existingClause.setFineValue(request.getFineValue());

        // Atualiza o contrato associado
        Contract contract = contractRepository.findById(request.getContractId())
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + request.getContractId()));
        existingClause.setContract(contract);

        ContractClause updatedClause = contractClauseRepository.save(existingClause);
        contractClauseRepository.flush();
        return updatedClause;

    }

    // Deleta uma cláusula de contrato
    @Transactional
    public void delete(Long id) {
        ContractClause clause = contractClauseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract clause not found with id: " + id));
        contractClauseRepository.delete(clause);
    }

}
