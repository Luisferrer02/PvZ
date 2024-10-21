# PvZ Project

## Descripción
Este proyecto es una implementación del juego **Plants vs. Zombies**.

## Instalación
1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu_usuario/PvZ.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd PvZ
    ```
3. Abre el proyecto en Eclipse.

## Uso
1. Ejecuta el proyecto desde Eclipse.
2. Selecciona el nivel de dificultad.
3. Coloca las plantas estratégicamente para defender tu casa.

## Juego

Esta version de Plantas VS Zombies se juega con dos parametros, la dificultad (EASY, HARD, INSANE) y la semilla (seed). Los argumentos se introducen antes de la ejecucion. Por ejemplo: EASY 25. La semilla es el numero que determina el RNG del juego, y la dificultad tiene que ver con la frecuencia y numero de enemigos que se va a encontrar el jugador. Si no se proporciona una semilla el juego proporciona una aleatoria, y si no se proporciona un nivel de dificultad el nivel se pone a EASY por defecto. 

## Main
En el main se crean las variables game y controller, llamando a los constructores adecuados, y se llama a controller.run(), que inicia el bucle de juego.

## GUI

La interfaz grafica esta controlada por el GamePrinter y se compone de:

Numero de ciclos, Sun coins, Zombies restantes, Soles generados, soles atrapados, puntuacion
Y el tablero

Los gameobjects (Zombies, Soles, Plantas) Aparecen en el tablero con su abreviacion (Z, Bz, P...) y sus puntos de salud entre corchetes.

## Controller

## Game Objects

### GameObject
Es la clase padre que implementa GameItem y de la cual heredan todos los otros objetos del juego.

### Plant
Es la clase generica para las plantas. Cada planta tiene atributos y habilidades distintas
#### CherryBomb, Peashooter, Sunflower, Wallnut

### Sun
Es la clase que representa los soles que hacen posible poner plantas.

### Zombie
Es la clase generica para los zombies. Cada tipo de zombie tiene atributos y habilidades distintas.
#### BucketHead, CommonZombie, ExplosiveZombie, Sporty

### PlantFactory y ZombieFactory
Son las clases que aplican el patron factory para crear los distintos tipos de zombie y plantas.

## Acciones
Son las clases que implementan las acciones (por ejemplo, ExplosionAction) las cuales pueden aplicar a tanto zombies como plantas.

## Lógica

### Game
Es la clase principal que maneja la lógica del juego. Se encarga de inicializar el juego, gestionar el bucle principal y coordinar las interacciones entre los diferentes componentes del juego.

### GameItem
Es la interfaz que representa un objeto genérico dentro del juego. Puede ser cualquier cosa que tenga una posición y pueda interactuar con otros objetos del juego.

### GameObjectContainer
Es un contenedor para los objetos del juego. Se encarga de almacenar y gestionar múltiples `GameItem` y facilita su acceso y manipulación.

### GameStatus
Mantiene el estado actual del juego, como la puntuación, el nivel y otras estadísticas relevantes. Es esencial para guardar y recuperar el progreso del jugador.

### GameWorld
Representa el mundo del juego. Define el entorno en el que se desarrolla el juego y contiene todos los objetos y entidades que existen en ese mundo.

### SunsManager
Gestiona los recursos solares en el juego. Es responsable de generar, recolectar y contabilizar los puntos de sol que los jugadores pueden usar para realizar acciones.

### ZombieManager
Se encarga de gestionar los zombis en el juego. Controla la aparición, el movimiento y las interacciones de los zombis con otros objetos del juego.

## Commands

El juego implementa el patrón de diseño software "Command", y por tanto el controlador del juego pide por consola un comando cada ciclo. Los comandos añaden plantas al juego, capturan soles que aparecen por el tablero (esta accion no gasta un ciclo de turno), lista las plantas o zombies, o resetea el juego por ejemplo. Para pasar un ciclo sin hacer nada el jugador pulsa la tecla "Enter" del teclado sin escribir nada. El comando "Help" lista los comandos y como se usan.:

[a]dd <plant> <col> <row>: añadir una planta en la posición (columna, fila)
[l]ist: imprimir la lista de plantas disponibles
[r]eset [<level> <seed>]: comenzar un nuevo juego (si se proporcionan tanto el nivel como la semilla, se utilizarán para inicializar el juego)
[h]elp: imprimir este mensaje de ayuda
[e]xit: terminar el programa
[n]one | "": saltar la acción del usuario para este ciclo
[l]ist[Z]ombies: imprimir la lista de zombis disponibles
[a]dd[Z]ombie <idx> <col> <row>: añadir un zombi en la posición (columna, fila)
[C]heat[P]lant <plant> <col> <row>: añadir una planta en la posición (columna, fila) sin consumir soles
[C]atch <col> <row>: atrapar un sol, si es posible, en la posición (columna, fila)
Rec[o]rd: mostrar el récord del nivel actual

## Record
La clase record se encarga de gestionar el fichero record.txt, donde se muestran las puntuaciones mas altas del jugador en los distintos niveles del juego.

## Exceptions
Son las clases que atrapan las distintas excepciones que pueden darse, ocmo por ejemlo un comando incorrecto o una posicion no valida.