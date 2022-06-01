(ns deploy.core
  (:require [deploy.logging :as logging]
            [deploy.config :as config]
            [taoensso.timbre :as log]
            [deploy.polkadot.api :as api]
            [deploy.polkadot.contracts :as contracts]
            [deploy.files :as files]
            [deploy.utils :refer [promise-> ]]))

(defn start!
  "called by main and after reloading the code"
  []
  (let [config          (config/load!)
        _               (logging/start! config)
        _               (log/info "Running with config" config)
        api-promise     (api/start! config)
        #_#_button-token    (files/read-json (-> config :artifacts :button-token))
        #_#_wasm            (-> button-token :source :wasm)
        #_#_abi             (-> button-token :V3 :spec)
        #_#_code-promise    (promise-> api-promise
                                   #(contracts/code-promise % abi wasm))
        keypair-promise (api/keypair api-promise
                                     (api/keyring)
                                     (-> config :account :phrase))]

    (promise-> api-promise
               #(api/account % (->  config :account :id))
               prn)

    ))

(defn stop! "called before reloading code"
  []
  (log/info "Stopping"))

(defn main "executed once, on startup, can do one time setup here"
  []
  (start!))
