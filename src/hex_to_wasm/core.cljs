(ns hex-to-wasm.core
  (:require ["@polkadot/util" :refer (u8aToU8a)]
            [shared.files :as files]
            [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

;; TODO : CLI

(defn start!
  "called by main and after reloading the code"
  []
  (prn "Starting")
  (let [contract (files/read-json "/home/filip/CloudStation/aleph/aleph-node/contracts/button_token/target/ink/button_token.contract")
        wasm (-> contract :source :wasm)
        binary (u8aToU8a wasm)]

    ;; (prn (u8aToU8a wasm))

    ))

(defn stop! "called before reloading code"
  []
  (prn "Stopping")
  )

(defn main "executed once, on startup, can do one time setup here"
  []
  (start!))
