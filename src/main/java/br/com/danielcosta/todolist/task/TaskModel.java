//Estrutura da tarefa, usado para criar uma tabela de tarefa no banco
/**
 * Uma estrutura de tarefa deve conter
 * Id
 * Usúaria que ela pertença (Id user)
 * Titulo
 * Descrição
 * Data de Inicio
 * Data de termino
 * Prioridade
 */

package br.com.danielcosta.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_task")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    
    @Column(length = 50)
    private String title;
    private LocalDateTime starAt;
    private LocalDateTime endAt;
    private String priority;
    
    private UUID idUser;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
    
    //criando mensagem de erro customizadas
    public void setTitle(String title) throws Exception{// throws Exception repassa a exceção para camada assima.
        
        // verifica se esta dentro dos 50 caracteres
        if (title.length() > 50) {
            throw new Exception("O campo title deve conter até 50 caracteres");
        }

        this.title = title;
    }
}
