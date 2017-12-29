(ns nightcoders.code-for-pizza-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [nightcoders.code-for-pizza :as core]))


(deftest test-calc-tip []
         (testing "that calculating a tip works"
                  (= 18.5 (core/calc-tip 100 18.5))))

(deftest test-sub-total []
         (testing "sub-total"
                  (= 118.5 (core/sub-total 100 18.5))))

(deftest test-make-even-total []
         (testing "make-even-total"
                  (= 119 (core/make-even-total 100 18.5))))

(deftest test-calc-actual-tip-percent []
         (testing "calc-actual-tip-percent"
                  (= 19 (core/calc-actual-tip-percent 100 18.5))))
