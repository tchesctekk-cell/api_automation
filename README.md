Automacao API JSONPlaceholder -
Projeto de automacao de testes para API JSONPlaceholder.

Contexto Importante
A API JSONPlaceholder (https://jsonplaceholder.typicode.com) e uma API publica simulada (fake) para fins de prototipacao e aprendizado.

Informacoes do Projeto
API Testada: JSONPlaceholder
URL Base: https://jsonplaceholder.typicode.com
Objetivo: Validar endpoints /posts e /users
Prazo: 4 dias
Abordagem: Testes funcionais, negativos, contrato e seguranca
Tecnologias Utilizadas
Java 11
Maven 3.8+
JUnit 5.10.0
RestAssured 5.3.2
JSON Schema Validator 5.3.2
Postman Desktop
Newman + newman-reporter-htmlextra
Estrutura do Projeto
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
Como Executar
Pre-requisitos
Java 11+
Maven 3.8+
Node.js e npm (para Newman)
Instalacao
# Newman
npm install -g newman newman-reporter-htmlextra

# Clonar projeto
git clone [url-repositorio]
cd api-automation
Executar Testes
Java + RestAssured:

mvn clean test
Postman + Newman:

cd postman
./run-newman.sh
Cenarios Adicionais (Diferenciais)
Testes Funcionais: Testes Negativos: Testes de Seguranca: Total: 22 casos de teste documentados
Separacao de Responsabilidades
Camada: Models (POJOs)
Arquivo: Post.java, User.java
Responsabilidade: Representar estrutura de dados
Uso: Serializacao/deserializacao via Jackson

Camada: Clients
Arquivo: PostsClient.java, UsersClient.java
Responsabilidade: Encapsular APENAS chamadas HTTP
Principio: Sem validacoes - apenas comunicacao

Camada: Tests
Arquivo: PostsTest.java, UsersTest.java
Responsabilidade: Executar cenarios e validar resultados
Principio: Usa Clients para requisicoes, foca em asserts

Camada: Base
Arquivo: BaseTest.java
Responsabilidade: Configuracao comum reutilizavel
Conteudo: RequestSpecification, URL base, logs

Camada: Utils
Arquivo: TestDataFactory.java
Responsabilidade: Geracao dinamica de dados
Beneficio: Evita dados hardcoded, garante unicidade

Decisoes Tecnicas
Por que jsonplaceholder.typicode.com?
API original, estavel, nao requer setup local, amplamente conhecida.

Por que nao validar persistencia?
A API nao persiste dados realmente. POST/PUT/DELETE retornam fake responses.

Estrategia adotada:

Validar contrato (JSON Schema)
Validar coerencia request-response
Validar tratamento de erros
Validar seguranca basica
Por que testes de seguranca em API fake?
Os testes de SQL Injection e XSS foram incluidos para demonstrar capacidade de analise de risco e visao de seguranca, mesmo sabendo que a API JSONPlaceholder nao usa SQL nem renderiza HTML.

Objetivo: Simular validacoes esperadas em APIs reais de producao, mostrando pensamento critico sobre OWASP Top 10 e vulnerabilidades comuns.

Por que separar Clients e Tests?
Separacao de responsabilidades:

Clients fazem requisicoes (COMO chamar API)
Tests fazem validacoes (O QUE validar)
Beneficio: Mudanca de endpoint = alterar 1 arquivo apenas.
