//Trata todos os erros da aplicação de forma personalizada se o erro estiver no código abaixo
package br.com.danielcosta.todolist.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //define classes globais
public class ExceptionHendlerController {
    
    //pega a mensagem de erro que foi criada no taskModel e rotorna para o cliente/usuário
    @ExceptionHandler(HttpMessageNotReadableException.class)
    
    public ResponseEntity<String> handleHttpMessageNotReadableExcption(HttpMessageNotReadableException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());

    }

}
