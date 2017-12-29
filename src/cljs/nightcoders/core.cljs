(ns cljs.nightcoders.core
  (:require [reagent.core :as r]
            [cljs.nightcoders.pizza :refer [main]]))

(r/render-component [main] (js/document.querySelector "#content"))