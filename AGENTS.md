# Java Asteroids - Agent Guidelines

## Project Overview

This is a classic Asteroids game clone implemented in pure Java using only native capabilities (Swing for GUI, no external game engines or frameworks). The project demonstrates what can be achieved with standard Java libraries.

**Tech Stack:**
- Language: Java 18
- Build Tool: Maven
- UI Framework: Java Swing (javax.swing)
- No external dependencies

## Architecture

### Package Structure

```
com.a29340/
├── Main.java              # Application entry point
├── MainPanel.java         # Main game panel
├── core/                  # Core abstractions
│   ├── Entity.java        # Base entity class
│   ├── PlayElement.java   # Game element base
│   ├── Scene.java         # Scene management
│   ├── UIElement.java     # UI component base
│   ├── Image.java         # Custom image handling
│   ├── Text.java          # Text rendering
│   ├── ClickableText.java # Interactive text
│   ├── Pixel.java         # Pixel manipulation
│   └── Velocity.java      # Vector velocity
├── elements/              # Game entities
│   ├── Ship.java          # Player spaceship
│   ├── Asteroid.java      # Asteroid obstacles
│   ├── Beam.java          # Laser projectiles
│   ├── Score.java         # Score display
│   ├── HealthBar.java     # Health indicator
│   └── StageService.java  # Stage progression
├── scenes/                # Game states
│   ├── StartMenuScene.java    # Main menu
│   ├── GameplayScene.java     # Active gameplay
│   └── PostScene.java         # End game screen
└── utils/                 # Utilities
    ├── Constants.java     # Game constants
    ├── Configurations.java # Settings
    ├── DebugInfo.java     # Debug overlay
    └── Graphics.java      # Drawing utilities
```

## Key Design Patterns

- **Scene-based architecture**: Different game states (menu, gameplay, end) are separate scenes
- **Entity-component approach**: All game objects extend Entity or PlayElement
- **Custom rendering**: Uses Swing's Graphics2D for all drawing operations
- **Asset management**: PNG images loaded via Image class wrapper

## Build & Run Commands

```bash
# Maven build
mvn clean compile
mvn package
# Run the game
java -cp bin com.a29340.Main
```

## Game Controls

- **WASD**: Ship movement (acceleration in cardinal directions)
- **Mouse**: Ship rotation (points toward cursor)
- **Spacebar**: Fire laser beams

## Important Constraints

1. **No external game libraries** - Only standard Java APIs allowed
2. **Swing-based rendering** - All graphics use Graphics2D
3. **Scene transitions** - Must properly manage scene lifecycle
4. **Asset paths** - Images located in `src/main/resources/images/`

## Common Tasks

### Adding new game elements
1. Extend `Entity.java` or `PlayElement.java`
2. Implement update() and draw() methods
3. Register with appropriate Scene

### Modifying gameplay mechanics
- Ship behavior: `elements/Ship.java`
- Asteroid spawning: `scenes/GameplayScene.java`
- Collision detection: Check Entity collision methods
- Scoring system: `elements/Score.java`

### UI modifications
- Menu screens: `scenes/StartMenuScene.java` or `PostScene.java`
- HUD elements: Extend `UIElement.java`
- Text rendering: Use `Text.java` or `ClickableText.java`

## Testing Considerations

- Game loop runs continuously - ensure update() methods are efficient
- Collision detection should account for object sizes
- Asset loading must handle missing files gracefully
- Scene transitions should clean up resources properly

## Contributing Guidelines

When making changes:
1. Follow existing code style (Java conventions)
2. Keep implementations pure Java (no new dependencies)
3. Update README.md if gameplay mechanics change
4. Test thoroughly before committing
5. Test coverage should only increase, never decrease. You can check the coverage by running `mvn verify` and looking in the target/site folder, where the JaCoCo report will be produced.
