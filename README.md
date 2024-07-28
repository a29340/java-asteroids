#  Java Asteroids - A Swing 2D Game

Welcome to the Java Asteroids repository! This is a classic 2D space shooter game, inspired by the arcade game Asteroids. It is developed purely using Java Swing for the graphical user interface and game mechanics. No external game engines are used.

## Table of Contents
- [Installation](#installation)
- [Gameplay](#gameplay)
- [Controls](#controls)
- [Features](#features)
- [Development](#development)
- [Contributing](#contributing)
- [License](#license)

## Installation

To run the game on your local machine, follow these steps:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/a29340/java-asteroids.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd java-asteroids
    ```

3. **Compile the project:**

   Ensure you have JDK installed. You can compile the project using the following command:

    ```bash
    javac -d bin src/main/java/com/a29340/*.java
    mkdir bin/images && cp src/main/resources/images/* bin/images/
    ```

4. **Run the game:**

    ```bash
    java -cp bin com.a29340.Main
    ```

## Gameplay

In this Asteroids Clone, you control a spaceship in an asteroid field. The objective is to shoot and destroy asteroids while avoiding collisions with them.

## Controls

- **WASD Keys:** Move the spaceship (W to accelerate up, S to accelerate down, A to accelerate left, D to accelerate right)
- **Mouse:** the spaceship points always where your mouse cursor is
- **Spacebar:** Shoot laser beams

## Features

- **Smooth Controls:** Responsive and smooth movement for an enjoyable gameplay experience.
- **Collision Detection:** Accurate collision detection between the spaceship, bullets, and asteroids.
- **Score Tracking:** Keeps track of your score as you destroy asteroids. (Coming soon)
- **Pause Functionality:** Ability to pause and resume the game. (Coming soon)

## Development

The game is developed using Java Swing for rendering and handling user inputs. The main components of the game include:

- **Spaceship:** The player-controlled ship that can rotate, accelerate, and shoot.
- **Asteroids:** Randomly spawning obstacles that the player must destroy or avoid.
- **Laser Beams:** Projectiles shot by the spaceship to destroy asteroids.
- **Game Loop:** The main loop that updates the game state and renders the graphics.

## Contributing

Contributions are welcome! If you have suggestions or improvements, feel free to open an issue or submit a pull request.

1. **Fork the repository.**
2. **Create a new branch:**

    ```bash
    git checkout -b feature-name
    ```

3. **Make your changes and commit them:**

    ```bash
    git commit -m "Description of your changes"
    ```

4. **Push to the branch:**

    ```bash
    git push origin feature-name
    ```

5. **Open a pull request.**

## License

This project is licensed under the MIT License. See the [LICENSE] file for more information.

---

Enjoy the game! If you have any questions or feedback, feel free to reach out.

Happy gaming! 🚀