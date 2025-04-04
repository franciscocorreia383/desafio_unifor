# Desafio Unifor

Desafio técnico 

## Tecnologias:

- [Java Quarkus](https://quarkus.io/) 
- [Angular v19](https://angular.dev/)
- [Postgres](https://www.postgresql.org/)
- [Docker](https://www.docker.com/)

## Pré requisitos:

- [Node.js](https://nodejs.org/)
- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://www.docker.com/)
- [GIT](https://git-scm.com/downloads)

# Como rodar o projeto

## Download do projeto | Repositório GitHub

```bash 
git clone https://github.com/franciscocorreia383/desafio_unifor.git
```

## O Projeto está dividido em 3 partes:
- Backend
- Frontend
- Banco de Dados

## Bando de Dados
Para o funcionamento aquelado da aplicação, é preciso iniciar pelo banco de dados que está em um container no docker.

Com o Docker devidamente instalado e iniciado na máquina, acesse o terminal na pasta raíz do projeto rode o comando:
```bash
docker-compose up -d
```

## Backend
Após iniciado o banco de dados com docker, verifique a versão do java instalado e certifique que está na versão 17 ou superior.

```bash
java -version
```

Inicie o terminal na pasta **desafio-backend** e execute o comando:

```bash
./nvmw quarkus:dev
```

## Frontend
por ultimo, verifique a versão do node instalada, a mesma tem que ser a 18.19.1 ou superior.

```bash
node -v
```

Inicie o terminal na pasta **desafio-frontend** e execute o comando:

```bash
ng serve
```

# Aplicação
Após subir todos os serviços, teremos os seguintes serviços:

- Postgres: jdbc:postgresql://localhost:5432/postgres
- Frontend: http://localhost:4200/
- Backend: http://localhost:8080/
- Swagger: http://localhost:8080/q/swagger-ui/


# Requisitos funcionais

- Cadastrar, listar e excluir alunos.
- Cadastrar, listar e excluir cursos.
- Matricular alunos em cursos.
- Listar alunos matriculados por curso.

## Java Quarkus
O framework sugerido para resolver o desafio na parte de backend foi o Quarkus.

![imagem](/assets/estrutura%20backend.png)

A Arquitetura utilizada no projeto foi a MVC.

A aplicação foi separada nos seguintes pacote:
- Entity - Todas as entidades foram criadas utilizando as notations do jakarta.persistence para que o framework consiga lidar como um modelo para o banco de dados

- Repository - Foi utilizado o PanacheRepository para lidar com as transações com o banco de dados

- Services - Onde foram criadas as regras de negocio, intemédio dos endpoints com os repositórios.

- Resources - Foram mapeados todos os endpoints que podem ser acessados através do Swagger

- DTO - Foram criados todos as entidades de transferência para proteger as entidades originais que carregam annotations de banco de dados.

- Mapper - Interfaces que transformam entidades em DTO's e vice-versa.


Foi disponibilizado o Swagger para teste dos endpoints:
![swagger](/assets/swagger.png)


## Angular 
A tecnologia exigida para resolução da etapa de frontend foi o Angular 17+

![frontend](/assets/estrutura%20frontend.png) 

O frontend foi dividido nos seguintes pacotes:
- Components - Onde foram criadas as telas da aplicação 
- Models - Foram criadas os modelos de objetos utilizados
- Services - Onde foi feita a conexão com o backend
- Shared - Onde foram armazenados todos os demais visuais auxiliares, como, *dialogs*.

# Telas

Tela de Cadastro, listagem e exclusão de alunos
![listagem alunos](/assets/listagem%20alunos.png)

Tela de Cadastro e listagem de cursos
![listagem cursos](/assets/listagem%20cursos.png)

Tela de listagem e cadastro de alunos por cursos
![alunos por cursos](/assets/listagem%20de%20alunos%20por%20curso.png)