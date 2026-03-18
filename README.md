# Academia - Sistema de Controle de Mensalidades

## Contexto

Em academias tradicionais, o instrutor precisa consultar manualmente folhas de papel para verificar quais alunos pagaram, quais estão inadimplentes e quais faturas estão vencidas — processo lento e suscetível a erros. Além disso, é difícil saber quais alunos estão ativos ou inativos.

**Solução:** Uma API REST que automatiza o controle de mensalidades e status dos alunos. O instrutor precisa apenas marcar uma mensalidade como paga. O restante é automatizado:

- Se a data limite (dia 20) do mês for ultrapassada sem pagamento, a mensalidade muda para **VENCIDA**
- Com **3 mensalidades vencidas**, o aluno é automaticamente marcado como **inativo**

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.3.1 |
| Spring Security | (incluso no Boot) |
| Spring Data JPA / Hibernate | (incluso no Boot) |
| MySQL | 8+ |
| JWT (Auth0 java-jwt) | 4.4.0 |
| Maven | 3.x |
| Lombok | (incluso no Boot) |
| H2 (testes) | (incluso no Boot) |

---

## Pré-requisitos

- **Java 17** instalado ([Download](https://adoptium.net/))
- **MySQL 8+** instalado e rodando localmente
- **Maven 3.x** instalado (ou use o wrapper `./mvnw` incluso no projeto)
- Uma ferramenta para testar a API (ex: Postman, Insomnia ou curl)

---

## Rodando com Docker (recomendado)

A forma mais simples de subir a aplicação completa (app + banco) sem precisar instalar Java, Maven ou MySQL localmente.

**Pré-requisito:** [Docker](https://www.docker.com/) instalado.

```bash
docker compose up --build
```

Isso irá:
1. Subir um container MySQL 8 com o banco `academiaDB` já criado
2. Compilar a aplicação e subir o container da API

A API ficará disponível em `http://localhost:8080`.

Para parar e remover os containers:
```bash
docker compose down
```

Para parar e também apagar os dados do banco:
```bash
docker compose down -v
```

---

## Configuração do Banco de Dados

### Perfil de desenvolvimento (recomendado para rodar localmente)

1. Crie o banco de dados no MySQL:

```sql
CREATE DATABASE academiaDB;
```

2. Verifique o arquivo `src/main/resources/application-dev.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/academiaDB
spring.datasource.username=root
spring.datasource.password=admin
```

> Altere `username` e `password` conforme suas credenciais locais do MySQL.

3. Ative o perfil `dev` em `src/main/resources/application.properties`:

```properties
spring.profiles.active=dev
```

> Por padrão, o perfil ativo é `prod` (aponta para banco remoto na Railway). Para rodar localmente, mude para `dev`.

As tabelas são criadas automaticamente pelo Hibernate (`ddl-auto=update`).

---

## Como Rodar a Aplicação

### Opção 1 — Maven Wrapper (sem instalar Maven)

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Opção 2 — Maven instalado

```bash
mvn spring-boot:run
```

### Opção 3 — Gerar JAR e executar

```bash
mvn clean package -DskipTests
java -jar target/academia-0.0.1-SNAPSHOT.jar
```

A aplicação sobe na porta **8080** por padrão.

---

## Variáveis de Ambiente (opcional)

As propriedades JWT podem ser sobrescritas via variáveis de ambiente:

| Variável | Padrão | Descrição |
|---|---|---|
| `JWT_SECRET` | `Q29udHJvbGUgZGUgUGFnYW1lbnRvcw==` | Segredo para assinar o token |
| `JWT_ISSUER` | `ACADEMIA` | Emissor do token |

---

## Perfis disponíveis

| Perfil | Banco | Uso |
|---|---|---|
| `prod` | MySQL remoto (Railway) | Produção |
| `dev` | MySQL local ou Docker (`academiaDB`) | Desenvolvimento local |
| `test` | H2 in-memory | Testes automatizados |

---

## Endpoints da API

A API base URL é `http://localhost:8080`.

### Autenticação

| Método | Endpoint | Auth | Descrição |
|---|---|---|---|
| POST | `/login` | Não | Autentica e retorna JWT |
| POST | `/instrutor` | Não | Cadastra novo instrutor |

> Todos os demais endpoints exigem o header `Authorization: Bearer <token>`.

---

### Instrutores

**Cadastrar instrutor**
```
POST /instrutor
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao@academia.com",
  "senha": "minhasenha123"
}
```

**Login**
```
POST /login
Content-Type: application/json

{
  "email": "joao@academia.com",
  "password": "minhasenha123"
}
```
Resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### Alunos

> Todos os endpoints abaixo requerem `Authorization: Bearer <token>`.

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/aluno` | Lista todos os alunos (paginado) |
| GET | `/aluno/{id}` | Busca aluno por ID |
| POST | `/aluno` | Cadastra novo aluno |
| PUT | `/aluno/{id}` | Atualiza dados do aluno |
| DELETE | `/aluno/{id}` | Inativa aluno (soft delete) |
| PUT | `/aluno/reativar/{id}` | Reativa aluno inativo |

**Cadastrar aluno**
```
POST /aluno
Authorization: Bearer <token>
Content-Type: application/json

{
  "nome": "Maria Souza",
  "telefone": "11999998888",
  "dataNascimento": "1995-06-15",
  "endereco": {
    "cep": "01310100",
    "logradouro": "Av. Paulista",
    "bairro": "Bela Vista",
    "estado": "São Paulo",
    "uf": "SP"
  }
}
```

---

### Mensalidades

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/mensalidade` | Lista todas as mensalidades |
| GET | `/mensalidade?status=1` | Filtra por status (ver tabela abaixo) |
| PUT | `/mensalidade/{id}` | Aprova/atualiza status de pagamento |

**Status de pagamento:**

| Código | Descrição |
|---|---|
| 1 | PENDENTE |
| 2 | PAGA |
| 3 | VENCIDA |
| 4 | ANULADA |

**Marcar mensalidade como paga**
```
PUT /mensalidade/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "status": 2
}
```

---

## Regras de Negócio

1. **Geração automática de mensalidades:** Todo dia 20 de cada mês, o sistema gera uma mensalidade (R$ 50,00) para todos os alunos ativos, com vencimento no dia 20 do mês seguinte.

2. **Vencimento automático:** Se o dia 20 passar sem pagamento, a mensalidade muda automaticamente para `VENCIDA`.

3. **Inativação automática:** Ao acumular **3 mensalidades vencidas**, o aluno é marcado como inativo.

4. **Reativação manual:** O instrutor pode reativar um aluno inativo — as mensalidades vencidas são anuladas ao reativar.

5. **Validações:**
   - CPF não pode ser duplicado
   - Telefone não pode ser duplicado
   - Email do instrutor não pode ser duplicado
   - Não é possível marcar como paga uma mensalidade já paga

---

## Estrutura do Projeto

```
src/main/java/com/br/luisvanique/academia/
├── AcademiaApplication.java          # Entry point
├── config/
│   └── SchedulerConfig.java          # Configuração do agendador
├── controller/
│   ├── AuthenticationController.java
│   ├── AlunoController.java
│   ├── InstrutorController.java
│   └── MensalidadesController.java
├── domain/
│   ├── Endereco.java
│   ├── aluno/
│   ├── instrutor/
│   ├── mensalidade/
│   └── enums/
├── repository/
├── security/
│   ├── SecurityConfig.java
│   ├── SecurityFilter.java
│   └── TokenService.java
└── service/
    ├── AlunoService.java
    ├── InstrutorService.java
    └── MensalidadeService.java
```

---

## Rodando os Testes

```bash
mvn test
```

Os testes usam H2 in-memory automaticamente (perfil `test`).
