# Spring Boot Concepts

A collection of simple notes and code examples to understand core Spring Boot concepts.

## 1. Spring Boot Web Starter

The behavior of a Spring Boot application depends on the dependencies added to the project.

**Without `spring-boot-starter-web`:**
- Spring initializes the application context.
- `Hello World` gets printed.
- The program can exit with code `0` when no non-daemon threads remain.

**With `spring-boot-starter-web`:**
- Spring Boot starts an embedded Tomcat server, usually on port `8080`.
- `Hello World` gets printed.
- The application keeps running to handle HTTP requests.

```java
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("Hello World");
    }
}
```

## 2. IoC (Inversion of Control)

IoC means that **Spring manages object creation and lifecycle instead of us creating objects manually using `new`.**

### Bean Creation

A **bean** is an object managed by the Spring IoC container.

Spring can discover classes annotated with `@Component`, `@Service`, or `@Repository` through component scanning.

**Repository.java**

```java
@Repository
public class Repository {

    public Repository() {
        System.out.println("Repository constructor called");
    }
}
```

**MyService.java**

```java
@Service
public class MyService {

    public MyService() {
        System.out.println("Service constructor called");
    }
}
```

When these classes are in a package scanned by `@SpringBootApplication`, Spring normally creates their singleton beans during application startup. Their constructors execute automatically, without manually calling `new`.

## 3. Dependency Injection (DI)

Dependency Injection means **providing an object with the dependencies it needs**, rather than making it create those dependencies itself.

Bean creation and dependency injection are different concepts.

### Constructor Injection (Recommended)

```java
@Repository
public class Repository {

    public void save() {
        System.out.println("Saving data");
    }
}
```

```java
@Service
public class MyService {

    private final Repository repository;

    public MyService(Repository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.save();
    }
}
```

Spring automatically passes the `Repository` bean into the `MyService` constructor. `@Autowired` is **not required when a class has only one constructor**.

### Field Injection

```java
@Autowired
private Repository repository;
```

### Setter Injection

```java
@Autowired
public void setRepository(Repository repository) {
    this.repository = repository;
}
```

Field and setter injection use `@Autowired` for automatic injection in these examples. Constructor injection is generally preferred for required dependencies.

## 4. Important Annotations

| Annotation | Purpose |
|---|---|
| `@SpringBootApplication` | Configures the Spring Boot application and enables component scanning |
| `@Component` | Marks a class as a Spring-managed component |
| `@Service` | Marks a service/business-logic component |
| `@Repository` | Marks a persistence/data-access component |
| `@Autowired` | Injects a dependency at the annotated injection point |

## Key Takeaways

- **IoC:** Spring controls object creation and lifecycle.
- **Bean:** An object managed by Spring.
- **Component scanning:** Discovers annotated classes in scanned packages.
- **Dependency Injection:** Connects beans to their dependencies.
- **`@Autowired`:** Used for dependency injection, not simply for bean creation.
- **Single constructor:** Spring can inject its dependencies without `@Autowired`.
- **Web Starter:** Starts embedded Tomcat by default, keeping the application running to serve HTTP requests.

---

*Learning Spring Boot one concept at a time.*
