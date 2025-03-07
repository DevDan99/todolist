package br.com.danielcosta.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/*
 * Controller primeira camada de aplicação, cliente enviar requisição e a controller decide o que fazer
 * @Controller => Permite retornar paginas templates, tem mais flexibilidade
 * @RestController => Permite retornar metodos Rest, usado para construir api, cria uma rota
 * Rota = http://localhost:8080/aqui vai a rota
 */

@RestController
@RequestMapping("/user")//responsavel por estruturar a minha rota http://localhost:8080/primeiraRota

/**
 * Modificadores 
 * Public - acesso qualquer um pode acesar
 * Private - acesso a apenas alguns atributos
 * protected - acesso apenas a mesma estrutura do pacote
 */
public class UserController {
    /* 
     * Instanciando o repositorio
     * Usando o Spring para gerenciar todo o ciclo de vida da instancia do repositorio com o Autowired
    */
    @Autowired
    private IUserRepository userRepository;

    /*
     * Métodos HTTPs
     * GET - Buscar informação/dados 
     * POST - Adcionar informação/dados
     * PUT - Alterar informação/dados
     * DELETE - Remover informação/dados
     * PATH - Alterar uma parte da informação/dados
     */ 
    @PostMapping("/")
    //Método (funcionalidade) de uma class
    /**
     * retorno de um método
     * String - textoss
     * Interge - números inteiros
     * Double -  números 0.0000
     * Float - número 0.000
     * Char - caracteres
     * Date - datas
     * Void - vazio
     */
    //Substituindo o tipo UserModel pelo ResponseEntity para poder retorna o objeto do ResponseEntity para ter retorno de sucesso e erro.
    public ResponseEntity create(@RequestBody UserModel userModel){
        //validando usuario sem antes dar o save no banco, criando mensagem personalizada de erro
        var user = this.userRepository.findByUsername(userModel.getUsername());
        
        if(user != null){
            //Mensagem 
            //Status Code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já Existe");
        }

        //lib para criptografia de senha. Recebe força de criptografia e o que deve criptografar
        var passwordHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

        //redefinindo campo com senha criptografada
        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
