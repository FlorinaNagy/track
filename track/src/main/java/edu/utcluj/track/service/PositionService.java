package edu.utcluj.track.service;

import edu.utcluj.track.controller.PositionController;
import edu.utcluj.track.repository.PositionRepository;
import edu.utcluj.track.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author radu.miron
 * @since 18.10.2016
 */

@Service
public class PositionService {
    @Autowired
    private PositionRepository positionRepository;

    public static final Logger log = LoggerFactory.getLogger(PositionController.class);

     //   @Transactional(propagation = Propagation.REQUIRED)
    public Position savePosition(Position position) {
        log.info("save position = " + position);
        return positionRepository.save(position);
    }

    public void deletePosition(Long id) {
        positionRepository.delete(id);
    }

    public Position getPosition(Long id) {
        return positionRepository.getPosition(id);
    }

    public ArrayList<Position> findByTerminalIdAndStartDateAndEndDate(String terminalId, Date startDate, Date endDate) {
        return positionRepository.findByTerminalIdAndStartDateAndEndDate(terminalId, startDate, endDate);
    }

    public List<Position> findAllPositionsByTerminalId(String terminalId) {
        return positionRepository.findAllPositionByTerminalId(terminalId);
    }
}

