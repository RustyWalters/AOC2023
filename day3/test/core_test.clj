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
    (is (= 10 (count parsed-input)))))

(deftest can-find-digit-sequences
  (is (= '({:start 0, :end 3, :group "467"} {:start 5, :end 8, :group "114"}) (core/re-seq-pos #"\d+" "467..114.."))))

(deftest no-digit-found-with-only-symbol
  (is (= nil (core/re-seq-pos #"\d+" "...*......"))))

(deftest can-find-digit-with-symbol-adjacent
  (is (= '({:start 0, :end 3, :group "617"}) (core/re-seq-pos #"\d+" "617*......"))))

(deftest can-determine-asterisk-as-symbol
  (is (= true (core/is-symbol? "*"))))

(deftest can-determine-number-is-not-symbol
  (is (= false (core/is-symbol? "1"))))

(deftest can-determine-dot-is-not-symbol
  (is (= false (core/is-symbol? "."))))

(deftest can-determine-$-as-symbol
  (is (= true (core/is-symbol? "$"))))

(deftest can-find-neighbors
  (is (= '([-1 -1] [-1 0] [-1 1] [0 -1] [0 0] [0 1] [1 -1] [1 0] [1 1]) (core/neighbors [0 0]))))

(deftest is-valid-coordinates
  (is (= true (core/is-valid? {:start 0, :end 3, :y 0, :group "467"}, (->> (core/read-input-file "test/test-part1-input.txt") (core/parse-input))))))

(deftest is-correct-gear-score
  (is (= 16345 (core/gear-score {:start 3, :end 4, :y 1, :group "*"}, (->> (core/read-input-file "test/test-part1-input.txt") (core/parse-input))))))
