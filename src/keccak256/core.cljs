(ns keccak256.core
  (:require ["js-sha3" :refer (keccak256)]
            [cljs.nodejs :as nodejs]
            [clojure.string :as string]))

(nodejs/enable-util-print!)

(defonce arguments (atom nil))

(defn start!
  "called by main and after reloading the code"
  []
  (prn (keccak256 (string/join @arguments))))

(defn main
  "executed once, on startup, can do one time setup here"
  [& args]
  ;; NOTE: we write to atom just for development purposes, to keep args in between hot reloads
  (reset! arguments args)
  (start!))
