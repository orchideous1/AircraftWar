package edu.hitsz.aircraft;

import edu.hitsz.bullet.BaseBullet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeroAircraftTest {
    private HeroAircraft heroAircraft;
    @BeforeAll
    static void beforeAll() {
        System.out.println("**--- Executed once before all test methods in this class ---**");
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        heroAircraft = HeroAircraft.getInstance();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        heroAircraft = null;
    }
    @DisplayName("getInstance")
    @org.junit.jupiter.api.Test
    void getInstance() {
        HeroAircraft hero = HeroAircraft.getInstance();
        assertEquals(heroAircraft, hero);
    }
    @DisplayName("shoot")
    @org.junit.jupiter.api.Test
    void shoot() {
        List<BaseBullet> bullets = heroAircraft.shoot();
        int x = heroAircraft.getLocationX();
        int y = heroAircraft.getLocationY() - 2;
        int speedX = 0;
        int speedY = heroAircraft.getSpeedY() - 5;
        for (BaseBullet bullet : bullets) {
            assertEquals(x, bullet.getLocationX());
            assertEquals(y, bullet.getLocationY());
            assertEquals(speedX, bullet.getspeedX());
            assertEquals(speedY, bullet.getSpeedY());
            assertEquals(30, bullet.getPower());
        }
    }
    @DisplayName("uphp")
    @ParameterizedTest
    @ValueSource(ints = {1, 10, 30, 45, 55, 67, 79, 81, 93, 100})
    void uphp(int plus) {
        heroAircraft.decreaseHp(300);
        int current_hp = heroAircraft.getHp();
        int max_hp = 1000;
        heroAircraft.uphp(plus);
        int curr = current_hp + plus;
        if (curr < max_hp){
            assertEquals(curr, heroAircraft.getHp());
        } else {
            assertEquals(max_hp, heroAircraft.getHp());
        }
    }
}