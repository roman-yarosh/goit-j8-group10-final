package ua.goit.finall.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.goit.finall.model.EventType;
import ua.goit.finall.service.EventTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/eventTypes")
public class EventTypeRestController {

    @Autowired
    private EventTypeService eventTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<EventType>> getAllEventTypes() {
        List<EventType> eventTypeList = this.eventTypeService.getAll();

        if (eventTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventTypeList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{eventTypeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> getEventType(@PathVariable("eventTypeId") Long eventTypeId) {
        if (eventTypeId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        EventType eventType = this.eventTypeService.getById(eventTypeId);

        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(eventType, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> saveEventType(@RequestBody @Valid EventType eventType, UriComponentsBuilder builder) {
        HttpHeaders headers = new HttpHeaders();

        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.eventTypeService.save(eventType);
        headers.setLocation(builder.path("/api/eventTypes/{eventTypeId}").buildAndExpand(eventType.getId()).toUri());
        return new ResponseEntity<>(eventType, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{eventTypeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> deleteEventType(@PathVariable("eventTypeId") Long eventTypeId) {
        EventType eventType = this.eventTypeService.getById(eventTypeId);

        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.eventTypeService.delete(eventTypeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
