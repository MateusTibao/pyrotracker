package br.com.pyrotracker.controller;

import br.com.pyrotracker.domain.Usuario;
import br.com.pyrotracker.dto.AuthRequestDTO;
import br.com.pyrotracker.security.JwtUtil;
import br.com.pyrotracker.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AuthRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        boolean senhaCorreta = passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha());
        if (!senhaCorreta) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("usuario", usuario.getEmail());

        return response;
    }
}
