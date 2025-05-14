# Cadastro de Clientes - Pessoa Física e Jurídica

Este projeto é uma API REST desenvolvida com Java 21 e Spring Boot que realiza o cadastro de clientes, podendo ser do tipo **Pessoa Física** ou **Pessoa Jurídica**. O projeto foi estruturado utilizando **arquitetura em camadas** e aplicando os padrões de projeto **Factory** e **Strategy**.

## 🚀 Tecnologias Utilizadas
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Web](https://img.shields.io/badge/Spring%20Web-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2%20Database-009E49?style=for-the-badge&logo=h2&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-2C4F88?style=for-the-badge&logo=lombok&logoColor=white)
![Bean Validation](https://img.shields.io/badge/Bean%20Validation-4A8DF9?style=for-the-badge&logo=java&logoColor=white)
![Swagger/OpenAPI](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)


## 📐 Arquitetura e Camadas

O projeto segue uma arquitetura em camadas bem definida com os seguintes pacotes:

- `config`: configurações da aplicação (ex: Swagger, Beans)
- `controller`: camadas REST que recebem as requisições HTTP
- `dto`: objetos de transferência de dados (divididos em `request` e `response`)
- `enums`: tipos enumerados usados no domínio (ex: tipo de cliente)
- `exception`: exceções personalizadas e handlers globais
- `factory`: implementação do padrão Factory para criação de objetos
- `model`: entidades de domínio (JPA)
- `repository`: interfaces de persistência (Spring Data JPA)
- `service`: lógica de negócio e orquestração
- `strategy`: implementação do padrão Strategy para validações dinâmicas

## 📌 Endpoint Principal

### `POST /client`
```text
  http://localhost:8080/client
```

Endpoint único para cadastro de ambos os tipos de cliente (Pessoa Física e Pessoa Jurídica). O tipo de cliente é definido através do campo `tipoCliente`.

#### Exemplo de Request - Pessoa Física

```json
{
  "cpf": "99534853054",
  "name": "João da Silva",
  "phone": "11999999999",
  "mobile": "11988888888",
  "email": "joao.silva@example.com",
  "clientType": "INDIVIDUAL",
  "address": [
    {
      "postalCode": "01234567",
      "street": "Rua Exemplo",
      "number": 123,
      "complement": "Apartamento 45",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP"
    }
  ]
}
```

#### Exemplo de Request - Pessoa Jurídica

```json
{
  "cnpj":"16837792000193",
  "responsibleCpf": "99534853054",
  "name": "João da Silva",
  "phone": "11999999999",
  "mobile": "11988888888",
  "email": "joao.silva@example.com",
  "clientType": "LEGAL_ENTITY",
  "address": [
    {
      "postalCode": "01234567",
      "street": "Rua Exemplo",
      "number": 123,
      "complement": "Apartamento 45",
      "neighborhood": "Centro",
      "city": "São Paulo",
      "state": "SP"
    }
  ]
}
```

## ✅ Validações

As validações seguem as seguintes regras, implementadas através do padrão **Strategy**:

- **Validação de CPF e CNPJ duplicado**
- **Validação de e-mail duplicado**
- **Validações de formato de CPF/CNPJ (anotações @CPF e @CNPJ do Hibernate Validator)**
- Campos obrigatórios (nome, telefone, celular, email, tipoCliente e endereço)

## 🏭 Factory

A lógica de criação de cada tipo de cliente é abstraída por uma classe `ClienteFactory`, responsável por instanciar corretamente os objetos de domínio com base no tipo informado.

## 🧠 Strategy

Cada regra de validação (como verificar duplicidade de CPF/CNPJ ou e-mail) é implementada como uma classe que implementa a interface `ClientValidator`. As validações são injetadas em uma lista e executadas dinamicamente com base na requisição.

## 🧪 Banco de Dados

- Utiliza o banco **H2 em memória** para testes locais.
- A estrutura é criada automaticamente com base nas entidades JPA.

## 🛠 Como executar o projeto

1. Clone o repositório:

```bash
  git clone https://github.com/seu-usuario/nome-do-repositorio.git
```

2. Navegue até o diretório:

```bash
  cd nome-do-repositorio
```

3. Compile e execute a aplicação:

```bash
  ./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📚 Swagger

A documentação interativa da API está disponível em:

```
http://localhost:8080/swagger-ui.html
```
