# language: es

Característica: Eliminar grupo de gastos

  Regla: El usuario solo puede eliminar grupos que aparecen en la tabla de grupos

    Escenario: Eliminar un grupo existente en la tabla exitosamente
      Dado que el usuario inicia la aplicación
      Y que el usuario visualiza un grupo de gastos llamado "Gasto minimo"
      Cuando el usuario hace clic en el botón de eliminar del grupo "Gasto minimo"
      Entonces el grupo "Gasto minimo" ya no debería aparecer en la lista de grupos