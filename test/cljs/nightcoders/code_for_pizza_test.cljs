(ns nightcoders.code-for-pizza-test
  (:require [cljs.test :refer-macros [deftest testing is]]))

(deftest fake-test
         (testing "fake description"
                  (is (= 1 2))))

(deftest test-arithmetic []
         (is (= (+ 0.1 0.2) 0.30000000000000004) "Something foul is a float."))