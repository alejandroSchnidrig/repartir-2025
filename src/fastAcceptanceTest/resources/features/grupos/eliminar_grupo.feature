# language: es

Característica: Eliminar grupo de gastos

  Regla: Solo se pueden eliminar grupos que existen en el sistema

    Escenario: Eliminar un grupo existente exitosamente
      Dado que existe un grupo con id 42
      Cuando el usuario elimina el grupo con id 42
      Entonces el grupo con id 42 ya no debería aparecer en la lista de grupos

    Escenario: Intentar eliminar un grupo que no existe
      Cuando se intenta eliminar el grupo con id 9999
      Entonces el sistema debería informar que el grupo no existe