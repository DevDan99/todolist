//Filtra a entrada de dados conforme o código inserido, serve para validar a entrada de informação e não deixar que qualquer informação seja gravada. Como por exemplo um usúario não existente gravar uma tarefa.

/**
 * Pode usar para filtrar tambem o Filter da servlet, porem ele precisa converter de ServleRequest para HttpServletRequest o que é necessário para contrução via Rest
 * import jakarta.servlet.ServletRequest;
 * import jakarta.servlet.ServletResponse;
 * import jakarta.servlet.Filter;
 * @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
        throws IOException, ServletException {
            //Execulta Ação
        }
 */

package br.com.danielcosta.todolist.Filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.danielcosta.todolist.user.IUserRepository;

//servlet base para qualquer framework web java

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //anotation para gerenciamento do Spring, para ele entender que tem que passar por aqui
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            
            var servletPath = request.getServletPath();//pega rota atual

            if (servletPath.startsWith("/tasks/")) {//validação de filtro, só acontece quanto a rota começar com /tasks/

                //Pega a Autenticação (Usuário e senha)
                var authorization = request.getHeader("Authorization");
                var authEncode = authorization.substring("Basic".length()).trim();//pega o request, separa a palavra Basic da parte codificada
                
                byte[] authDecode = Base64.getDecoder().decode(authEncode);//decotifica a parte codificada em base64 e salva em um arry de bytes
    
                var authString = new String(authDecode);//converte o arry de byte em string
    
                String[] credentials = authString.split(":");//divide a string em dois um dado antes do ':' e outro dado depois, salvendo em um array cada dado em um nó
                String username = credentials[0];
                String password = credentials[1];
    
                //Valida Usuário
                var user = this.userRepository.findByUsername(username);//usando o repositorio de usuário para verificar se o mesmo exite no banco
    
                if(user == null){
                    response.sendError(401);
    
                } else {
                    // Valida Senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());//compara a senha do usuário com a senha da autenticação, como a senha da autenticação é codificada tem que usar a os objetos da BCrypt. O verifyer espera 2 arrys por isso tem que converter a variavel password em array.
                    
                    if (passwordVerify.verified) {//verifica as duas senhas e passar valor como true ou false
                        //inserindo o atributo idUser pelo filtro, usando o usuário da autenticação
                        request.setAttribute("idUser", user.getId());//cira um nova atributo no json, usando nome do atributo e objeto(valor).

                        // Segue em frente
                        filterChain.doFilter(request, response);//request tudo que vem da requisição, response tudo que enviamos para o usuário
                    
                    } else {
                        response.sendError(401);
                    }
                }

            } else {
                filterChain.doFilter(request, response);
            }

        }
}
