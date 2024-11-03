package com.factoriaf5.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    private Character character;
    private Character theOther;

    @BeforeEach
    public void init() {
        character = new Character();
        theOther = new Character();
    }

    /////// TESTEANDO getters y setters /////////

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

    /////// TESTEANDO QUE SE INICIALICEN CORRECTAMENTE LOS ATRIBUTOS /////////

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

    /////// TESTEANDO CONSTRUCTOR /////////

    @Test
    public void constructorTestConParametros() {
        Character character = new Character(1_000, 1, true, 2, 20);
        assertEquals(1_000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertEquals(true, character.isAlive());
        assertEquals(2, character.getMinDistance());
        assertEquals(20, character.getMaxDistance());
    }

    @Test
    public void constructorTestVacio() {
        assertEquals(1_000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertEquals(true, character.isAlive());
        assertEquals(2, character.getMinDistance());
        assertEquals(20, character.getMaxDistance());
    }

    /////// TESTEANDO METODO healHealth() /////////

    @Test
    /// aquí chequeo ambos objetos, para ver cómo hacerlo, aunque no es necesario
    public void addHealTest() {
        character.setHealth(550);
        theOther.setHealth(550);
        int heal = 300;
        int resultCharacter = character.healHealth(heal);
        int resultTheOther = theOther.healHealth(heal);
        assertEquals(850, resultCharacter);
        assertEquals(850, resultTheOther);
    }

    @Test
    public void noAddHealToOtherTest() {
        character.setHealth(550);
        theOther.setHealth(550);
        int heal = 300;
        int resultCharacter = character.healHealth(heal);
        int resultTheOther = theOther.getHealth();
        assertEquals(850, resultCharacter);
        assertEquals(550, resultTheOther);
    }

    @Test
    public void noAddHealToCharacterTest() {
        character.setHealth(550);
        theOther.setHealth(550);
        int heal = 300;
        int resultCharacter = character.getHealth();
        int resultTheOther = theOther.healHealth(heal);
        assertEquals(550, resultCharacter);
        assertEquals(850, resultTheOther);
    }

    @Test
    public void limitHealth1000Test() {
        character.setHealth(1_200);
        int heal = 300;
        int result = character.healHealth(heal);
        assertEquals(1_000, result);
    }

    @Test
    public void noAddHealToDeathTest() {
        character.setAlive(false);
        character.setHealth(550);
        int heal = 300;
        int expected = character.getHealth();
        int result = character.healHealth(heal);
        assertEquals(expected, result);
    }

    @Test
    /// el expected es mejor darlo como el test anterior o como lo hice en este ???
    public void addHealToAliveTest() {
        character.setAlive(true);
        character.setHealth(550);
        int heal = 300;
        int result = character.healHealth(heal);
        assertEquals(850, result);
    }

    /////// TESTEANDO METODO damageHealth() /////////

    @Test
    public void healthLimit0Test() {
        character.setHealth(-200); // de este modo estaría muerto
        int damage = 100;
        int result = character.damageHealth(theOther, damage);
        assertEquals(0, result);
    }

    @Test
    /// test si está vivo, aunque ya está probado en:
    /// noAddHealToDeathCharacterTest()
    public void characterThisIsAliveTest() {
        character.setHealth(-200); // de este modo estaría muerto
        int damage = 100;
        character.damageHealth(theOther, damage);
        assertFalse(character.isAlive());
    }

    @Test
    public void theOtherIsAliveTest() {
        theOther.setHealth(5);
        int damage = 100;
        theOther.damageHealth(character, damage);
        // assertFalse(theOther.isAlive());
    }

    @Test
    public void testDamageReduction() {
        theOther.setHealth(500);
        theOther.setAlive(true);
        theOther.setMinDistance(2);
        theOther.setMaxDistance(20);
        int charLevel = character.getLevel();
        theOther.setLevel(charLevel + 5);
        int damage = 100;
        character.damageHealth(theOther, damage);
        assertEquals(450, theOther.getHealth());
    }

    @Test
    public void testNoDamage() {
        character.setHealth(500);
        character.setAlive(true);
        character.setMinDistance(1);
        character.setMaxDistance(19);
        int theOtherLevel = theOther.getLevel();
        character.setLevel(theOtherLevel + 5);
        int damage = 100;
        theOther.damageHealth(character, damage);
        assertEquals(500, character.getHealth());
    }

    @Test
    public void testDamageIncrease() {
        theOther.setHealth(500);
        theOther.setAlive(true);
        theOther.setMinDistance(2);
        theOther.setMaxDistance(20);
        int charLevel = character.getLevel();
        theOther.setLevel(charLevel - 5);
        int damage = 100;
        character.damageHealth(theOther, damage);
        assertEquals(350, theOther.getHealth());
    }

    @Test
    public void testNoDamageToSelf() {
        character.setHealth(500);
        character.setAlive(true);
        int damage = 100;
        character.damageHealth(character, damage);
        assertEquals(500, character.getHealth());
    }

    @Test
    public void testNoDamageToDeadCharacter() {
        theOther.setHealth(500);
        theOther.setAlive(false);
        int damage = 100;
        character.damageHealth(theOther, damage);
        assertEquals(500, theOther.getHealth());
    }

    @Test
    public void testDamageNormal() {
        theOther.setHealth(500);
        theOther.setAlive(true);
        theOther.setMinDistance(2);
        theOther.setMaxDistance(20);
        int charLevel = character.getLevel();
        theOther.setLevel(charLevel + 2);
        int damage = 100;
        character.damageHealth(theOther, damage);
        assertEquals(400, theOther.getHealth());
    }

}