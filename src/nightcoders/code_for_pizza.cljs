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
    [:p {:style {:color "red"}}
     (str "you should need # " @total)]))
  
(defn number-input []
    [:input {:type "number"
             :on-change #(reset! number (-> % .-target .-value))}])

(defn parent []
  [:div 
   [:pre "--- \n" 
    "number::" @number "\n"
    "selection::" @selection "\n"
    "total::" @total "\n ----"]
   [number-input]
   [size-selection]
   [total-display @number @selection]])


(r/render-component [parent] (.querySelector js/document "#content"))

