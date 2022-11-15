import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FootballPlayerGeneratorTests {
    private FootballPlayer footballPlayer;


    @Test
    void randomPlayerByPosition_GOALKEEPER_EqualsGOALKEEPER(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.GOALKEEPER);
        assertEquals(footballPlayer.getPosition(), FootballPlayerPosition.GOALKEEPER,"FootballPlayer positions are not equal");
    }

    @Test
    void randomPlayerByPosition_ATTACKER_EqualsATTACKER(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.ATTACKER);
        assertEquals(footballPlayer.getPosition(), FootballPlayerPosition.ATTACKER,"FootballPlayer positions are not equal");
    }

    @Test
    void randomPlayerByPosition_DEFENDER_EqualsDEFENDER(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.DEFENDER);
        assertEquals(footballPlayer.getPosition(), FootballPlayerPosition.DEFENDER,"FootballPlayer positions are not equal");
    }

    @Test
    void randomPlayerByPosition_MIDFIELDER_EqualsMIDFIELDER(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.MIDFIELDER);
        assertEquals(footballPlayer.getPosition(), FootballPlayerPosition.MIDFIELDER,"FootballPlayer positions are not equal");
    }

    @Test
    void randomPlayerByPosition_nullValue_ThrowsException(){
        assertThrows(NullPointerException.class, ()-> FootballPlayerGenerator.randomPlayerByPosition(null),"Created football player with null position");
    }

    @Test
    void randomPlayerByPosition_GOALKEEPER_NotNull(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.GOALKEEPER);
        assertNotNull(footballPlayer,"FootballPlayer generated is null");
    }

    @Test
    void randomPlayerByPosition_DEFENDER_NotNull(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.DEFENDER);
        assertNotNull(footballPlayer,"FootballPlayer generated is null");
    }

    @Test
    void randomPlayerByPosition_ATTACKER_NotNull(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.ATTACKER);
        assertNotNull(footballPlayer,"FootballPlayer generated is null");
    }

    @Test
    void randomPlayerByPosition_MIDFIELDER_NotNull(){
        footballPlayer = FootballPlayerGenerator.randomPlayerByPosition(FootballPlayerPosition.MIDFIELDER);
        assertNotNull(footballPlayer,"FootballPlayer generated is null");
    }

    @Test
    void randomPlayer_returnedValue_NotNull(){
        footballPlayer = FootballPlayerGenerator.randomPlayer();
        assertNotNull(footballPlayer,"FootballPlayer generated is null");
    }

    @Test
    void randomJerseyNumber_returnedValue_BetweenOneAndNinetyNine(){
        int num = FootballPlayerGenerator.randomPlayer().getJerseyNumber();
        assertTrue(num>=1 && num<100, "Football Player jersey number is not in range 1...99");
    }

    @Test
    void randomPlayerPosition_returnedValue_validPlayerPosition(){
        FootballPlayerPosition position = FootballPlayerGenerator.randomPlayer().getPosition();
        assertTrue(Arrays.asList(FootballPlayerPosition.values()).contains(position), "Football player has an invalid position");
    }

    @Test
    void randomPlayerGrade_returnedValue_BetweenZeroAndTen(){
        float num = FootballPlayerGenerator.randomPlayer().getGrade();
        assertTrue(num>=0 && num<=10, "Football Player grade is not between 0 and 10");
    }




}
