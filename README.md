# Carrito de Compras - Arquitectura Hexagonal

Este proyecto implementa un sistema de carrito de compras utilizando la arquitectura hexagonal. La arquitectura hexagonal, también conocida como arquitectura de puertos y adaptadores, permite una separación clara entre la lógica de negocio y las interfaces externas, facilitando la mantenibilidad y escalabilidad del sistema.

## Estructura del Proyecto

El proyecto está dividido en varios módulos siguiendo los principios de la arquitectura hexagonal:

- **Model**: Contiene la lógica de negocio y los modelos de dominio.
- **Application**: Contiene los casos de uso y los puertos (interfaces) que definen las operaciones del sistema.
- **Adapter**: Contiene los adaptadores que implementan los puertos y conectan la lógica de negocio con las interfaces externas (por ejemplo, controladores REST, repositorios de datos).
- **Infrastructure**: Contiene la configuración y las dependencias del proyecto.

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior
- Spring Boot 2.5 o superior

## Instalación

1. Clona el repositorio:
   ```sh
   git clone https://github.com/Christhiansch/arq-hxg-shopping-cart.git
   cd arq-hxg-shopping-cart
   git checkout master
   ```
   
2. Compila el proyecto:
   ```sh
   mvn clean install
   ```

3. Ejecuta la aplicación:
   ```sh
   mvn spring-boot:run
   ```

4. Ejecuta las pruebas unitarias
   ```sh
   mvn test
   ```
