package com.usuarios.api_crud_usuarios.repository;

import com.usuarios.api_crud_usuarios.model.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByCliente_Id(Long clienteId);

    List<Agendamento> findByProfissional_Id(Long profissionalId);

    List<Agendamento> findByProfissional_IdAndDataHoraInicioBetween(
            Long profissionalId, LocalDateTime dataInicio, LocalDateTime dataFim);

    boolean existsByProfissional_IdAndDataHoraInicioLessThanAndDataHoraFimGreaterThan(
            Long profissionalId, LocalDateTime dataHoraFim, LocalDateTime dataHoraInicio);
}
