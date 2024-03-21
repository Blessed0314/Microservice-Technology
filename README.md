
## Descripción

El primer microservicio es responsable de crear las tecnologías y permite listarlas en un orden ascendente o descendente.

## Arquitectura

El proyecto sigue una arquitectura hexagonal (puertos y adaptadores) que facilita la separación de preocupaciones y el mantenimiento del código. La arquitectura se compone de las siguientes capas:

- **Dominio**: Contiene la lógica de negocio del microservicio.
- **Puertos**: Define interfaces para interactuar con el mundo exterior.
- **Adaptadores**: Implementaciones concretas de las interfaces definidas en los puertos.

## Tecnologías Utilizadas

El proyecto utiliza las siguientes tecnologías principales:

- Java
- Spring Boot
- Hibernate
- Gradle
- JUnit y Mockito

## Estructura del Repositorio

La estructura del repositorio es la siguiente:

proyecto-primer-microservicio/
│
├── src/
│ ├── main/
│ │ └── java/
│ │ └── ...
│ └── test/
│ └── java/
│ └── ...
├── docs/
│ └── ...
└── README.md

## Instrucciones de Uso

Para ejecutar el microservicio localmente, sigue estos pasos:

1. Clona el repositorio.
2. Abre el proyecto en tu IDE favorito.
3. ...
4. ...

## Licencia

Este proyecto está bajo la licencia [MIT License](LICENSE).
