# Desafio Pismo para desenvolvimento Backend

API criada com o objetivo de demonstrar os conceitos de desenvolvimento backend necessários para a entrevista técnica com o líder técnico e os desenvolvedores da Pismo.

## Requisitos mínimos

* Java 11+;
* Maven 3.6+;
* Docker 19.03+;
* Docker Compose 1.29+.

## Compilando o projeto

O projeto não possui muitos parâmetros para compilação, podendo rodar simplesmente a tarefa de compilação padrão do Maven:

```bash
mvn clean install
 ```

### Executando o projeto em modo de desenvolvimento

O projeto atualmente tem como requisito mínimo que exista um servidor do PostgreSQL carregado na estação do desenvolvedor. Para que não haja diferenciações na configuração deste servidor, podemos efetuar a carga deste serviço usando parte da configuração disponível via Docker Compose, conforme demonstrado abaixo: 

```bash
# Carregando apenas a instância do container Docker (Caso queira acompanhar os logs, recomenda-se remover o parâmetro "-d",
# responsável por rodar os comandos em segundo plano
docker-compose up postgres -d
```

Para a inicialização do projeto por linha de comando em modo de desenvolvimento, é necessário apenas chamar uma tarefa disponível nos plugins do Maven que este fará toda a configuração necessária ao projeto, conforme a instrução abaixo:

```bash
# Carregando a aplicação por linha de comando
mvn quarkus:dev
```

Efetuado a carga do projeto, todos endpoints estarão disponíveis na URL abaixo:
* http://localhost:8080/swagger-ui/

## Executando o projeto

Para a execução do projeto, há disponível um arquivo do Docker Compose que permite a carga completa de todas as dependências necessárias ao projeto. Para isto, é necessário que no passo anterior a execução da carga via Docker Compose, o projeto esteja compilado, para que os artefatos estejam disponíveis para cópia durante as validações e geração dinâmica do container da aplicação:

```bash
# Gerar os artefatos necessários para geração do container Docker
mvn clean package

# Carregar o Docker Compose informando para regerar o container Docker referente a aplicação
docker-compose up --build
```

Efetuado a carga do projeto, todos endpoints estarão disponíveis na URL abaixo:
* http://localhost:8080/swagger-ui/

## Referências

* [Instalando o Docker Compose 1.29 do Docker CE no Fedora 32+](https://computingforgeeks.com/install-and-use-docker-compose-on-fedora/)
