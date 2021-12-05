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

## Inicialização do sistema
### Com utilização do Docker
Para inicializar o sistema utilizando Docker bastar digitar o comando abaixo:

> docker-compose up --build

A rotina fará o seguinte processo:
1. carregará as imagens necessárias (Mysql e Maven)
1. inicializará o banco de dados Mysql
1. fará o download das dependências do projeto
1. inicializará o servidor

### Sem utilização do Docker
Para inicializar o sistema sem utilizar o Docker, será necessário que a máquina (local ou remota) tenha o banco de dados Mysql instaldo e esteja configurado com as informações abaixo.

#### Configuração do Mysql
1. IP: Definir conforme o IP da máquina que estará rodando a instância
1. porta: Definir conforme a porta do Mysql da máquina que estará rodando a instância
1. database: Definir conforme no nome do banco da instância
1. senha do usuário root: Definir conforme a senha do banco da instância
1. **Estas alterações devem ser feitas arquivo application.properties nas linhas 11, 12 e 13**

#### Inicialização com o Maven
Uma vez que o banco de dados esteja operacional e as configurações alteradas no arquivo, poderá então ser inicializada a aplicação com o comando abaixo, estando na pastas _authorizationApp_

> mvn spring-boot:run

## Acessando o sistema
Quando o sistemas estiver em execução, seja via Docker ou não, o acesso ao sistema se faz pelo acesso ao link abaixo por um navegador. Caso for acessar de outra máquina que não a que está rodando o aplicativo, subistituir o localhost pelo IP da máquina e garantir que a máquina tenha acesso

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