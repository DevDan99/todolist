# 📌 To-Do List API com Java e Spring Boot

## 📖 Descrição

Este projeto é uma API REST para gerenciamento de tarefas (To-Do List), desenvolvida com Java e Spring Boot. Inclui funcionalidades de autenticação, validação de dados e persistência com banco de dados H2. O deploy foi realizado utilizando Docker e Render.com.

## 🚀 Tecnologias Utilizadas

* Java 17

* Spring Boot

* Spring Security (JWT)

* Banco de Dados H2 (nuvem)

* Docker

* Render.com

## 🌐 Demonstração

* API em Produção: [https://todolist-rocket-phwo.onrender.com](https://todolist-rocket-phwo.onrender.com)

* Coleção do Postman: [Baixar Coleção Apidog]([/docs/My%20Project.openapi.json])

## 🛠 Como Rodar o Projeto Localmente

1. Clone o repositório:

  ```
  git clone githttps://github.com/DevDan99/todolist
  ```

2. Acesse a pasta do projeto:

  ```
  cd nome-do-repositorio
  ```

3. Instale as dependências:

  ```
  mvn clean install
  ```

4. Execute o projeto:
```
  mvn spring-boot:run
```

5. Acesse a API via `http://localhost:8080`

## 🔗 Rotas da API

* `POST /user/` → Cria usuário

* `POST /auth/login` → Autenticação com JWT

* `POST /tasks` → Cria uma nova tarefa

* `GET /tasks` → Lista todas as tarefas

* `PUT /tasks/{id}` → Atualiza uma tarefa

## 📸 Imagens
### Demonstração do Código:
![Demonstração do Codigo](https://github.com/DevDan99/todolist/blob/main/images/Captura%20de%20tela%202025-03-27%20114658.png)

![Demonstração do Codigo](http://github.com/DevDan99/todolist/blob/main/images/Captura%20de%20tela%202025-03-27%20114731.png)
### Demonstração do apidog:
![Demonstração do Codigo](https://github.com/DevDan99/todolist/blob/main/images/Captura%20de%20tela%202025-03-27%20115217.png)



## 📜 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.
