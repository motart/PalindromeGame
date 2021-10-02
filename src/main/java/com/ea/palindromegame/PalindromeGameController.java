package com.ea.palindromegame;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@RestController
public class PalindromeGameController {

    @Value("${hallOfFameDepth}")
    private int hallOfFameDepth;

    // Local mem storage
    private ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();

    // Record a new palindrome submission
    @PostMapping("/{user}/{word}")
    public Optional<Map.Entry<String, Integer>> submit(@PathVariable String word, @PathVariable String user) {
        scores.computeIfPresent(user, (k,v) -> v + PalindromeUtil.score(word));
        scores.putIfAbsent(user, PalindromeUtil.score(word));
        return scores.entrySet().stream().filter( entry -> entry.getKey().equals(user)).findFirst();
    }

    // Get top hallOfFameDepth scores
    @GetMapping("/scores")
    public Stream<Map.Entry<String, Integer>> getTopScores() {
        return scores.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(hallOfFameDepth);
    }

    @GetMapping("/scores/{user}")
    public Optional<Map.Entry<String, Integer>> getUserScore(@PathVariable String user) {
        return scores.entrySet().stream().filter( entry -> entry.getKey().equals(user)).findFirst();
    }
}
