(ns hex-to-wasm.core
  (:require ["@polkadot/" :refer (CodePromise)]
   [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defn start!
  "called by main and after reloading the code"
  []
  (prn "Starting")
  )

(defn stop! "called before reloading code"
  []
  (prn "Stopping")
  )

(defn main "executed once, on startup, can do one time setup here"
  []
  (start!))
