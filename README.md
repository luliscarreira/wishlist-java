## Descrição

Uma das funcionalidades mais interessantes em um e-commerce é a Wishlist, ou a lista de desejos.
No e-commerce o cliente pode realizar a busca de produtos, ou pode acessar a tela de detalhes do produto.
Em ambas as telas ele pode selecionar os produtos de sua preferência e armazená-los na sua Wishlist.
A qualquer momento o cliente pode visualizar sua Wishlist completa, com todos os produtos que ele selecionou em uma única tela.

## Requisitos

### Tecnologias Principais
- **Java 21**
- **Spring Boot**
- **MongoDB**
- **Maven**

### Ferramentas de Desenvolvimento
- **Docker compose** (Para subir o banco de dados)
- **Postman** ou **cURL** (para testar as APIs)

## Configuração do Ambiente

### Requisitos do Sistema
- **IntelliJ** ou qualquer IDE de sua preferência
- **Java 21** instalado
- **MongoDB** através de um container Docker
- **Maven** build e execução do projeto

### Comandos para Instalação e Execução

1. **Clonar o repositório:**:
    ```bash
   git clone git@github.com:luliscarreira/wishlist-java.git
   cd wishlist-java
    ```

2. **Configurar o projeto:**:
   Criar arquivo application.properties na pasta ./config/ seguindo o examplo na mesma página

3. **Compilar o projeto:**:
    ```bash
   mvn clean install
    ```

4. **Rodar o MongoDB usando Docker compose:**
   ```bash
    docker-compose up -d
   ```

5. **Executar a aplicação:**
    ```bash
   mvn spring-boot:run
    ```
   
6. **Testar a aplicação (opcional):**
    ```bash
   mvn test
    ``` 

### Documentação da API

A documentação da API foi feita utilizando o Swagger. Para acessar a documentação, basta acessar a URL [http://localhost:8080/documentation.html](http://localhost:8080/documentation.html) após a execução da aplicação.
