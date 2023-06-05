package view.console.frame;

import model.entity.hero.Hero;
import model.entity.hero.Priestess;
import model.entity.hero.Thief;
import model.entity.hero.Warrior;
import model.util.Utility;
import view.console.pattern.Color;
import view.console.pattern.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Console GUI utilities to display game information.
 *
 * @version 1.0.2
 * @author Kihsomray
 */
public final class HeroSelectionFrame extends ConsoleFrame {

    /** Progress bar width */
    private static final int PROGRESS_BAR_WIDTH = 18;

    /** Width of display that shows the type of hero. */
    private static final int CHARACTER_MENU_HEADER_WIDTH = 33;

    /** With of character statistics display. */
    private static final int CHARACTER_MENU_STATS_WIDTH = 95;

    /** Width of separator between header and stats display. */
    private static final String CHARACTER_MENU_SEGMENT_SEPARATOR = "     ";

    /** Width of character display. */
    private static final int CHARACTER_MENU_WIDTH = 133;

    /** Width of separator between hero characters. */
    private static final String CHARACTER_MENU_HERO_SEPARATOR = "   ";

    /** Used to set the position of characters on the display. */
    private static final char[] CHARACTER_POSITIONING = {'P', 'W', 'T'};

    /** Message to display in tooltip. */
    private static final String MESSAGE = "TIP: W for warrior, T for thief, P for priestess\n" +
            "Type letter twice to confirm.";

    /**
     * Display the hero selection panel.
     *
     * @return Selected hero by user as a character.
     */
    public char display() {

        // Generate menu with no one selected.
        System.out.println(generate('W'));

        // Character that was previously chosen.
        char chosen = ' ';

        // Character that is currently selected.
        char input = 'W';

        // While it hasn't been selected twice.
        while (chosen != input) {

            // Set the current to the previous.
            chosen = input;

            // Get the console input.
            input = new Scanner(System.in)
                    .next()
                    .toUpperCase(Locale.ROOT)
                    .charAt(0);

            // Check the console input & update UI based on it.
            if (input == 'W' || input == 'T' || input == 'P')
                System.out.println(generate(input));

        }

        return chosen;

    }

