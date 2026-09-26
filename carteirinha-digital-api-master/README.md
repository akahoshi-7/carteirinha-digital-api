# Carteirinha Digital API — etapa de autenticação

Backend criado para atender exatamente o contrato usado atualmente pelo aplicativo Android da turma. Nesta etapa, o objetivo é ensinar o fluxo de autenticação com login, senha, BCrypt e JWT. **Refresh token ainda não faz parte do projeto.**

O código está organizado como um **monólito modular**, aplicando DDD e Clean Architecture de maneira simplificada. Há uma única aplicação Spring Boot e um único banco, mas o código é separado por capacidade de negócio. O primeiro módulo implementado é `usuario`; autenticar é um dos casos de uso desse módulo.

## Tecnologias

- Java 21;
- Spring Boot 3.5.16;
- Spring Web;
- Spring Data JPA;
- Spring Security e BCrypt;
- JWT assinado com HMAC SHA-256;
- H2 em memória;
- PostgreSQL opcional;
- Swagger/OpenAPI;
- JUnit e MockMvc.

## Executar

Requisitos: JDK 21 e Maven 3.6.3 ou superior.

```bash
mvn spring-boot:run
```

A API será iniciada em:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui.html
```

## Contrato usado pelo Android

### Requisição

```http
POST /auth/login
Content-Type: application/json
```

```json
{
  "login": "aluno",
  "senha": "123"
}
```

### Resposta de sucesso — `200 OK`

```json
{
  "id": "00000000-0000-0000-0000-000000000001",
  "nome": "Rafael Costa",
  "curso": "Desenvolvimento de Sistemas",
  "turma": "2DEVEST-A",
  "token": "eyJ..."
}
```

Esse formato corresponde ao `LoginResponseDto` atual do aplicativo:

```kotlin
data class LoginResponseDto(
    val id: String,
    val nome: String,
    val curso: String,
    val turma: String,
    val token: String
)
```

Não são enviados `accessToken`, `refreshToken`, `tokenType`, `expiresIn` nem um objeto interno chamado `usuario`.

### Credenciais incorretas — `401 Unauthorized`

```json
{
  "message": "Login ou senha inválidos"
}
```

### Campos vazios — `400 Bad Request`

```json
{
  "message": "Preencha login e senha",
  "errors": {
    "login": "O login é obrigatório",
    "senha": "A senha é obrigatória"
  }
}
```

## Usuários prontos

| Login | Senha | Nome | Turma |
|---|---|---|---|
| `aluno` | `123` | Rafael Costa | 2DEVEST-A |
| `maria` | `456` | Maria Oliveira | 2DEVEST-B |

As senhas são armazenadas como hash BCrypt. Os valores simples existem apenas para facilitar a aula.

## Configuração do Android

O projeto Android enviado já utiliza o endereço correto para o emulador:

```kotlin
private const val BASE_URL = "http://10.0.2.2:8080/"
```

O `10.0.2.2` representa o computador hospedeiro visto de dentro do emulador. O endpoint do Retrofit é relativo a essa URL:

```kotlin
@POST("auth/login")
suspend fun login(@Body body: LoginRequestDto): LoginResponseDto
```

## Organização do monólito modular

```text
br.senai.carteirinha
├── CarteirinhaDigitalApplication.java
├── modules
│   └── usuario
│       ├── domain
│       ├── application
│       │   ├── dto
│       │   ├── port
│       │   └── service
│       ├── infrastructure
│       │   ├── config
│       │   └── persistence
│       └── presentation
└── infrastructure
    ├── config
    ├── security
    └── web
```

### Regra principal

O domínio contém apenas `Usuario` e a exceção com significado de negócio. `Credenciais` e `UsuarioAutenticado` não são entidades: o login entra como `LoginRequestDto` e sai como `LoginResponseDto`. Spring, JPA, HTTP, BCrypt e JWT ficam fora do domínio.

As configurações de segurança, OpenAPI e tratamento global de erros ficam na infraestrutura da aplicação, e não dentro do módulo. Já o repositório JPA de usuário permanece no módulo porque pertence à implementação dessa capacidade de negócio.

### Por que ainda é um monólito modular?

- existe apenas uma aplicação executável;
- existe apenas um processo em execução;
- os módulos usam o mesmo banco nesta etapa;
- cada capacidade de negócio fica em seu próprio pacote;
- novos módulos, como `turma` e `unidadecurricular`, poderão ser adicionados como irmãos de `usuario`.

Consulte `docs/ARQUITETURA.md` para a explicação do fluxo, das portas e dos adaptadores.

## Testes

```bash
mvn test
```

A suíte possui testes unitários da aplicação, sem Spring, e testes de integração HTTP. Ela verifica:

- autenticação válida;
- contrato exato esperado pelo Android;
- ausência de refresh token e de campos antigos;
- geração de JWT;
- login sem diferenciar letras maiúsculas e minúsculas;
- senha incorreta;
- usuário inexistente ou inativo;
- validação de campos vazios;
- resposta de erro com o campo `message`.

## Banco H2

Console:

```text
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:carteirinha
User: sa
Password: deixe vazio
```

O H2 é recriado sempre que a aplicação reinicia.

## PostgreSQL opcional

```bash
docker compose up -d
mvn spring-boot:run -Dspring-boot.run.profiles=postgres
```

## Materiais de teste

- `http/api-exemplos.http`: requisições executáveis no IntelliJ;
- `postman/Carteirinha-Digital.postman_collection.json`: coleção para importar no Postman;
- Swagger: contrato interativo gerado pela própria aplicação.
