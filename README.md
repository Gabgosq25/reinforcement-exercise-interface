# 📄 Contract Processing System (Processador de Contratos)

Sistema em Java para automação e cálculo de parcelas de contratos, focado na aplicação prática de **Orientação a Objetos**, **Princípios SOLID** e **Injeção de Dependência**.

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Paradigma](https://img.shields.io/badge/Paradigma-POO%20%2F%20SOLID-blue?style=for-the-badge)
![Arquitetura](https://img.shields.io/badge/Arquitetura-Baixo%20Acoplamento-success?style=for-the-badge)

---

## 💡 Sobre o Projeto

O **Contract Processing System** é uma solução backend para a geração de cronogramas de pagamento. A aplicação recebe os dados brutos de um contrato (número, data de registro e valor total) juntamente com a quantidade de parcelas desejadas. A partir daí, processa dinamicamente as datas de vencimento e aplica juros e taxas de serviço por parcela.

O principal diferencial técnico deste projeto é a **Injeção de Dependência** aplicada na camada de serviço. Utilizamos a interface `OnlinePaymentService` para garantir que o núcleo do sistema (`ContractService`) não conheça as regras específicas de um gateway de pagamento (como PayPal, Stripe, etc.). Isto permite alterar as regras tributárias ou o fornecedor do serviço sem ter de modificar a lógica principal da aplicação.

## 🛠️ Arquitetura & Fluxo de Processamento

```text
┌──────────────────────┐
│  Dados de Entrada    │ (Número, Data, Valor Total, Parcelas)
└──────────┬───────────┘
           │
           ▼
┌──────────────────────┐       Interface        ┌──────────────────────┐
│   ContractService    │───────────────────────►│ OnlinePaymentService │
└──────────┬───────────┘     (Desacoplado)      └──────────┬───────────┘
           │                                               │
           │                                               │ (Implementação: PaypalService)
           │                                               ▼
           │                                    ┌──────────────────────┐
           └───────────────────────────────────►│ Cálculos de Taxas    │
                                                └──────────┬───────────┘
                                                           │
┌──────────────────────┐                                   │
│  Gera Installments   │◄──────────────────────────────────┘
└──────────────────────┘
```

## 🚀 Tecnologias e Conceitos Aplicados

- **Java Core**: Utilização avançada de `java.time` para manipulação de datas (`LocalDate`, `DateTimeFormatter`).
- **Inversão de Controle / Injeção de Dependência**: Instanciação do serviço de pagamento a partir da aplicação principal (`Program.java`).
- **Tratamento de Exceções**: Proteção de regras de negócio customizadas com a criação da `DomainException`.
- **Coleções**: Uso prático de `List` e `ArrayList` para gerenciar as parcelas do contrato.

## 💻 Como Executar

1. Clone o repositório ou baixe o arquivo fonte.
2. Abra o projeto na sua IDE de preferência (IntelliJ IDEA, Eclipse, VS Code).
3. Execute a classe principal `application.Program`.
4. Siga as instruções no terminal para inserir os dados do contrato.

---
*Desenvolvido como demonstração prática de Arquitetura de Software e Padrões de Projeto.*