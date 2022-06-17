(ns hex-to-wasm.core
  (:require ["@polkadot/util" :refer (u8aToU8a isWasm)]
            [shared.files :as files]
            [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defonce arguments (atom nil))

(defn start!
  "called by main and after reloading the code"
  []
  (prn "Starting")
  (let [[from to] @arguments
        _ (prn (str "Reading WASM in hex from: " from " and writing to " to))
        contract (files/read-json from)
        wasm (u8aToU8a (-> contract :source :wasm))]
    (assert (isWasm wasm) "not a WASM binary")
    (files/write-file (js/Buffer.from wasm) to)))

(defn stop! "called before reloading code"
  []
  (prn "Stopping"))

(defn main "executed once, on startup, can do one time setup here"
  [& args]
  ;; NOTE: we write to atom just for development purposes, to keep args in between hot reloads
  (reset! arguments args)
  (start!))
