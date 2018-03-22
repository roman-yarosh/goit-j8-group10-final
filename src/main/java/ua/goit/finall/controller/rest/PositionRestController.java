package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.Position;
import ua.goit.finall.service.PositionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/positions")
public class PositionRestController {
    
    @Autowired
    PositionService positionService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Position>> getAllPositions() {
        List<Position> positionList = this.positionService.getAll();

        if (positionList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(positionList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{positionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Position> getPosition(@PathVariable("positionId") Long positionId) {
        if (positionId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Position position = this.positionService.getById(positionId);

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(position, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Position> savePosition(@RequestBody @Valid Position position, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.positionService.save(position);
        headers.setLocation(builder.path("/api/positions/{positionId}").buildAndExpand(position.getId()).toUri());
        return new ResponseEntity<>(position, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{positionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Position> deletePosition(@PathVariable("positionId") Long positionId) {
        Position position = this.positionService.getById(positionId);

        if (position == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.positionService.delete(positionId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
