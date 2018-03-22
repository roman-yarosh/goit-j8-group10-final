package ua.goit.finall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.finall.dao.RoleRepository;
import ua.goit.finall.model.Role;
import ua.goit.finall.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role getById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Override
    public Role update(Role entity) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
