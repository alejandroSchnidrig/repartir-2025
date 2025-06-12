# language: es

Característica: Eliminar grupo de gastos

  Regla: El usuario solo puede eliminar grupos que aparecen en la tabla de grupos

    Escenario: Eliminar un grupo existente en la tabla exitosamente
      Dado que el usuario inicia la aplicación
      Y el usuario crea un grupo de gastos llamado "Gasto Mayo"
      Y el usuario crea un grupo de gastos llamado "Gasto Julio"
      Cuando el usuario hace click en el botón de eliminar del grupo "Gasto Julio"
      Entonces el grupo "Gasto minimo" ya no debería aparecer en la lista de grupos