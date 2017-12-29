(ns nightcoders.pizza-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [cljs.nightcoders.pizza :as pizza]))


(deftest test-calc-tip []
         (testing "that calculating a tip works"
                  (= 18.5 (pizza/calc-tip 100 18.5))))

(deftest test-sub-total []
         (testing "sub-total"
                  (= 118.5 (pizza/sub-total 100 185))))

(deftest test-make-even-total []
         (testing "make-even-total"
                  (= 119 (pizza/make-even-total 100 18.5))))

(deftest test-calc-actual-tip-percent []
         (testing "calc-actual-tip-percent"
                  (= 19 (pizza/calc-actual-tip-percent 100 18.5))))
