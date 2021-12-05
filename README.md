# Autorização
Projeto para prova da empresa Zitrus.
 
## Tecnologias utilizadas
* Docker
* Mysql
* Maven
* Java
* JSP
* Spring Boot (Web, JPA, Security)
* Liquibase
* JUnit
* H2

## Inicialização do sistema
### Com utilização do Docker
Para inicializar o sistema utilizando Docker bastar digitar o comando abaixo, estando ma pasta _zitrus_:

> docker-compose up --build

A rotina fará o seguinte processo:
1. carregará as imagens necessárias (Mysql e Maven)
1. inicializará o banco de dados Mysql
1. fará o download das dependências do projeto
1. compilará o projeto
1. inicializará o servidor

### Sem utilização do Docker
Para inicializar o sistema sem utilizar o Docker, será necessário que a máquina tenha o banco de dados Mysql instaldo, ou outra qualquer máquina na rede, e esteja configurado com as informações abaixo.

#### Configuração do Mysql
1. porta: 3306
1. database: authorization
1. senha do usuário root: zitrus
1. no arquivo application.properties deve-se ajustar as linhas 11, 12 e 13 apontando para a instância do MySql desejada.

#### Inicialização com o Maven
Uma vez que o banco de dados esteja operacional, poderá então ser inicializada a aplicação com o comando abaixo, estando na pastas _authorizationApp_

> mvn spring-boot run

## Acessando o sistema
Quando o sistemas estiver em execução, seja via Docker ou não, o acesso ao sistema se faz pelo acesso ao link abaixo por um navegador.

> localhost:8080

## Utilizando o sistema
Para vizualizar, cadastrar ou validar as regras será necessário fazer login no sistema. Há dois perfis criados que permitiram utilizar os recursos.

| Tipo          | Login          | Senha          |
| ------------- | -------------- | -------------- |
| Administrador | admin          | admin          |
| Usuário       | fulano         | fulano         |
| Usuário       | cicrano        | cicrano        |
| Usuário       | beltrano       | beltrano       |

O administrador poderá ver todas as regras, no entando os usuários poderão ver somente as regras criadas por ele próprio.