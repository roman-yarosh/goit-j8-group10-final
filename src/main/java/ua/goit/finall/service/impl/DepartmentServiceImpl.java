package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.DepartmentRepository;
import ua.goit.finall.model.Department;
import ua.goit.finall.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department getById(Long id) {
        return departmentRepository.findOne(id);
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.delete(id);
    }

    @Override
    public Department update(Department entity) {
        return null;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

}
