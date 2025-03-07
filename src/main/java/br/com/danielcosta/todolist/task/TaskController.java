package br.com.danielcosta.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.danielcosta.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")

public class TaskController {
    
    @Autowired
    private ITaskRepository taskRepository;
    
    @PostMapping("/")
    //usando o HttpServletRequest request para conseguir usar o setAtribut do filtro
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request){
        //pegando o valor do atributo idUser
        var idUser = request.getAttribute("idUser");

        //Setando no meu taskmodel o valor do atributo da resquest
        taskModel.setIdUser((UUID) idUser);

        //Validando datas de criação/termino das tarefas
        var currentDate = LocalDateTime.now();

        //valida se data atual vem depois da data de criação Ex: atual 2025-03-06T11:00:00 Start 2025-03-05T11:00:00
        if (currentDate.isAfter(taskModel.getStarAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de criação/termino da tarefa, deve ser maior que a data atual!");
        }

        //valdidando se a data de inicio vem depois da data de termino Ex: start 2025-03-06T11:00:00 end 2025-03-06T10:00:00
        if(taskModel.getStarAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A data de criação da tarefa, deve ser maior que a data de termino!");
        }

        var task = this.taskRepository.save(taskModel);//salvadno na classe taskModel os valores 
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    //listando as tarefas do usuário
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");//pega o idUser da request
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);//usa o método do repositorio
        return tasks;
    }
    
    //deixando o update mais dinamico, podendo ser feito parcial.
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
        var task = this.taskRepository.findById(id).orElse(null); 

        //validando se id da tarefa existe
        if (task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada!");
        }

        //validando se o usuáro é dono do update
        var idUser = request.getAttribute("idUser");//pega id do usuário
        
        //verificando se usuário é diferente
        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não tem premissão de alterar essa tarefa");
        }
        
        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }

    /*criando update(total) para tarefas
    //o PathVarieble é para receber o paramentro passado na rota, um parametro Path que no caso é o Id da task
    @PutMapping("/{id}")
    public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
        var idUser = request.getAttribute("idUser");//pega valor do idUser da request
        taskModel.setIdUser((UUID) idUser);//seta o valor do idUser no taskModel
        taskModel.setId(id);//seta o valor do id da task no taskModel
        return this.taskRepository.save(taskModel);
    }*/
}