package edu.utcluj.track.repository;

import edu.utcluj.track.model.Position;


import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
public interface PositionRepository extends JpaRepository<Position, Long> {
}
