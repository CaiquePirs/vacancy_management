# 🚀 Backend API para Gerenciamento de Vagas

Uma API escalável e pronta para produção, desenvolvida para gerenciar todo o ciclo de vagas de emprego, desde o cadastro de empresas e candidatos até a publicação e aplicação em vagas. Este sistema backend oferece uma infraestrutura segura, performática e automatizada.

---

## 🎯 Funcionalidades

- 🔐 Autenticação baseada em JWT para Candidatos e Empresas
- 👥 Fluxos de cadastro e login de usuários
- 📄 Criar, listar, atualizar e excluir vagas de emprego
- 🛡️ Controle de acesso baseado em papéis com Spring Security
- 🗃️ Banco de dados PostgreSQL hospedado na AWS RDS
- 🚢 Implantação conteinerizada na AWS EC2 via GitHub Actions
- 📦 Imagem Docker hospedada no GitHub Container Registry (GHCR)

---

## 🛠️ Stack Tecnológica

| Camada           | Tecnologias                            |
|------------------|----------------------------------------|
| Backend          | Java 21, Spring Boot                   |
| Segurança        | JWT, Spring Security                   |
| Banco de Dados   | PostgreSQL (AWS RDS)                   |
| Conteinerização  | Docker, GitHub Container Registry      |
| Infraestrutura   | GitHub Actions, AWS EC2                |
| CI/CD            | Workflow do GitHub Actions             |

---

## 🧪 Desenvolvimento Local

```bash
# Clonar o repositório e compilar
git clone https://github.com/caiquepirs/vacancy-management.git
cd vacancy-management
mvn clean install

# Construir imagem Docker
docker build -t vacancy-management .

# Executar o container com variáveis de ambiente
docker run -d -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://localhost:5432/seu_banco \
  -e DATABASE_USERNAME=seu_usuario \
  -e DATABASE_PASSWORD=sua_senha \
  -e SECRET_KEY_CANDIDATE=segredo_jwt_candidato \
  -e SECRET_KEY_COMPANY=segredo_jwt_empresa \
  --name vacancy-management \
  vacancy-management
```

## 📚 Documentação da API

A interface interativa do Swagger UI está disponível para explorar todos os endpoints e testar requisições/respostas.

**🔗 Acesse em:**
http://13.53.132.128:8080/swagger-ui/index.html#/


Certifique-se de que esse caminho está exposto na configuração do Spring e que a porta está liberada nas regras de entrada do EC2.

---

## 🌍 Implantação em Produção

A aplicação é implantada via **GitHub Actions** em uma instância **EC2 da AWS**. Ao realizar push na branch `main`, o workflow executa as seguintes etapas:

1. Compila o projeto com Maven
2. Cria uma imagem Docker
3. Publica no GitHub Container Registry
4. Conecta-se à EC2 e puxa a nova imagem
5. Reinicia o container com as variáveis de ambiente atualizadas

---

## 🔐 Variáveis de Ambiente

As variáveis são configuradas via **GitHub Secrets** e injetadas durante o processo de deploy:

- `DATABASE_URL`
- `DATABASE_USERNAME`
- `DATABASE_PASSWORD`
- `SECRET_KEY_CANDIDATE`
- `SECRET_KEY_COMPANY`

---

## 💻 URL da Aplicação

A aplicação está disponível em:
http://13.53.132.128:8080/


Certifique-se de que a porta `8080` está liberada no grupo de segurança da instância EC2!

---

## 🙋 Autor

Feito com paixão por **Caique Pirs** 🧑‍💻  
Conecte-se comigo para criar aplicações inteligentes e ideias ainda mais incríveis!

---

## 🎓 Inspiração

Este projeto foi desenvolvido com o objetivo de aprender:

- Implantação backend em ambiente real
- Pipelines automatizados de CI/CD
- Infraestrutura em nuvem e orquestração de containers

---

## 📣 Quer contribuir?

Sinta-se à vontade para fazer **fork**, dar uma **estrela** ou enviar um **pull request**.  
Tem dúvidas ou sugestões? Abra uma **issue** ou fale comigo pelo GitHub!


