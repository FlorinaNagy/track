package edu.utcluj.track.controller;

import edu.utcluj.track.utils.DateUtils;
import edu.utcluj.track.utils.TokenUtils;
import jdk.nashorn.internal.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.utcluj.track.service.PositionService;
import edu.utcluj.track.model.Position;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author radu.miron
 * @since 18.10.2016
 */
@RestController
@RequestMapping(value = "/position")
public class PositionController {

    public static final Logger log = LoggerFactory.getLogger(PositionController.class);

    @Autowired
    private PositionService positionService;

    @RequestMapping(method = RequestMethod.POST)
    public Position savePosition(@RequestBody Position position) {
        try {
            position.setCreateTime(DateUtils.getCurrentDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return positionService.savePosition(position);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deletePosition(@RequestBody Long id) {
        positionService.deletePosition(id);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Position getPosition(@PathVariable Long id) {
        return positionService.getPosition(id);
    }


    @RequestMapping(value = "/terminalId/{terminalId}", method = RequestMethod.GET)
    public Response findByTerminalIdAndStartDateAndEndDate(@PathVariable String terminalId, @RequestParam String startDate, @RequestParam String endDate) {
        ArrayList<Position> positionList;
        //log.info("startdate = " + startDate);
        //log.info("endDate = " + endDate);
        Date startDateConvert = DateUtils.convertStringToDate(startDate);
        Date endDateConvert = DateUtils.convertStringToDate(endDate);

        try {
            positionList = positionService.findByTerminalIdAndStartDateAndEndDate(terminalId, startDateConvert, endDateConvert);
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(("Error: " + e.getMessage())).build();
        }

        if (positionList.size() != 0) {
            return Response.status(Response.Status.OK).entity(positionList).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(("TerminalID not found")).build();
    }

    @RequestMapping(value = "/{terminalId}", method = RequestMethod.GET)
    public List<Position> findAllPositionByTerminalId(@PathVariable String terminalId) {
        return positionService.findAllPositionsByTerminalId(terminalId);
    }
}
