(ns deploy.polkadot.contracts
  (:require ["@polkadot/api-contract" :refer (CodePromise)]
            [deploy.utils :refer [promise->]]))

(defn code-promise [^js api abi wasm]
  (new CodePromise api abi wasm))
