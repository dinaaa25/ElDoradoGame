package org.utwente.game.view.gui;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Dimension;

import javax.swing.JPanel;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GameFrameTest {

    /**
     * Method under test: {@link GameFrame#GameFrame(JPanel)}
     */
    @Test
    void testNewGameFrame() {
        try {
            // Arrange
            JPanel gamePanel = new JPanel();

            // Act
            GameFrame gameFrame = new GameFrame(gamePanel);

            // Assert
            assertNotNull(gameFrame);
            assertSame(gamePanel, gameFrame.getContentPane().getComponent(0));
            assertEquals(JFrame.EXIT_ON_CLOSE, gameFrame.getDefaultCloseOperation());
            assertEquals(new Dimension(1400, 800), gameFrame.getMinimumSize());
            assertTrue(gameFrame.getTitle().contains("El Dorado"));
        } catch (java.awt.HeadlessException e) {
            // Test cannot be executed in a headless environment, such as a CI pipeline
            System.out.println("Headless environment, test skipped: " + e.getMessage());
        }
    }

    /**
     * Method under test: {@link GameFrame#display()}
     */
    @Test
    void testDisplay() {
        try {
            // Arrange
            JPanel gamePanel = new JPanel();
            GameFrame gameFrame = new GameFrame(gamePanel);

            // Act
            gameFrame.display();

            // Assert
            assertTrue(gameFrame.isVisible());
        } catch (java.awt.HeadlessException e) {
            // Test cannot be executed in a headless environment, such as a CI pipeline
            System.out.println("Headless environment, test skipped: " + e.getMessage());
        }
    }

    @Test
    void testGameFrameConstruction() {
        try {
            // Arrange
            JPanel gamePanel = new JPanel();

            // Act
            GameFrame gameFrame = new GameFrame(gamePanel);

            // Assert
            assertNotNull(gameFrame);
            assertSame(gamePanel, gameFrame.getContentPane().getComponent(0));
            assertEquals(JFrame.EXIT_ON_CLOSE, gameFrame.getDefaultCloseOperation());
            assertEquals(new Dimension(1400, 800), gameFrame.getMinimumSize());
            assertTrue(gameFrame.getTitle().contains("El Dorado"));
        } catch (java.awt.HeadlessException e) {
            // Test cannot be executed in a headless environment, such as a CI pipeline
            System.out.println("Headless environment, test skipped: " + e.getMessage());
        }
    }

    @Test
    void testGameFrameDisplay() {
        try {
            // Arrange
            JPanel gamePanel = new JPanel();
            GameFrame gameFrame = new GameFrame(gamePanel);

            // Act
            gameFrame.display();

            // Assert
            assertTrue(gameFrame.isVisible());
        } catch (java.awt.HeadlessException e) {
            // Test cannot be executed in a headless environment, such as a CI pipeline
            System.out.println("Headless environment, test skipped: " + e.getMessage());
        }
    }

    @Test
    void testGameFrameLocationRelativeToNull() {
        try {
            // Arrange
            JPanel gamePanel = new JPanel();
            GameFrame gameFrame = new GameFrame(gamePanel);

            // Act
            gameFrame.setLocationRelativeTo(null);

            // Assert
            assertNotNull(gameFrame.getLocation());
        } catch (java.awt.HeadlessException e) {
            // Test cannot be executed in a headless environment, such as a CI pipeline
            System.out.println("Headless environment, test skipped: " + e.getMessage());
        }
    }
}