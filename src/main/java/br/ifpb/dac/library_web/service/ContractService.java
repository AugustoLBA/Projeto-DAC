package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.ContractRequest;
import br.ifpb.dac.library_web.dto.ContractResponse;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.entity.ContractClause;
import br.ifpb.dac.library_web.entity.Library;
import br.ifpb.dac.library_web.entity.User;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.mapper.ContractMapper;
import br.ifpb.dac.library_web.repository.ContractClauseRepository;
import br.ifpb.dac.library_web.repository.ContractRepository;
import br.ifpb.dac.library_web.repository.LibraryRepository;
import br.ifpb.dac.library_web.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;
    private final LibraryRepository libraryRepository;
    private final ContractClauseRepository contractClauseRepository;

    // Cria um novo contrato
    @Transactional
    public Contract save(Contract contract) {


        // Verifica se o usuário, a biblioteca e as cláusulas existem
        User user = userRepository.findById(contract.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + contract.getUser().getId()));
        contract.setUser(user);

        Library library = libraryRepository.findById(contract.getLibrary().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Library not found with id: " + contract.getLibrary().getId()));
        contract.setLibrary(library);

        if (contract.getClause() != null) {
            List<ContractClause> clauses = contractClauseRepository.findAll();
            /*if (clauses.size() != contract.getClause().size()) {
                throw new RuntimeException("One or more clauses not found");
            }*/
            contract.setClause(clauses);
        }
        return contractRepository.save(contract);
    }
    public Contract findById(Long id) {
        return contractRepository.findById(id).orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
    }
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
    @Transactional
    public Contract update(Long id, ContractRequest request) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));

        existingContract.setContractStartDate(request.getContractStartDate());
        existingContract.setContractEndDate(request.getContractEndDate());
        existingContract.setStatus(request.getStatus());
        existingContract.setValue(request.getValue());

        // Atualiza o usuário e a biblioteca
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(MessageKeyEnum.USER_NOT_FOUND_WITH_ID.getMessage()));
        existingContract.setUser(user);

        Library library = libraryRepository.findById(request.getLibraryId())
                .orElseThrow(() -> new ResourceNotFoundException(MessageKeyEnum.LIBRARY_NOT_FOUND_WITH_ID.getMessage()));;
        existingContract.setLibrary(library);

        // Atualiza as cláusulas
        if (request.getClauseIds() != null) {
            List<ContractClause> clauses = contractClauseRepository.findAllById(request.getClauseIds());
            if (clauses.size() != request.getClauseIds().size()) {
                throw new ResourceNotFoundException(MessageKeyEnum.ONE_OR_MORE_CLAUSES_NOT_FOUND.getMessage());
            }
            existingContract.setClause(clauses);
        }
        Contract updatedContract = contractRepository.save(existingContract);
        return  contractRepository.save(updatedContract);
    }
    @Transactional
    public void delete(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new ResourceNotFoundException(MessageKeyEnum.CONTRACT_NOT_FOUND_WITH_ID.getMessage());
        }
        contractRepository.deleteById(id);
    }
}

