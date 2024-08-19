<h1>Desafio PicPay Simplificado</h1>
Este projeto é uma API desenvolvida com a finalidade de gerenciar transações financeiras entre usuários, permitindo o registro, consulta e controle de transferências monetárias. A API foi construída utilizando Spring Boot, Spring Data JPA, e outras tecnologias do ecossistema Spring.

<h2>Funcionalidades:</h2>
- Gerenciamento de Usuários: Cadastro, consulta, listagem de usuários com paginação.
- Transações: Criação, consulta e listagem de transações financeiras entre usuários.
- Segurança: Implementação de boas práticas de segurança para garantir a integridade e privacidade das transações.
- Documentação da API: A API está documentada utilizando o Swagger.

<h2>Tecnologias Utilizadas:</h2>
-  Spring Boot 3: Framework principal para construção da API.
-  Spring Data JPA: Integração com o banco de dados e manipulação de entidades.
-  Spring Security: Uso da funcionalidade de criptografia de senhas
-  Swagger: Ferramenta para documentação da API.

<h2>Requisitos:</h2>
- Java 17 ou superior
- Maven para gerenciamento de dependências
- Banco de Dados MySQL

<h2>Configuração do Projeto:</h2>

<h3>Clonar o Repositório:</h3>

- Git Clone: https://github.com/RenatoGuii/picpaysimplificado.git

<h3>Configurar o Banco de Dados:</h3>

- Atualize o arquivo application.properties com as configurações do seu banco de dados.
Executar a Aplicação:

- Rode o arquivo PicpaysimplificadoApplication para iniciar o servidor Spring Boot.

<h2>Documentação da API:</h2>
- A documentação da API foi gerada utilizando o Swagger e pode ser acessada no ambiente de desenvolvimento:

- Swagger UI: Acesse a documentação da API através do link http://localhost:8080/swagger-ui.html.

<h2>Endpoints:</h2>

<h3>Usuários:</h3>

- POST /users/register: Registra um novo usuário.

- GET /users/{id}: Busca um usuário por ID.

- GET /users: Obter todos os usuários com paginação.

<h3>Transações:</h3>

- POST /transaction: Cria uma nova transação.

- GET /transaction/{id}: Obter uma transação por ID.

- GET /transaction: Obter todas as transações com paginação.

<h2>Considerações Finais:</h2>
Este projeto foi desenvolvido com o intuito de praticar e implementar conceitos de desenvolvimento com Spring. Sinta-se à vontade para contribuir ou sugerir melhorias.