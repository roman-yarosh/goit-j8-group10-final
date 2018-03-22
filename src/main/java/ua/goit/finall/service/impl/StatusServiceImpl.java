package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.StatusRepository;
import ua.goit.finall.model.Status;
import ua.goit.finall.service.StatusService;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public Status getById(Long id) {
        return statusRepository.findOne(id);
    }

    @Override
    public void save(Status status) {
        statusRepository.save(status);
    }

    @Override
    public void delete(Long id) {
        statusRepository.delete(id);
    }

    @Override
    public Status update(Status entity) {
        return null;
    }

    @Override
    public List<Status> getAll() {
        return statusRepository.findAll();
    }
}
