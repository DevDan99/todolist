package br.com.danielcosta.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data //usa a lib do lombok que faz os getters e setters usando a notation
@Entity(name = "tb_users")//cria a tabela no banco de dados

public class UserModel {
    
    @Id
    @GeneratedValue(generator = "UUID")//define que o gerenciamento do UUID seja feito pelo JPA
    private UUID id; //ira criar uma coluna de id usando o UUID
    
    @Column (unique = true)// valida a coluna como unica criando uma constraint na banco, porem ele dispara mensagem de erro no log e valida no banco.
    private String username;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createAt;// cria coluna metadata de data de quando o usuario foi criado.
  
}

//getters busca informação em um atributo private da class
   //setters inserir valor em um atributo private da class
   
   //settrs pega informação do userModel e insere no atributo da class UserModel
  /* public void setUsername(String username) {
       this.username = username;
   }

   public void setName(String name) {
       this.name = name;
   }

   public void setPassword(String password) {
       this.password = password;
   }
   
   //getters buscar valor atribuido ao atributo
   public String getUsername() {
       return username;
   }

   public String getName() {
       return name;
   }

   public String getPassword() {
       return password;
   } */