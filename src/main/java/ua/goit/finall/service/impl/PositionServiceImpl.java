package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.PositionRepository;
import ua.goit.finall.model.Position;
import ua.goit.finall.service.PositionService;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionRepository positionRepository;

    @Override
    public Position getById(Long id) {
        return positionRepository.findOne(id);
    }

    @Override
    public void save(Position position) {
        positionRepository.save(position);
    }

    @Override
    public void delete(Long id) {
        positionRepository.delete(id);
    }

    @Override
    public Position update(Position entity) {
        return null;
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }
}
