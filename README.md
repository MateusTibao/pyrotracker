<div align="center">
  
![slide_principal](https://github.com/user-attachments/assets/dad1bbdf-e890-4faa-992d-784abf5ca8fa)

</div>

## 📚 Índice

- [👥 Equipe](-Equipe)
- [🌍 Descrição Geral do Projeto](-descrição-geral-do-projeto)
- [⚙️ Funcionalidades Principais](#️-funcionalidades-principais)
- [🧠 Justificativas Técnicas e Arquitetura da Solução](#-justificativas-técnicas-e-arquitetura-da-solução)
- [📖 Instruções de Uso da API](#-instruções-de-uso-da-api)
- [🧬 Diagrama de Classes](#-diagrama-de-classes)

## 👥 Equipe

- **Caio Hideki** - RM553630
- **Jorge Booz** - RM552700
- **Mateus Tibão** - RM553267

## 🌍 Descrição Geral do Projeto

O **PyroTracker** é uma aplicação backend desenvolvida em Java com Spring Boot, focada na **prevenção, monitoramento e resposta inteligente a queimadas ambientais**. A solução visa oferecer uma API RESTful robusta para integrar usuários comuns e agentes ambientais em um ecossistema colaborativo de combate a incêndios florestais.

### 🎯 Justificativa

As queimadas florestais são um problema crescente que afetam diretamente o clima, a biodiversidade, a saúde pública e a economia. A ausência de sistemas integrados e inteligentes de resposta rápida contribui para a dificuldade no controle desses eventos. Diante desse cenário, o PyroTracker foi idealizado como uma ferramenta tecnológica capaz de **centralizar dados, validar focos de calor e coordenar intervenções**, promovendo agilidade e eficiência na resposta a desastres ambientais.

## ⚙️ Funcionalidades Principais

A aplicação PyroTracker expõe uma API RESTful com diversas funcionalidades organizadas em perfis de acesso distintos (USUÁRIO, AGENTE, ADMIN). A seguir, destacam-se as principais operações por módulo:

### 🔥 Pontos de Foco
- Cadastro de focos de incêndio com informações como localização, nível de fumaça, comentários e foto (realizado por usuários autenticados).
- Validação de focos por agentes ambientais.
- Atualização de validade limitada a perfis AGENTE e ADMIN.

### 🚨 Alertas
- Geração e visualização de alertas vinculados a focos de incêndio válidos.
- Listagem acessível a perfis autorizados.

### 🧯 Intervenções
- Registro de ações de combate vinculadas a alertas existentes.

### 📍 Zonas de Risco
- Cadastro e consulta de zonas com maior propensão a queimadas.
- Gerenciado por agentes ambientais e administradores.

### 👥 Usuários
- Registro de novos usuários com atribuição de perfil (USUÁRIO, AGENTE ou ADMIN).
- Autenticação com geração de JWT.
- Atualização da reputação (somente por perfis AGENTE ou ADMIN).
- Controle de acesso baseado em perfis com Spring Security.

## 🧠 Justificativas Técnicas e Arquitetura da Solução

### 🧱 Arquitetura em Camadas

O projeto segue uma **arquitetura em camadas**, favorecendo a separação de responsabilidades, manutenção e extensibilidade:

- **Controller**: expõe a API REST e lida com requisições HTTP.
- **Service**: contém a lógica de negócio (validações, regras de reputação, verificação de papéis).
- **Repository**: abstração da persistência de dados usando Spring Data JPA.
- **Domain**: contém as entidades do sistema, mapeadas com JPA.

---

### 🔐 Segurança com Spring Security e JWT

Para garantir a **segurança e autenticação**, foi implementado o **Spring Security** com geração e validação de **JWTs (JSON Web Tokens)**:

- Autenticação com email e senha.
- Geração de tokens via `/auth/login`.
- Validação de token e controle de acesso por perfil (`ROLE_USUARIO`, `ROLE_AGENTE`, `ROLE_ADMIN`).
- Filtros (`JwtAuthenticationFilter`) para validar cada requisição de forma stateless.
- Rotas públicas e protegidas devidamente configuradas via `SecurityFilterChain`.

---

### 🧩 Padrões de Projeto Aplicados

- **Repository Pattern**  
  Separação clara entre a lógica de persistência e a lógica de negócio, usando interfaces e injeção de dependência (`UsuarioRepository`, `AlertaRepository` etc).

- **DTO (Data Transfer Object)**  
  Utilização de DTOs para entrada de dados e operações específicas como `AuthRequestDTO`, `PontoDeFocoCreateDTO`, `UsuarioReputacaoDTO`. Isso protege o modelo de domínio e facilita a manipulação dos dados na API.

- **Factory Simples (Implícita no cadastro de domínio)**  
  No cadastro de focos, alertas e intervenções, os serviços constroem os objetos a partir dos DTOs, centralizando as regras de criação em um único ponto.

---

### 🧾 Outros Pontos Técnicos

- **Validações**: garantem reputação entre 0 e 100, foco só válido após verificação de agente, etc.
- **Roles como Enum**: facilita a leitura, segurança e evita erros de string.
- **Token enriquecido com informações de perfil**: cada requisição leva as credenciais necessárias no JWT.
- **Atualização automática de relacionamento**: por exemplo, o foco é automaticamente vinculado ao usuário autenticado no momento do envio.


## 📖 Instruções de Uso da API

#### 📺 Vídeo demonstrativo:
https://youtu.be/lbrrTvod3C0


### 🔐 Autenticação (`/auth`)

#### `POST /auth/register`
Registra um novo usuário. O perfil é definido no corpo da requisição.

**Permissão:** Pública

**Exemplo de Body:**
```json
{
  "nome": "Administrador Tibão",
  "email": "tibao@pyrotracer.com",
  "senha": "DEZnaGS",
  "role": "ADMIN"
}
```

---

#### `POST /auth/login`
Realiza o login e retorna um token JWT.

**Permissão:** Pública

**Exemplo de Body:**
```json
{
  "email": "hideki@pyrotracer.com",
  "senha": "EsseTrabalhoMerece"
}
```

---

### 📍 Pontos de Foco (`/focos`)

#### `GET /focos`
Lista todos os focos de incêndio.

**Permissão:** USUARIO, AGENTE, ADMIN

---

#### `POST /focos`
Cadastra um novo ponto de foco vinculado ao usuário autenticado.

**Permissão:** USUARIO

**Exemplo de Body:**
```json
{
  "latitude": -10.12,
  "longitude": -53.88,
  "nivelFumaca": 2,
  "comentario": "Fogo na mata",
  "fotoUrl": "http://foto.com/fogo123.jpg.."
}
```

---

#### `PUT /focos/{id}/validade`
Atualiza a validade do foco (confirmação do agente).

**Permissão:** AGENTE, ADMIN

**Exemplo de Body:**
```json
{
  "valido": true
}
```

---

### 🚨 Alertas (`/alertas`)

#### `GET /alertas`
Lista todos os alertas de incêndio.

**Permissão:** AGENTE, ADMIN

---

#### `POST /alertas`
Cadastra um novo alerta com base em ponto de foco validado.

**Permissão:** AGENTE

**Exemplo de Body:**
```json
{
  "latitude": -10.12,
  "longitude": -54.88,
  "criticidade": "MEDIO",
  "idsPontosRelacionados": [1,2]
}
```

---

#### `PUT /alertas/{id}/status`
Atualiza um alerta existente.

**Permissão:** AGENTE, ADMIN

**Exemplo de Body:**
```json
{
  "status": "CONTIDO"
}
```

---

### 🧯 Intervenções (`/intervencoes`)

#### `GET /intervencoes`
Lista todas as intervenções realizadas.

**Permissão:** AGENTE, ADMIN

---

#### `POST /intervencoes`
Registra uma nova ação de combate a incêndio.

**Permissão:** AGENTE

**Exemplo de Body:**
```json
{
  "equipeResponsavel": "Brigada Cerrado Bravo",
  "descricaoOperacao": "Ação coordenada de contenção com apoio aéreo.",
  "alertaId": 1
}
```

---

#### `PUT /intervencoes/{id}/status`
Atualiza uma intervenção.

**Permissão:** AGENTE, ADMIN

**Exemplo de Body:**
```json
{
  "status": "FINALIZADA_COM_SUCESSO"
}
```

---

### 🧭 Zonas de Risco (`/zonas`)

#### `GET /zonas`
Lista todas as zonas de risco mapeadas.

**Permissão:** AGENTE, ADMIN

---

#### `POST /zonas`
Cadastra uma nova zona com base em variáveis geográficas.

**Permissão:** AGENTE

**Exemplo de Body:**
```json
{
  "regiao": "Chapada dos Veadeiros",
  "nivelRisco": "ALTO",
  "comentario": "Área com alerta recorrente",
  "idsAlertasRelacionados": [1]
}
```

#### `PUT /zonas/{id}`
Atualiza as informações de uma zona de risco existente.

**Permissão:** AGENTE, ADMIN

**Exemplo de Body:**
```json
{
  "nivelRisco": "BAIXO",
  "idsAlertasRelacionados": [1, 2, 3]
}
```

---

### 👤 Usuários (`/usuarios`)

#### `GET /usuarios`
Lista todos os usuários cadastrados.

**Permissão:** ADMIN

---

#### `PUT /usuarios/{id}/reputacao`
Atualiza manualmente a reputação de um usuário (entre 0 e 100).

**Permissão:** ADMIN, AGENTE

**Exemplo de Body:**
```json
{
  "reputacao": 75
}
```

## 🧬 Diagrama de classes

<div align="center">
  
![image](https://github.com/user-attachments/assets/9e00f5ee-7eac-47fd-850e-481f7d69286e)

</div>
