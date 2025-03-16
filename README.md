# API RESTful para Gestão de Estoque

## Introdução

Este repositório contém a implementação de uma API RESTful para gestão de estoque de uma rede de lojas. O sistema permite o cadastro, atualização, remoção e consulta de produtos no estoque, além de possibilitar o controle da quantidade de itens disponíveis e a geração de alertas quando o estoque estiver baixo.

## Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3+
- **Banco de Dados:** MySQL ou PostgreSQL
- **Gerenciamento de Dependências:** Maven
- **Padrão de API:** RESTful
- **Formato de Dados:** JSON
- **Versionamento de Código:** Git

## Funcionalidades

### Cadastro de Produto (`POST /produtos`)
Permite o cadastro de novos produtos no estoque.

**Campos obrigatórios:**
- `nome` (String)
- `descricao` (String)
- `preco` (BigDecimal)
- `quantidade` (Integer)
- `categoria` (String)

**Respostas:**
- **201 Created:** Produto cadastrado com sucesso.
- **400 Bad Request:** Requisição inválida.

### Atualização de Produto (`PUT /produtos/{id}`)
Permite a atualização das informações de um produto já cadastrado.

**Respostas:**
- **200 OK:** Produto atualizado com sucesso.
- **404 Not Found:** Produto não encontrado.

### Consulta de Produtos (`GET /produtos`)
Lista os produtos cadastrados no estoque com filtros opcionais.

**Filtros opcionais:**
- `categoria`: Filtra produtos por categoria.
- `estoqueBaixo`: Retorna apenas produtos com quantidade abaixo de 10 unidades.

**Respostas:**
- **200 OK:** Lista de produtos.
- **204 No Content:** Nenhum produto encontrado.

### Remoção de Produto (`DELETE /produtos/{id}`)
Remove um produto do estoque.

**Respostas:**
- **200 OK:** Produto removido com sucesso.
- **404 Not Found:** Produto não encontrado.

## Diferenciais Implementados

- **Testes unitários e de integração** com JUnit e Mockito.
- **Docker** para facilitar a execução do projeto.
- **Swagger/OpenAPI** para documentação dos endpoints.
- **Autenticação JWT** para segurança dos endpoints.
- **Notificações automáticas** quando o estoque estiver baixo.

## Como Executar o Projeto

### 1. Clonar o Repositório
```sh
 git clone git@github.com:WaldirJuniorGPN/api-gestao-estoque.git
 cd api-gestao-estoque
```

### 2. Configurar Banco de Dados
Configure o banco de dados no arquivo `application.yml` ou utilize as variáveis de ambiente:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/estoque
    username: usuario
    password: senha
  jpa:
    hibernate:
      ddl-auto: update
```

### 3. Construir e Rodar a Aplicação
```sh
 mvn clean install
 mvn spring-boot:run
```

### 4. Acessar a Documentação da API
Acesse o Swagger pelo navegador:
```
http://localhost:8080/swagger-ui.html
```

## Contribuição

Sinta-se à vontade para contribuir com melhorias, correções e novas funcionalidades.

## Licença

Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

---
