package com._dev_ruan.helpDesk.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com._dev_ruan.helpDesk.Security.SecurityConfig;
import com._dev_ruan.helpDesk.domain.Chamado;
import com._dev_ruan.helpDesk.domain.Cliente;
import com._dev_ruan.helpDesk.domain.Tecnico;
import com._dev_ruan.helpDesk.domain.enums.Perfil;
import com._dev_ruan.helpDesk.domain.enums.Prioridade;
import com._dev_ruan.helpDesk.domain.enums.Status;
import com._dev_ruan.helpDesk.repositories.ChamadoRepository;
import com._dev_ruan.helpDesk.repositories.ClienteRepository;
import com._dev_ruan.helpDesk.repositories.TecnicoRepository;


@Configuration
@Profile("test")
public class DbConfig implements CommandLineRunner {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;
    
    @Autowired
    private SecurityConfig encript;


    public void run(String... args) throws Exception {
        
        
        Tecnico tec1 = new Tecnico(null, "Maria Souza", "12345678901", "maria@helpdesk.com", encript.passwordEncoder().encode("123"));
        
        Tecnico tec2 = new Tecnico(null, "Mara Souza", "123454754201", "mara@helpdesk.com", encript.passwordEncoder().encode("123"));
        
        Cliente cli1 = new Cliente(null, "João Silva", "11122233344", "joao@gmail.com", encript.passwordEncoder().encode("123"));

        
        Tecnico tecAdmin = new Tecnico(null, "Admin Ruan", "01987654321", "admin", encript.passwordEncoder().encode("admin"));
        tecAdmin.addPerfil(Perfil.ADMIN);

        clienteRepository.save(cli1);
        tecnicoRepository.save(tec1);
        tecnicoRepository.save(tec2);
        tecnicoRepository.save(tecAdmin); 
        
        Chamado ch1 = new Chamado(null, new Date(), null, Prioridade.ALTA, Status.ABERTO, 
                "Erro no login", "Usuário não consegue logar no sistema");
        ch1.setCliente(cli1);
        ch1.setTecnico(tec1);

        chamadoRepository.save(ch1);
    
    }
        
}
