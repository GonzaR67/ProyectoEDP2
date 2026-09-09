# ProyectoEDP2

Proyecto Integrador Evolutivo (ProyIE) – AutoMove

Entrega Parcial N° 1: Desarrollo de la arquitectura inicial del sistema aplicando Programación Orientada a Objetos,
clases abstractas e interfaces

# Fecha de presentación: Domingo 20 de septiembre, a las 23:59hs.
Se Pide:

• Analizar el problema e identificar las clases, responsabilidades y relaciones necesarias.

• Elaborar el diagrama UML de clases de la arquitectura inicial, indicando atributos principales, operaciones,
visibilidad, herencia, implementación de interfaces y demás relaciones.

• Implementar en Java la arquitectura diseñada.

• Implementar las funcionalidades requeridas y realizar pruebas que permitan verificar el comportamiento de los
distintos tipos de vehículos.

• Presentar una breve justificación de las decisiones de diseño adoptadas, especialmente respecto de la clase
abstracta y las interfaces utilizadas.

# Producto de Entrega:

 • Diagrama UML de clases.
 
• Diagramas de flujo de todas las funcionalidades del sistema.

• Proyecto Java funcional en formato .zip.

• Casos de prueba básicos.

• Breve documento con las decisiones de diseño.

Condiciones de Presentación:

• Diagramas de flujo: Presentar de forma manuscrita, utilizando papel y lápiz/lapicera, siempre que sean
claramente legibles y presentados de manera ordenada, o mediante algún software/herramienta de diseño pero
impresos.

• Diagramas UML de clases, Casos de pruebas y Documento Resumen: presentarse en formato digital, utilizando el
formato o herramienta que se considere adecuada para su elaboración, procurando que la información sea clara,
completa y legible. Que deberá subirse al espacio de entrega disponible en el SED.

• Código de resolución: el código correspondiente a los enunciados solicitados deberá ser entregado en un único
archivo con formato .zip, que deberá subirse al espacio de entrega disponible en el SED.
En todos los casos, los archivos y/o producciones presentadas deberán ser claramente legibles, estar correctamente
identificados y corresponder a las actividades solicitadas.

# Situación Problemática:

Una empresa tecnológica administra una red de movilidad autónoma denominada AutoMove, integrada por drones
aéreos, robots terrestres y vehículos eléctricos autónomos. Estos equipos son utilizados para realizar misiones de
traslado de paquetes, insumos y equipamiento entre distintos puntos de una ciudad inteligente.
En esta primera etapa se deberá desarrollar la arquitectura inicial de la aplicación, modelando el dominio mediante
Programación Orientada a Objetos. El diseño deberá permitir incorporar nuevas funcionalidades en las etapas
posteriores sin reemplazar la solución construida.

De cada vehículo se deberá registrar, como mínimo:

• código identificador, modelo, nivel de batería, capacidad máxima de carga, kilómetros recorridos y estado
operativo;

# Se deberán contemplar al menos los siguientes tipos con sus datos específicos:

• Dron aéreo: autonomía de vuelo y altura máxima.

• Robot terrestre: tipo de terreno permitido y velocidad máxima.

• Vehículo eléctrico autónomo: autonomía en kilómetros y tiempo estimado de recarga.

De cada misión se registrará, como mínimo:

• código de misión, origen, destino, distancia estimada, peso de la carga, prioridad y estado.
# Funcionalidades Requeridas:
1. Registrar vehículos de los diferentes tipos previstos.
2. Registrar misiones de traslado.
3. Consultar y mostrar la información de los vehículos registrados.
4. Determinar si un vehículo se encuentra en condiciones de realizar una misión, considerando su estado, batería,
capacidad de carga y restricciones propias del tipo de vehículo.
5. Asignar una misión a un vehículo disponible y actualizar los estados correspondientes.
   
# Requisitos de Diseño:

La solución deberá evidenciar una correcta aplicación de abstracción, encapsulamiento, herencia y especialización.

• Definir una clase abstracta VehiculoAutonomo que reúna el estado y comportamiento común de los distintos
vehículos.

• Derivar de ella las clases concretas Dron, RobotTerrestre y VehiculoElectrico.

• Definir al menos una interfaz que represente una capacidad transversal del dominio, por ejemplo Recargable,
Transportable o Mantenible, y justificar qué clases la implementan.

• Incluir las clases necesarias para representar Mision y la gestión general de la aplicación.

• Mantener separadas las responsabilidades de las entidades del dominio y de la interacción con el usuario.
