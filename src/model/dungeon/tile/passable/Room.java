package model.dungeon.tile.passable;

import model.Entity;
import model.Utility;
import model.dungeon.tile.passable.Neighbors;
import model.dungeon.tile.passable.Passable;
import model.sprite.DungeonCharacterFactory;
import model.sprite.enemy.Enemy;
import model.sprite.enemy.Trap;
import model.inventory.RoomInventory;
import model.inventory.item.potion.HealthPotion;
import model.inventory.item.potion.VisionPotion;
import model.sprite.hero.Hero;

import java.util.HashSet;
import java.util.Set;

public class Room implements Passable {

    /** Constant for monster spawn rate. */
    private static final double MONSTER_SPAWN_RATE = 0.5;

    /** Constant for trap spawn rate. */
    private static final double TRAP_SPAWN_RATE = 0.15;

    /** Constant for potion spawn rate. */
    private static final double POTION_SPAWN_RATE = 0.10;

    private final int myX;

    private final int myY;

    private final RoomInventory myInventory;

    private final Set<Enemy> myEnemies;

    private final Neighbors myNeighbors;

    /**
     * Creates a room.
     *
     *
     */
    public Room(final int theX, final int theY) {

        myX = theX;
        myY = theY;
        myInventory = new RoomInventory();
        myEnemies = new HashSet<>();
        myNeighbors = new Neighbors(this);

        // Randomize the spawns.
        randomizeSpawns();
    }

    @Override
    public Neighbors getNeighbors() {
        return myNeighbors;
    }

    @Override
    public void interactWith(Hero theHero) {
        // TODO interaction
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    public RoomInventory getInventory() {
        return myInventory;
    }

    public Set<Entity> getEntities() {
        final Set<Entity> entities = new HashSet<>(myInventory.getInventory());
        entities.addAll(myEnemies);
        return entities;
    }

    /**
     * Randomizes the spawns of the monsters, traps, and potions.
     * Makes sure entrances and exits cannot spawn these secondary
     * items as well.
     */
    private void randomizeSpawns() {

        // Given a chance, generate a monster.
        if (Utility.RANDOM.nextDouble() <= MONSTER_SPAWN_RATE)
            myEnemies.add(DungeonCharacterFactory.generateMonster());

        // Given a chance, generate a trap.
        if (Utility.RANDOM.nextDouble() <= TRAP_SPAWN_RATE)
            myEnemies.add(new Trap());

        // Given a chance, generate a potion.
        if (Utility.RANDOM.nextDouble() <= POTION_SPAWN_RATE)
            myInventory.addItem(new HealthPotion());

        // Given a chance, generate a potion.
        if (Utility.RANDOM.nextDouble() <= POTION_SPAWN_RATE)
            myInventory.addItem(new VisionPotion());

    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        final Set<Entity> entities = getEntities();
        int i = 1;
        for (final Entity entity : entities) {

            sb.append(entity.getColoredDisplay())
                    .append(i++ != 3 ? ' ' : '\n');

        }
        for (int a = i; a <= 6; a++) {
            sb.append(' ').append(a % 3 == 0 ? a == 6 ? "" : '\n' : ' ');
        }
        return sb.toString();
    }

}


