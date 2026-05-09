# Insights-financeiros

# 🚀 Plataforma de Insights Financeiros com IA (RAG)

**Projeto de Conclusão de Curso - Trilha Backend (Hackathon Artemisia Elas+ Tech)**

## 📌 Objetivo do Projeto
Desenvolver uma plataforma onde o usuário "conversa com seus dados financeiros" e recebe insights, alertas e relatórios. A aplicação processa dados transacionais, aplica regras de negócio para análise de perfil financeiro e gera insights inteligentes utilizando um pipeline RAG (Retrieval-Augmented Generation).

**Equipe:**

## ⚙️ Arquitetura da Solução e Tecnologias
O projeto foi estruturado utilizando **Clean Architecture** para garantir a separação de responsabilidades e facilitar a testabilidade[cite: 1], sendo dividido em camadas de Apresentação (REST), Aplicação (Use Cases), Domínio e Infraestrutura.

**Stack Tecnológica:**
*   **Java 21**: Uso intensivo de `records`, `sealed classes` e programação funcional.
*   **Spring Boot 3.x**: Framework principal.
*   **Spring Data JPA & Banco de Dados**: PostgreSQL recomendado para produção, utilizando H2 para o ambiente de desenvolvimento local.
*   **Spring Security**: Autenticação via JWT, garantindo isolamento multi-tenant (cada usuário só tem acesso aos próprios dados).
*   **Spring AI**: Integração com LLM e Vector Store local para o pipeline RAG completo (consulta → recuperação → prompt → resposta).
*   **JUnit 5 & Mockito**: Para garantia de qualidade via testes automatizados.

## 🔒 Guardrails de Segurança
A segurança é tratada como prioridade. A arquitetura implementa guardrails rigorosos de segurança para não vazar dados de outros usuários. Toda interação com o Vector Store e consultas de dados utilizam o `usuarioId` (extraído do token JWT) como filtro de metadados inegociável, impossibilitando que a LLM acesse dados cruzados.
