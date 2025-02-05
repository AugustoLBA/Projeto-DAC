package br.ifpb.dac.library_web.service;

import br.ifpb.dac.library_web.dto.LibraryRequest;
import br.ifpb.dac.library_web.dto.LibraryResponse;
import br.ifpb.dac.library_web.entity.Adress;
import br.ifpb.dac.library_web.entity.Contract;
import br.ifpb.dac.library_web.entity.Library;
import br.ifpb.dac.library_web.exception.ResourceNotFoundException;
import br.ifpb.dac.library_web.exception.infra.MessageKeyEnum;
import br.ifpb.dac.library_web.mapper.AdressMapper;
import br.ifpb.dac.library_web.mapper.LibraryMapper;
import br.ifpb.dac.library_web.repository.AdressRepository;
import br.ifpb.dac.library_web.repository.ContractRepository;
import br.ifpb.dac.library_web.repository.LibraryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final ContractRepository contractRepository;


    @Transactional
    public Library save(Library library) {

        if(library.getContract() != null){
            Contract contract = contractRepository.findById(library.getContract().getId()).
                    orElseThrow(() -> new RuntimeException("Contract not found with id: " + library.getContract().getId()));
            library.setContract(contract);
        }
        return libraryRepository.save(library);
    }


    public Library findById(Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(MessageKeyEnum.ADDRESS_NOT_FOUND_WITH_ID.getMessage(id)));
    }

    public List<Library> findAll() {
        return libraryRepository.findAll();
    }
    @Transactional
    public Library update(Long id, LibraryRequest libraryRequest) {
        Library existinLibrary = libraryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(MessageKeyEnum.LIBRARY_NOT_FOUND_WITH_ID.getMessage(id)));

        existinLibrary.setName(libraryRequest.getName());
        Adress adress = AdressMapper.toAdress(libraryRequest.getAddress());
        adress.setLibrary(existinLibrary);
        existinLibrary.setAdress(adress);


        if(libraryRequest.getContractId() != null){
            Contract contract = contractRepository.findById(libraryRequest.getContractId())
                .orElseThrow(() -> new ResourceNotFoundException(MessageKeyEnum.CONTRACT_NOT_FOUND_WITH_ID.getMessage(libraryRequest.getContractId())));
            existinLibrary.setContract(contract);
        }
        return libraryRepository.save(existinLibrary);
    }
    @Transactional
    public void delete(Long id) {
        if(!libraryRepository.existsById(id)) {
            throw new ResourceNotFoundException(MessageKeyEnum.LIBRARY_NOT_FOUND_WITH_ID.getMessage(id));
        }
        libraryRepository.deleteById(id);
    }


}
