package br.com.danielcosta.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//Interface é um modelo de negocio que tem dentro da aplicação. Na interface tem os metodos mas não a implementação dos metodos, somente define qual metodo a interface tera! Para utiliar o metodo deve ter uma classe que ustiliza essa interface
public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    //A JpaRepository é uma interface que foi criada pela spring e em alguns metodos ele utiliza o parametro T e ID. Assim o repositorio JpaRepository necessita de dois parametros, a classe que o repositorio representa e qual tipo de ID a classe utiliza.

    // cirando repositorio com spring Data
    UserModel findByUsername(String username);//tipo UserModel nome(parametros). Spring entende que tem que fazer um select buscando o valor na coluna username.
}