    /**
     * Generate a character menu based on a selected hero.
     *
     * @param theHero Hero to display.
     * @return A string representation of the menu.
     */
    private String generate(final char theHero) {

        final StringBuilder sb = new StringBuilder();
        final Hero hero = Utility.generateHeroFromChar(theHero, "");

        // Top border of header and stats.
        appendHorizontalBorderHeaderStats(sb);

        // Walls of each segment.
        appendVerticalBorderHeaderStats(sb);

        // Hero statistics.
        final String heroStats =
                Color.WHITE +
                        "    HP: [" +
                        ProgressBar.generate(
                                hero.getHP(),
                                Hero.getAllHeroMaxHP(),
                                PROGRESS_BAR_WIDTH
                        ) +
                        Color.WHITE +
                        "]  DAMAGE: [" +
                        ProgressBar.generate(
                                hero.getMinDamage(),
                                hero.getMaxDamage(),
                                Hero.getAllHeroMaxDamage(),
                                PROGRESS_BAR_WIDTH
                        ) +
                        Color.WHITE +
                        "]  SPEED: [" +
                        ProgressBar.generate(
                                hero.getAttackSpeed(),
                                Hero.getAllHeroMaxAttackSpeed(),
                                PROGRESS_BAR_WIDTH
                        ) +
                        Color.WHITE +
                        "]    ";


        // Hero name & statistics append.
        sb
                .append(PATTERN.generateVerticalBorder(
                        1,
                        CHARACTER_MENU_HEADER_WIDTH,
                        hero.getClass().getSimpleName(),
                        true,
                        true)
                )
                .append(CHARACTER_MENU_SEGMENT_SEPARATOR)
                .append(PATTERN.generateVerticalBorder(
                        1,
                        CHARACTER_MENU_STATS_WIDTH,
                        heroStats,
                        false,
                        false)
                )
                .append('\n');


        // Walls of each segment.
        appendVerticalBorderHeaderStats(sb);

        // Bottom border of header and stats.
        appendHorizontalBorderHeaderStats(sb);

        // ------- HERO DISPLAY -------
        sb
                .append(" ".repeat(CHARACTER_MENU_WIDTH))
                .append('\n');

        // Top border of character selection.
        generateSimpleHorizontal(sb);

        // Wall of character selection.
        generateSimpleVertical(sb);

        // Each hero's skin.
        List<String[]> characters = new ArrayList<>();

        int chosen = 0;
        for (int i = 0; i < CHARACTER_POSITIONING.length; i++) {
            char c = CHARACTER_POSITIONING[i];
            switch (c) {
                case 'W' -> characters.add(Warrior.ASCII_SKIN.split("\n"));
                case 'T' -> characters.add(Thief.ASCII_SKIN.split("\n"));
                case 'P' -> characters.add(Priestess.ASCII_SKIN.split("\n"));
                default -> throw new IllegalArgumentException("Invalid character");
            }
            if (c == hero.getDisplayChar()) chosen = i;

        }

        for (int i = 0; i < 20; i++) {

            // Create te hero line.
            final String heroLine = Color.getColor(0 == chosen  ? '2' : '3') +
                    characters.get(0)[i] +
                    CHARACTER_MENU_HERO_SEPARATOR +
                    Color.getColor(1 == chosen  ? '2' : '3') +
                    characters.get(1)[i] +
                    CHARACTER_MENU_HERO_SEPARATOR +
                    Color.getColor(2 == chosen  ? '2' : '3') +
                    characters.get(2)[i];

            // Append the hero line.
            sb.append(PATTERN.generateVerticalBorder(
                            1,
                            CHARACTER_MENU_WIDTH,
                            heroLine,
                            false,
                            false))
                    .append('\n');

        }

        // Wall of character selection.
        generateSimpleVertical(sb);

        // Bottom border of character selection.
        generateSimpleHorizontal(sb);


        // ------- TIP TOOLBAR -------
        sb
                .append(" ".repeat(CHARACTER_MENU_WIDTH))
                .append('\n');

        // Top border of tip toolbar.
        generateSimpleHorizontal(sb);

        // Wall of tip toolbar.
        generateSimpleVertical(sb);

        for (String line : MESSAGE.split("\n"))
            sb
                    .append(PATTERN.generateVerticalBorder(false, CHARACTER_MENU_WIDTH, line))
                    .append('\n');

        // Wall of tip toolbar.
        generateSimpleVertical(sb);

        // Bottom border of tip toolbar.
        generateSimpleHorizontal(sb);

        // Return completed string
        return sb.toString();

    }

    /**
     * Appends a horizontal border for header and stats.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void appendHorizontalBorderHeaderStats(
            final StringBuilder theStringBuilder
    ) {

        // Append the border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(CHARACTER_MENU_HEADER_WIDTH))
                .append(CHARACTER_MENU_SEGMENT_SEPARATOR)
                .append(PATTERN.generateHorizontalBorder(CHARACTER_MENU_STATS_WIDTH))
                .append('\n');

    }

    /**
     * Appends a vertical border for header and stats.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void appendVerticalBorderHeaderStats(
            final StringBuilder theStringBuilder
    ) {

        // Append the border.
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(
                        true,
                        CHARACTER_MENU_HEADER_WIDTH)
                )
                .append(CHARACTER_MENU_SEGMENT_SEPARATOR)
                .append(PATTERN.generateVerticalBorder(
                        true,
                        CHARACTER_MENU_STATS_WIDTH)
                )
                .append('\n');

    }

    /**
     * Appends a simple horizontal border spanning the total width.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void generateSimpleHorizontal(
            final StringBuilder theStringBuilder
    ) {

        // Append the horizontal border.
        theStringBuilder
                .append(PATTERN.generateHorizontalBorder(CHARACTER_MENU_WIDTH))
                .append('\n');

    }

    /**
     * Appends a simple vertical border spanning the total width.
     *
     * @param theStringBuilder StringBuild to append to.
     */
    private static void generateSimpleVertical(
            final StringBuilder theStringBuilder
    ) {
        theStringBuilder
                .append(PATTERN.generateVerticalBorder(
                        true,
                        CHARACTER_MENU_WIDTH)
                )
                .append('\n');
    }


}
