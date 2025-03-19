package com.example.NovoTesteCrud.service;

import com.example.NovoTesteCrud.domain.personal.Personal;
import com.example.NovoTesteCrud.domain.personal.PersonalRepository;
import com.example.NovoTesteCrud.domain.user.UserAcad;
import com.example.NovoTesteCrud.domain.user.UserAcadRepository;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdmin;
import com.example.NovoTesteCrud.domain.useracadadmin.UserAcadAdminRepository;
import com.example.NovoTesteCrud.domain.useradmin.UserAdmin;
import com.example.NovoTesteCrud.domain.useradmin.UserAdminRepository;
import com.example.NovoTesteCrud.domain.userbase.IRequestUsuario;
import com.example.NovoTesteCrud.domain.userbase.Usuario;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
        if (usuario instanceof UserAcad user) {
            return userAcadRepository.save(user);
        } else if (usuario instanceof UserAdmin user) {
            return userAdminRepository.save(user);
        } else if (usuario instanceof UserAcadAdmin user) {
            return userAcadAdminRepository.save(user);
        } else if (usuario instanceof Personal user) {
            return personalRepository.save(user);
        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }
    }

    @Transactional
    public Usuario updateUsuario(Long id, IRequestUsuario data) {
        Usuario usuario = findUsuarioById(id);
        usuario.atualizarDados(data);
        return registerUsuario(usuario);
    }

    private Usuario findUsuarioById(Long id) {
        if (userAcadRepository.existsById(id)) return userAcadRepository.findById(id).orElseThrow();
        if (userAdminRepository.existsById(id)) return userAdminRepository.findById(id).orElseThrow();
        if (userAcadAdminRepository.existsById(id)) return userAcadAdminRepository.findById(id).orElseThrow();
        if (personalRepository.existsById(id)) return personalRepository.findById(id).orElseThrow();
        throw new EntityNotFoundException("Usuário não encontrado!");
    }

    @Transactional
    public void deleteUsuario(Long id, Class<? extends Usuario> userType) {
        if (userType == UserAcad.class) {
            userAcadRepository.deleteById(id);
        } else if (userType == UserAdmin.class) {
            userAdminRepository.deleteById(id);
        } else if (userType == UserAcadAdmin.class) {
            userAcadAdminRepository.deleteById(id);
        } else if (userType == Personal.class) {
            personalRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Tipo de usuário inválido");
        }
    }

}
