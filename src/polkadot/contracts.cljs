(ns polkadot.contracts
  (:require ["@polkadot/api-contract" :refer (CodePromise)]
            [shared.utils :refer [promise->]]))

(defn code-promise [^js api abi wasm]
  (new CodePromise api abi wasm))
