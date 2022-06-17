(ns polkadot.api
  (:require ["@polkadot/api" :refer [ApiPromise WsProvider Keyring ContractPromise]]
            [deploy.utils :refer [promise->]]))

(defn start!
  "returns a js/Promise"
  [config]
  (let [{:keys [host port]} (:api config)
        url                 (str host (when (not (nil? port))
                                        (str ":" port)))]
    (.create ApiPromise (clj->js {:provider (new WsProvider url)}))))

(defn genesis-hash [^js api]
  (-> api
      .-genesisHash
      .toHex))

(defn account [^js api address]
  (-> api .-query .-system (.account address)))

(defn keyring
  "creates new Keyring object"
  []
  (new Keyring (clj->js {:type "sr25519"})))

(defn keypair
  "creates new keypair from a passed seed phrase
  Returns js/Promise"
  [^js api-promise ^js keyring phrase]
  (promise->
    ;; NOTE: we just resolve the promise, despite no dependency on api it throws here otherwise (Error: The WASM interface has not been initialized)
    api-promise
    #(.addFromUri keyring phrase)))
