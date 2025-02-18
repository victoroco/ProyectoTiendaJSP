ProyectoTiendaJSP

Este proyecto es una aplicación web de una tienda en línea desarrollada como parte de mi formación en Serbatic. La aplicación está construida utilizando JSP y Servlets en el backend, con una interfaz frontend implementada con HTML, CSS y JavaScript. El objetivo principal es gestionar productos, usuarios y pedidos de manera eficiente.
Características

  Gestión de productos: Permite añadir, editar y eliminar productos del catálogo.
  Gestión de usuarios: Registro y autenticación de usuarios, con roles de administrador y cliente.
  Gestión de pedidos: Los clientes pueden realizar pedidos y ver su historial de compras.
  Carrito de compras: Funcionalidad para agregar productos al carrito y proceder al pago.

Tecnologías utilizadas

    Backend: Java con JSP y Servlets.
    Frontend: HTML5, CSS3, JavaScript y jQuery.
    Base de datos: MySQL para el almacenamiento de datos.
    Control de versiones: Git y GitHub para la gestión del código fuente.

Instalación y ejecución

  Clonar el repositorio:

    git clone https://github.com/victoroco/ProyectoTiendaJSP.git

  Importar el proyecto en tu IDE preferido: Recomendado Eclipse o IntelliJ IDEA.

  Configurar la base de datos:
      Crear una base de datos en MySQL llamada tienda_db.
      Importar el esquema y los datos desde el archivo tienda_db.sql ubicado en la carpeta sql del proyecto.

  Configurar el archivo de propiedades:
      En el archivo src/main/resources/config.properties, actualizar las credenciales de acceso a la base de datos según tu configuración local.

  Desplegar en un servidor:
      Utilizar Apache Tomcat o cualquier otro servidor compatible con Servlets.
      Desplegar el archivo WAR generado o configurar el servidor para apuntar al directorio del proyecto.

  Acceder a la aplicación:
       Abrir un navegador web y navegar a 
        
        http://localhost:8080/ProyectoTiendaJSP.

Contribuciones

  Las contribuciones son bienvenidas. Si deseas colaborar, por favor, abre un issue o envía un pull request con tus sugerencias o mejoras.
  
Licencia

  Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
  

Este README proporciona una visión general de las funcionalidades y la configuración básica del proyecto. Para más detalles, revisa la documentación y los comentarios en el código fuente.

Recuerda personalizar y ampliar este README según las especificaciones y características particulares de tu proyecto.
