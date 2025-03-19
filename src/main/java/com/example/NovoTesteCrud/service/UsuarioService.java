package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdminRepository;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdminRepository;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class UsuarioService {

    @Autowired
    private UserAcadRepository userAcadRepository;
    @Autowired
    private UserAdminRepository userAdminRepository;
    @Autowired
    private UserAcadAdminRepository userAcadAdminRepository;
    @Autowired
    private PersonalRepository personalRepository;

    private final Map<Class<? extends Usuario>, Function<Usuario, Usuario>> saveFunctions = new HashMap<>();
    private final Map<Class<? extends Usuario>, Function<Long, Usuario>> findByIdFunctions = new HashMap<>();
    private final Map<Class<? extends Usuario>, Function<Long, Void>> deleteFunctions = new HashMap<>();

    public UsuarioService() {
        saveFunctions.put(UserAcad.class, user -> userAcadRepository.save((UserAcad) user));
        saveFunctions.put(UserAdmin.class, user -> userAdminRepository.save((UserAdmin) user));
        saveFunctions.put(UserAcadAdmin.class, user -> userAcadAdminRepository.save((UserAcadAdmin) user));
        saveFunctions.put(Personal.class, user -> personalRepository.save((Personal) user));

        findByIdFunctions.put(UserAcad.class, id -> userAcadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Acad não encontrado!")));
        findByIdFunctions.put(UserAdmin.class, id -> userAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Admin não encontrado!")));
        findByIdFunctions.put(UserAcadAdmin.class, id -> userAcadAdminRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário Acad Admin não encontrado!")));
        findByIdFunctions.put(Personal.class, id -> personalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personal não encontrado!")));

        deleteFunctions.put(UserAcad.class, id -> {
            userAcadRepository.deleteById(id);
            return null;
        });
        deleteFunctions.put(UserAdmin.class, id -> {
            userAdminRepository.deleteById(id);
            return null;
        });
        deleteFunctions.put(UserAcadAdmin.class, id -> {
            userAcadAdminRepository.deleteById(id);
            return null;
        });
        deleteFunctions.put(Personal.class, id -> {
            personalRepository.deleteById(id);
            return null;
        });
    }

    public List<UserAcad> getAllUserAcad() {
        return userAcadRepository.findAll();
    }

    public List<UserAdmin> getAllUserAdmin() {
        return userAdminRepository.findAll();
    }

    public List<UserAcadAdmin> getAllUserAcadAdmin() {
        return userAcadAdminRepository.findAll();
    }

    public List<Personal> getAllPersonal() {
        return personalRepository.findAll();
    }

    @Transactional
    public Usuario registerUsuario(Usuario usuario) {
        Function<Usuario, Usuario> saveFunction = saveFunctions.get(usuario.getClass());
        if (saveFunction == null) {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }
        return saveFunction.apply(usuario);
    }

    @Transactional
    public Usuario updateUsuario(Long id, Usuario data) {
        Function<Long, Usuario> findFunction = findByIdFunctions.get(data.getClass());
        if (findFunction == null) {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }

        Usuario usuario = findFunction.apply(id);
        usuario.atualizarDados(data);

        return registerUsuario(usuario);
    }

    @Transactional
    public void deleteUsuario(Long id, Class<? extends Usuario> userType) {
        Function<Long, Void> deleteFunction = deleteFunctions.get(userType);
        if (deleteFunction == null) {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }
        deleteFunction.apply(id);
    }
}
