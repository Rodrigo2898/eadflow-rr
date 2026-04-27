package com.rr.plataformaead.service;

import com.rr.plataformaead.entity.Role;
import com.rr.plataformaead.entity.enums.PermissaoUsuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService extends BaseService<Role> {

    protected RoleService() {
        super(Role.class);
    }

    public Optional<Role> findByName(PermissaoUsuario nome) {
        String jpql = "SELECT r FROM Role r WHERE r.permissaoUsuario = :nome";

        List<Role> result = entityManager
                .createQuery(jpql, Role.class)
                .setParameter("nome", nome)
                .getResultList();

        return result.stream().findFirst();
    }
}
