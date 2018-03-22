package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.EventRepository;
import ua.goit.finall.model.Event;
import ua.goit.finall.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public Event getById(Long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public void save(Event event) {
        eventRepository.save(event);
    }

    @Override
    public void delete(Long id) {
        eventRepository.delete(id);
    }

    @Override
    public Event update(Event entity) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }
}
