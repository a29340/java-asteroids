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
    ├── Graphics.java      # Drawing utilities
    └── Sound.java         # Audio playback and sound effect generation
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
4. **Asset paths** - Images and sound files located in `src/main/resources/images/`

## Sound Effects System

The game includes synthesized sound effects using Java's standard audio APIs (javax.sound.sampled). No external dependencies are required.

### Event Types
- **ASTEROID_EXPLOSION**: Played when an asteroid is destroyed by a beam or collision
- **SHIP_HIT**: Played when the ship collides with an asteroid  
- **BEAM_FIRE**: Played when a laser shot is fired

### Audio Generation
Sound effects are generated at runtime using `generate_sounds.java`, which creates synthetic WAV files (22050 Hz, mono) with:
- Attack/decay envelopes for natural sound shaping
- Harmonic content for richer audio
- Frequency modulation for realism

### Usage
```java
// Play sound by event type
Sound.playSound(Sound.EventType.ASTEROID_EXPLOSION);

// Play sound by file name
Sound.playSound("custom-sound.wav");

// Toggle mute
Sound.setMuted(true);  // Mute all sounds
Sound.setMuted(false); // Enable sounds

// Check mute state
if (Sound.isMuted()) { ... }
```

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

## Testing

### Test Framework & Dependencies
- **JUnit 5** (Jupiter) - `junit-jupiter-api` + `junit-jupiter-engine`
- **AssertJ** - Fluent assertions library (`assertj-core`)
- **Mockito** - Mocking framework (`mockito-inline`)

### Test Commands
```bash
# Run all tests
mvn test

# Run tests with code coverage report
mvn verify

# View coverage report (generated in target/site/jacoco/)
# Open: target/site/jacoco/index.html
```

### Coverage Requirements
- **Minimum line coverage: 50%** (enforced by JaCoCo check goal)
- Coverage threshold configured in pom.xml via `jacoco-maven-plugin`

### Test File Conventions
- Test files: `**/*Test.java` or `**/*Tests.java`
- Test classes: `*Test` suffix
- Location: `src/test/java/com/a29340/`

### State Management in Tests
Classes with static state (`HealthBar`, `Score`) require explicit reset:
```java
@BeforeEach
void setUp() {
    HealthBar.setHealth(HealthBar.INITIAL_HEALTH);
    Score.resetScore();
}

@AfterEach
void tearDown() {
    HealthBar.setHealth(HealthBar.INITIAL_HEALTH);
    Score.resetScore();
}
```

### Testable Components
- **Unit tests**: `Velocity`, `Entity`, `PlayElement`, `Asteroid`, `Beam`, `HealthBar`, `Score`, `Text`, `ClickableText`, `Configurations`
- **Integration tests** (with mocked Swing): `StageService`, `StartMenuScene`, `GameplayScene`, `PostScene`

### GUI Testing Notes
- Scene classes extend `Scene` which uses `KeyListener` and `MouseInputListener`
- Use Mockito to mock `MouseEvent` for clickable elements
- Avoid testing actual rendering (`update()` methods with `Graphics2D`) - test logic only

## Contributing Guidelines

When making changes:
1. Follow existing code style (Java conventions)
2. Keep implementations pure Java (no new dependencies)
3. Update README.md if gameplay mechanics change
4. Test thoroughly before committing
5. **Test coverage must not decrease** - run `mvn verify` to check coverage
6. **Coverage threshold: 50%** - tests will fail if coverage drops below this
