

# Automacao API JSONPlaceholder - 

Projeto de automacao de testes para API JSONPlaceholder.

## Contexto Importante

A API JSONPlaceholder (https://jsonplaceholder.typicode.com) e uma **API publica simulada** (fake) para fins de prototipacao e aprendizado. 

---

## Informacoes do Projeto

- **API Testada:** JSONPlaceholder
- **URL Base:** https://jsonplaceholder.typicode.com
- **Objetivo:** Validar endpoints /posts e /users
- **Prazo:** 4 dias
- **Abordagem:** Testes funcionais, negativos, contrato e seguranca

---

## Tecnologias Utilizadas

- Java 11
- Maven 3.8+
- JUnit 5.10.0
- RestAssured 5.3.2
- JSON Schema Validator 5.3.2
- Postman Desktop
- Newman + newman-reporter-htmlextra

---

## Estrutura do Projeto
```
api-automation/
├── src/test/java/
│   ├── base/              # Configuracao comum (BaseTest)
│   ├── clients/           # Camada de requisicoes HTTP
│   ├── models/            # POJOs (Post, User)
│   ├── tests/             # Camada de validacoes
│   └── utils/             # Geracao dinamica de dados
├── src/test/resources/
│   └── schemas/           # JSON Schemas para validacao de contrato
├── postman/
│   ├── JSONPlaceholder_API.postman_collection.json
│   ├── environment.postman_environment.json
│   └── run-newman.sh
├── docs/
│   ├── Plano_Teste_JSONPlaceholder_API.xlsx
│   └── screenshots/
└── README.md
```

---

## Como Executar

### Pre-requisitos

- Java 11+
- Maven 3.8+
- Node.js e npm (para Newman)

### Instalacao
```bash
# Newman
npm install -g newman newman-reporter-htmlextra

# Clonar projeto
git clone [url-repositorio]
cd api-automation
```

### Executar Testes

**Java + RestAssured:**
```bash
mvn clean test
```

**Postman + Newman:**
```bash
cd postman
./run-newman.sh
```

---

## Cobertura de Testes

### Cenarios Obrigatorios (Conforme Requisito)

✅ **TC-001:** GET /posts - validar estrutura do retorno  
✅ **TC-002:** GET /posts/{id} - validar dado especifico  
✅ **TC-015:** Validar contrato JSON com JSON Schema Validator  

**Automacao:** Java + Postman  
**Status:** 100% implementado

---

### Cenarios Adicionais (Diferenciais)

**Testes Funcionais:**
- TC-003 a TC-014: CRUD completo, validacoes de erro

**Testes Negativos:**
- TC-004, TC-006 a TC-010: Validacao de campos obrigatorios, tipos invalidos

**Testes de Seguranca:**
- TC-017 a TC-019: SQL Injection, XSS (simulacao de analise de risco)

**Total:** 22 casos de teste documentados

---

## Separacao de Responsabilidades

### Camada: Models (POJOs)
**Arquivo:** `Post.java`, `User.java`  
**Responsabilidade:** Representar estrutura de dados  
**Uso:** Serializacao/deserializacao via Jackson

### Camada: Clients
**Arquivo:** `PostsClient.java`, `UsersClient.java`  
**Responsabilidade:** Encapsular APENAS chamadas HTTP  
**Principio:** Sem validacoes - apenas comunicacao

### Camada: Tests
**Arquivo:** `PostsTest.java`, `UsersTest.java`  
**Responsabilidade:** Executar cenarios e validar resultados  
**Principio:** Usa Clients para requisicoes, foca em asserts

### Camada: Base
**Arquivo:** `BaseTest.java`  
**Responsabilidade:** Configuracao comum reutilizavel  
**Conteudo:** RequestSpecification, URL base, logs

### Camada: Utils
**Arquivo:** `TestDataFactory.java`  
**Responsabilidade:** Geracao dinamica de dados  
**Beneficio:** Evita dados hardcoded, garante unicidade

---

## Decisoes Tecnicas

### Por que jsonplaceholder.typicode.com?

API original, estavel, nao requer setup local, amplamente conhecida.

### Por que nao validar persistencia?

A API nao persiste dados realmente. POST/PUT/DELETE retornam fake responses.

**Estrategia adotada:**
- Validar contrato (JSON Schema)
- Validar coerencia request-response
- Validar tratamento de erros
- Validar seguranca basica

**Nao validar:**
- Persistencia em banco
- Consistencia entre chamadas
- Regras de negocio com estado

### Por que testes de seguranca em API fake?

Os testes de SQL Injection e XSS foram incluidos para **demonstrar capacidade de analise de risco** e visao de seguranca, mesmo sabendo que a API JSONPlaceholder nao usa SQL nem renderiza HTML.

**Objetivo:** Simular validacoes esperadas em APIs reais de producao, mostrando pensamento critico sobre OWASP Top 10 e vulnerabilidades comuns.

### Por que separar Clients e Tests?

**Separacao de responsabilidades:**
- Clients fazem requisicoes (COMO chamar API)
- Tests fazem validacoes (O QUE validar)

**Beneficio:** Mudanca de endpoint = alterar 1 arquivo apenas.

### Por que usar POJO e Map?

- **POJO:** Casos positivos (type-safe, legibilidade)
- **Map:** Casos negativos (flexibilidade para payloads invalidos)

### Por que JSON Schema Validator?

Garante contrato. Mudancas na estrutura da API sao detectadas automaticamente sem precisar alterar asserts manuais.































PLANO DE TESTE - AUTOMACAO API JSONPLACEHOLDER

INFORMACOES DO PROJETO

Campo                    Valor
Projeto                  Automacao de Testes - API JSONPlaceholder
Responsavel              [Seu Nome]
Email                    [Seu Email]
Data Inicio              [Data Atual]
Prazo Entrega            4 dias uteis
Versao Documento         1.0
Status                   Em Execucao


OBJETIVO

Validar funcionalidades principais dos endpoints /posts e /users da API JSONPlaceholder atraves de automacao em Java (RestAssured) e Postman, garantindo conformidade contratual, tratamento de erros e seguranca basica.


AMBIENTE

API Alvo                 JSONPlaceholder
URL Base                 https://jsonplaceholder.typicode.com
Documentacao             https://jsonplaceholder.typicode.com/guide
Tipo                     API REST publica fake para testes
Disponibilidade          24/7
Dependencias             Conexao internet


TECNOLOGIAS

Linguagem                Java 11
Build Tool               Maven 3.8+
Framework Testes         JUnit 5.10.0
Framework API            RestAssured 5.3.2
Validacao Schema         JSON Schema Validator 5.3.2
Automacao Manual         Postman Desktop
CLI Postman              Newman + newman-reporter-htmlextra
IDE                      IntelliJ IDEA Community
Controle Versao          Git + GitHub


ESCOPO

IN SCOPE:
- Endpoint GET /posts (listar todos)
- Endpoint GET /posts/{id} (buscar por ID)
- Endpoint POST /posts (criar)
- Endpoint PUT /posts/{id} (atualizar completo)
- Endpoint PATCH /posts/{id} (atualizar parcial)
- Endpoint DELETE /posts/{id} (deletar)
- Endpoint GET /users (listar usuarios)
- Endpoint GET /users/{id} (buscar usuario)
- Validacao de contrato JSON via Schema
- Testes funcionais positivos
- Testes negativos (erros, validacoes)
- Testes de seguranca basicos (XSS, SQL Injection)
- Automacao completa em Java
- Colecao Postman executavel via Newman
- Documentacao de bugs com evidencias

OUT OF SCOPE:
- Endpoints /comments, /albums, /photos, /todos
- Testes de performance/carga
- Testes de autenticacao (API publica)
- Integracao CI/CD (diferencial futuro)
- Validacao de persistencia em banco (ver RESTRICOES)


RESTRICOES TECNICAS

1. Ausencia de Persistencia Real
   A API JSONPlaceholder nao persiste dados realmente.
   POST/PUT/PATCH/DELETE retornam respostas simuladas (fake) mas nao alteram estado.
   
2. Sem Acesso a Banco de Dados
   Nao e possivel validar dados persistidos.
   
3. Sem Documentacao Formal
   API nao possui Swagger/OpenAPI ou documento de requisitos funcionais.

Consequencia:
Os testes focam em validacao de contrato, coerencia request-response, 
tratamento de erros e seguranca. Nao ha validacao de persistencia.


ESTRATEGIA DE QUALIDADE

Pilares:
1. Validacao de Contrato
   - JSON Schema para estrutura de dados
   - Tipos corretos (integer, string)
   - Campos obrigatorios presentes

2. Coerencia Request-Response
   - Dados enviados sao retornados no response
   - IDs gerados seguem padrao esperado

3. Testes Negativos
   - IDs inexistentes (404)
   - Payloads invalidos (400 esperado)
   - Tipos incorretos

4. Testes de Seguranca
   - SQL Injection em query params
   - XSS em campos de texto
   - Overflow de campos

5. Rastreabilidade
   - Todo caso de teste vinculado a codigo Java
   - Todo bug vinculado a caso de teste
   - Evidencias de todos os bugs


ANALISE DE RISCO POR ENDPOINT

Endpoint: POST /posts
Risco: ALTO
Motivo: Ausencia de validacao permite dados invalidos
Impacto: Posts sem campos obrigatorios podem ser criados
Mitigacao: Testes negativos extensivos, documentacao de bugs

Endpoint: GET /posts/{id}
Risco: MEDIO
Motivo: Tratamento de erro pode ser inconsistente
Impacto: Confusao entre 400 e 404
Mitigacao: Documentar comportamento real observado

Endpoint: DELETE /posts/{id}
Risco: BAIXO
Motivo: Operacao simulada, sem impacto real
Mitigacao: Validar apenas status code


PRIORIZACAO

CRITICA (execucao obrigatoria):
- Cenarios que validam funcionalidade core
- Validacao de contrato (JSON Schema)
- Testes de seguranca (XSS, SQL Injection)

ALTA (execucao prioritaria):
- Validacao de campos obrigatorios
- Tratamento de erros (404, 400)
- Testes negativos principais

MEDIA (execucao recomendada):
- Casos de borda
- Validacoes secundarias

BAIXA (execucao opcional):
- Testes exploratórios
- Validacoes de configuracao


CRITERIOS DE ACEITE

1. 100% dos casos CRITICOS executados
2. Codigo Java executando com: mvn clean test
3. Colecao Postman executavel via Newman
4. Minimo 5 bugs documentados com evidencias
5. README.md com instrucoes claras
6. Rastreabilidade completa (caso -> codigo -> bug)
7. Relatorio JUnit gerado


RISCOS

Risco: API fake pode nao refletir comportamento real
Probabilidade: ALTA
Impacto: MEDIO
Mitigacao: Documentar limitacoes, focar em contrato

Risco: Ausencia de validacoes gera muitos bugs
Probabilidade: ALTA
Impacto: BAIXO (esperado em API fake)
Mitigacao: Documentar como gaps de validacao


CRONOGRAMA

DIA 1:
- Setup ambiente (Java, Maven, Postman, Newman)
- Criar estrutura do projeto Java
- Configurar pom.xml
- Modelagem inicial dos casos de teste (refinamento continuo)
- Priorizacao baseada em risco
- Estrutura Excel com casos CRITICOS

DIA 2:
- Implementar camada Clients (PostsClient, UsersClient)
- Implementar Models (POJOs)
- Criar JSON Schema
- Automatizar casos CRITICOS (TC-001, TC-002, TC-017)
- Executar e documentar resultados

DIA 3:
- Automatizar casos ALTOS e MEDIOS
- Criar colecao Postman
- Executar via Newman
- Capturar evidencias de bugs
- Documentar bugs na aba BUGS

DIA 4:
- Finalizar automacoes restantes
- Preencher matriz de rastreabilidade
- Criar README.md
- Revisao final
- Preparar repositorio GitHub





Colunas:
Caso | Classe Java | Metodo Java | Request Postman | Status Automacao | Status Execucao | Bugs

TC-001 | PostsTest.java | shouldReturn100PostsWithCorrectStructure() | GET All Posts | AUTOMATIZADO | PASS | -
TC-002 | PostsTest.java | shouldReturnPostById() | GET Post by ID | AUTOMATIZADO | PASS | -
TC-003 | PostsTest.java | shouldReturn404ForNonExistentPost() | GET Post 404 | AUTOMATIZADO | PASS | -
TC-004 | PostsTest.java | shouldHandleInvalidIdType() | - | AUTOMATIZADO | FAIL | BUG-001
TC-005 | PostsTest.java | shouldCreatePostWithValidPayload() | POST Create Post | AUTOMATIZADO | PASS | -
TC-006 | PostsTest.java | shouldRejectPostWithoutTitle() | POST Without Title | AUTOMATIZADO | FAIL | BUG-002
TC-007 | PostsTest.java | shouldRejectPostWithoutBody() | POST Without Body | AUTOMATIZADO | FAIL | BUG-003
TC-008 | PostsTest.java | shouldRejectPostWithoutUserId() | POST Without UserId | AUTOMATIZADO | FAIL | BUG-004
TC-009 | PostsTest.java | shouldRejectInvalidUserIdType() | - | AUTOMATIZADO | FAIL | BUG-005
TC-010 | PostsTest.java | shouldRejectEmptyPayload() | POST Empty Payload | AUTOMATIZADO | FAIL | BUG-006
TC-011 | PostsTest.java | shouldUpdatePostCompletely() | PUT Update Post | AUTOMATIZADO | PASS | -
TC-012 | PostsTest.java | shouldPatchPostPartially() | PATCH Post | AUTOMATIZADO | PASS | -
TC-013 | PostsTest.java | shouldDeletePost() | DELETE Post | AUTOMATIZADO | PASS | -
TC-014 | PostsTest.java | shouldReturn404OnDeleteNonExistent() | - | AUTOMATIZADO | PASS | -
TC-015 | PostsTest.java | shouldMatchJsonSchema() | - | AUTOMATIZADO | PASS | -
TC-016 | PostsTest.java | shouldFilterByUserId() | GET Posts by User | AUTOMATIZADO | PASS | -
TC-017 | PostsTest.java | shouldBlockSQLInjection() | - | AUTOMATIZADO | PASS | -
TC-018 | PostsTest.java | shouldSanitizeXSSInTitle() | - | AUTOMATIZADO | FAIL | BUG-007
TC-019 | PostsTest.java | shouldSanitizeXSSInBody() | - | AUTOMATIZADO | FAIL | BUG-008
TC-020 | UsersTest.java | shouldReturnAllUsers() | GET All Users | AUTOMATIZADO | PASS | -
TC-021 | UsersTest.java | shouldReturnUserById() | GET User by ID | AUTOMATIZADO | PASS | -
TC-022 | UsersTest.java | shouldReturn404ForNonExistentUser() | - | AUTOMATIZADO | PASS | -

RESUMO:
Total Casos: 22
Automatizados: 22
Passando: 14
Falhando: 8
Bugs Criticos: 2
Bugs Altos: 4
Bugs Medios: 2



Colunas:
ID | Origem | Modulo | Severidade | Titulo | Descricao | Passos Reproducao | Resultado Atual | Resultado Esperado | Evidencia | Sugestao Correcao | Ambiente

BUG-001
Origem: TC-004
Modulo: Posts
Severidade: MEDIA
Titulo: Gap de validacao - API retorna 404 para ID com tipo invalido ao inves de 400
Descricao:
Quando GET /posts/{id} recebe ID no formato string, a API retorna 404 (Not Found) 
ao inves de 400 (Bad Request), indicando que validacao de tipo nao ocorre antes da busca.
Passos Reproducao:
1. Enviar GET https://jsonplaceholder.typicode.com/posts/abc
2. Observar status code
Resultado Atual:
- Status 404
- Body vazio {}
Resultado Esperado:
- Status 400
- Body: {"error": "Invalid parameter type", "message": "ID must be numeric"}
Evidencia: screenshots/BUG-001.png
Sugestao Correcao:
Validar tipo de parametro antes da busca:
if (isNaN(parseInt(id))) return 400;
Ambiente: jsonplaceholder.typicode.com

BUG-002
Origem: TC-006
Modulo: Posts
Severidade: ALTA (em producao seria CRITICA)
Titulo: Ausencia de validacao - API aceita POST sem campo obrigatorio title
Descricao:
POST /posts permite criacao sem campo title, que deveria ser obrigatorio.
Retorna 201 e cria post com title null/undefined.
Passos Reproducao:
1. POST https://jsonplaceholder.typicode.com/posts
2. Body: {"body":"Teste","userId":1}
3. Observar status 201
Resultado Atual:
- Status 201
- Post criado sem title
Resultado Esperado:
- Status 400
- Body: {"error": "title is required"}
Evidencia: screenshots/BUG-002.png
Sugestao Correcao:
Validar campos obrigatorios:
const required = ['title','body','userId'];
required.forEach(field => {
  if (!req.body[field]) return 400;
});
Ambiente: jsonplaceholder.typicode.com

BUG-003
Origem: TC-007
Modulo: Posts
Severidade: ALTA (em producao seria CRITICA)
Titulo: Ausencia de validacao - API aceita POST sem campo obrigatorio body
Descricao:
Similar ao BUG-002. POST aceita criacao sem campo body.
Passos Reproducao:
1. POST /posts
2. Body: {"title":"Titulo","userId":1}
Resultado Atual:
- Status 201
- Post sem body
Resultado Esperado:
- Status 400
- Mensagem: "body is required"
Evidencia: screenshots/BUG-003.png
Sugestao Correcao: Mesma do BUG-002
Ambiente: jsonplaceholder.typicode.com

BUG-004
Origem: TC-008
Modulo: Posts
Severidade: ALTA (em producao seria CRITICA)
Titulo: Ausencia de validacao - API aceita POST sem campo obrigatorio userId
Descricao:
POST aceita criacao de post sem userId, criando registro orfao.
Passos Reproducao:
1. POST /posts
2. Body: {"title":"Teste","body":"Corpo"}
Resultado Atual:
- Status 201
- Post sem proprietario
Resultado Esperado:
- Status 400
- Mensagem: "userId is required"
Evidencia: screenshots/BUG-004.png
Sugestao Correcao: Validacao de campo obrigatorio
Ambiente: jsonplaceholder.typicode.com

BUG-005
Origem: TC-009
Modulo: Posts
Severidade: MEDIA
Titulo: Gap de validacao de tipo - API aceita userId como string
Descricao:
POST aceita userId com tipo string ao inves de validar tipo integer.
Passos Reproducao:
1. POST /posts
2. Body: {"title":"x","body":"y","userId":"texto"}
Resultado Atual:
- Status 201 ou conversao silenciosa
Resultado Esperado:
- Status 400
- Mensagem: "userId must be integer"
Evidencia: screenshots/BUG-005.png
Sugestao Correcao:
if (typeof req.body.userId !== 'number') return 400;
Ambiente: jsonplaceholder.typicode.com

BUG-006
Origem: TC-010
Modulo: Posts
Severidade: ALTA
Titulo: Ausencia de validacao - API aceita payload completamente vazio
Descricao:
POST aceita body vazio {}, criando post sem dados.
Passos Reproducao:
1. POST /posts
2. Body: {}
Resultado Atual:
- Status 201
- Post criado vazio
Resultado Esperado:
- Status 400
- Mensagem: "Payload cannot be empty"
Evidencia: screenshots/BUG-006.png
Sugestao Correcao:
if (Object.keys(req.body).length === 0) return 400;
Ambiente: jsonplaceholder.typicode.com

BUG-007
Origem: TC-018
Modulo: Posts
Severidade: CRITICA (vulnerabilidade de seguranca)
Titulo: Gap de seguranca - XSS nao sanitizado no campo title
Descricao:
API nao sanitiza scripts maliciosos em title, permitindo XSS armazenado.
Scripts sao retornados sem escape, possibilitando execucao em frontend.
Passos Reproducao:
1. POST /posts
2. Body: {"title":"<script>alert('XSS')</script>","body":"Teste","userId":1}
3. GET post criado
4. Observar script sem escape
Resultado Atual:
- Script retornado sem sanitizacao
Resultado Esperado:
- Script escapado: &lt;script&gt;...&lt;/script&gt; OU
- Request rejeitada com status 400
Evidencia: screenshots/BUG-007.png
Sugestao Correcao:
Sanitizar HTML:
const sanitize = require('sanitize-html');
req.body.title = sanitize(req.body.title, {allowedTags:[]});
Ambiente: jsonplaceholder.typicode.com

BUG-008
Origem: TC-019
Modulo: Posts
Severidade: CRITICA (vulnerabilidade de seguranca)
Titulo: Gap de seguranca - XSS nao sanitizado no campo body
Descricao:
Similar ao BUG-007. Campo body aceita e retorna scripts sem sanitizacao.
Passos Reproducao:
1. POST /posts
2. Body: {"title":"x","body":"<img src=x onerror=alert('XSS')>","userId":1}
Resultado Atual:
- Payload malicioso retornado sem escape
Resultado Esperado:
- Sanitizado ou rejeitado
Evidencia: screenshots/BUG-008.png
Sugestao Correcao: Mesma do BUG-007 aplicada ao body

Ambiente: jsonplaceholder.typicode.com
