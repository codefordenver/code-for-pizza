(ns nightcoders.code-for-pizza
  (:require [reagent.core :as r]))


(def total (r/atom 0))

(def selection (r/atom "SMALL"))

(def number (r/atom 0))

(def sizes {:SMALL 0.5
            :MEDIUM 0.4
            :LARGE 0.3
            :XLARGE 0.3})

(defn size-selection []
  [:select {:value @selection
            :on-change #(reset! selection (-> % .-target .-value))}
   [:option {:value "SMALL"} "SMALL"]
   [:option {:value "MEDIUM"} "MEDIUM"]
   [:option {:value "LARGE"} "LARGE"]
   [:option {:value "XLARGE"} "XLARGE"]])

(defn total-display [pizzas type]
  (let [total-calc (* ((keyword type) sizes) pizzas)
        _ (reset! total total-calc)]
    [:p {:class "order-result"}
     (str "Order " @total " " @selection " pizzas!")]))

(defn number-input []
    [:input {:type "number"
             :on-change #(reset! number (-> % .-target .-value))}])

(def tip-percent (r/atom 0))
(def total-charge (r/atom 0))

(defn total-charge-input []
  [:input {:label "total-charge"
           :type "number"
           :on-change
           (fn [e]
             (let [num (js/parseFloat (-> e .-target .-value))]
               (if (js/isNaN num)
                 (reset! total-charge 0)
                 (reset! total-charge num))))}])

(defn tip-percent-input []
  [:input {:label "tip-percent" :type "number"
           :on-change
           (fn [e]
             (let [num (js/parseFloat (-> e .-target .-value))]
               (if (js/isNaN num)
                 (reset! tip-percent 0)
                 (reset! tip-percent num))))}])

;(defn calc-tip-a [total-charge tip-percent])
;   (gstring/format "%.2f" (* total-charge (/ tip-percent 100)))

(defn calc-tip [total tip]
  (/ (js/Math.round (* 100 (* total (/ tip 100))))
     100))
(defn sub-total [total tip]
  (/ (js/Math.round (* 100 (+ (calc-tip total tip) total)))
     100))

(defn make-even-total [total tip]
  (js/Math.ceil (sub-total total tip)))

(defn calc-actual-tip-percent [total tip]
 (if-not (= total 0)
   (/ (js/Math.round (* 100
                       (* 100
                          (/ (- (make-even-total total tip) (js/parseFloat total))
                             (js/parseFloat total)))))
      100)
   0))


(defn parent []
  [:div {:class "main-wrapper"}
    [:header
     [:div
      [:img {:class "cfd-logo" :src "https://pbs.twimg.com/profile_images/712092996938833920/37hddklS.jpg"}]]
     [:img {:src "../assets/pizza.png"}]
     [:h1 "Code for Pizzayyyyyy"]
     [:img {:src "../assets/pizza.png"}]]

    [:div
     [:p "How many people are you feeding?"]
     [number-input]]

    [:div
     [:p "What size of pizza would you like?"]
     [size-selection]]

   [:div {:class "result-statement"}
    "If you have " [:span {:class "number"} @number " people" ] " to feed," "\n"
    "then you need " [:span {:class "number"} @total " " @selection " pizzas!"]]
   [total-display @number @selection]

   [:div
    [:p "Total $"]
    [total-charge-input]]

   [:div
    [:p "Tip in %"]
    [tip-percent-input]]

   [:div
    [:p "Tip is $"
     (calc-tip @total-charge @tip-percent)]]

   [:div
    [:p "Sub total is $"
     (sub-total @total-charge @tip-percent)]]

   [:div
    [:p "Make-even-total is $"
     (make-even-total @total-charge @tip-percent)]]

   [:div
    [:p "Now the tip is "
     (calc-actual-tip-percent @total-charge @tip-percent) "%"]]])





(r/render-component [parent] (.querySelector js/document "#content"))
