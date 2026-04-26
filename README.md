# Lab 2 – Compiladores con ANTLR

## Descripción

Este proyecto implementa un lenguaje sencillo que permite la declaración de variables y la evaluación de expresiones booleanas, utilizando ANTLR para el análisis léxico y sintáctico, y un visitor para el análisis semántico.

El sistema incluye:

* Tabla de símbolos
* Evaluación de expresiones booleanas
* Manejo de errores léxicos, sintácticos y semánticos

---

## Estructura del proyecto

```
lab2/
├── src/
│   └── main/
│       ├── java/com/compis/
│       │   ├── Main.java
│       │   ├── Semantica.java
│       │   ├── Symbol.java
│       │   ├── SymbolTabla.java
│       │   └── Errores.java
│       └── antlr4/
│           └── Lab2.g4
├── pom.xml
```

---

## Tecnologías utilizadas

* Java 17
* Maven
* ANTLR 4

---

## Cómo ejecutar

### 1. Generar código ANTLR

```
mvn clean generate-sources
```

### 2. Compilar proyecto

```
mvn compile
```

### 3. Ejecutar programa

```
mvn exec:java "-Dexec.mainClass=com.compis.Main"
```

Ingresar la entrada por consola y finalizar con:

```
Ctrl + Z + Enter
```

---

## Ejemplos de uso

### Entrada válida

```
x = true
y = false
x and y and not ( x )
```

Salida:

```
VARIABLE | TIPO | VALOR
x | boolean | true
y | boolean | false

Resultado de la expresión: false
```

---

### Uso de enteros como booleanos

```
x = 1
y = 0
z = 20
x and not ( y ) and z
```

Salida:

```
Resultado de la expresión: true
```

---

### Error semántico (string)

```
x = "Hola"
x and x
```

Salida:

```
Error semántico: Las variables de tipo string no están permitidas en expresiones booleanas.
```

---

## Reglas del lenguaje

* Tipos soportados:

  * int
  * boolean
  * string

* Los enteros se evalúan como boolean:

  * 0 → false
  * cualquier otro → true

* No se permite el uso de strings en expresiones booleanas

---

## Autor

Axel Alvarado
