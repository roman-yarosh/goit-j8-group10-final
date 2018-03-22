package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.SalaryRepository;
import ua.goit.finall.model.Salary;
import ua.goit.finall.service.SalaryService;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    @Override
    public Salary getById(Long id) {
        return salaryRepository.findOne(id);
    }

    @Override
    public void save(Salary salary) {
        salaryRepository.save(salary);
    }

    @Override
    public void delete(Long id) {
        salaryRepository.delete(id);
    }

    @Override
    public Salary update(Salary entity) {
        return null;
    }

    @Override
    public List<Salary> getAll() {
        return salaryRepository.findAll();
    }

    @Override
    public List<Salary> getPersonalSalaries(Long employeeId, Integer fromYear, Integer fromMonth, Integer toYear, Integer toMonth) {
        return salaryRepository.getPersonalSalaries(employeeId, fromYear, fromMonth, toYear, toMonth);
    }
}
