package com.valoinfo.controller;

import com.valoinfo.model.Personagem;
import com.valoinfo.model.ContaRiot;
import com.valoinfo.repository.PersonagemRepository;
import com.valoinfo.repository.ContaRiotRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private ContaRiotRepository contaRiotRepository;

    @PostConstruct
    public void inicializarBanco() {
        if (personagemRepository.count() == 0) {
            personagemRepository.save(new Personagem("Jett", "Duelista", "Jett é uma duelista sul-coreana extremamente ágil...", "https://static.wikia.nocookie.net/valorant-lore/images/2/24/Jett_-_Full_body.png/revision/latest/scale-to-width-down/1000?cb=20241030123829"));
            personagemRepository.save(new Personagem("Sova", "Iniciador", "Sova é um arqueiro russo especialista em rastreamento...", "https://static.wikia.nocookie.net/valorant-lore/images/5/58/Sova_-_Full_body.png/revision/latest/scale-to-width-down/1000?cb=20241030123543"));
            personagemRepository.save(new Personagem("Brimstone", "Controlador", "Brimstone é um comandante americano...", "https://static.wikia.nocookie.net/valorant-lore/images/9/9b/Brimstone_-_Full_body.png/revision/latest/scale-to-width-down/1000?cb=20241030122918"));
            personagemRepository.save(new Personagem("Cypher", "Sentinela", "Cypher é um espião marroquino especialista em vigilância...", "https://static.wikia.nocookie.net/valorant-lore/images/e/e5/Cypher_-_Full_body.png/revision/latest/scale-to-width-down/1000?cb=20241030123423"));
        }

        if (contaRiotRepository.count() == 0) {
            ContaRiot admin = new ContaRiot();
            admin.setNick("Admin");
            admin.setPassword("1234");
            contaRiotRepository.save(admin);
        }
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session, @RequestParam(required = false) String aba) {
        List<Personagem> personagens = personagemRepository.findAll();
        model.addAttribute("listaPersonagens", personagens);
        model.addAttribute("contaRiot", new ContaRiot()); 

        String usuarioLogado = (String) session.getAttribute("usuarioLogado");
        model.addAttribute("usuarioLogado", usuarioLogado);

        // Controla qual aba mostrar: "cadastro" ou "login" (padrão é login)
        model.addAttribute("abaAtiva", aba != null ? aba : "login");

        return "index";
    }

    // Rota para fazer Login
    @PostMapping("/entrar")
    public String fazerLogin(@ModelAttribute ContaRiot contaRiot, HttpSession session) {
        ContaRiot contaEncontrada = contaRiotRepository.findByNickAndPassword(contaRiot.getNick(), contaRiot.getPassword());
        
        if (contaEncontrada != null) {
            session.setAttribute("usuarioLogado", contaEncontrada.getNick());
        }
        
        return "redirect:/"; 
    }

    // Rota nova para criar nova conta e guardar no MySQL
    @PostMapping("/cadastrar-conta")
    public String cadastrarConta(@ModelAttribute ContaRiot contaRiot) {
        try {
            contaRiotRepository.save(contaRiot); // Salva a nova conta no banco de dados
        } catch (Exception e) {
            // Se o nick já existir, evita crashar a aplicação
        }
        return "redirect:/?aba=login"; // Redireciona de volta para a aba de login após criar
    }

    @GetMapping("/logout")
    public String fazerLogout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }
}