package edu.utcluj.track.repository;

import edu.utcluj.track.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p FROM Position p WHERE p.id = :id")
    Position getPosition(@Param("id") Long id);


    @Query("SELECT p FROM Position p WHERE p.terminalId = :terminalId " +
            "AND p.createTime between :startDate and :endDate")
    ArrayList<Position> findByTerminalIdAndStartDateAndEndDate(
            @Param("terminalId") String terminalId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT p FROM Position p WHERE p.terminalId = :terminalId")
    List<Position> findAllPositionByTerminalId(@Param("terminalId") String terminalId);
}
