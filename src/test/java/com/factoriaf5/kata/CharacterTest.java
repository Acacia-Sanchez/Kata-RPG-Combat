package com.factoriaf5.kata;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    private Character character;

    @BeforeEach
    public void init() {
        this.character = new Character();
    }

    @Test
    public void addHealToHealthTest() {
        character.setHealth(550);
        int heal = 300;
        int expected = character.getHealth() +heal;
        int result = character.healHealth(heal);
        assertEquals(expected, result);
    }
    @Test
    public void limitHealth1000Test() {
        character.setHealth(900);
        int heal = 300;
        int expected = character.getHealth() +heal;
        if (expected > 1000) {
            expected = 1000;
        }
        int result = character.healHealth(heal);
        assertEquals(expected, result);
    }
    @Test
    public void characterIsNotAliveTest() {
        character.setAlive(false);
        boolean result = character.isAlive();
        assertEquals(false, result);
    }
    @Test
    public void noAddHealToDeathCharacterTest() {
        character.setAlive(false);
        character.setHealth(550);
        int heal = 300;
        int expected = character.getHealth();
        int result = character.healHealth(heal);
        assertEquals(expected, result);
    }
    @Test
    public void addHealToAliveCharacterTest() {
        character.setAlive(true);
        character.setHealth(550);
        int heal = 300;
        int expected = character.getHealth() +heal;
        int result = character.healHealth(heal);
        assertEquals(expected, result);
    }
    /// testeo de getters y setters ////
    @Test
    public void getYsetHealthTest() {
        character.setHealth(155);
        int result = character.getHealth();
        assertEquals(155, result);
    }
    @Test
    public void getYsetLevelTest() {
        character.setLevel(3);
        int result = character.getLevel();
        assertEquals(3, result);
    }
    @Test
    public void getYsetAliveTest() {
        character.setAlive(false);
        boolean result = character.isAlive();
        assertEquals(false, result);
    }
    @Test
    public void getYsetMinDistanceTest() {
        character.setMinDistance(1);
        int result = character.getMinDistance();
        assertEquals(1, result);
    }
    @Test
    public void getYsetMaxDistanceTest() {
        character.setMaxDistance(15);
        int result = character.getMaxDistance();
        assertEquals(15, result);
    }   
    @Test
    public void atributoHealthTest() {
        int inicializado = 1_000;
        int result = character.getHealth();
        assertEquals(inicializado, result);
    }
    @Test
    public void atributoLevelTest() {
        int inicializado = 1;
        int result = character.getLevel();
        assertEquals(inicializado, result);
    }
    @Test
    public void atributoAliveTest() {
        boolean inicializado = true;
        boolean result = character.isAlive();
        assertEquals(inicializado, result);
    }    
    @Test
    public void atributoMinDistanceTest() {
        int inicializado = 2;
        int result = character.getMinDistance();
        assertEquals(inicializado, result);
    } 
    @Test
    public void atributoMaxDistanceTest() {
        int inicializado = 20;
        int result = character.getMaxDistance();
        assertEquals(inicializado, result);
    } 
    @Test
    public void constructorTest(){
        assertEquals(1_000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertEquals(true, character.isAlive());
        assertEquals(2, character.getMinDistance());
        assertEquals(20, character.getMaxDistance());
    }
    @Test
    public void healthLimit0Test(){
        character.setHealth(-200);
        Character theOther = new Character();  // instancio el Character theOther
        int damage = 100;
        // int expected = character.getHealth() +heal;
        int result = character.damageHealth(theOther, damage);
        assertEquals(0, result);
    }
    @Test
    public void characterThisIsAliveTest(){
        Character theOther = new Character();
        character.setHealth(-200);  // de este modo estaría muerto
        int damage = 100;
        character.damageHealth(theOther, damage);
        assertFalse(character.isAlive());
    }
    @Test
    public void theOtherIsAliveTest(){
        Character theOther = new Character();
        theOther.setHealth(-200);  // de este modo estaría muerto
        int damage = 100;
        theOther.damageHealth(theOther, damage);
        assertFalse(theOther.isAlive());
    }

}