📌 To-Do List API com Java e Spring Boot

📖 Descrição

Este projeto é uma API REST para gerenciamento de tarefas (To-Do List), desenvolvida com Java e Spring Boot. Inclui funcionalidades de autenticação, validação de dados e persistência com banco de dados H2. O deploy foi realizado utilizando Docker e Render.com.

🚀 Tecnologias Utilizadas

Java 17

Spring Boot

Spring Security (JWT)

Banco de Dados H2 (nuvem)

Docker

Render.com

🌐 Demonstração

API em Produção: [Adicione o link da API]

Coleção do Postman: [Adicione o link]

🛠 Como Rodar o Projeto Localmente

Clone o repositório:

git clone https://github.com/seu-usuario/nome-do-repositorio.git

Acesse a pasta do projeto:

cd nome-do-repositorio

Instale as dependências:

mvn clean install

Execute o projeto:

mvn spring-boot:run

Acesse a API via http://localhost:8080

🔗 Rotas da API

POST /auth/login → Autenticação com JWT

GET /tasks → Lista todas as tarefas

POST /tasks → Cria uma nova tarefa

PUT /tasks/{id} → Atualiza uma tarefa

DELETE /tasks/{id} → Exclui uma tarefa

🤝 Contribuição

Contribuições são bem-vindas! Siga os passos:

Fork o repositório

Crie uma branch (git checkout -b feature/nova-feature)

Commit suas alterações (git commit -m 'Adiciona nova funcionalidade')

Envie para o repositório remoto (git push origin feature/nova-feature)

Abra um Pull Request

📜 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.
