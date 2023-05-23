package model.hero;

import model.monster.Monster;

/**
 * A type of Hero that can be chosen when playing.
 *
 * Special skill: Surprise Attack - Two attacks (40% success rate)
 *                                  One attack  (40% success rate)
 *                                  No attacks  (20% success rate)
 *
 * @version 1.0.0
 * @author Kihsomray
 */
public class Thief extends Hero {

    //        FIELDS        //

    // Ability success chance.
    private static final double ABILITY_SUCCESS_CHANCE = 0.4;

    // Ability failure chance.
    private static final double ABILITY_FAILURE_CHANCE = 0.2;


    //        CONSTRUCTORS        //

    /**
     * Creates an instance of a Thief.
     */
    public Thief() {

        super(
                "Thief",
                125,
                35,
                60,
                4,
                0.8,
                0.2,
                5,
                15
        );

    }


    //        MUTATORS        //

    /**
     * Attacks a monster.
     *
     * @param theMonster Monster to attack.
     * @param theUseAbility Should the hero use Surprise Attack ability.
     */
    @Override
    public void attackMonster(
            final Monster theMonster,
            final boolean theUseAbility
    ) {

        // If use ability
        if (theUseAbility) {

            // If succeeds in double attack
            if (RANDOM.nextDouble() <= ABILITY_SUCCESS_CHANCE) {

                // Attack the monster
                attack(theMonster);

            // Elif use ability and fails an attack.
            } else if (RANDOM.nextDouble()
                    <= ABILITY_FAILURE_CHANCE /
                    (1.0 - ABILITY_SUCCESS_CHANCE)
            ) {

                // Return, don't proceed.
                return;

            }
        }

        // Perform attack.
        attack(theMonster);

    }

}
