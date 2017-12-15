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

(defn parent []
  [:div {:class "main-wrapper"}
    [:header
      [:img {:src "../assets/pizza.png"}]
      [:h1 "Code for Pizza"]
      [:img {:src "../assets/pizza.png"}]
    ]
    [:div
     [:p "How many people are you feeding?"] 
     [number-input]
    ]
    [:div
     [:p "What size of pizza would you like?"]
     [size-selection]
    ]
   [:div {:class "result-statement"} 
    "If you have " [:span {:class "number"} @number " people" ] " to feed," "\n"
    "then you need " [:span {:class "number"} @total " " @selection " pizzas!"]]
   [total-display @number @selection]])


(r/render-component [parent] (.querySelector js/document "#content"))
