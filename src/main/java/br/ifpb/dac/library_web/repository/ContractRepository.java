package br.ifpb.dac.library_web.repository;

import br.ifpb.dac.library_web.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
