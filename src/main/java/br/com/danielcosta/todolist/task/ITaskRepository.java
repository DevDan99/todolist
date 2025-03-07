package br.com.danielcosta.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    //criando metodo no repositorio para listar tarefas de usuário
    List<TaskModel> findByIdUser(UUID idUser);//o Spring faz uma query buscando pelo idUser as taferas existentes
}
