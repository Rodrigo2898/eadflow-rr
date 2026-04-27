package com.rr.plataformaead.service;

import com.rr.plataformaead.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService extends BaseService<Usuario> {

    protected UsuarioService() {
        super(Usuario.class);
    }

    public Optional<Usuario> findByEmail(String email) {
        String query = "SELECT u FROM Usuario u WHERE u.email = :email";

        List<Usuario> resultado = entityManager.
                createQuery(query, Usuario.class)
                .setParameter("email", email)
                .getResultList();
        return resultado.stream().findFirst();
    }

    public boolean existsByEmail(String email) {
        String query = "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email";

        Long resultado = entityManager.
                createQuery(query, Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return resultado > 0;
    }
}
