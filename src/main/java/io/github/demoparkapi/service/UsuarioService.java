package io.github.demoparkapi.service;

import io.github.demoparkapi.entity.Usuario;
import io.github.demoparkapi.exception.EntityNotFoundException;
import io.github.demoparkapi.exception.UsernameUniqueViolationException;
import io.github.demoparkapi.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException exception) {
            throw new UsernameUniqueViolationException(String.format("Username {%s} já cadastrado", usuario.getUsername()));
        }

    }

    @Transactional(readOnly = true)
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Usuário id=%s não encontrado.", id)));
    }

    @Transactional
    public Usuario editarSenha(Long id, String senhaAtual, String senhaNova, String confirmaSenha) {
        if (!senhaNova.equals(confirmaSenha)) {
            throw new RuntimeException("Nova senha não confere com a confirmação de senha.");
        }

        Usuario user = buscarPorId(id);
        if (!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Sua senha não confere.");
        }
        user.setPassword(senhaNova);
        return user;
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
