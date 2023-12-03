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
    (is (= 4 (count parsed-input)))))

(deftest can-get-digits-with-just-two-digits
  (let [digits (core/get-digits "14")]
    (is (= 14 digits))))

(deftest can-get-digits-with-more-than-two-digits
  (let [digits (core/get-digits "123")]
    (is (= 13 digits))))

(deftest can-get-digits-with-mixed-digits-chars
  (let [digits (core/get-digits "1a2b3c4d")]
    (is (= 14 digits))))

(deftest can-get-digits-when-just-1-digit
  (let [digits (core/get-digits "7")]
    (is (= 77 digits))))

(deftest can-get-digits-when-same-2-digits
  (let [digits (core/get-digits "88")]
    (is (= 88 digits))))

(deftest can-get-digits-with-single-word-number
  (let [digits (core/get-digits2 "one")]
    (is (= 11 digits))))

(deftest can-get-digits-with-string-number
  (let [digits (core/get-digits2 "1")]
    (is (= 11 digits))))

(deftest can-get-digits-with-multiple-word-numbers
  (let [digits (core/get-digits2 "onetwothreefourfive")]
    (is (= 15 digits))))

(deftest can-get-digits-with-multiple-mix-word-numbers
  (let [digits (core/get-digits2 "8onetwothree3four8five9")]
    (is (= 89 digits))))
