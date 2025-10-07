package com._dev_ruan.helpDesk.Security;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com._dev_ruan.helpDesk.domain.Pessoa;
import com._dev_ruan.helpDesk.repositories.PessoaRepository;



/**
 * Implementação de {@link UserDetailsService} para autenticação de usuários.
 *
 * <p>
 * O Spring Security utiliza esta classe para buscar um usuário no banco de dados
 * a partir do e-mail informado no login. Ela retorna um objeto {@link UserDetails}
 * que o Spring Security usa para verificar senha e permissões.
 * </p>
 *
 * <p>
 * A anotação {@link Service} permite que o Spring gerencie esta classe como um bean,
 * tornando-a disponível para injeção em outros componentes.
 * </p>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Repositório para acesso à entidade {@link Pessoa}.
     * <p>
     * Usado para consultar o banco de dados e buscar o usuário pelo e-mail.
     * </p>
     */
    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Carrega o usuário a partir do e-mail informado.
     *
     * <p>
     * Passo a passo:
     * </p>
     * <ol>
     *   <li>Consulta o banco de dados em busca de uma {@link Pessoa} com o e-mail fornecido.</li>
     *   <li>Caso encontre, cria e retorna um objeto {@link UserSS}, que implementa {@link UserDetails}.</li>
     *   <li>Se não encontrar, lança {@link UsernameNotFoundException}, indicando que o login é inválido.</li>
     * </ol>
     *
     * @param email e-mail do usuário informado no login
     * @return instância de {@link UserSS} contendo ID, e-mail, senha e perfis/roles
     * @throws UsernameNotFoundException se não houver usuário com o e-mail informado
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca a pessoa no banco de dados.
        // O método findByUsername deve retornar um Optional<Pessoa>
        return (UserDetails) pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    


    }
}
