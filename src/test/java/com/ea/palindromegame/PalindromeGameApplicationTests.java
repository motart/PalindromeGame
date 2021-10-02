package com.ea.palindromegame;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PalindromeGameApplicationTests {

    @Test
    void testPalindromeUtils() {
        Map<String, Integer> data = new HashMap<>();
        data.put("",0);
        data.put(null,0);
        data.put("e",0);
        data.put("e'e",1);
        data.put("+__+;''",0);
        data.put("+__+e''",0);
        data.put("erre",2);
        data.put("ertre",2);
        data.put("ertre              ",2);
        data.put("Madam, I'm Adam",5);
        data.put("Poor Dan is in a droop",8);
        data.put("Do geese see God?",6);
        data.put("16461",2);
        data.put("-16461",2);

        PalindromeUtil fixture = new PalindromeUtil();
        for (Map.Entry<String, Integer> entry: data.entrySet()) {
            assertEquals(entry.getValue(), fixture.score(entry.getKey()));
        }
    }


    @Test
    void testEndpoints() {
        PalindromeGameController fixture = new PalindromeGameController();
        fixture.submit("rr","user1"); // 1
        fixture.submit("rr","user1"); // 1  => user1 -> 2

        fixture.submit("r","user2"); // 0
        fixture.submit("rr","user2"); // 1 => user2 -> 1

        fixture.submit("","user3"); // 0
        fixture.submit("rrrrrrrr","user3"); // 4 => user3 -> 4

        fixture.submit("rrrrr","user4"); // 2
        fixture.submit("rrrrrr","user4"); // 3 => user4 -> 5

        fixture.submit("rr","user5"); // 1
        fixture.submit("rrrrrrrrrrrr","user5"); // 6 => user5 -> 7

        fixture.submit("rr","user6"); // 1
        fixture.submit("rrrrrrrrrrrrrrrr","user6"); // 8 => user6 -> 9

        fixture.submit("rrrrrrrrrrrrrr","user7"); // 7
        fixture.submit("rrrrrrrrr","user7"); // 4 => user7 -> 11

        fixture.submit("rrr","user8"); // 1
        fixture.submit("rrrrr","user8"); // 2 => user8 -> 3

        fixture.submit("rrrrr","user9"); // 2
        fixture.submit("rrrrrr","user9"); // 3 => user9 -> 5

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            fixture.getUserScore("user0").get();
        });
        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

        assertEquals("user1=2", fixture.getUserScore("user1").get().toString());
        assertEquals("user2=1", fixture.getUserScore("user2").get().toString());
        assertEquals("user3=4", fixture.getUserScore("user3").get().toString());
        assertEquals("user4=5", fixture.getUserScore("user4").get().toString());
        assertEquals("user5=7", fixture.getUserScore("user5").get().toString());
        assertEquals("user6=9", fixture.getUserScore("user6").get().toString());
        assertEquals("user7=11", fixture.getUserScore("user7").get().toString());
        assertEquals("user8=3", fixture.getUserScore("user8").get().toString());
        assertEquals("user9=5", fixture.getUserScore("user9").get().toString());
    }
}
