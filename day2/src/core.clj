(ns core
  (:require [taoensso.timbre :as log]
            [clojure.string :as str]))

(defn -main []
  (log/debug "Hello, from AOC day2"))

(defn read-input-file [file-name]
  (slurp file-name))

(defn parse-input [file-content]
  (clojure.string/split-lines file-content))

(defn get-game-id [game]
  (re-find #"\d+" (first (clojure.string/split game #":"))))

(defn split-into-hands [game]
  (clojure.string/split (second (clojure.string/split game #": ")) #"; "))

(defn block->map [block]
  (->> block
       (re-seq #"(\d+) (\w+)")
       (map (fn [[_ v k]] [(keyword k) (Integer/parseInt v)]))
       (into {})))

(def maximum-blocks {:red 12 :blue 14 :green 13})

(defn is-hand-possible? [results]
  (and (<= (:red results) (:red maximum-blocks))
       (<= (:blue results) (:blue maximum-blocks))
       (<= (:green results) (:green maximum-blocks))))

(defn score-game [game]
  (let [hands (split-into-hands game)
        game-id (get-game-id game)
        blocks (map block->map hands)
        results (reduce #(merge-with max %1 %2) blocks)
        is-possible (is-hand-possible? results)]
    (if is-possible (Integer/parseInt game-id) 0)))

(defn part1 [data]
  (->> (read-input-file data)
       (parse-input)
       (map score-game)
       (reduce + 0)))

(defn part2 [data]
  (->> (read-input-file data)
       (parse-input)))
(comment
  (println (part1 "test/test-part1-input.txt"))
  (println (part1 "src/part1-input.txt"))
  (println (part2 "test/test-part2-input.txt"))
  (println (part2 "src/part2-input.txt")))
