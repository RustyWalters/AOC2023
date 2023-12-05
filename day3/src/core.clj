(ns core
  (:require [taoensso.timbre :as log]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn -main []
  (log/debug "Hello, from AOC day3"))

(defn read-input-file [file-name]
  (slurp file-name))

(defn parse-input [file-content]
  (clojure.string/split-lines file-content))

(defn re-seq-pos [pattern string]
  (let [m (re-matcher pattern string)]
    ((fn step []
       (when (. m find)
         (cons {:start (. m start) :end (. m end) :group (. m group)}
               (lazy-seq (step))))))))

(defn is-symbol? [c]
  (nil? (re-matches #"\d|\." c)))

(defn neighbors [[x y]]
  (for [i [-1 0 1]
        j [-1 0 1]]
    [(+ x i) (+ j y)]))

(defn is-valid? [{:keys [start end y group]} v]
  (let [indices (for [x (range start end)] [y x])
        coords  (mapcat neighbors indices)]
    (->> coords
         (remove (set indices))
         (some #(is-symbol? (str (get-in v % ".")))))))

(defn get-all-by [regex v]
  (->> v
       (map-indexed (fn [y row]
                      (map (fn [n] (assoc n :y y))
                           (re-seq-pos regex row))))
       (apply concat)))

(defn gear-score [{:keys [start end y group]} v]
  (let [numbers (get-all-by #"\d+" v)
        coords  (set (neighbors [y start]))]
    (->> numbers
      (filter (fn [{:keys [start end y group]}]
                (->> (set (for [x (range start end)] [y x]))
                  (set/intersection coords)
                  (not-empty))))
      (map :group)
      (map #(Integer/parseInt %))
      ((fn [n] (if (= 2 (count n))
                 (reduce * n)
                 0))))))

(defn part1 [data]
  (let [file-content (read-input-file data)
        parsed-input (parse-input file-content)]
    (->> parsed-input
         (get-all-by #"\d+")
         (filter #(is-valid? % parsed-input))
         (map :group)
         (map #(Integer/parseInt %))
         (reduce + 0))))

(defn part2 [data]
  (let [file-content (read-input-file data)
        parsed-input (parse-input file-content)]
    (->> parsed-input
         (get-all-by #"\*")
         (map #(gear-score % parsed-input))
         (reduce + 0))))

(comment
  (log/debug (part1 "test/test-part1-input.txt"))
  (log/debug (part1 "src/part1-input.txt"))
  (log/debug (part2 "test/test-part2-input.txt"))
  (log/debug (part2 "src/part2-input.txt")))
