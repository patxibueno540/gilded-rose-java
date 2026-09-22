# Gilded Rose - Guía de Tests

Guía completa para ejecutar tests, generar reportes de cobertura y entender los resultados.

---

## 📋 Requisitos

- **Java 8+** (se recomienda Java 11 o superior)
- **Gradle 8.10** (se descarga automáticamente con el wrapper)

Verifica tu versión:
```bash
java -version
```

---

## 🧪 Ejecutar Tests

### Opción 1: Tests simples (sin reporte)

```bash
.\gradlew.bat test
```

**Output esperado:**
```
GildedRoseShould > decrease_the_quality_by_one_when_not_expired PASSED
GildedRoseShould > decrease_the_quality_by_two_when_expired PASSED
...
16 tests completed
BUILD SUCCESSFUL in 2s
```

---

### Opción 2: Tests con más detalle en consola

```bash
.\gradlew.bat test --info
```

Muestra logs detallados de cada paso del build.

---

### Opción 3: Tests con reporte JaCoCo (RECOMENDADO)

```bash
.\gradlew.bat test jacocoTestReport
```

Genera:
- ✅ Tests ejecutados
- 📊 Reporte de cobertura en HTML
- 📈 Análisis por clase y línea

---

## 📊 Generar Reporte de Cobertura

Si solo quieres el reporte sin ejecutar tests de nuevo:

```bash
.\gradlew.bat jacocoTestReport
```

---

## 🔍 Leer el Reporte JaCoCo

Después de ejecutar `test jacocoTestReport`, abre el reporte:

```
build/reports/jacoco/test/html/index.html
```

**En Windows:**
```bash
start build\reports\jacoco\test\html\index.html
```

---

## 📈 Entender el Reporte

### Pantalla principal

| Métrica | Qué significa |
|---------|---------------|
| **Instructions** | Líneas de código ejecutadas (%) |
| **Branches** | Decisiones if/else cubiertas (%) |
| **Complexity** | Rutas de ejecución cubiertas (%) |
| **Lines** | Líneas cubiertas (%) |
| **Methods** | Métodos cubiertas (%) |

---

### Ejemplo de interpretación

```
GildedRose.java
├─ 85% Instructions → 85% de líneas se ejecutaron en tests
├─ 75% Branches → 75% de if/else se probaron
├─ 90% Lines → 90% de líneas verdes
└─ 100% Methods → Todos los métodos se ejecutaron
```

---

### Colores en el código

Dentro de cada archivo ves el código con colores:

- 🟢 **Verde** → Línea cubierta (ejecutada en tests)
- 🔴 **Rojo** → Línea NO cubierta (sin tests)
- 🟡 **Amarillo** → Rama parcialmente cubierta (solo un camino del if/else)

---

## ✅ Mejorar Cobertura

Para subir la cobertura desde 57%:

1. Abre el reporte (`index.html`)
2. Haz click en **`GildedRose.java`**
3. Busca líneas **rojas** (sin cubrir)
4. Escribe tests que las ejecuten

**Ejemplo: Si ves `initializeItemArray()` en rojo:**

```java
@Test
public void initialize_items_when_no_items_provided() {
    GildedRose gildedRose = new GildedRose();
    // Verifica que los items de ejemplo existan
    assertEquals(6, GildedRose.getItems().size());
}
```

---

## 🎯 Objectivos de Cobertura

- **< 50%** → Insuficiente
- **50-70%** → Aceptable
- **70-85%** → Bueno
- **> 85%** → Excelente

Para una kata, apunta a **mínimo 80%**.

---

## 🔧 Comandos útiles

```bash
# Tests + reporte (lo más común)
.\gradlew.bat test jacocoTestReport

# Solo tests, sin reporte
.\gradlew.bat test

# Reporte sin ejecutar tests nuevamente
.\gradlew.bat jacocoTestReport

# Un test específico
.\gradlew.bat test --tests "*decrease_the_quality*"

# Ver logs detallados
.\gradlew.bat test --info

# Limpiar y ejecutar desde cero
.\gradlew.bat clean test jacocoTestReport
```

---

## 💡 Tips

- **El reporte se sobrescribe** → Cada vez que ejecutas, se regenera
- **Guarda copias** si quieres compararlos → `cp -r build/reports/jacoco/test/html backup_reports`
- **No commitees `/build`** → Añádelo a `.gitignore`
- **Ejecuta tests antes de hacer push** → Así no rompes nada en la rama

---

## 🚨 Troubleshooting

**Error: "Could not find method testCompile()"**
→ Actualiza `build.gradle` con `testImplementation` en lugar de `testCompile`

**Reporte no se genera**
→ Ejecuta: `.\gradlew.bat clean test jacocoTestReport`

**Tests pasan pero reporte muestra 0%**
→ Asegúrate de que el plugin `jacoco` esté en `plugins { }`

---

## 📚 Más información

- [Documentación JaCoCo](https://www.jacoco.org/jacoco/)
- [Gradle Testing](https://docs.gradle.org/current/userguide/testing_java_project.html)
