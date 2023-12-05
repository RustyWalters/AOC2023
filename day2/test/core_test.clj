(ns core-test
  (:require [clojure.test :refer :all]
            [core :as core]))

(defn -main []
  (println "Testing in progress."))

(deftest can-read-input-file
  (let [file-content (core/read-input-file "test/test-part1-input.txt")] 
    (is (seq file-content))))

(deftest can-parse-input
  (let [file-content (core/read-input-file "test/test-part1-input.txt")
        parsed-input (core/parse-input file-content)]
    (println parsed-input)
    (is (= 5 (count parsed-input)))))

(deftest test-game-id
  (is (= "1" (core/get-game-id "Game 1:"))))

(deftest can-split-into-hands
  (is (= ["3 blue, 4 red" "1 red, 2 green, 6 blue" "2 green"] (core/split-into-hands "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"))))

(deftest can-transform-block-to-map
  (is (= {:blue 6 :red 1 :green 2} (core/block->map "1 red, 2 green, 6 blue"))))

(deftest hand-possible-with-zero-blocks
  (is (= true (core/is-hand-possible? {:blue 0 :red 0 :green 0}))))

(deftest hand-possible-with-all-max-blocks
  (is (= true (core/is-hand-possible? {:blue 14 :red 12 :green 13}))))

(deftest hand-not-possible-with-blue-block-over
  (is (= false (core/is-hand-possible? {:blue 15 :red 12 :green 13}))))

(deftest hand-possible-with-red-block-over
  (is (= false (core/is-hand-possible? {:blue 14 :red 13 :green 13}))))

(deftest hand-possible-with-green-block-over
  (is (= false (core/is-hand-possible? {:blue 14 :red 12 :green 14}))))

(deftest score-single-game-within-threshold
  (is (= 1 (core/score-game "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"))))

(deftest score-single-game-outside-threshold
  (is (= 0 (core/score-game "Game 1: 3 blue, 4 red; 1 red, 2 green, 15 blue; 2 green"))))
