(ns core
  (:require [taoensso.timbre :as log]
            [clojure.string :as str]))

(defn -main []
  (log/debug "Hello, from AOC day1"))

(defn read-input-file [file-name]
  (slurp file-name))

(defn parse-input [file-content]
  (clojure.string/split-lines file-content))

(defn get-digits [string]
  (let [digits (re-seq #"\d" string)
        first-digit (first digits)
        last-digit (last digits)]
    (Integer/parseInt(str first-digit last-digit))))

(def digits-map
  {"one"   "1"
   "two"   "2"
   "three" "3"
   "four"  "4"
   "five"  "5"
   "six"   "6"
   "seven" "7"
   "eight" "8"
   "nine"  "9"
   "zero"  "0"
   "1"     "1"
   "2"     "2"
   "3"     "3"
   "4"     "4"
   "5"     "5"
   "6"     "6"
   "7"     "7"
   "8"     "8"
   "9"     "9"
   "0"     "0"})
  

(defn get-digits2 [string]
  (let [pattern1 (re-pattern (str/join "|" (keys digits-map)))
        pattern2 (re-pattern (str/join "|" (map str/reverse (keys digits-map))))
        first-digit (digits-map (re-find pattern1 string))
        second-digit (digits-map (str/reverse (re-find pattern2 (str/reverse string))))]
    (Integer/parseInt(str first-digit second-digit))))
        
(defn part1 [data]
  (->> (read-input-file data)
       (parse-input)
       (map get-digits)
       (reduce + 0)))

(defn part2 [data]
  (->> (read-input-file data)
       (parse-input)
       (map get-digits2)
       (reduce + 0)))

(comment
  (println (part1 "test/test-part1-input.txt"))
  (println (part1 "src/part1-input.txt"))
  (println (part2 "test/test-part2-input.txt"))
  (println (part2 "src/part2-input.txt")))

