package edu.utcluj.track.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.utcluj.track.service.PositionService;
import edu.utcluj.track.model.Position;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
@RestController
@RequestMapping(value = "/position")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.POST)
    public Position save(@RequestBody Position position) {
        return positionService.savePosition(position);
    }
}
