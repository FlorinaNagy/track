package edu.utcluj.track.service;

import edu.utcluj.track.repository.PositionRepository;
import edu.utcluj.track.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

     //   @Transactional(propagation = Propagation.REQUIRED)
    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }
}
