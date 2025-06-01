package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByClienteId(Long clienteId);
    List<Agendamento> findByProfissionalId(Long profissionalId);

    boolean existsByProfissionalIdAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
        Long profissionalId, LocalDateTime dataHoraFim, LocalDateTime dataHoraInicio);
}
