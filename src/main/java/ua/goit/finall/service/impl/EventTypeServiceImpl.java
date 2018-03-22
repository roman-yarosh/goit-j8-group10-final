package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.EventTypeRepository;
import ua.goit.finall.model.EventType;
import ua.goit.finall.service.EventTypeService;

import java.util.List;

@Service
public class EventTypeServiceImpl implements EventTypeService {

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Override
    public EventType getById(Long id) {
        return eventTypeRepository.findOne(id);
    }

    @Override
    public void save(EventType eventType) {
        eventTypeRepository.save(eventType);
    }

    @Override
    public void delete(Long id) {
        eventTypeRepository.delete(id);
    }

    @Override
    public EventType update(EventType entity) {
        return null;
    }

    @Override
    public List<EventType> getAll() {
        return eventTypeRepository.findAll();
    }
}
