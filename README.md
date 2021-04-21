# Hotmart Challenge
## Implementação do Back-end da Marketplace 

Tecnologias Utilizadas no desenvolvimento do projeto.
- Java 8
- SpringBoot 2.4.5
- Spring Security
- Spring Data JPA
- Spring Data Envers
- Hibernate
- H2 Database
- Maven
- Lombok
- Flyway
- ModelMapper
- JavaxValidations
- Docker

## Banco de dados
Banco está sendo utilizado em memória, as configurações estão no arquivo

```
application.properties
```
Acesso ao banco em memória no navegador:
```
http://localhost:8080/h2-console/
user: sa
password: sa
```
O versionamento do banco de dados é feito pelo Flyway, contem 7 scripts da criação dos schemas as cargas necessárias para a execução do programa sem erros.
```
/hotmart-marketplace-api/src/main/resources/application.properties
```
## Verificação do sistema operacional.
• Verificar se o computador possui uma JRE ou JDK do Java 8 instalada, caso tenha dúvida abra o prompt de comando e execute o comando:
```
$ java -version
```
• Deverá aparecer a seguinte mensagem ou próximo disso:
```
java version "1.8.0_241"
Java(TM) SE Runtime Environment (build 1.8.0_241-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.241-b07, mixed mode)
```
• Clonar o repositório em
```
 https://github.com/lmfrocha/hotmart-api
```
• Verificar se possui o Apache Maven 3.+ instalado para saber abra o prompt de comando e execute o comando:
```
$ mvn -version
```
• Deverá aparecer uma mensagem semelhante: 
```
$ Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: C:\dev\server\apache-maven-3.6.3\bin\..
Java version: 1.8.0_241, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_241\jre
Default locale: pt_BR, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```
## Build e Execução
• Instalar as dependências do projeto executando o comando no diretório raiz do projeto onde está localizado o pom.xml 
• todos os comandos deverão ser executados na pasta raiz do projeto ou onde esteja localizado o pom.xml.
```
$ mvn clean install
````
• Gerar o pacote
```
$ mvn package
```
• Para executar a api compilada:
```
$ java –jar target/hotmart-marketplace-api-0.0.1-SNAPSHOT.jar
```
• Para executar de forma direta na raiz do diretório execute:
```
$ mvn spring-boot: run
```
• Para executar pela ide basta executar a classe:
```
HotmartMarketplaceApiApplication.java
```

## Desafio
A api tem um serviço disponível que cumpre os desafios propostos:
- Criar um Crud(create, read, update, delete) de produtos.
- Criar uma API de produtos e disponibilizar as operações de crud (list, find, delete, update, insert).
- Criar um serviço para buscar os produtos ordenados pelo ranqueamento pelo nome e pela categoria.
- O output do serviço deve conter as informações dataAtual e termoPesquisado, bem como a lista de produtos que atendem à pesquisa. 
- Os atributos de cada produto retornado são 
```{ identificador, nome, descrição, data de criação, score}```
- Todos os serviços devem ser auditados.
- Consumir uma API externa de Notícias
- Criar um serviço para atualizar o score dos produtos com base na fórmula:
    ``` 
        X = Média de avaliação do produto nos últimos 12 meses
        Y = Quantidade de vendas/dias que o produto existe
        Z = Quantidade de notícias da categoria do produto no dia corrente
        Score = X + Y + Z
    ```
## Implementações CRUD

* Operações para CRUD(create, read, update, delete) de produtos.
* Cadastrar produto
* ```Post /api/v1/product```
    ```json 
    {
        "name": "Notebook",
        "description": "Lenovo Thinkpad E490",
        "category": {
            "id": 4
        },
        "score":3
    }
    ```
* Atualizar produto
* ```Post /api/v1/product/id/{id}```
    ```json 
    {
        "name": "Notebook",
        "description": "Lenovo Thinkpad E490",
        "category": {
            "id": 4
        },
        "score":3
    }
    ```
*  Buscar produto por Id 
``` Get /api/v1/product/{id}```
*  Listar todos os produtos com paginação
``` Get /api/v1/product/?page=0&size=10```
*  Listar todos os produtos com paginação e termo
``` Get /api/v1/product/search?searchTerm=termoApesquisar&page=0&size=10```

## Autenticação
Tipo -> `Basic Auth`.
`Usuário: user`
`Senha: user`
## Schedule
* Foi implementado uma Schedule no sistema, uma para atualizar a quantidade de notícias por categória consumindo a api de 6 em 6 horas totalizando 4 atualizações diárias sempre a partir da 00:01 link da api utilizada:
```https://newsapi.org/docs/endpoints/top-headlines```
* Foi implementado uma Schedule no sistema que atualiza o Score de todos os produtos sendo executada as 01:00 todos os dias

## Docker
• A aplicação está configurada com o Dockerfile na raiz do projeto, para executar tenha uma instancia do docker no computador e execute o seguinte comando no prompt: 
```
$ docker build -t=hotmart-marketplace:lasted .
```
• Depois de realizar o build basta executar o comando: 
```
$ docker run -p8080:8080 hotmart-marketplace:lasted
```