# Projeto para gerenciamento de contas e transações bancárias

Projeto desenvolvido com as seguintes tecnologias:
  - Java 11
  - Spring Boot
  - Spring Data JPA
  - PostgreSQL
  - Docker


# Executando o projeto

Antes de executar o servidor, é necessário criar o banco de dados na sua maquina. Caso prefira utilizar o Docker para criar um container do banco de dados basta criar o container rodando o comando abaixo no seu terminal:

```
    docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres -p 5432:5432
```

Antes de executar o projeto, crie uma base de dados para ser usada no projeto. No meu caso utilizei o nome account-management.

no arquivo application.properties que fica dentro de src/main/resources ficam as configurações para conexão com o banco de dados da seguinte forma:

```
  spring.datasource.url=jdbc:postgresql://localhost:5432/account-management
  spring.datasource.username={username-bd}
  spring.datasource.password={senha-bd}
```

Altere as variáveis conforme as configurações locais para realizar a conexão com o banco.


Ao executar o projeto servidor será executado em: [http://localhost:8080](http://localhost:8080) e as tabelas serão criadas automaticamente no seu banco de dados local.

SCRIPT PARA INSERCÃO DE UM USUÁRIO MANUALMENTE:

```
  INSERT INTO peoples
  (birth_date, cpf, "name")
  VALUES('1991-01-01', '11122233344', 'Jhon Doe');
```


Na pasta Insomnia, existe um arquivo com os testes de todas as requisições que podem ser realizadas no projeto. Basta importa-lo para o Insomnia localmente e realizar as requisições com o projeto rodando.

Para baixar o Insomnia: [https://insomnia.rest/download](https://insomnia.rest/download)


### Desafio Dock Tech de Seleção 
Olá, queremos convidá-lo a participar de nosso desafio de seleção.  Pronto para participar? Seu trabalho será visto por nosso time e você receberá ao final um feedback sobre o que achamos do seu trabalho. Não é legal?

### Sobre a oportunidade 
A vaga é para software engineer, temos vagas com diversos níveis de senioridade e para cada um deles utilizaremos critérios específicos considerando esse aspecto, combinado? 
Se você for aprovado nesta etapa, será convidado para uma entrevista final com nosso time de especialistas.

### Desafio Técnico
  Nós trabalhamos com meios de pagamento e nada melhor que um bom sistema para gestão de contas:
  
  - Pré-requisitos:
    ```
    * Desenvolver os recursos em API Rest que realizam operações bancárias com a entidade conta a seguir:
    ```
    | Contas | Tipo |
    |-|-|
    | idConta | Numérico |
    | idPessoa | Numérico |
    | saldo | Monetário |
    | limiteSaqueDiario | Monetário |
    | flagAtivo | Condicional |
    | tipoConta | Numérido |
    | dataCriacao | Data |

    ```
    * Tabela de transações realizadas na conta
    ```
    | Transacoes | Tipo |
    |-|-|
    | idTransacao | Numérico |
    | idConta | Numérico |
    | valor | Monetário |
    | dataTransacao | Data |

    ```
    * P.S.: Não é necessário realizar operações com a tabela pessoa, mas é necessária a criação da tabela para mapeamento da relação com a conta e enviar script de criação de pelo menos uma pessoa.
    ```

    | Pessoas | Tipo |
    |-|-|
    | idPessoa | Numérico |
    | nome | Texto |
    | cpf | Texto |
    | dataNascimento | Data |    

  - O que esperamos como escopo mínimo:
    ```
    * Implementar path que realiza a criação de uma conta;
    * Implementar path que realiza operação de depósito em uma conta;
    * Implementar path que realiza operação de consulta de saldo em determinada conta;
    * Implementar path que realiza operação de saque em uma conta;
    * Implementar path que realiza o bloqueio de uma conta;
    * Implementar path que recupera o extrato de transações de uma conta;
    ```
  - O que será diferencial:
    ```
    * Implementar extrato por período;
    * Elaborar manual de execução;
    * Elaborar documentação javadoc;
    * Elaborar testes;
    * Prazo de entrega;
    ```
    
  - O que vamos avaliar:
    ```
    * Seu código; 
    * Script de banco;
    * Organização;
    * Boas práticas;
    * Diferenciais;    
    ```


### Instruções
      1. Faça o fork do desafio;
      2. Desenvolva. Você terá até 3 (três) dias a partir da data do envio do desafio; 
      3. Envie um e-mail para arthur.azevedo@dock.tech notificando a finalização do desafio e o link do repositório para validação.
