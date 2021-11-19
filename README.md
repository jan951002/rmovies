# rmovies

# Librerías
  - Retrofit 2.7.1
  - Coroutines 1.5.0
  - Koin 3.0.2
  - GSON 2.7.1
  - Room 2.3.0
  - LyfeCycle 2.4.0
  - Navigation component 2.3.5
  - Kotlin 1.5.20
  - Gradle 4.2.2
  - Databinding 3.1.4
  
# Configuración
  Se ha utilizado una combinación de Clean architecture para la ditribución del código fuente en general, Android architecture components y MVVM como arquitectura de presentación; se implementó Hilt con el fin de gestionar la inyección de dependencias de los módulos: data, framework, use cases. En cuanto al manejo de API se utilizó Retrofit y para la gestión y manejo de base de datos se trabaja con la librería Room. Para la navegación de pantallas se utiliza la librería Navigation component. Finalmente, la aplicación fue escrita 100% en kotlin debido a que actualmente es el lenguaje oficial para Android.
  
# Capas utilizadas
  - Dominio: Con el fin de establecer la lógica de negocio.
  - Datos: Encargado de definir las abstracciones para acceder a las fuentes de datos. Dentro de esta capa se implementa el patrón repositorio.
  - Framework: Implementa librerías externas y define el comportamiento de las fuentes de datos.
  - Presentación: Interactúa con la interfaz de usuario.
  - Casos de uso: Gestiona las acciones a las cuales el usuario posee acceso.
  
# API's
  https://api.themoviedb.org/3/
