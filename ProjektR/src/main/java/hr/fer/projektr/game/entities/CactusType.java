package hr.fer.projektr.game.entities;

import hr.fer.projektr.game.GameState;

/**
 * This Enum holds all cactus types.
 * Each cactus type is defined by its height and width.
 */
public enum CactusType {
    /**
     * The most standard cactus, declared by the ISO
     */
    STANDARD(GameState.STANDARD_CACTUS_WIDTH, GameState.STANDARD_CACTUS_HEIGHT);

    final double width;
    final double height;

    CactusType(double width, double height) {
        this.width = width;
        this.height = height;
    }
}
